package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Distrito;

public interface IDistritoService {
	public Integer insert(Distrito distrito);
	List<Distrito> list();
}
