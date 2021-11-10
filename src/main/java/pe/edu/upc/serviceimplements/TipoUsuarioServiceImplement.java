package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TipoRecomendacion;
import pe.edu.upc.entities.TipoUsuario;
import pe.edu.upc.repositories.ITipoUsuarioRepository;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;

@Service
public class TipoUsuarioServiceImplement implements ITipoUsuarioService{

	@Autowired
	private ITipoUsuarioRepository tuR;
	@Override
	public boolean insert(TipoUsuario tipousuario) {
		TipoUsuario rpta=tuR.save(tipousuario);
		if(rpta==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<TipoUsuario> list() {
		// TODO Auto-generated method stub
		return tuR.findAll();
	}

	@Override
	public TipoUsuario listarId(int idTipousuario) {
		// TODO Auto-generated method stub
		Optional<TipoUsuario>op=tuR.findById(idTipousuario);
		return op.isPresent() ? op.get() : new TipoUsuario();
	}

	@Override
	public void delete(int idTipousuario) {
		// TODO Auto-generated method stub
		tuR.deleteById(idTipousuario);
	}
}
