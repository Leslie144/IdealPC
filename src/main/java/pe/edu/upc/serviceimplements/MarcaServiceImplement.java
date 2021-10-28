package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Marca;
import pe.edu.upc.repositories.IMarcaRepository;
import pe.edu.upc.serviceinterfaces.IMarcaService;

@Service
public class MarcaServiceImplement implements IMarcaService{
	
	@Autowired
	private IMarcaRepository mR;
	
	@Override
	public Integer insert(Marca marca) {
		
		int rpta = mR.MarcasExistentes(marca.getnMarca());
		if(rpta ==0) {
			mR.save(marca);
		}
		return rpta;
	}
	
	@Override
	public List<Marca> list(){
		return mR.findAll();
	}

}
