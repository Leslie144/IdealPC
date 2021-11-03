package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Usuario;
import pe.edu.upc.repositories.IUsuarioRepository;
import pe.edu.upc.serviceinterfaces.IUsuarioService;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

	@Autowired
	private IUsuarioRepository uR;

	@Override
	public boolean insert(Usuario usuario) {

		Usuario rpta = uR.save(usuario);
		if (rpta == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<Usuario> list() {
		return uR.findAll();
	}

	@Override
	// @Transactional(readOnly=true)

	@Transactional(readOnly = true)
	public Usuario listarId(int idUsuario) {
		Optional<Usuario> op = uR.findById(idUsuario);
		return op.isPresent() ? op.get() : new Usuario();
	}

	@Override
	public List<Usuario> findBynombreUsuario(String name) {
		// TODO Auto-generated method stub
		return uR.findBynombreUsuario(name);
	}

	@Override
	public void delete(int idUsuario) {
		// TODO Auto-generated method stub
		uR.deleteById(idUsuario);
	}
}
