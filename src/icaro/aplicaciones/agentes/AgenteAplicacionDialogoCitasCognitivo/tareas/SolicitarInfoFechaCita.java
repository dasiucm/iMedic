/*
 * SolicitarDatos.java
 *
 * Creado 23 de abril de 2007, 12:52
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionDialogoCitasCognitivo.tareas;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;

/**
 *
 * @author F Garijo
 */
public class SolicitarInfoFechaCita extends Tarea {
	private String identAgenteOrdenante;
	private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {
		this.getIdentTarea();
	}
}
