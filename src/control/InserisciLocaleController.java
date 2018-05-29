package control;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import feature.GestoreNotifiche;
import libreria.Data;
import libreria.Locale;
import libreria.Notifica;

public class InserisciLocaleController {
	
	private static InserisciLocaleController instance;

	public InserisciLocaleController() {

	}
	
	
	public static InserisciLocaleController getInstance() {
		if (instance == null)
            instance = new InserisciLocaleController();
        
		return instance;
	}
	
	
	public synchronized int checkParameters(String id, String nome, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		UtenteRegistrato ur;
		
		if(id == null || nome == null || ruolo == null)
			return -1;
		
		try{
			ur = gdb.datiUtenteRegistrato(Integer.parseInt(id));
		} catch(NumberFormatException nfe) {
			return -1;
		}
		
		if(!ur.getNome().equals(nome)) 
			return -1;
		if(!ur.getRuolo().equals(ruolo))
			return -1;
	
		
		return 0;
	}
	
	
	public synchronized int aggiungiLocale(int IDlocatore, String indirizzo, int numCivico, String citta, String provincia, int mQ, int nBagni, int persone,
			int prezzo, boolean animali, boolean postoAuto, boolean giardino, String descrizione) {
		GestoreDB gdb = GestoreDB.getInstance();
		
		int err = 0;
		
		
		//assegna il locale ad un legale
		int IDlegale = GestoreNotifiche.assegnaLegale(0);
		//assegna il locale ad uno scout
		int IDscout = GestoreNotifiche.assegnaScout();
		
		indirizzo = indirizzo + ", " + numCivico;
		
		Locale l = new Locale(IDlocatore, indirizzo, citta, provincia, mQ, nBagni, persone, animali, giardino, postoAuto, prezzo,
				descrizione, Data.getData(), IDscout, IDlegale);
		
		int IDlocale = gdb.salvaLocale(l);
		
		gdb.incrementaCampo(IDlocatore, "nLocali");
		
		Notifica n1;
		String testo = "Un nuovo locale deve essere convalidato";
		
		//invia notifica al Legale
		n1 = new Notifica(IDlocale, "Locale", IDlocatore, IDlegale, testo, Data.getData(), false);
		gdb.salvaNotifica(n1);
		
		Notifica n2;
		
		//invia notifica allo Scout
		n2 = new Notifica(IDlocale, "Locale", IDlocatore, IDscout, testo, Data.getData(), false);
		gdb.salvaNotifica(n2);
		
		return err;
	}
	

}
