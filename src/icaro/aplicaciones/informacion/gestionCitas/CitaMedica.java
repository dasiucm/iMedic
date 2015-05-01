package icaro.aplicaciones.informacion.gestionCitas;

public class CitaMedica {
	
	/*public String usuario;
	public String nombrePaciente;
	public String dniPaciente;
	public String nombreMedico;*/
	public UsuarioContexto paciente;
	public String medico;
	public String fecha;

	
	
	public CitaMedica(UsuarioContexto paciente, String medico, String fecha) {
		this.paciente = paciente;
		this.medico = medico;
		this.fecha = fecha;
	}

	public String getMedico() {
		return medico;
	}

	public String getFecha() {
		return fecha;
	}
}
