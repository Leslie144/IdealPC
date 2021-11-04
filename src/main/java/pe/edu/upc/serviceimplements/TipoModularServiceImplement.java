package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TipoModular;
import pe.edu.upc.repositories.ITipoModularRepository;
import pe.edu.upc.serviceinterfaces.ITipoModularService;

@Service
public class TipoModularServiceImplement implements ITipoModularService {

	@Autowired
	private ITipoModularRepository tmR;

	@Override
	public Integer insert(TipoModular tipomodular) {
		int rpta = tmR.TipoModularExistentes(tipomodular.getnTipoModular());
		if (rpta == 0) {
			tmR.save(tipomodular);
		}
		return rpta;
	}

	@Override
	public List<TipoModular> list() {
		// TODO Auto-generated method stub
		return tmR.findAll();
	}

	@Override
	public TipoModular listarId(int idTipoModular) {
		// TODO Auto-generated method stub
		Optional<TipoModular> op = tmR.findById(idTipoModular);
		return op.isPresent() ? op.get() : new TipoModular();
	}

	@Override
	public void delete(int idTipoModular) {
		// TODO Auto-generated method stub
		tmR.deleteById(idTipoModular);

	}

}
