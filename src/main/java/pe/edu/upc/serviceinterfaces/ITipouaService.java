package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Tipoua;

public interface ITipouaService {
	public Integer insert(Tipoua tipoua);

	List<Tipoua> list();

	Tipoua listarId(int idTipoua);

	public void delete(int idTipoua);
}
