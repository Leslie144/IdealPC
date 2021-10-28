package pe.edu.upc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.TipoUsuario;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;

@Controller
@RequestMapping("/tipousuarios")
public class TipoUsuarioController {

	private ITipoUsuarioService tuService;
	
	@GetMapping("/new")
	public String newTipoUsuario(Model model) {
		model.addAttribute("tipousuario", new TipoUsuario());
		return "tipousuario/tipousuario";
	}
	
	@GetMapping("/list")
	public String listTipoUsuario(Model model) {
		try {
			model.addAttribute("tipousuario", new TipoUsuario());
			model.addAttribute("listaTipoUsuario", tuService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		return "tipousuario/listTipoUsuario";
	}
	
	@PostMapping("/save")
	public String saveTipoUsuario(@Validated TipoUsuario tipousuario, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tipousuario/tipousuario";
		} else {
			int rpta = tuService.insert(tipousuario);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe");
				return "tipousuario/tipousuario";
			} else {
				model.addAttribute("mensaje","Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tipousuario",new TipoUsuario());
		return "redirect:/tipousuarios/list";
	}	
}
