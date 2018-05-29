package control;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import feature.GestoreTimer;
import libreria.Data;
import libreria.Locale;
import libreria.Notifica;
import libreria.Prenotazione;

public class GestionePrenotazioneController {
	
	private static GestionePrenotazioneController instance;
	
	private GestionePrenotazioneController() {
		
	}
	
	public static GestionePrenotazioneController getInstance() {
		if(instance == null)
			instance = new GestionePrenotazioneController();
		
		return instance;
	}

	public synchronized int controllaModifiche(Prenotazione p, UtenteRegistrato ur) {
		GestoreDB gdb = GestoreDB.getInstance();
		int e = 0;
		
		if(!Data.correttezzaData(p.getDataStart(), p.getDataEnd()))
			return -1;
		
		if(Data.giorniDifferenza(p.getDataStart(), p.getDataEnd()) > 28)
			return -2;
		
		if(!gdb.verificaDataPrenotazione(p.getIdLocale(), p.getDataStart(), p.getDataEnd(), p.getIdPrenotazione()))
			return -3; 
		
		if(Data.giorniDifferenza(Data.getData(), p.getDataStart()) > 365)
			return -5;
		
		if(Data.giorniDifferenza(Data.getData(), p.getDataStart()) < 10)
			return -6; 
		
		//controlla se l'utente ha inserito 2 partecipanti uguali
	/*	if(p.getPartecipanti().size() > 1) {
			for(int i=0; i<p.getPartecipanti().size(); i++) {
				for(int j=i+1; j<p.getPartecipanti().size(); j++) {
					if(p.getPartecipanti().get(i).getCodiceFiscale().equals(p.getPartecipanti().get(j).getCodiceFiscale()))
						return -7;
				}
			}
		}*/
		
		gdb.modificaPrenotazione(p);
		Locale l = gdb.getLocale(p.getLocale().getID());
		int IDlocatore = l.getIDlocatore();
		
		Notifica n = new Notifica();
		n.setIDoggetto(p.getIdPrenotazione());
		n.setCategoria("prenotazione");
		n.setIDmittente(p.getIdLocatario());
		n.setIDdestinatario(IDlocatore);
		n.setDataInvioNotifica(Data.getData());
		n.setTesto("La prenotazione in via " + l.getIndirizzo() + " (" + l.getProvincia() + ") "
				+ "con data inizio " + Data.dataToString(p.getDataStart()) + " e data fine "
				+ Data.dataToString(p.getDataEnd()) + " è stata modificata");
		
		gdb.salvaNotifica(n);
		
		return e;
		
	}

	public synchronized boolean getTimer(Prenotazione p) {
		GestoreTimer gt = GestoreTimer.getInstance();
		
		if(gt.getConvalidaPrenotazione10(p))
			return true;
		else
			return false;
	}
	
}
