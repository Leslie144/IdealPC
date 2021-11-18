package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Hardwares;
import pe.edu.upc.repositories.IHardwareRepository;
import pe.edu.upc.serviceinterfaces.IHardwareService;

@Service
public class HardwareServiceImplement implements IHardwareService{

	@Autowired
	private IHardwareRepository hR;
	@Override
	public boolean insert(Hardwares hardware) {
		// TODO Auto-generated method stub
		Hardwares rpta=hR.save(hardware);
		if(rpta==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Hardwares> list() {
		// TODO Auto-generated method stub
		return hR.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Hardwares listarId(int idHardware) {
		// TODO Auto-generated method stub
		Optional<Hardwares>op=hR.findById(idHardware);
		return op.isPresent()?op.get():new Hardwares();
	}

	@Override
	public void delete(int idHardware) {
		// TODO Auto-generated method stub
		hR.deleteById(idHardware);
	}

	@Override
	public List<Hardwares> findBynombreHardware(String name){
	return hR.findBynombreHardware(name);	
	}
	
}
