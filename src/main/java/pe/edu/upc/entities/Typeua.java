package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "typeua")
public class Typeua {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "type", nullable = false, length = 20)
	private String type;

	public Typeua() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Typeua(int idTipoua, String nameTipoua) {
		super();
		this.id = idTipoua;
		this.type = nameTipoua;
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
