package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import pe.edu.upc.entities.TipoRecomendacion;


@Repository
public interface ITipoRecomendacionRepository extends JpaRepository<TipoRecomendacion, Integer>{

	@Query("slect count (t.tRecomendacion) from tipoRecomendacion t where t.tRecomendacion=:tipo")
	public int TipoRecomendacionExistentes(@Param("tipo") String tipo);
	
	
}
