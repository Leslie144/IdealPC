package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Marca;

public interface IMarcaService {
	
	public Integer insert(Marca marca);
	
	List<Marca> list();
	
	Marca listarId(int idMarca);
	
	public void delete(int idMarca);
}
