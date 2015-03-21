package icaro.aplicaciones.recursos.persistenciaUsuarios;

import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

import java.rmi.RemoteException;

public class ClaseGeneradoraPersistenciaUsuarios extends ImplRecursoSimple 
	implements ItfPersistenciaUsuarios {


	public ClaseGeneradoraPersistenciaUsuarios(String idRecurso)
			throws RemoteException {
		super(idRecurso);
		System.out.println("SE CREA EL RECURSO QUE QUERIAMOS");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void insertarUsuario(String nombreChat, String nombre, String DNI)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarNombreUsuario(String nombreChat, String nombre)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarDniUsuario(String nombreChat, String DNI)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String obtenerNombreUsuario(String nombreChat) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerDNI(String nombreChat) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validarUsuario(String nombreChat, String DNI)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
