package pe.edu.upc.serviceimplements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Users;
import pe.edu.upc.repositories.IUsuarioRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Nombre: "+username);
		Users user = userRepository.findByUsername(username).get(0);
		System.out.println("Password: "+user.getPassword());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		//for (Role role : user.getRoles()) {
			//authorities.add(new SimpleGrantedAuthority(role.getRol()));
		authorities.add(new SimpleGrantedAuthority(user.getRoles().getRol()));
		//}

		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

}
