package control;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import feature.GestoreNotifiche;
import feature.GestoreTimer;
import libreria.Data;
import libreria.Locale;
import libreria.Notifica;
import libreria.Prenotazione;

public class DettagliPrenotazioneController {
	private static DettagliPrenotazioneController instance;

	private DettagliPrenotazioneController() {
		
	}
	
	public static DettagliPrenotazioneController getInstance() {
		if(instance == null)
			instance = new DettagliPrenotazioneController();
		
		return instance;
	}

	public void convalidaPrenotazione(String ruolo, boolean convalida, Prenotazione p, int IDutente) {
		GestoreDB gdb = GestoreDB.getInstance();
		gdb.aggiornaConvalidaPrenotazione(ruolo, p.getIdPrenotazione(), convalida);
		Notifica n;
		String testo;
		
		/*Invia notifica al Legale e al Locatario per l'esito della convalida della prenotazione*/
		if(ruolo.equals("Locatore")) {
			
			if(convalida) {
				int IDlegale = GestoreNotifiche.assegnaLegale(0);
				//invia notifica al legale
				n = new Notifica(p.getIdPrenotazione(), "prenotazione", IDutente, IDlegale, null, Data.getData(), false);
				gdb.salvaNotifica(n);
				testo = "La prenotazione è stata convalidata da parte del Locatore";
				gdb.incrementaCampo(IDlegale, "nPrenotazioni"); //incrementa nPrenotazioni del Legale
				gdb.incrementaCampo(IDutente, "nPrenotazioni"); //incrementa nPrenotazioni del Locatore
				
			}
			else
				testo = "La prenotazione non è stata convalidata da parte del Locatore";
			
			/*Invia notifica al Locatario dell'esito della convalida*/
			n = new Notifica(p.getIdPrenotazione(), "prenotazione", IDutente, p.getIdLocatario(), testo, Data.getData(), false);
			gdb.salvaNotifica(n);
		}
		
		/*Invia notifica al Locatore e Locatario dell'esito della convalida della prenotazione*/
		if(ruolo.equals("Legale")) {
			Locale l = gdb.getLocale(p.getLocale().getID());
			int IDlocatore = l.getIDlocatore();
			
			if(convalida) {
				testo = "La prenotazione è stata convalidata da parte del Legale";
				gdb.incrementaCampo(p.getIdLocatario(), "nPrenotazioni"); //incrementa nPrenotazioni del Locatario
			}
			else
				testo = "La prenotazione non è stata convalidata da parte del Legale";
			
			//invia notifica al Locatore
			n = new Notifica(p.getIdPrenotazione(), "prenotazione", IDutente, IDlocatore, testo, Data.getData(), false);
			gdb.salvaNotifica(n);
			
			//invia notifica al Locatario
			n = new Notifica(p.getIdPrenotazione(), "prenotazione", IDutente, p.getIdLocatario(), testo, Data.getData(), false);
			gdb.salvaNotifica(n);
		}
	}
	
	public synchronized Prenotazione caricaDettagliPrenotazione(String iDprenotazione, String iDutente_registrato, String nome, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		Prenotazione p = new Prenotazione();
		UtenteRegistrato ur;
		
		try{
			ur = gdb.datiUtenteRegistrato(Integer.parseInt(iDutente_registrato));
		} catch(NumberFormatException nfe) {
			return null;
		}
		
		try{
			p = gdb.caricaPrenotazione(Integer.parseInt(iDprenotazione));
		}catch(NumberFormatException nfe) {
			return null;
		}
		if(ruolo.equals("Locatario") && p.getIdLocatario() != Integer.parseInt(iDutente_registrato))
			return null;
		if(ruolo.equals("Locatore") && p.getLocale().getIDlocatore() != Integer.parseInt(iDutente_registrato)) 
			return null;
		if(ruolo.equals("Legale") && p.getLocale().getIDLegale() != Integer.parseInt(iDutente_registrato))
			return null;
		
		return p;
	}

	public synchronized boolean getTimer(String ruolo, Prenotazione p) {
		GestoreTimer gt = GestoreTimer.getInstance();
		
		/*Se è scaduto il timer, non sarà più possibile convalidare
		 * e la prenotazione viene persa
		 */
		if(ruolo.equals("Locatore") || ruolo.equals("Legale")) {
			if(gt.getConvalidaPrenotazione10(p))
				return true;
			else
				return false;
		}
		
		/*Se è scaduto il timer, non è più possibile pagare
		 * e la prenotazione viene persa
		 */
		if(ruolo.equals("Locatario")) {
			if(gt.getConvalidaPrenotazione20(p))
				return true;
			else
				return false;
		}
		
		/*Se è scaduto il timer, non è più possibile modificare 
		 * o annullare la prenotazione
		 */
		if(ruolo.equals("LocatarioGestionePrenotazione")) {
			if(gt.getConvalidaPrenotazione10(p))
				return true;
			else
				return false;
		}
		
		return false;
	}

	/*Elimina la prenotazione*/
	public synchronized void annullaPrenotazione(Prenotazione prenotazione) {
		GestoreDB gdb = GestoreDB.getInstance();
		gdb.eliminaPrenotazione(prenotazione);
		
		/*se la prenotazione era stata precedentemente convalidata dal Locatore
		 * allora inviagli una notifica
		 */
		if(prenotazione.getConvalidaLocatore() == 1) {
			Locale l = gdb.getLocale(prenotazione.getLocale().getID());
			int IDlocatore = l.getIDlocatore();
			Notifica n = new Notifica(prenotazione.getIdPrenotazione(), "eliminazione", 
					prenotazione.getIdLocatario(), IDlocatore, "La prenotazione a " 
							+ prenotazione.getLocale().getIndirizzo() + " con inizio il "
							+ Data.formattaData(prenotazione.getDataStart()) + " e fine il " 
							+ Data.formattaData(prenotazione.getDataEnd()) + " è stata annullata",
					Data.getData(), false);
			
			gdb.salvaNotifica(n);
		}
	}
	
}
