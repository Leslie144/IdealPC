package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("select count (u.nombreUsuario) from Usuario u where u.nombreUsuario=:name")
	public int UsuarioExistentes(@Param("name") String nombre);

	List<Usuario> findBynombreUsuario(String name);
}
