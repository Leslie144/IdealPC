package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "marca")
public class Marca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMarca;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la Marca no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la Marca no puede contener un número")
	@Column(name = "nMarca", nullable = false, length = 20)
	private String nMarca;

	@Column(name = "urlMarca", length = 300, nullable = false)
	private String urlMarca;

	public Marca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Marca(int idMarca,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la Marca no puede contener caracteres especiales") @Pattern(regexp = "[^0-9]+", message = "El nombre de la Marca no puede contener un número") String nMarca,
			String urlMarca) {
		super();
		this.idMarca = idMarca;
		this.nMarca = nMarca;
		this.urlMarca = urlMarca;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getnMarca() {
		return nMarca;
	}

	public void setnMarca(String nMarca) {
		this.nMarca = nMarca;
	}

	public String getUrlMarca() {
		return urlMarca;
	}

	public void setUrlMarca(String urlMarca) {
		this.urlMarca = urlMarca;
	}
	
	
	

}
