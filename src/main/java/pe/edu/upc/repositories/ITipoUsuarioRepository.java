package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TipoUsuario;

@Repository
public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer>{

	@Query("select count (tu.nTipousuario) from TipoUsuario tu where tu.nTipousuario=:nTipousuario")
	public int TipoUsuarioExistentes(@Param("nTipousuario") String nTipousuario);
}
