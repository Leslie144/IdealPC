package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entities.TipoModular;

public interface ITipoModularRepository extends JpaRepository<TipoModular, Integer> {
  
	@Query("select count (tm.nTipoModular) from TipoModular tm where tm.nTipoModular=:tipo")
	public int TipoModularExistentes(@Param("tipo") String tipo);
}
