package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.ModularType;
import pe.edu.upc.repositories.IModularTypeRepository;
import pe.edu.upc.serviceinterfaces.IModularTypeService;

@Service
public class ModularTypeServiceImplement implements IModularTypeService {

	@Autowired
	private IModularTypeRepository tmR;

	@Override
	public int insert(ModularType modulartype) {
		int rpta = tmR.ExistentModularType(modulartype.getType());
		if (rpta == 0) {
			tmR.save(modulartype);
		}
		return rpta;
	}

	@Override
	public ModularType listId(int id) {
		Optional<ModularType> op = tmR.findById(id);
		return op.isPresent() ? op.get() : new ModularType();
	}

	@Override
	public List<ModularType> list() {
		// TODO Auto-generated method stub
		return tmR.findAll();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		tmR.deleteById(id);

	}
}
