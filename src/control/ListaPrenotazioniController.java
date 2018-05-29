package control;

import java.util.ArrayList;
import java.util.List;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import libreria.Data;
import libreria.Prenotazione;

public class ListaPrenotazioniController {
	
	private static ListaPrenotazioniController instance;
	String today = Data.dataToString(Data.getData());
	
	private ListaPrenotazioniController() {
		
	}
	
	public static ListaPrenotazioniController getInstance() {
		if(instance == null)
			instance = new ListaPrenotazioniController();
		
		return instance;
	}
	
	public synchronized List<Prenotazione> setListaPrenotazioni(String IDutente_registrato, String nome, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		UtenteRegistrato ur;
		
		try{
			ur = gdb.datiUtenteRegistrato(Integer.parseInt(IDutente_registrato));
		} catch(NumberFormatException nfe) {
			return null;
		}
		
		if(ur == null || !ur.getNome().equals(nome) || !ur.getRuolo().equals(ruolo))
			return null;

		return gdb.caricaPrenotazioni(IDutente_registrato, ruolo);
	}

	public synchronized List<String> setListaPassate(List<Prenotazione> listaPrenotazioni) {
		String dataEnd, formatted;
		List<String> prenotazioni = new ArrayList<>();
		
		for(int i=0; i<listaPrenotazioni.size(); i++) {
			dataEnd = Data.dataToString(listaPrenotazioni.get(i).getDataEnd());
			
			if(dataEnd.compareTo(today) < 0) {
				formatted = "Data prenotazione: " + Data.formattaData((listaPrenotazioni.get(i).getDataPrenotazione())) + ";" +
						" Data inizio affitto: " + Data.formattaData(listaPrenotazioni.get(i).getDataStart()) + ";" + 
						" Data fine affitto: " + Data.formattaData(listaPrenotazioni.get(i).getDataEnd()) + ";" + 
						" Numero partecipanti: " + String.valueOf(listaPrenotazioni.get(i).getnPartecipanti()) + ";";
				
				prenotazioni.add(String.valueOf(listaPrenotazioni.get(i).getIdPrenotazione()));
				prenotazioni.add(formatted);
			}
				
		}
		return prenotazioni;
	}

	public synchronized List<String> setListaPresenti(List<Prenotazione> listaPrenotazioni) {
		String dataStart, dataEnd, formatted;
		List<String> prenotazioni = new ArrayList<>();
		
		for(int i=0; i<listaPrenotazioni.size(); i++) {
			dataStart = Data.dataToString(listaPrenotazioni.get(i).getDataStart());
			dataEnd = Data.dataToString(listaPrenotazioni.get(i).getDataEnd());
			
			if(dataStart.compareTo(today) < 0 && dataEnd.compareTo(today) > 0) {
				formatted = "Data prenotazione: " + Data.formattaData(listaPrenotazioni.get(i).getDataPrenotazione()) + ";" +
						" Data inizio affitto: " + Data.formattaData(listaPrenotazioni.get(i).getDataStart()) + ";" + 
						" Data fine affitto: " + Data.formattaData(listaPrenotazioni.get(i).getDataEnd()) + ";" + 
						" Numero partecipanti: " + String.valueOf(listaPrenotazioni.get(i).getnPartecipanti()) + ";";
				
				prenotazioni.add(String.valueOf(listaPrenotazioni.get(i).getIdPrenotazione()));
				prenotazioni.add(formatted);
			}
				
		}
		return prenotazioni;
	}

	public synchronized List<String> setListaFuture(List<Prenotazione> listaPrenotazioni) {
		String dataStart, formatted;
		List<String> prenotazioni = new ArrayList<>();
		
		for(int i=0; i<listaPrenotazioni.size(); i++) {
			dataStart = Data.dataToString(listaPrenotazioni.get(i).getDataStart());
			
			if(today.compareTo(dataStart) < 0) {
				formatted = "Data prenotazione: " + Data.formattaData(listaPrenotazioni.get(i).getDataPrenotazione()) + ";" +
						" Data inizio affitto: " + Data.formattaData(listaPrenotazioni.get(i).getDataStart()) + ";" + 
						" Data fine affitto: " + Data.formattaData(listaPrenotazioni.get(i).getDataEnd()) + ";" + 
						" Numero partecipanti: " + String.valueOf(listaPrenotazioni.get(i).getnPartecipanti()) + ";";
				
				prenotazioni.add(String.valueOf(listaPrenotazioni.get(i).getIdPrenotazione()));
				prenotazioni.add(formatted);
				
				//System.out.println(this.prenotazioni.get(i));
			}
				
		}
		return prenotazioni;
	}
	
}
