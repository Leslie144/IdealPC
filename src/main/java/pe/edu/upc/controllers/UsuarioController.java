package pe.edu.upc.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Users;
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
	public String newUsuario(Model model) {
		model.addAttribute("listaDistritos", dService.list());
		model.addAttribute("listaTipos", tService.list());
		model.addAttribute("users", new Users());
		return "usuario/usuario";
	}

	@GetMapping("/list")
	public String listUsuarios(Model model) {
		try {
			model.addAttribute("usuario", new Users());
			model.addAttribute("listaUsuarios", uService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "usuario/listUsuario";
	}

	@RequestMapping("/save")
	public String saveUsuario(@ModelAttribute @Valid Users usuario, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaDistritos", dService.list());
			model.addAttribute("listaTipos", tService.list());
			return "usuario/usuario";
		} else {
			if (!photo.isEmpty()) {
			System.out.println("Foto: "+photo.getName());

				if (Math.toIntExact(usuario.getId()) > 0 && usuario.getPhoto() != null
						&& usuario.getPhoto().length() > 0) {
					subirarchivoService.delete(usuario.getPhoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = subirarchivoService.copy(photo);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				usuario.setPhoto(uniqueFilename);
			}
			boolean flag = uService.insert(usuario);
			if (flag) {
				return "redirect:/usuario/list";
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
		Users usuario = uService.listarId(id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "usuario/listUsuario";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Detalle de usuario: " + usuario.getUsername());
		return "usuario/ver";
	}
	
	@RequestMapping("/list")
	public String listUsuarios(Map<String, Object> model) {
		model.put("listaUsuario", uService.list());
		return "usuario/listUsuario";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Users usuario) {
		uService.listarId(usuario.getId());
		return "usuario/listUsuario";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Users objUsuario = uService.listarId(id);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/usuario/list";
		} else {
			model.addAttribute("listaDistritos", dService.list());
			model.addAttribute("listaTipos", tService.list());
			model.addAttribute("users", objUsuario);
			return "usuario/usuario";
		}
	}

	@RequestMapping("/delete")
	public String deleteUsuario(Model model, @RequestParam(value = "id") int id) {
		uService.delete(id);
		model.addAttribute("usuario", new Users());
		model.addAttribute("listaUsuarios", uService.list());
		return "usuario/listUsuario";
	}

	@RequestMapping("/search")
	public String findUsuario(@ModelAttribute Users usuario, Model model) {
		List<Users> listaUsuarios;
		listaUsuarios=uService.findBynombreUsuario(usuario.getUsername());
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "usuario/listUsuario";
	}
}
