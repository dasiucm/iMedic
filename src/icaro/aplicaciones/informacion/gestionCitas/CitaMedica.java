package icaro.aplicaciones.informacion.gestionCitas;

public class CitaMedica {
	// public String usuario;
	UsuarioContexto paciente;
	// public String nombrePaciente;
	// public String dniPaciente;
	public String nombreMedico;
	public String fecha;

	public CitaMedica(UsuarioContexto paciente, String nombreMedico, String fecha) {
		this.paciente = paciente;
		this.nombreMedico = nombreMedico;
		this.fecha = fecha;
	}

	public String getMedico() {
		return this.nombreMedico;
	}

	public String getFecha() {
		return this.fecha;
	}

	public UsuarioContexto getPaciente() {
		return this.paciente;
	}

}
