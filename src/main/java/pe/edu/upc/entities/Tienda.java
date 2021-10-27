package pe.edu.upc.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "Tienda")
public class Tienda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTienda;
	
	@Column(name = "fotoTienda", nullable = true)
	private String fotoTienda;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del producto no puede contener un número")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del producto no puede contener un número")
	@NotNull(message = "El nombre del producto no puede estar vacio")
	@Column(name = "direccionTienda",nullable = false)
	private String direccionTienda;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del producto no puede contener un número")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del producto no puede contener un número")
	@NotNull(message = "El nombre del producto no puede estar vacio")
	@Column(name = "nombreTienda",nullable = false)
	private String nombreTienda;
	
	@NotNull
	@Positive
	@Min(value = 1, message = "El precio no debe ser menor a 1")
    @Max(value = 999999999, message = "El precio no debe ser mayor a 100")
	@Column(name = "telefonoTienda", columnDefinition = "Decimal(8,2)", nullable = false)
	private double telefonoTienda;
	
	@Column(name = "webTienda",nullable = false)
	private String webTienda;
	
	
	
	@ManyToOne
	@JoinColumn(name = "idDistrito",nullable = false)
	private Distrito distrito;
	
	private Date fechaRegistro;

	public Tienda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tienda(int idTienda, String fotoTienda,
			String direccionTienda,
			String nombreTienda,
			double telefonoTienda,
			String webTienda, Distrito distrito, Date fechaRegistro) {
		super();
		this.idTienda = idTienda;
		this.fotoTienda = fotoTienda;
		this.direccionTienda = direccionTienda;
		this.nombreTienda = nombreTienda;
		this.telefonoTienda = telefonoTienda;
		this.webTienda = webTienda;
		this.distrito = distrito;
		this.fechaRegistro = fechaRegistro;
	}

	public int getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(int idTienda) {
		this.idTienda = idTienda;
	}

	public String getFotoTienda() {
		return fotoTienda;
	}

	public void setFotoTienda(String fotoTienda) {
		this.fotoTienda = fotoTienda;
	}

	public String getDireccionTienda() {
		return direccionTienda;
	}

	public void setDireccionTienda(String direccionTienda) {
		this.direccionTienda = direccionTienda;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public double getTelefonoTienda() {
		return telefonoTienda;
	}

	public void setTelefonoTienda(double telefonoTienda) {
		this.telefonoTienda = telefonoTienda;
	}

	public String getWebTienda() {
		return webTienda;
	}

	public void setWebTienda(String webTienda) {
		this.webTienda = webTienda;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
