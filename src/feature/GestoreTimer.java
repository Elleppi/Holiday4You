package feature;

import control.NotificheController;
import libreria.Data;
import libreria.Prenotazione;

public class GestoreTimer {
	
	private static GestoreTimer instance;
	
	private GestoreTimer() {
		
	}
	
	public static GestoreTimer getInstance() {
		if(instance == null)
			instance = new GestoreTimer();
		
		return instance;
	}

	
	/*Ritorna true se non sono ancora trascorsi il 10% dei giorni
	 * rimanenti tra la data di prenotazione e la data di inizio 
	 * affitto del Locale
	 */
	public boolean getConvalidaPrenotazione10(Prenotazione p) {
		Data today = Data.getData();
		int giorniTotali = Data.giorniDifferenza(p.getDataPrenotazione(), p.getDataStart());
		int giorniRimanenti = Data.giorniDifferenza(today, p.getDataStart());
		
		int giorniDisponibili = giorniTotali / 10;
		
		if(giorniRimanenti > giorniDisponibili)
			return true;
		else
			return false;
	}
	
	/*Ritorna true se non sono ancora trascorsi il 20% dei giorni
	 * rimanenti tra la data di prenotazione e la data di inizio 
	 * affitto del Locale
	 */
	public boolean getConvalidaPrenotazione20(Prenotazione p) {
		Data today = Data.getData();
		int giorniTotali = Data.giorniDifferenza(p.getDataPrenotazione(), p.getDataStart());
		int giorniRimanenti = Data.giorniDifferenza(today, p.getDataStart());
		
		int giorniDisponibili = giorniTotali / 20;
		
		if(giorniRimanenti > giorniDisponibili)
			return true;
		else
			return false;
	}
	
}
