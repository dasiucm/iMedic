package icaro.aplicaciones.agentes.AgenteAplicacionDialogoMedico.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class AnularCitas extends Objetivo {
	
	public String fechaInicio;
	public String fechaFin;

	public AnularCitas() {
		super.setgoalId("AnularCitas");
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
	
}
