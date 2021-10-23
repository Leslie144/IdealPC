package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "tipoRecomendacion")
public class TipoRecomendacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoRecomendacion;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El Tipo de Recomendación no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El Tipo de Recomendación no puede contener un número")
	@Column(name = "tRecomendacion", nullable = false, length = 100)
	private String tRecomendacion;

	public TipoRecomendacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoRecomendacion(int idTipoRecomendacion,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El Tipo de Recomendación no puede contener caracteres especiales") @Pattern(regexp = "[^0-9]+", message = "El Tipo de Recomendación no puede contener un número") String tRecomendacion) {
		super();
		this.idTipoRecomendacion = idTipoRecomendacion;
		this.tRecomendacion = tRecomendacion;
	}

	public int getIdTipoRecomendacion() {
		return idTipoRecomendacion;
	}

	public void setIdTipoRecomendacion(int idTipoRecomendacion) {
		this.idTipoRecomendacion = idTipoRecomendacion;
	}

	public String gettRecomendacion() {
		return tRecomendacion;
	}

	public void settRecomendacion(String tRecomendacion) {
		this.tRecomendacion = tRecomendacion;
	}
	
	
}
