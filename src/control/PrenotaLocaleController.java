package control;

import java.util.List;

import attori.Partecipante;
import attori.UtenteRegistrato;
import feature.GestoreDB;
import libreria.Data;
import libreria.Locale;
import libreria.Notifica;
import libreria.Prenotazione;

public class PrenotaLocaleController {
	private static PrenotaLocaleController instance;
	
	private PrenotaLocaleController() {
	}
	
	public static PrenotaLocaleController getInstance() {
		if(instance == null)
			instance = new PrenotaLocaleController();
		
		return instance;
	}

	public synchronized int prenota(Prenotazione p) {
		GestoreDB gdb = GestoreDB.getInstance();
		int e = 0;
		
		if(!gdb.verificaDataPrenotazione(p.getIdLocale(), p.getDataStart(), p.getDataEnd(), p.getIdPrenotazione()))
			return -3; 
		
		if(!gdb.verificaNumeroPartecipanti(p.getIdLocale(), p.getPartecipanti().size()))
			return -4;
		
		//controlla se l'utente ha inserito 2 partecipanti uguali
		if(p.getPartecipanti().size() > 1) {
			for(int i=0; i<p.getPartecipanti().size(); i++) {
				for(int j=i+1; j<p.getPartecipanti().size(); j++) {
					if(p.getPartecipanti().get(i).getCodiceFiscale().equals(p.getPartecipanti().get(j).getCodiceFiscale()))
						return -7;
				}
			}
		}
		
		/*p.setDataPrenotazione(Data.getData());
		
		int IDprenotazione = gdb.salvaPrenotazione(p);
		
		Locale l = gdb.getLocale(p.getIdLocale());
		int IDlocatore = l.getIDlocatore();
		
		Notifica n = new Notifica();
		n.setIDoggetto(IDprenotazione);
		n.setCategoria("prenotazione");
		n.setIDmittente(p.getIdLocatario());
		n.setIDdestinatario(IDlocatore);
		n.setDataInvioNotifica(Data.getData());*/
		
		/*Invia notifica al Locatore corrispondente*/
	//	gdb.salvaNotifica(n);
			
		return 0;
		
	}

	public int verificaUtente(int iD, String nome, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		
		UtenteRegistrato ur = gdb.datiUtenteRegistrato(iD);
		if(ur == null || !ur.getNome().equals(nome) || !ur.getRuolo().equals(ruolo))
			return -1;
		else
			return 0;
	} 
	
}
