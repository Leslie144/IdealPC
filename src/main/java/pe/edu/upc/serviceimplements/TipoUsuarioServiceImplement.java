package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Role;
import pe.edu.upc.repositories.ITipoUsuarioRepository;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;

@Service
public class TipoUsuarioServiceImplement implements ITipoUsuarioService {

	@Autowired
	private ITipoUsuarioRepository tuR;

	@Override
	public Integer insert(Role tipousuario) {
		int rpta=tuR.TipoUsuarioExistentes(tipousuario.getRol());
		if(rpta==0) {
			tuR.save(tipousuario);
		}
		return rpta;
	}

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return tuR.findAll();
	}

	@Override
	public Role listarId(int idTipousuario) {
		// TODO Auto-generated method stub
		Optional<Role>op=tuR.findById(idTipousuario);

		return op.isPresent() ? op.get() : new Role();
	}

	@Override
	public void delete(int idTipousuario) {
		// TODO Auto-generated method stub
		tuR.deleteById(idTipousuario);
	}

}
