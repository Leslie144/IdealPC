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

import pe.edu.upc.entities.TipoModular;
import pe.edu.upc.serviceinterfaces.ITipoModularService;

@Controller
@RequestMapping("/tipomodulares")
public class TipoModularController {

	@Autowired
	private ITipoModularService tmService;
	
	@GetMapping("/new")
	public String newTipoModular(Model model) {
		model.addAttribute("tipomodular", new TipoModular());
		return "tipoModular/tipoModular";
	}

	@GetMapping("/list")
	public String listTipoModular(Model model) {
		try {
			model.addAttribute("tipomodular", new TipoModular());
			model.addAttribute("listaTipoModular", tmService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		return "tipoModular/listTipoModular";
	}

	@PostMapping("/save")
	public String saveTipoModular(@Validated TipoModular tipomodular, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tipoModular/tipoModular";
		} else {
			int rpta = tmService.insert(tipomodular);
			if (rpta > 0) {
				model.addAttribute("tipomodular", tipomodular);
				model.addAttribute("mensaje", "ya existe");
				return "tipoModular/tipoModular";
			} else {
				model.addAttribute("mensaje","Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tipomodular",new TipoModular());
		return "redirect:/tipomodulares/list";
	}
}
