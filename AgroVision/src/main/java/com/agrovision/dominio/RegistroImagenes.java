package com.agrovision.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Objeto que corresponde a un Registro
 * @author Jesus
 *
 */
@Entity
public class RegistroImagenes {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String url_imagen;
    @Column
	private boolean plaga_encontrada;
    @Column
	private String plaga;
    @Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl_imagen() {
		return url_imagen;
	}
	public void setUrl_imagen(String url_imagen) {
		this.url_imagen = url_imagen;
	}
	public boolean isPlaga_encontrada() {
		return plaga_encontrada;
	}
	public void setPlaga_encontrada(boolean plaga_encontrada) {
		this.plaga_encontrada = plaga_encontrada;
	}
	public String getPlaga() {
		return plaga;
	}
	public void setPlaga(String plaga) {
		this.plaga = plaga;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	@Override
	public String toString() {
		return "RegistroImagenes [id=" + id + ", url_imagen=" + url_imagen + ", plaga_encontrada=" + plaga_encontrada
				+ ", plaga=" + plaga + ", fecha_registro=" + fecha_registro + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha_registro == null) ? 0 : fecha_registro.hashCode());
		result = prime * result + id;
		result = prime * result + ((plaga == null) ? 0 : plaga.hashCode());
		result = prime * result + (plaga_encontrada ? 1231 : 1237);
		result = prime * result + ((url_imagen == null) ? 0 : url_imagen.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroImagenes other = (RegistroImagenes) obj;
		if (fecha_registro == null) {
			if (other.fecha_registro != null)
				return false;
		} else if (!fecha_registro.equals(other.fecha_registro))
			return false;
		if (id != other.id)
			return false;
		if (plaga == null) {
			if (other.plaga != null)
				return false;
		} else if (!plaga.equals(other.plaga))
			return false;
		if (plaga_encontrada != other.plaga_encontrada)
			return false;
		if (url_imagen == null) {
			if (other.url_imagen != null)
				return false;
		} else if (!url_imagen.equals(other.url_imagen))
			return false;
		return true;
	}

}
