package icaro.aplicaciones.informacion.gestionCitas;

public class NotificacionPaciente extends Notificacion {

	public NotificacionPaciente() {
	}

	public NotificacionPaciente(String usuarioId) {
		super(usuarioId);
	}
	
	public NotificacionPaciente(Notificacion notif) {		
		identNotificador = notif.getidentNotificador();
		setMensajeNotificacion(notif.getMensajeNotificacion());
		setcontexto(notif.getcontexto());
		setidentObjectRefNotificacion(notif.getidentObjectRefNotificacion());
		setTipoNotificacion(notif.getTipoNotificacion());
		setUsuario(notif.getUsuario());
	}

}