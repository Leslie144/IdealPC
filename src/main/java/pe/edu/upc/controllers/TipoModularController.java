package pe.edu.upc.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import pe.edu.upc.entities.Distrito;
import pe.edu.upc.entities.TipoModular;
import pe.edu.upc.serviceinterfaces.ITipoModularService;

@Controller
@RequestMapping("/tipomodulares")
public class TipoModularController {

	@Autowired
	private ITipoModularService tmService;
	
	@GetMapping("/new")
	public String newTipoModular(Model model) {
		model.addAttribute("tipoModular", new TipoModular());
		return "tipoModular/tipoModular";
	}

	@GetMapping("/list")
	public String listTipoModulares(Model model) {
		try {
			model.addAttribute("tipoModular", new TipoModular());
			model.addAttribute("listaTipoModular", tmService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exceptionSS
		}
		return "tipoModular/listTipoModular";
	}

	@PostMapping("/save")
	public String saveTipoModular(@Valid TipoModular tipomodular, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tipoModular/tipoModular";
		} else {
			int rpta = tmService.insert(tipomodular);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe");
				return "tipoModular/tipoModular";
			} else {
				model.addAttribute("mensaje","Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tipoModular",new TipoModular());
		return "redirect:/tipomodulares/list";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute TipoModular tipomodular) {
		tmService.listarId(tipomodular.getIdTipoModular());
		return "tipoModular/listTipoModular";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		TipoModular objTipoModular = tmService.listarId(id);
		if (objTipoModular == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/tipomodulares/list";
		} else {
			model.addAttribute("tipomodular", objTipoModular);
			return "tipoModular/tipoModular";
		}
	}
	
	@RequestMapping("/delete")
	public String delete(Model model, @RequestParam(value = "id") Integer id) {
		tmService.delete(id);
		model.addAttribute("listaTipoModular", tmService.list());
		return "tipoModular/listTipoModular";
	}
}
