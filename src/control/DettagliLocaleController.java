package control;

import java.util.List;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import libreria.Data;
import libreria.Locale;
import libreria.Notifica;
import libreria.Prenotazione;

public class DettagliLocaleController {
	
	private static DettagliLocaleController instance;
	
	private DettagliLocaleController() {
		
	}
	
	public static DettagliLocaleController getInstance() {
		if(instance == null)
			instance = new DettagliLocaleController();
		
		return instance;
	}

	public synchronized int checkParameters(String id, String nome, String ruolo, String idlocale) {
		GestoreDB gdb = GestoreDB.getInstance();
		UtenteRegistrato ur;
		Locale l;
		
		if(id == null || nome == null || ruolo == null || idlocale == null)
			return -1;
		
		try{
			ur = gdb.datiUtenteRegistrato(Integer.parseInt(id));
			l = gdb.getLocale(Integer.parseInt(idlocale));
		} catch(NumberFormatException nfe) {
			return -1;
		}
		
		if(ur.getID() != l.getIDlocatore())
			return -1;
		if(!ur.getNome().equals(nome)) 
			return -1;
		if(!ur.getRuolo().equals(ruolo))
			return -1;
	
		
		return 0;
	}
	
	public synchronized Locale cercaLocale(int iDlocale) {
		GestoreDB gdb = GestoreDB.getInstance();
		
		return gdb.getLocale(iDlocale);
	}

	public synchronized List<Prenotazione> cercaPrenotazioni(int iDlocale) {
		GestoreDB gdb = GestoreDB.getInstance();
		return gdb.getPrenotazioniLocale(iDlocale);
	}


	public boolean rimuoviLocale(int IDlocale) {
		GestoreDB gdb = GestoreDB.getInstance();
		List<Prenotazione> list;
		list = gdb.getPrenotazioniLocale(IDlocale);
		for(int i = 0; i < list.size(); i++) {
			if(Data.dataToString(Data.getData()).compareTo(Data.dataToString(list.get(i).getDataEnd())) < 0) {
				return false;
			}
		}
		
		int IDlocatore = gdb.rimuoviLocale(IDlocale);
		
		gdb.decrementaCampo(IDlocatore, "nLocali");
		
		return true;
	}

	public void convalidaLocale(int IDlocale, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		Locale l = gdb.getLocale(IDlocale);
		
		if(ruolo.equals("Scout")) {
			int convLegale = l.getConvalidaLegale();
			gdb.convalidaLocale(IDlocale, ruolo, convLegale == 1, true);
			
			Notifica n;
			String testo = "Il locale è stato convalidato dallo Scout";
			
			//invia notifica allo Scout
			n = new Notifica(IDlocale, "Locale", l.getIDScout(), l.getIDlocatore(), testo, Data.getData(), false);
			gdb.salvaNotifica(n);
			
		}
		
		if(ruolo.equals("Legale")) {
			int convScout = l.getConvalidaScout();
			gdb.convalidaLocale(IDlocale, ruolo, convScout == 1, true);
			
			Notifica n;
			String testo = "Il locale è stato convalidato dal Legale";
			
			//invia notifica al Legale
			n = new Notifica(IDlocale, "Locale", l.getIDLegale(), l.getIDlocatore(), testo, Data.getData(), false);
			gdb.salvaNotifica(n);
		}
		
	}

	public void nonConvalidaLocale(int IDlocale, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		gdb.convalidaLocale(IDlocale, ruolo, false, false);
		
		Locale l = gdb.getLocale(IDlocale);
		
		if(ruolo.equals("Scout")) {		
			Notifica n;
			String testo = "Il locale non è stato convalidato dallo Scout";
			
			//invia notifica allo Scout
			n = new Notifica(IDlocale, "Locale", l.getIDScout(), l.getIDlocatore(), testo, Data.getData(), false);
			gdb.salvaNotifica(n);
			
		}
		
		if(ruolo.equals("Legale")) {			
			Notifica n;
			String testo = "Il locale non è stato convalidato dal Legale";
			
			//invia notifica al Legale
			n = new Notifica(IDlocale, "Locale", l.getIDLegale(), l.getIDlocatore(), testo, Data.getData(), false);
			gdb.salvaNotifica(n);
		}
		
	}



}
