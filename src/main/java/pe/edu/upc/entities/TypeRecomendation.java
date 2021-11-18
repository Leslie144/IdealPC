package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "typeRecomendation")
public class TypeRecomendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El Tipo de Recomendación no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El Tipo de Recomendación no puede contener un número")
	@Column(name = "type", nullable = false, length = 100)
	private String type;

	public TypeRecomendation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeRecomendation(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
