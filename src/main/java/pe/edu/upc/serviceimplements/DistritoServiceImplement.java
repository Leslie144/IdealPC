package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Distrito;
import pe.edu.upc.repositories.IDistritoRepository;
import pe.edu.upc.serviceinterfaces.IDistritoService;
@Service
public class DistritoServiceImplement implements IDistritoService{

	@Autowired
	private IDistritoRepository dR;
	@Override
	public Integer insert(Distrito dis) {
		int rpta=dR.distritosExistentes(dis.getNombreDistrito());
		if(rpta==0) {
			dR.save(dis);
		}
		return rpta;
	}
	@Override
	public List<Distrito> list() {
		// TODO Auto-generated method stub
		return dR.findAll();
	}
}
