package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Hardware;


@Repository
public interface IHardwareRepository extends JpaRepository<Hardware, Integer>{
	@Query("select count(h.nombreHardware) from Hardware h where h.nombreHardware=:name")
	public int buscarHardware(@Param("name")String nombre);

	List<Hardware>findBynombreHardware(String name);
}
