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

import pe.edu.upc.entities.ModularType;
import pe.edu.upc.serviceinterfaces.IModularTypeService;

@Controller
@RequestMapping("/modulartypes")
public class ModularTypeController {

	@Autowired
	private IModularTypeService mtService;
	
	@GetMapping("/new")
	public String newModularType(Model model) {
		model.addAttribute("modularType", new ModularType());
		return "modularType/modularType";
	}

	@GetMapping("/list")
	public String listModularTypes(Model model) {
		try {
			model.addAttribute("modularType", new ModularType());
			model.addAttribute("listModularType", mtService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exceptionSS
		}
		return "modularType/listModularType";
	}

	@PostMapping("/save")
	public String saveTipoModular(@Valid ModularType modulartype, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "modularType/modularType";
		} else {
			int rpta = mtService.insert(modulartype);
			if (rpta > 0) {
				model.addAttribute("message", "ya existe");
				return "modularType/modularType";
			} else {
				model.addAttribute("message","Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("modularType",new ModularType());
		return "redirect:/modulartypes/list";
	}
	
	@RequestMapping("/listId")
	public String listId(Map<String, Object> model, @ModelAttribute ModularType modulartype) {
		mtService.listId(modulartype.getId());
		return "modularType/listModularType";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		ModularType objModularType = mtService.listId(id);
		if (objModularType == null) {
			objRedir.addFlashAttribute("message", "ocurrió un error");
			return "redirect:/modulartypes/list";
		} else {
			model.addAttribute("modularType", objModularType);
			return "modularType/modularType";
		}
	}
	
	@RequestMapping("/delete")
	public String delete(Model model, @RequestParam(value = "id") Integer id) {
		mtService.delete(id);
		model.addAttribute("listModularType", mtService.list());
		return "modularType/listModularType";
	}
}
