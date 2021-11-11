package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TipoModular;

public interface ITipoModularService {
	public boolean insert(TipoModular tipomodular);
	List<TipoModular> list();
	TipoModular listarId(int idTipoModular);
	public void delete (int idTipoModular);
}
