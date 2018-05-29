package bean;

import java.util.ArrayList;
import java.util.List;

import control.NotificheController;
import libreria.Notifica;

public class NotificheBean {
	
	private List<String> notificheRicevute;
	private List<String> notificheSpedite;
	private List<Notifica> listaNotifiche;
	private int errore;
	
	public NotificheBean() {
		notificheRicevute = new ArrayList<>();
		notificheSpedite = new ArrayList<>();
		errore = 0;
	}
	
	/**
	 * @return the errore
	 */
	public int getErrore() {
		return errore;
	}

	/**
	 * @param errore the errore to set
	 */
	public void setErrore(int errore) {
		this.errore = errore;
	}

	public String getNotificaRicevuta(int i) {
		return this.notificheRicevute.get(i);
	}
	
	public String getNotificaSpedita(int i) {
		return this.notificheSpedite.get(i);
	}
	
	public int sizeRicevute() {
		return this.notificheRicevute.size()/3;
	}
	
	public int sizeSpedite() {
		return this.notificheSpedite.size()/3;
	}
	
	public void loadStringheNotifiche(String IDutente_registrato, String nome, String ruolo) {
		NotificheController nc = NotificheController.getInstance();
		
		List<Notifica> ln = nc.caricaNotifiche(IDutente_registrato, nome, ruolo);
		
		if(ln == null)
			this.errore = -1;
		else {
			this.listaNotifiche = ln;
			this.notificheRicevute = nc.setRicevute(this.listaNotifiche, IDutente_registrato);
			this.notificheSpedite = nc.setSpedite(this.listaNotifiche, IDutente_registrato);
		}
		
	}

}
