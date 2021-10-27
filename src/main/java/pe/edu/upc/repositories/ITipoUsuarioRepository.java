package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entities.TipoUsuario;

public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer>{

	@Query("select count (tu.nTipousuario) from TipoUsuario tu where tu.nTipousuario=:tipo")
	public int TipoUsuarioExistentes(@Param("tipo") String tipo);
}
