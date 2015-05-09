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
		dameDatos.add("Muy bien, necesito que me diga una hora y una fecha asi como el doctor con el que desea la cita");
		
		List<String> focoRepetido = new ArrayList<String>();
		focoRepetido.add("mmmmm... creo que ya estamos haciendo eso...");
		focoRepetido.add("ya estamos en eso");
		focoRepetido.add("le recuerdo que le estoy pidiendo los datos para eso");

		List<String> imperativoPedirDatos = new ArrayList<String>();
		imperativoPedirDatos.add("Por favor, deme los datos que le solicito para poder hacer la cita");
		imperativoPedirDatos.add("Creo que vamos a perder todo el dia esperando a que me diga los datos");
		imperativoPedirDatos.add("Digame los datos por favor, no me haga perder el tiempo");
		
		List<String> imperativoPedirFecha = new ArrayList<String>();
		imperativoPedirFecha.add("Le recuerdo que necesito una fecha para poder continuar");
		imperativoPedirFecha.add("No me haga perder el tiempo y digame en que fecha la quiere");
		imperativoPedirFecha.add("digame por favor la fecha");
		
		List<String> imperativoPedirDoctor = new ArrayList<String>();
		imperativoPedirDoctor.add("Le recuerdo que necesito saber con que medico desea la cita para poder continuar");
		imperativoPedirDoctor.add("Por favor digame el medico que desea y no me haga perder el tiempo");
		imperativoPedirDoctor.add("digame por favor el nombre del medico");
		
		List<String> imperativoConfirmar = new ArrayList<String>();
		imperativoConfirmar.add("Por favor, digame si quiere o no la cita");
		imperativoConfirmar.add("No me haga perder el tiempo digame si la quiere no la cita");
		imperativoConfirmar.add("Para continuar le recuerdo que necesito saber si quiere o no esa cita");
		
		List<String> medicoRegistrado = new ArrayList<String>();
		medicoRegistrado.add("Listo con el medico");
		medicoRegistrado.add("Vale, ya tengo el medico");
		medicoRegistrado.add("Perfecto ya tengo el nombre del medico");
		
		List<String> fechaRegistrada = new ArrayList<String>();
		fechaRegistrada.add("Listo con la fecha");
		fechaRegistrada.add("Vale, ya tengo la fecha");
		fechaRegistrada.add("Perfecto ya tengo la fecha");
		
		conversacion.put("dameDatos", dameDatos);
		conversacion.put("focoRepetido", focoRepetido);
		conversacion.put("imperativoPedirFecha", imperativoPedirFecha);
		conversacion.put("imperativoPedirDoctor", imperativoPedirDoctor);
		conversacion.put("medicoRegistrado", medicoRegistrado);
		conversacion.put("imperativoConfirmar", imperativoConfirmar);
		conversacion.put("fechaRegistrada", fechaRegistrada);
		conversacion.put("imperativoPedirDatos", imperativoPedirDatos);
		
		
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