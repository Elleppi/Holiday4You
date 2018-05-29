package control;

import java.util.ArrayList;
import java.util.List;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import libreria.Data;
import libreria.Notifica;

public class NotificheController {
	
	private static NotificheController instance;
	
	private NotificheController() {
		
	}
	
	public static NotificheController getInstance() {
		if(instance == null)
			instance = new NotificheController();
		
		return instance;
	}
	
	/*Ritorna tutte le notifiche del'utente*/
	public synchronized List<Notifica> caricaNotifiche(String IDutenteRegistrato, String nome, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		List<Notifica> n;
		UtenteRegistrato ur;
		
		try{
			ur = gdb.datiUtenteRegistrato(Integer.parseInt(IDutenteRegistrato));
		} catch(NumberFormatException nfe) {
			return null;
		}
		
		n = gdb.caricaNotifiche(Integer.parseInt(IDutenteRegistrato));
		
		return n;
	}
	
	/*Ritorna una lista di stringhe contenenti alcuni campi delle notifiche ricevute
	 * Il primo elemento della lista contiene l'IDoggetto
	 * Il secondo elemento contiene il tipo di notifica
	 * Il terzo elemento contiene la stringa visualizzata*/
	public synchronized List<String> setRicevute(List<Notifica> listaNotifiche, String iDutente_registrato) {
		List<String> l = new ArrayList<>();
		String s = "";
		
		for(int i=0; i<listaNotifiche.size(); i++) {
			if(listaNotifiche.get(i).getIDdestinatario() == Integer.parseInt(iDutente_registrato)) {
				s = "Tipo " + listaNotifiche.get(i).getCategoria() + "; "
						+ "ricevuta il: " + Data.formattaData(listaNotifiche.get(i).getDataInvioNotifica()) + "; ";
				if(listaNotifiche.get(i).getTesto() != null && listaNotifiche.get(i).getTesto().length() > 1)
					s += listaNotifiche.get(i).getTesto() + "; ";
				s += "letta: ";
				if(listaNotifiche.get(i).isLetta())
					s += "sì";
				else
					s += "no";
				
				l.add(String.valueOf(listaNotifiche.get(i).getIDoggetto()));
				l.add(listaNotifiche.get(i).getCategoria());
				l.add(s);
				
			}
		}
		
		return l;
	}
	
	/*Ritorna una lista di stringhe contenenti alcuni campi delle notifiche spedite
	 * Il primo elemento della lista contiene l'IDoggetto
	 * Il secondo elemento contiene il tipo di notifica
	 * Il terzo elemento contiene la stringa visualizzata*/
	public synchronized List<String> setSpedite(List<Notifica> listaNotifiche, String iDutente_registrato) {
		List<String> l = new ArrayList<>();
		String s = "";
		
		for(int i=0; i<listaNotifiche.size(); i++) {
			if(listaNotifiche.get(i).getIDmittente() == Integer.parseInt(iDutente_registrato)) {
				s = "Tipo " + listaNotifiche.get(i).getCategoria() + "; "
						+ "spedita il: " + Data.formattaData(listaNotifiche.get(i).getDataInvioNotifica()) + "; ";
				
				l.add(String.valueOf(listaNotifiche.get(i).getIDoggetto()));
				l.add(listaNotifiche.get(i).getCategoria());
				l.add(s);
			}
		}
		
		return l;
	}

}
