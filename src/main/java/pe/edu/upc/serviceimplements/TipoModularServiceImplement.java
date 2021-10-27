package pe.edu.upc.serviceimplements;

import java.util.List;

import pe.edu.upc.entities.TipoModular;
import pe.edu.upc.repositories.ITipoModularRepository;
import pe.edu.upc.serviceinterfaces.ITipoModularService;

public class TipoModularServiceImplement implements ITipoModularService {

	private ITipoModularRepository tmR;
	@Override
	public Integer insert(TipoModular tipomodular) {
		int rpta=tmR.TipoModularExistentes(tipomodular.getnTipoModular());
		if(rpta==0) {
			tmR.save(tipomodular);
		}
		return rpta;
	}

	@Override
	public List<TipoModular> list() {
		// TODO Auto-generated method stub
		return tmR.findAll();
	}

}
