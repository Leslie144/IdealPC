package pe.edu.upc.serviceimplements;

import java.util.List;

import pe.edu.upc.entities.TipoUsuario;
import pe.edu.upc.repositories.ITipoUsuarioRepository;
import pe.edu.upc.serviceinterfaces.ITipoUsuarioService;

public class TipoUsuarioServiceImplement implements ITipoUsuarioService{

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
