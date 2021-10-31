package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Marca;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Integer>{
	
	@Query("select count (m.nMarca) from Marca m where m.nMarca=:name")
	public int MarcasExistentes(@Param("name") String nombre);
	
	List<Marca> findBynMarca(String name);
}
