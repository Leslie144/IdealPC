package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Tipoua;



@Repository
public interface ITipouaRepository extends JpaRepository<Tipoua, Integer>{
	@Query("select count(l.nameTipoua) from Tipoua l where l.nameTipoua=:name")
	public int buscarTipoua(@Param("name") String nombre);
}