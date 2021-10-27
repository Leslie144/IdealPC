package pe.edu.upc.controllers;

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

	private ITipoModularService tmService;
	
	@GetMapping("/new")
	public String newTipoModular(Model model) {
		model.addAttribute("tipomodular", new TipoModular());
		return "tipomodular/tipomodular";
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
		return "tipomodular/listaTipoModular";
	}

	@PostMapping("/save")
	public String saveTipoModular(@Validated TipoModular tipomodular, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tipomodular/tipomodular";
		} else {
			int rpta = tmService.insert(tipomodular);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe");
				return "tipomodular/tipomodular";
			} else {
				model.addAttribute("mensaje","Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tipomodular",new TipoModular());
		return "redirect:/tipomodular/list";
	}
}
