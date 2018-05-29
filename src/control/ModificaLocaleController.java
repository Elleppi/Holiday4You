package control;

import java.util.Arrays;
import java.util.List;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import feature.GestoreNotifiche;
import libreria.Data;
import libreria.Locale;
import libreria.Notifica;
import libreria.Prenotazione;

public class ModificaLocaleController {
	
	private static ModificaLocaleController instance;

	public ModificaLocaleController() {

	}
	
	public static ModificaLocaleController getInstance() {
		if (instance == null)
            instance = new ModificaLocaleController();
        
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
	
	public synchronized boolean modificaLocale(int IDlocale, int nBagni, int persone,
			int prezzo, boolean animali, boolean postoAuto, boolean giardino, String descrizione) {
		GestoreDB gdb = GestoreDB.getInstance();
		
		List<Prenotazione> list;
		list = gdb.getPrenotazioniLocale(IDlocale);
		for(int i = 0; i < list.size(); i++) {
			if(Data.dataToString(Data.getData()).compareTo(Data.dataToString(list.get(i).getDataEnd())) < 0) {
				return false;
			}
		}
		
		Locale l = gdb.getLocale(IDlocale);
		
		l.setID(IDlocale);
		l.setnBagni(nBagni);
		l.setPersoneMax(persone);
		l.setPrezzoANotte(prezzo);
		l.setAnimali(animali);
		l.setPostoAuto(postoAuto);
		l.setGiardino(giardino);
		l.setDescrizione(descrizione);
		
		int IDlocatore = gdb.modificaLocale(l);
		
		Notifica n;
		String testo = "Le modifiche ad un locale devono essere convalidate";
		
		int IDscout = 0; 
		IDscout = l.getIDScout();
		
		//invia notifica allo Scout
		n = new Notifica(IDlocale, "Locale", IDlocatore, IDscout, testo, Data.getData(), false);
		gdb.salvaNotifica(n);
		
		return true;
	}
	

}
