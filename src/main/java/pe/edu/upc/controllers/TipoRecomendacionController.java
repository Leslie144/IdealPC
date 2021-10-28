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

import pe.edu.upc.entities.TipoRecomendacion;
import pe.edu.upc.serviceinterfaces.ITipoRecomendacionService;

@Controller
@RequestMapping("/tipoderecomendaciones")
public class TipoRecomendacionController {

	@Autowired
	private ITipoRecomendacionService trService;

	@GetMapping("/new")
	public String newTipoRecomendacion(Model model) {
		model.addAttribute("tipoRecomendacion", new TipoRecomendacion());
		return "tiporecomendacion/tiporecomendacion";
	}

	@GetMapping("/list")
	public String listTipoRecomendaciones(Model model) {
		try {
			model.addAttribute("tiporecomendacion", new TipoRecomendacion());
			model.addAttribute("listaTipoRecomendacion", trService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tiporecomendacion/listTipoRecomendacion";
	}

	@PostMapping("/save")
	public String saveTipoRecomendacion(@Valid TipoRecomendacion tiporecomendacion, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "tiporecomendacion/tiporecomendacion";
		} else {
			model.addAttribute("tipoRecomendacion", tiporecomendacion);
			int rpta = trService.insert(tiporecomendacion);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "tiporecomendacion/tiporecomendacion";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tiporecomendacion", new TipoRecomendacion());

		return "redirect:/tipoderecomendaciones/list";
	}
}
