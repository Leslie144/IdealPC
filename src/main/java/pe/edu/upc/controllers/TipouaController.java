package pe.edu.upc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Tipoua;
import pe.edu.upc.serviceinterfaces.ITipouaService;



@Controller
@RequestMapping("/tipouas")
public class TipouaController {
	@Autowired
	private ITipouaService cService;
	
	@GetMapping("/new")
	public String newTipoua(Model model) {
		model.addAttribute("tipoua", new Tipoua());
		return "tipoua/tipoua";
	}
	
	@GetMapping("/list")
	public String listTipouas(Model model) {
		try {
			model.addAttribute("tipoua", new Tipoua());
			model.addAttribute("listaTipouas", cService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tipoua/listTipouas";
	}
	
	@PostMapping("/save")
	public String saveMarca(@Valid Tipoua tipoua, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tipoua/tipoua";
		} else {
			int rpta = cService.insert(tipoua);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "tipoua/tipoua";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tipoua", new Tipoua());
		return "redirect:/tipouas/list";
	}
}
