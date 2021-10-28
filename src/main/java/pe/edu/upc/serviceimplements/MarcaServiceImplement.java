package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Marca;
import pe.edu.upc.repositories.IMarcaRepository;
import pe.edu.upc.serviceinterfaces.IMarcaService;

@Service
public class MarcaServiceImplement implements IMarcaService {

	@Autowired
	private IMarcaRepository mR;

	@Override
	public boolean insert(Marca marca) {
		Marca rpta=mR.save(marca);
		if(rpta==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Marca> list() {
		return mR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Marca listarId(int idMarca) {
		Optional<Marca> op = mR.findById(idMarca);
		return op.isPresent() ? op.get() : new Marca();
	}

	@Override
	public void delete(int idMarca) {
		// TODO Auto-generated method stub
		mR.deleteById(idMarca);
	}

}
