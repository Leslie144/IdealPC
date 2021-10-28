package pe.edu.upc.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Marca;
import pe.edu.upc.serviceinterfaces.IMarcaService;

@Controller
@RequestMapping("/marca")
public class MarcaController {
	@Autowired
	private IMarcaService mService;

	@GetMapping("/new")
	public String newMarca(Model model) {
		model.addAttribute("marca", new Marca());
		return "marca/marca";
	}

	@GetMapping("/list")
	public String listMarcas(Model model) {
		try {
			model.addAttribute("marca", new Marca());
			model.addAttribute("listaMarcas", mService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "marca/listMarca";
	}

	@PostMapping("/save")
	public String saveMarca(@Validated Marca marca, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "marca/marca";
		} else {
			int rpta = mService.insert(marca);
			if (rpta > 0) {
				model.addAttribute("marca", marca);
				model.addAttribute("menssaje", "Ya existe");
				return "marca/marca";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("marca", new Marca());
		return "redirect:/marca/list";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Marca marca) {
		mService.listarId(marca.getIdMarca());
		return "marca/listMarca";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Marca objMarca = mService.listarId(id);
		if (objMarca == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/marca/list";
		} else {
			model.addAttribute("marca", objMarca);
			return "marca/marca";
		}
	}
	
	@RequestMapping("/delete")
	public String deleteMarca(Model model, @RequestParam(value = "id") Integer id) {
		mService.delete(id);
		model.addAttribute("listaMarcas", mService.list());
		return "marca/listMarca";
	}
}
