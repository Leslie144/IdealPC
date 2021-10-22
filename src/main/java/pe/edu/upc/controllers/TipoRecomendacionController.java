package pe.edu.upc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entities.TipoRecomendacion;
import pe.edu.upc.serviceinterfaces.ITipoRecomendacionService;

@Controller
@RequestMapping("/tipoderecomendaciones")
public class TipoRecomendacionController {
	
	@Autowired
	private ITipoRecomendacionService trS;
	
	@GetMapping("/new")
	public String newTipoRecomendacion(Model model) {
		model.addAttribute("tiporecomendacion", new TipoRecomendacion());
		return "";
	}
	
}
