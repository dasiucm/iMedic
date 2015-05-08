package icaro.aplicaciones.agentes.AgenteAplicacionDialogoPaciente.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class conversacionPaciente {
	
	
	private static Random randomGenerator;
	private static HashMap<String,List<String>> conversacion;
	
	static{
		conversacion = new HashMap<String, List<String>>();
		randomGenerator = new Random();
		List<String> dameDatos = new ArrayList<String>();
		dameDatos.add("Para la cita voy a necesitar una fecha y hora asi como el nombre del Medico");
		dameDatos.add("Bueno para crear la cita voy a necesitar el nombre del doctor y la fecha");
		
		List<String> focoRepetido = new ArrayList<String>();
		focoRepetido.add("mmmmm... creo que ya estamos haciendo eso...");
		focoRepetido.add("ya estamos en eso");
		
		conversacion.put("dameDatos", dameDatos);
		conversacion.put("focoRepetido", focoRepetido);
	}
	
	
	public static String msg(String tipo){
		String result = null;
		
		if(conversacion.get(tipo) != null && conversacion.get(tipo).size() > 0){
	        int index = randomGenerator.nextInt(conversacion.get(tipo).size());
	        String item = conversacion.get(tipo).get(index);		
			return item;
		}
				
		return result;
	}
	
	public static final String obtenerInfoCita1dameDatos = "Facilitame los datos de la cita (una fecha y un médico)";
	public static final String obtenerInfoCita2fechaRegistrada = "Fecha registrada, ahora queda el médico";
	public static final String obtenerInfoCita3medicoRegistrado = "Médico registrado, ahora queda la fecha";
	public static final String obtenerInfoCita4todoCompletado = "Estupendo, la cita se ha realizado con éxito!";
	public static final String obtenerInfoCita4cancelado = "Vale, no le hare la cita";
	
	
	public static final String mostrarCitasPaciente = "Estas son sus citas: 1- Cita 1 2- Cita 2  3- Cita 3";
}