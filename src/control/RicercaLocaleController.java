package control;

import java.util.ArrayList;
import java.util.List;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import libreria.Locale;

public class RicercaLocaleController {
	private static RicercaLocaleController instance;
	
	private RicercaLocaleController() {
		
	}
	
	public static RicercaLocaleController getInstance() {
		if(instance == null)
			instance = new RicercaLocaleController();
		
		return instance;
	}

	public synchronized String ricercaLocali(Locale locale) {
		GestoreDB gdb = GestoreDB.getInstance();
		List<Locale> l = new ArrayList<>();
		
		l = gdb.ricercaLocali(locale);
		String s = "";
		
		for(int i=0; i<l.size(); i++) {
			s += l.get(i).getID() + "|Indirizzo: " + l.get(i).getIndirizzo() + ", " + l.get(i).getCittà() + " ("
					+ l.get(i).getProvincia() + "); "
					+ "prezzo a notte: " + l.get(i).getPrezzoANotte() + " euro; "
					+ "persone max: " + l.get(i).getPersoneMax() + "-";
		}
		
		return s;
		
	}

	public int verificaUtente(String iD, String nome, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		UtenteRegistrato ur;
		
		try{
			ur = gdb.datiUtenteRegistrato(Integer.parseInt(iD));
		} catch(NumberFormatException nfe) {
			return -1;
		}
		
		if(ur == null || !ur.getNome().equals(nome) || !ur.getRuolo().equals(ruolo))
			return -1;
		else
			return 0;
	} 
	
}
