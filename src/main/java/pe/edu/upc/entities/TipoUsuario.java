package pe.edu.upc.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "tipoUsuario")
public class TipoUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipousuario;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El Tipo Usuario no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El Tipo Usuario no puede contener un número")
	@Column(name = "nTipousuario", length = 50, nullable = false)
	private String nTipousuario;

	public TipoUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoUsuario(int idTipousuario, String nTipousuario) {
		super();
		this.idTipousuario = idTipousuario;
		this.nTipousuario = nTipousuario;
	}
	//Getters and setters

	public int getIdTipousuario() {
		return idTipousuario;
	}

	public void setIdTipousuario(int idTipousuario) {
		this.idTipousuario = idTipousuario;
	}

	public String getnTipousuario() {
		return nTipousuario;
	}

	public void setnTipousuario(String nTipousuario) {
		this.nTipousuario = nTipousuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTipousuario, nTipousuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoUsuario other = (TipoUsuario) obj;
		return idTipousuario == other.idTipousuario && Objects.equals(nTipousuario, other.nTipousuario);
	}

	
	
}
