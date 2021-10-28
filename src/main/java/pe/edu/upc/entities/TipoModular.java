package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "tipoModular")
public class TipoModular  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoModular;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El Tipo Modular no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El Tipo Modular no puede contener un número")
	@Column(name = "nTipoModular", length = 100, nullable = false)
	private String nTipoModular;

	public TipoModular() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoModular(int idTipoModular, String nTipoModular) {
		super();
		this.idTipoModular = idTipoModular;
		this.nTipoModular = nTipoModular;
	}

	public int getIdTipoModular() {
		return idTipoModular;
	}

	public void setIdTipoModular(int idTipoModular) {
		this.idTipoModular = idTipoModular;
	}

	public String getnTipoModular() {
		return nTipoModular;
	}

	public void setnTipoModular(String nTipoModular) {
		this.nTipoModular = nTipoModular;
	}

	
}