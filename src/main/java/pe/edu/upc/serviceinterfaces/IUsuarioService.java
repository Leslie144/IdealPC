package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Users;


public interface IUsuarioService {

	public boolean insert(Users usuario);

	List<Users> list();

	Users listarId(Long idUsuario);
	
	public void delete(Long idUsuarioidUsuario);

	List<Users> findBynombreUsuario(String name);

}
