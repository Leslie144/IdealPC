package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.TipoRecomendacion;

public interface ITipoRecomendacionService {
	public boolean insert(TipoRecomendacion tiporecomendacion);

	List<TipoRecomendacion> list();

	TipoRecomendacion listarId(int idTipoRecomendacion);

	public void delete(int idTipoRecomendacion);
}
