package bean;

import attori.UtenteRegistrato;
import control.GestionePrenotazioneController;
import libreria.Prenotazione;

public class GestionePrenotazioneBean {
	
	private Prenotazione p;
	
	private static String errDataScorretta = "La data di fine prenotazione è minore della data di inizio prenotazione";
	private static String errTroppiGiorni = "Il soggiorno deve essere massimo di 28 giorni";
	private static String errPrenotazioneEsistente = "Esiste già una prenotazione esistente nell'intorno di quella data";
	private static String errTroppiGiorniAnticipo = "Non è possibile prenotare con più di 12 mesi di anticipo";
	private static String errTroppiPochiGiorniAnticipo = "Non è possibile prenotare un Locale con meno di 10 giorni di anticipo";
	private static String errPartecipantiUguali = "Hai inserito 2 partecipanti uguali";
	
	public GestionePrenotazioneBean() {
		this.p = null;
	}

	public Prenotazione getP() {
		return p;
	}

	public void setP(Prenotazione p) {
		this.p = p;
	}
	
	public String errore(int e) {
		String errore = "";
		
		switch (e) {
		case -1:
			errore = errDataScorretta;
			break;
		case -2:
			errore = errTroppiGiorni;
			break;
		case -3:
			errore = errPrenotazioneEsistente;
			break;
		case -5:
			errore = errTroppiGiorniAnticipo;
			break;
		case -6:
			errore = errTroppiPochiGiorniAnticipo;
			break;
		case -7:
			errore = errPartecipantiUguali;
			break;
		}
		
		return errore;
	}
	
	public boolean isTimer(Prenotazione p) {
		GestionePrenotazioneController gpc = GestionePrenotazioneController.getInstance();
		
		return gpc.getTimer(p);
	}
	
	public int convalidaModifica(Prenotazione prenotazione, UtenteRegistrato ur) {
		GestionePrenotazioneController gpc = GestionePrenotazioneController.getInstance();
		
		int e = gpc.controllaModifiche(prenotazione, ur);
		
		return e;
		
	}
		
}
