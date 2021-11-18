package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.ModularType;

public interface IModularTypeService {
	public int insert(ModularType modulartype);
	List<ModularType> list();
	ModularType listId(int id);
	public void delete (int id);
}
