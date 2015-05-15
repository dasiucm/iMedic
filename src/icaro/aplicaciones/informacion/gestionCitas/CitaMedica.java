package icaro.aplicaciones.informacion.gestionCitas;

import icaro.aplicaciones.recursos.persistenciaUsuarios.imp.PersistenciaUsuariosImp;

import java.io.Serializable;

public class CitaMedica implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5902449218356230485L;
	public String usuario;
	public String nombrePaciente;
	public String dniPaciente;
	public String nombreMedico;
	public String fecha;
	
	public CitaMedica(){
		this.usuario = null;
		this.nombrePaciente = null;
		this.dniPaciente = null;
		this.nombreMedico = null;
		this.fecha = null;
	}
	
	public CitaMedica(String usuario, String nombreMedico, String fecha) {
		this.usuario = usuario;
		this.nombrePaciente = PersistenciaUsuariosImp.obtenerContextoUsuario(usuario).getNombre();
		this.dniPaciente = PersistenciaUsuariosImp.obtenerContextoUsuario(usuario).getDNI();
		this.nombreMedico = nombreMedico;
		this.fecha = fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getMedico() {
		return nombreMedico;
	}

	public String getFecha() {
		return fecha;
	}
	
	@Override
	public String toString() {
		return getFecha() + " --> " + getMedico();
	}
}
