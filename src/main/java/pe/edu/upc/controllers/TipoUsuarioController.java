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

import pe.edu.upc.entities.Distrito;
import pe.edu.upc.entities.TipoUsuario;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;

@Controller
@RequestMapping("/tipousuarios")
public class TipoUsuarioController {
	@Autowired
	private ITipoUsuarioService tuService;
	
	@GetMapping("/new")
	public String newTipoUsuario(Model model) {
		model.addAttribute("tipoUsuario", new TipoUsuario());
		return "tipoUsuario/tipoUsuario";
	}
	
	@GetMapping("/list")
	public String listTipoUsuario(Model model) {
		try {
			model.addAttribute("tipoUsuario", new TipoUsuario());
			model.addAttribute("listaTipoUsuario", tuService.list());
		} catch (Exception e) {
			
			model.addAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		return "tipoUsuario/listTipoUsuario";
	}
	
	@PostMapping("/save")
	public String saveTipoUsuario(@Validated TipoUsuario tipousuario, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tipoUsuario/tipoUsuario";
		} else {
			int rpta = tuService.insert(tipousuario);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe");
				return "tipoUsuario/tipoUsuario";
			} else {
				model.addAttribute("mensaje","Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tipoUsuario",new TipoUsuario());
		return "redirect:/tipousuarios/list";
	}	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute TipoUsuario tipousuario) {
		tuService.listarId(tipousuario.getIdTipousuario());
		return "tipoUsuario/listTipoUsuario";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		TipoUsuario objTipoUsuario = tuService.listarId(id);
		if (objTipoUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrió un error");
			return "redirect:/tipousuarios/list";
		} else {
			model.addAttribute("tipoUsuario", objTipoUsuario);
			return "tipoUsuario/tipoUsuario";
		}
	}
	
	@RequestMapping("/delete")
	public String delete(Model model, @RequestParam(value = "id") Integer id) {
		tuService.delete(id);
		model.addAttribute("listaTipoUsuario", tuService.list());
		return "tipoUsuario/listTipoUsuario";
	}
}
