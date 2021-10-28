package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TipoUsuario;
import pe.edu.upc.repositories.ITipoUsuarioRepository;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;

@Service
public class TipoUsuarioServiceImplement implements ITipoUsuarioService{

	@Autowired
	private ITipoUsuarioRepository tuR;
	@Override
	public Integer insert(TipoUsuario tipousuario) {
		int rpta=tuR.TipoUsuarioExistentes(tipousuario.getnTipousuario());
		if(rpta==0) {
			tuR.save(tipousuario);
		}
		return rpta;
	}

	@Override
	public List<TipoUsuario> list() {
		// TODO Auto-generated method stub
		return tuR.findAll();
	}
}
