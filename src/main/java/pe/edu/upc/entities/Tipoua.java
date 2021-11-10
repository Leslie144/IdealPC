package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tipoua")
public class Tipoua {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoua;
	
	@Column(name = "nameTipoua", nullable = false, length = 20)
	private String nameTipoua;

	public Tipoua() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tipoua(int idTipoua, String nameTipoua) {
		super();
		this.idTipoua = idTipoua;
		this.nameTipoua = nameTipoua;
	}

	public int getIdTipoua() {
		return idTipoua;
	}

	public void setIdTipoua(int idTipoua) {
		this.idTipoua = idTipoua;
	}

	public String getNameTipoua() {
		return nameTipoua;
	}

	public void setNameTipoua(String nameTipoua) {
		this.nameTipoua = nameTipoua;
	}
	
}
