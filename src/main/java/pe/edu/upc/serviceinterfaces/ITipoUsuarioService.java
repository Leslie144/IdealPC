package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TipoUsuario;

public interface ITipoUsuarioService {
	public Integer insert(TipoUsuario tipousuario);
	List<TipoUsuario> list();
}
