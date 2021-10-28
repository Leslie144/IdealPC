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
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "marca/listMarca";
	}
	
	@PostMapping("/save")
	public String saveMarca(@Valid Marca marca, BindingResult result, Model model, SessionStatus status)
	throws Exception{
		if(result.hasErrors()) {
			return "marca/marca";
		}else {
			int rpta = mService.insert(marca);
			if(rpta>0) {
				model.addAttribute("menssaje", "Ya existe");
				return "marca/marca";
			}else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("marca", new Marca());
		return "redirect:/marca/list";
	}
}
