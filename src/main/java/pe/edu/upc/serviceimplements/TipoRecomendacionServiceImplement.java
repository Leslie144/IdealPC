package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.TipoRecomendacion;
import pe.edu.upc.repositories.ITipoRecomendacionRepository;
import pe.edu.upc.serviceinterfaces.ITipoRecomendacionService;

@Service
public class TipoRecomendacionServiceImplement implements ITipoRecomendacionService {

	@Autowired
	private ITipoRecomendacionRepository trR;

	@Override
	public boolean insert(TipoRecomendacion tiporeco) {
		TipoRecomendacion rpta=trR.save(tiporeco);
		if(rpta==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<TipoRecomendacion> list() {
		// TODO Auto-generated method stub
		return trR.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public TipoRecomendacion listarId(int idTipoRecomendacion) {
		Optional<TipoRecomendacion> op = trR.findById(idTipoRecomendacion);
		return op.isPresent() ? op.get() : new TipoRecomendacion();
	}

	@Override
	public void delete(int idTipoRecomendacion) {
		// TODO Auto-generated method stub
		trR.deleteById(idTipoRecomendacion);
	}

}
