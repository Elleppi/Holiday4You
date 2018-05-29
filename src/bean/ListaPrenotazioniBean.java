package bean;

import java.util.List;

import control.ListaPrenotazioniController;
import libreria.Prenotazione;

public class ListaPrenotazioniBean {
	
	private List<Prenotazione> listaPrenotazioni;
	private List<String> prenotazioniPassate;
	private List<String> prenotazioniPresenti;
	private List<String> prenotazioniFuture;
	private int IDutente_registrato;
	private String ruolo;
	private int errore;
	
	public ListaPrenotazioniBean() {
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

	/**
	 * @return the iDutente_registrato
	 */
	public int getIDutente_registrato() {
		return IDutente_registrato;
	}

	/**
	 * @param iDutente_registrato the iDutente_registrato to set
	 */
	public void setIDutente_registrato(int iDutente_registrato) {
		IDutente_registrato = iDutente_registrato;
	}

	/**
	 * @return the ruolo
	 */
	public String getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getPrenotazionePassata(int i) {
		return this.prenotazioniPassate.get(i);
	}
	
	public String getPrenotazionePresente(int i) {
		return this.prenotazioniPresenti.get(i);
	}
	
	public String getPrenotazioneFutura(int i) {
		return this.prenotazioniFuture.get(i);
	}
	
	public int sizeFuture() {
		return this.prenotazioniFuture.size();
	}
	
	public int sizePresenti() {
		return this.prenotazioniPresenti.size();
	}
	
	public int sizePassate() {
		return this.prenotazioniPassate.size();
	}
	
	/**
	 * @return the listaPrenotazioni
	 */
	public List<Prenotazione> getListaPrenotazioni() {
		return listaPrenotazioni;
	}

	/**
	 * @param listaPrenotazioni the listaPrenotazioni to set
	 */
	public void setListaPrenotazioni(List<Prenotazione> listaPrenotazioni) {
		this.listaPrenotazioni = listaPrenotazioni;
	}

	public void setListe(String ID, String nome, String ruolo) {
		ListaPrenotazioniController lpc = ListaPrenotazioniController.getInstance();
		
		List<Prenotazione> l = lpc.setListaPrenotazioni(ID, nome, ruolo);
		
		if(l == null)
			errore = -1;
		else {
			this.IDutente_registrato = Integer.parseInt(ID);
			this.ruolo = ruolo;
			this.listaPrenotazioni = l;
			this.prenotazioniPassate = lpc.setListaPassate(this.listaPrenotazioni);
			this.prenotazioniPresenti = lpc.setListaPresenti(this.listaPrenotazioni);
			this.prenotazioniFuture = lpc.setListaFuture(this.listaPrenotazioni);
		}
		
	}
}
