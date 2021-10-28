package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TipoModular;
@Repository
public interface ITipoModularRepository extends JpaRepository<TipoModular, Integer> {
  
	@Query("select count(tm.nTipoModular) from TipoModular tm where tm.nTipoModular=:tipo")
	public int TipoModularExistentes(@Param("tipo") String tipo);
}
