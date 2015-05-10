package icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class conversacion {
	
	private static Random randomGenerator;
	private static HashMap<String,List<String>> conversacion;
	
	static{
		conversacion = new HashMap<String, List<String>>();
		
		List<String> saludoInicial = new ArrayList<String>();
		saludoInicial.add("Hola, soy un agente encargado en recepcion y solicitud de citas medicas");
		saludoInicial.add("Buenas, soy tu agente de confianza para manejo de citas medicas");
		saludoInicial.add("Buenos dias, soy asistente tu para manejo de citas medicas");
		
		List<String> solicitarNombre = new ArrayList<String>();
		solicitarNombre.add("Con quien tengo el gusto de hablar?");
		solicitarNombre.add("me darias tu nombre?");
		solicitarNombre.add("tengo que comprobarte en el sistema, Cual es tu nombre?");
		solicitarNombre.add("dime tu nombre?");
		
		List<String> solicitarNombreImperativo = new ArrayList<String>();
		solicitarNombreImperativo.add("Para continuar le recuerdo que necesito su nombre");
		solicitarNombreImperativo.add("por favor coopere, digame su nombre");
		solicitarNombreImperativo.add("realmente necesito su nombre, por favor");
	
		List<String> solicitarAccionImperativo = new ArrayList<String>();
		solicitarAccionImperativo.add("Me gustaria saber si me piensa decir que quiere hacer");
		solicitarAccionImperativo.add("Por favor sea concreto, digame que quiere");
		solicitarAccionImperativo.add("Le recuerdo que solo puedo ayudarlo con asuntos de citas medicas");
		
		List<String> obtencionNombre = new ArrayList<String>();
		obtencionNombre.add("Perfecto, te llamare de ahora en adelante ");
		obtencionNombre.add("De acuerdo ");
		obtencionNombre.add("Muchas gracias, ");
		obtencionNombre.add("Esta todo listo, ");
		
		List<String> obtencionDNI = new ArrayList<String>();
		obtencionDNI.add("Perfecto, te estoy registrando en el sistema ");
		obtencionDNI.add("Muchas gracias, ya con eso puedo registrarte en el sistema");
		
		List<String> inactividad = new ArrayList<String>();
		inactividad.add("hola?? se encuentra aun alli?");
		inactividad.add("hola?? hola?? alguien en casa?");
		inactividad.add("presumo que ya no se encuentra entre nosotros");
		inactividad.add("hola??? sigue alli?.... como molesta que lo dejen hablando solo");
		
		List<String> usuarioRegistrado = new ArrayList<String>();
		usuarioRegistrado.add("Veo que ya se encuentras en el sistema");
		usuarioRegistrado.add("Veo que no es la primera vez que pasas por aqui");
		usuarioRegistrado.add("mmmm... pues ya te tengo en el sistema, ya estabas registrado");
		
		List<String> usuarionoRegistrado = new ArrayList<String>();
		usuarionoRegistrado.add("Veo que aun no te encuentras registrado en el sistema");
		usuarionoRegistrado.add("No te tengo en pantalla, voy a necesitar mas datos");
		usuarionoRegistrado.add("Al ser la primera vez que utilizas el sistema necesitare mas datos");
		
		List<String> resaludar = new ArrayList<String>();
		resaludar.add("hola?? creo que ya nos saludamos");
		resaludar.add("hola hola?? podemos estar todo el dia en esto si quieres");
		
		
		List<String> sincontexto = new ArrayList<String>(); 
		sincontexto.add("no entiendo de que hablas");
		sincontexto.add("Te recuerdo que es un sistema exclusivo para citas medicas");
		sincontexto.add("Hablame de asuntos con citas medicas por favor");
		
		List<String> distribucion = new ArrayList<String>(); 
		distribucion.add("Listo, dime en que puedo ayudarte");
		distribucion.add("En que puedo ayudarte?");
		distribucion.add("Que te gustaria hacer hoy?");
		distribucion.add("Estoy a tus ordenes");
		
		List<String> objetivoLogrado = new ArrayList<String>(); 
		objetivoLogrado.add("Para que mas soy bueno?");
		objetivoLogrado.add("En que mas puedo ayudarte?");
		objetivoLogrado.add("En que mas puedo complacerte?");
		objetivoLogrado.add("Estoy a tus ordenes para cualquier otra cosa");
		
		List<String> despedida = new ArrayList<String>(); 
		despedida.add("hasta otro dia");
		despedida.add("un placer haberte ayudado");
		despedida.add("hasta la proxima");
		despedida.add("bye");
		
		List<String> peticionDNI = new ArrayList<String>(); 
		peticionDNI.add("Para acelerar las cosas necesito tu DNI?");
		peticionDNI.add("quiero tu DNI para validar que estas en el sistema");
		peticionDNI.add("serias tan amable de darme tu DNI para revisarte en el sistema");
		
		List<String> peticionDNIimperativo = new ArrayList<String>(); 
		peticionDNIimperativo.add("Disculpe, pero de verdad necesito el DNI para poder continuar");
		peticionDNIimperativo.add("realmente necesito su DNI, el sistema no permite avanzar si el");
		peticionDNIimperativo.add("NECESITO SU DNI, DEJE DE JUGAR");
		
		
		conversacion.put("saludoInicial", saludoInicial);
		conversacion.put("solicitarNombre", solicitarNombre);
		conversacion.put("obtencionNombre", obtencionNombre);
		conversacion.put("obtencionDNI", obtencionDNI);
		conversacion.put("inactividad", inactividad);
		conversacion.put("usuarioRegistrado", usuarioRegistrado);
		conversacion.put("usuarionoRegistrado", usuarionoRegistrado);
		conversacion.put("resaludar", resaludar);
		conversacion.put("sincontexto", sincontexto);
		conversacion.put("distribucion", distribucion);
		conversacion.put("objetivoLogrado", objetivoLogrado);
		conversacion.put("despedida", despedida);
		conversacion.put("peticionDNI", peticionDNI);
		conversacion.put("solicitarNombreImperativo", solicitarNombreImperativo);
		conversacion.put("peticionDNIimperativo", peticionDNIimperativo);
		conversacion.put("solicitarAccionImperativo", solicitarAccionImperativo);
		
	}
	
	
	public static String msg(String tipo){
		String result = null;
		randomGenerator = new Random(new Date().getTime());
		if(conversacion.get(tipo) != null && conversacion.get(tipo).size() > 0){
	        int index = randomGenerator.nextInt(conversacion.get(tipo).size());
	        String item = conversacion.get(tipo).get(index);		
			return item;
		}
				
		return result;
	}
	
	public static final String saludoInicial = "Hola, soy un agente encargado en recepcion y solicitud de citas medicas";
	public static final String resaludo1 = "hola?? creo que ya nos habiamos saludado";

	public static final String SolicitarDNI1 = "ahora necesito que me digas tu numero DNI";

	public static final String SolicitarNombre1 = "Con quien tengo el gusto de hablar";
	public static final String SolicitarNombre2 = "me darias tu nombre?";
	public static final String SolicitarNombre3 = "Hola, Tengo que comprobarte en el sistema, Cual es tu nombre?";

	public static final String obtencionNombre1 = "Perfecto, te llamare de ahora en adelante ";
	public static final String obtencionNombre2 = "De acuerdo ";

	public static final String obtencionDNI1 = "Perfecto, te estoy registrando en el sistema";
	public static final String obtencionDNI2 = "muchas gracias, te estoy registrando en el sistema";

	public static final String distribucion1 = "Listo, dime en que puedo ayudarte";
	public static final String distribucion2 = "En que puedo ayudarte";
	public static final String distribucion3 = "Que deseas hacer?";

	public static final String genericos1 = "y de que me sirve eso";
	public static final String genericos2 = "no estoy interesado";
	public static final String genericos3 = "hablemos de otra cosa";

	public static final String inactividad1 = "hola?? se encuentra aun alli?";
	public static final String inactividad2 = "hola?? hola?? alguien en casa?";
	public static final String inactividad3 = "presumo que ya no se encuentra entre nosotros";

	public static final String usuarioRegistrado = "Veo que ya se encuentras en el sistema";
	public static final String usuarioNoRegistrado = "Veo que aun no te encuentras registrado en el sistema";
	
	public static final String despedida1 = "hasta otra ocasion";
}
