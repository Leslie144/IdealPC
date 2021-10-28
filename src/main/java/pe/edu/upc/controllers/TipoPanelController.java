package pe.edu.upc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.TipoPanel;
import pe.edu.upc.serviceinterfaces.ITipoPanelService;

@Controller
@RequestMapping("/tipopanel")
public class TipoPanelController {
	@Autowired
	private ITipoPanelService tpService;
	
	@GetMapping("/new")
	public String newTipoPanel(Model model) {
		model.addAttribute("tipopanel", new TipoPanel());
		return "tipopanel/tipopanel";
	}
	
	@GetMapping("/list")
	public String listTiposPanel(Model model) {
		try {
			model.addAttribute("tipopanel", new TipoPanel());
			model.addAttribute("listaTiposPanel", tpService.list());
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tipopanel/listTipoPanel";
	}
	
	@PostMapping("/save")
	public String saveTipoPanel(@Validated TipoPanel tipopanel, BindingResult result, Model model, SessionStatus status)
	throws Exception{
		if(result.hasErrors()) {
			return "tipopanel/tipopanel";
		}else {
			int rpta = tpService.insert(tipopanel);
			if(rpta>0) {
				model.addAttribute("tipopanel", tipopanel);
				model.addAttribute("menssaje", "Ya existe");
				return "tipopanel/tipopanel";
			}else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tipopanel", new TipoPanel());
		return "redirect:/tipopanel/list";
	}
	
}
