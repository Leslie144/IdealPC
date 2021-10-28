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

import pe.edu.upc.entities.TamanoMB;
import pe.edu.upc.serviceinterfaces.ITamanoMBService;

@Controller
@RequestMapping("/tamanomb")
public class TamanoMBController {
	@Autowired
	private ITamanoMBService mService;
	
	@GetMapping("/new")
	public String newTamanoMB(Model model) {
		model.addAttribute("tamanomb", new TamanoMB());
		return "tamanomb/tamanomb";
	}
	
	@GetMapping("/list")
	public String listTamanosMB(Model model) {
		try {
			model.addAttribute("tamanomb", new TamanoMB());
			model.addAttribute("listaTamanosMB", mService.list());
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tamanomb/listTamanoMB";
	}
	
	@PostMapping("/save")
	public String saveTamanoMB(@Validated TamanoMB tamanomb, BindingResult result, Model model, SessionStatus status)
	throws Exception{
		if(result.hasErrors()) {
			return "tamanomb/tamanomb";
		}else {
			int rpta = mService.insert(tamanomb);
			if(rpta>0) {
				model.addAttribute("tamanomb", tamanomb);
				model.addAttribute("menssaje", "Ya existe");
				return "tamanomb/tamanomb";
			}else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tamanomb", new TamanoMB());
		return "redirect:/tamanomb/list";
	}
}
