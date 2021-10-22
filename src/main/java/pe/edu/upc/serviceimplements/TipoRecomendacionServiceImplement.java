package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TipoRecomendacion;
import pe.edu.upc.repositories.ITipoRecomendacionRepository;
import pe.edu.upc.serviceinterfaces.ITipoRecomendacionService;

@Service
public class TipoRecomendacionServiceImplement implements ITipoRecomendacionService {

	@Autowired
	private ITipoRecomendacionRepository trR;

	@Override
	public Integer insert(TipoRecomendacion tiporecomendacion) {
		int rpta = trR.TipoRecomendacionExistentes(tiporecomendacion.gettRecomendacion());
		if (rpta == 0) {
			trR.save(tiporecomendacion);
		}

		return rpta;
	}

	@Override
	public List<TipoRecomendacion> list() {
		// TODO Auto-generated method stub
		return trR.findAll();
	}

}
