package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Locale.Category;

import pe.edu.upc.entities.Usuario;


public interface IUsuarioService {
	
	public boolean insert(Usuario usuario);
	
	List<Usuario> list();
	
	Usuario listarId(int idUsuario);
	
	List<Usuario>findBynombreUsuario(String name);

}
