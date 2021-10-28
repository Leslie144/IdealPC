package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Usuario;


public interface IUsuarioService {
	
	public boolean insert(Usuario usuario);
	
	List<Usuario> list();
	
	Usuario listarId(int idUsuario);
	

}
