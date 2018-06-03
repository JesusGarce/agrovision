package com.agrovision.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Objeto que representa una relación entre tratamiento, plaga y cultivo.
 * @author Jesus
 *
 */
@Entity
public class TratamientoPlaga {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private int id_plaga;
    @Column
	private int id_tratamiento;
    @Column
	private String cultivo;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_plaga() {
		return id_plaga;
	}
	public void setId_plaga(int id_plaga) {
		this.id_plaga = id_plaga;
	}
	public int getId_tratamiento() {
		return id_tratamiento;
	}
	public void setId_tratamiento(int id_tratamiento) {
		this.id_tratamiento = id_tratamiento;
	}
	public String getCultivo() {
		return cultivo;
	}
	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

    
}
