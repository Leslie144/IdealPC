package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.ModularType;
@Repository
public interface IModularTypeRepository extends JpaRepository<ModularType, Integer> {
  
	@Query("select count(tm.type) from ModularType tm where tm.type=:tipo")
	public int ExistentModularType(@Param("tipo") String tipo);
}
