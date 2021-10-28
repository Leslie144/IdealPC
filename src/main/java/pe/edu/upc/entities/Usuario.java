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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name= "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Usuario no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del Usuario no puede contener un número")
	@NotNull(message = "El nombre de la TIENDA no puede estar vacio")
	@Column(name = "nombreUsuario",nullable = false)
	private String nombreUsuario;

	@Column(name = "tContrasena",nullable = false)
	private String tContrasena;
	
	@Column(name = "tTelefono",nullable = false)
	private String tTelefono;
	
	@Column(name = "tCorreo",nullable = false)
	private String tCorreo;
	
	@Column(name = "fotoUsuario", nullable = true)
	private String fotoUsuario;
	
	private Date fechaRegistro;
	
	@ManyToOne
	@JoinColumn(name = "idDistrito",nullable = false)
	private Distrito distrito;
	
	@ManyToOne
	@JoinColumn(name = "idTipousuario",nullable = false)
	private TipoUsuario tipoUsuario;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(int idUsuario,
			String nombreUsuario,
			String tContrasena, String tTelefono, String tCorreo, String fotoUsuario, Date fechaRegistro,
			Distrito distrito, TipoUsuario tipoUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.tContrasena = tContrasena;
		this.tTelefono = tTelefono;
		this.tCorreo = tCorreo;
		this.fotoUsuario = fotoUsuario;
		this.fechaRegistro = fechaRegistro;
		this.distrito = distrito;
		this.tipoUsuario = tipoUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String gettContrasena() {
		return tContrasena;
	}

	public void settContrasena(String tContrasena) {
		this.tContrasena = tContrasena;
	}

	public String gettTelefono() {
		return tTelefono;
	}

	public void settTelefono(String tTelefono) {
		this.tTelefono = tTelefono;
	}

	public String gettCorreo() {
		return tCorreo;
	}

	public void settCorreo(String tCorreo) {
		this.tCorreo = tCorreo;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

		
}
