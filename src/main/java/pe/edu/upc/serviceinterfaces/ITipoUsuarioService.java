package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Role;



public interface ITipoUsuarioService {
	public Integer insert(Role tipousuario);
	List<Role> list();
	Role listarId(int idTipousuario);
	public void delete(int idTipousuario);
	
}
