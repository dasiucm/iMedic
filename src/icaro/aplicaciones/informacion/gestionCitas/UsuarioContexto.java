package icaro.aplicaciones.informacion.gestionCitas;

import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.recursoCalendario.ItfUsoRecursoCalendario;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;

import java.io.Serializable;

public class UsuarioContexto implements Serializable {

	public String usuario;
	public String nombre;
	public String DNI;
	public boolean medico;
	public long tiempo;

	public UsuarioContexto() {
		tiempo = System.currentTimeMillis();
	}

	public String getUsuario() {
		return usuario;
	}

	public void actividad() {
		tiempo = System.currentTimeMillis();
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public boolean isMedico() {
		return medico;
	}

	public void setMedico(boolean medico) {
		this.medico = medico;
	}
	
	public String obtenerRecomendacionMedico(){
		try {
			ItfUsoRecursoCalendario recursoCalendario = (ItfUsoRecursoCalendario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoCalendario);
			
			return recursoCalendario.recomiendaMedico(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	
	public String obtenerRecomendacionFecha(){
		try {
			ItfUsoRecursoCalendario recursoCalendario = (ItfUsoRecursoCalendario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoCalendario);
			
			return recursoCalendario.recomiendaFecha(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	
	
	public boolean puedeRecibirRecomendacion(){
		try {
			ItfUsoRecursoCalendario recursoCalendario = (ItfUsoRecursoCalendario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoCalendario);
			
			
			
			String fecha = recursoCalendario.recomiendaFecha(usuario);
			
			String medico = recursoCalendario.recomiendaMedico(usuario);
			
			if(fecha != null && medico != null){
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean inactividad(int i) {
		long tim = ((System.currentTimeMillis() - tiempo) / 1000) / 60;
		return tim >= i;
	}

}
