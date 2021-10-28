package pe.edu.upc.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Usuario;
import pe.edu.upc.serviceinterfaces.IDistritoService;
import pe.edu.upc.serviceinterfaces.ISubirFotoService;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;
import pe.edu.upc.serviceinterfaces.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private ISubirFotoService subirarchivoService;
	@Autowired
	private IDistritoService dService;
	@Autowired
	private ITipoUsuarioService tService;

	@GetMapping("/new")
	public String newMarca(Model model) {
		model.addAttribute("listaDistritos", dService.list());
		model.addAttribute("listaTipos", tService.list());
		model.addAttribute("usuario", new Usuario());
		return "usuario/usuario";
	}

	@GetMapping("/list")
	public String listMarcas(Model model) {
		try {
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("listaUsuarios", uService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "usuario/listUsuario";
	}

	@PostMapping("/save")
	public String saveUsuario(@ModelAttribute @Valid Usuario usuario, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaDistritos", dService.list());
			model.addAttribute("listaTipos", tService.list());
			return "usuario/usuario";
		} else {
			if (!foto.isEmpty()) {

				if (usuario.getIdUsuario() > 0 && usuario.getFotoUsuario() != null
						&& usuario.getFotoUsuario().length() > 0) {

					subirarchivoService.delete(usuario.getFotoUsuario());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = subirarchivoService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				usuario.setFotoUsuario(uniqueFilename);
			}
			boolean flag = uService.insert(usuario);
			if (flag) {
				return "redirect:/store/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/usuario/new";

			}
		}
	}
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = null;
		try {
			recurso = subirarchivoService.load(filename);
		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	
	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {
		Usuario usuario = uService.listarId(id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "La tienda no existe en la base de datos");
			return "tienda/listTienda";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Detalle de usuario: " + usuario.getNombreUsuario());
		return "usuario/ver";
	}
	
	@RequestMapping("/list")
	public String listUsuarios(Map<String, Object> model) {
		model.put("listaUsuario", uService.list());
		return "tienda/listUsuario";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Usuario usuario) {
		uService.listarId(usuario.getIdUsuario());
		return "usuario/listUsuario";
	}

}
