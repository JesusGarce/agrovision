package com.agrovision.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Objeto que representa un Tratamiento
 * @author Jesus
 *
 */
@Entity
public class Tratamiento {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    @Column
	private String nombre;
    @Column
	private String descripcion;
    @Column
	private String principio_activo;
    @Column
	private String clasificacion;
    @Column
  	private String tipo;
    @Column
  	private String producto;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrincipio_activo() {
		return principio_activo;
	}
	public void setPrincipio_activo(String principio_activo) {
		this.principio_activo = principio_activo;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	@Override
	public String toString() {
		return "Tratamiento [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", principio_activo="
				+ principio_activo + ", clasificacion=" + clasificacion + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clasificacion == null) ? 0 : clasificacion.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((principio_activo == null) ? 0 : principio_activo.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Tratamiento other = (Tratamiento) obj;
		if (clasificacion == null) {
			if (other.clasificacion != null)
				return false;
		} else if (!clasificacion.equals(other.clasificacion))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (principio_activo == null) {
			if (other.principio_activo != null)
				return false;
		} else if (!principio_activo.equals(other.principio_activo))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
    
    
    

}
