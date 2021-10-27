package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TipoModular;

public interface ITipoModularService {
	public Integer insert(TipoModular tipomodular);
	List<TipoModular> list();
}
