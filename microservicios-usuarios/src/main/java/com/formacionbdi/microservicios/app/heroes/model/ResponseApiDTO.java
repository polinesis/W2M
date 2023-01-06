package com.formacionbdi.microservicios.app.heroes.model;

import java.util.List;


public class ResponseApiDTO {

	private String tiempoRespuesta;
	private List<?> data;
	private String mensaje;
	
	
	public String getTiempoRespuesta() {
		return tiempoRespuesta;
	}
	public void setTiempoRespuesta(String tiempoRespuesta) {
		this.tiempoRespuesta = tiempoRespuesta;
	}

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}

	
	
}
