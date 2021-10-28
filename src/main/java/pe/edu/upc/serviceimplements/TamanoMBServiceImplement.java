package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TamanoMB;
import pe.edu.upc.repositories.ITamanoMBRepository;
import pe.edu.upc.serviceinterfaces.ITamanoMBService;

@Service
public class TamanoMBServiceImplement implements ITamanoMBService{

	@Autowired
	private ITamanoMBRepository tmbR;

	@Override
	public Integer insert(TamanoMB tamanomb) {
		int rpta = tmbR.TamanosMBExistentes(tamanomb.getnTamanoMB());
		if(rpta ==0) {
			tmbR.save(tamanomb);
		}
		return rpta;
	}

	@Override
	public List<TamanoMB> list() {
		return tmbR.findAll();
	}
	
	
	
}
