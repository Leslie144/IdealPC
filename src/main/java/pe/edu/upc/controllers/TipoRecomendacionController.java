package pe.edu.upc.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.TipoRecomendacion;
import pe.edu.upc.serviceinterfaces.ITipoRecomendacionService;

@Controller
@RequestMapping("/tipoderecomendaciones")
public class TipoRecomendacionController {

	@Autowired
	private ITipoRecomendacionService trService;
	@Secured("ROLE_ADMIN")
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
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveTipoRecomendacion(@Valid TipoRecomendacion tiporecomendacion, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "tiporecomendacion/tiporecomendacion";
		} else {
			boolean flag = trService.insert(tiporecomendacion);
			if (flag) {
				return "redirect:/tipoderecomendaciones/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/tipoderecomendaciones/new";
			}
		}
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute TipoRecomendacion tiporecomendacion) {
		trService.listarId(tiporecomendacion.getIdTipoRecomendacion());
		return "tiporecomendacion/listTipoRecomendacion";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		TipoRecomendacion objTipoRecomendacion = trService.listarId(id);
		if (objTipoRecomendacion == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/tipoderecomendaciones/list";
		} else {
			model.addAttribute("tipoRecomendacion", objTipoRecomendacion);
			return "tiporecomendacion/tiporecomendacion";
		}
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteMarca(Model model, @RequestParam(value = "id") Integer id) {
		trService.delete(id);
		model.addAttribute("listaTipoRecomendacion", trService.list());
		return "tiporecomendacion/listTipoRecomendacion";
	}
}
