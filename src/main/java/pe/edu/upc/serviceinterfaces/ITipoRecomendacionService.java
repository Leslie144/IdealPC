package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TipoRecomendacion;

public interface ITipoRecomendacionService {
	public Integer insert(TipoRecomendacion tiporecomendacion);
	List<TipoRecomendacion> list();
}
