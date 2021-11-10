package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Users;

@Repository
public interface IUsuarioRepository extends JpaRepository<Users, Long> {

	@Query("select count(u.username) from Users u where u.username=:name")
	public int UsuarioExistentes(@Param("name") String nombre);
	
	@Transactional
	@Modifying
	@Query(value = "insert into roles (rol, user_id) VALUES (:rol, :user_id)", nativeQuery = true)
	public void insRol(@Param("rol") String authority, @Param("user_id") Long user_id);


	List<Users> findByUsername(String name);
	
	//public Users findBynombreUsuario(String name);
}
