package control;

import java.util.ArrayList;
import java.util.List;
import libreria.Data;
import libreria.Prenotazione;

public class PrenotazioniLocatoreController {
	
	private static PrenotazioniLocatoreController instance;
	
	public static PrenotazioniLocatoreController getInstance() {
        if (instance == null)
            instance = new PrenotazioniLocatoreController();
        return instance;
    }
	
	private PrenotazioniLocatoreController() {
		
	}
	
	public List<Prenotazione> getPrenotazioni(int IDlocatore) {
		List<Prenotazione> listPre;
		
		//listPre = GestoreDB.getPrenotazioni(IDlocatore);
		
		listPre = new ArrayList<>();
		
		Prenotazione p1 = new Prenotazione();
		Prenotazione p2 = new Prenotazione();
		
		p1.setIdPrenotazione(2);
		p1.setIdLocale(5);
		p1.setDataPrenotazione(Data.stringInData("20151028"));
		
		p2.setIdPrenotazione(3);
		p2.setIdLocale(1);
		p2.setDataPrenotazione(Data.stringInData("20160610"));
		
		listPre.add(p1);
		listPre.add(p2); 
		
		return listPre;
	}

	public List<Prenotazione> getPrenotazioniFuture(List<Prenotazione> listPrenotazioni) {
		List<Prenotazione> listFuture = new ArrayList<Prenotazione>();
		
		for(int i=0; i<listPrenotazioni.size(); i++) 
			if(Data.dataToString(listPrenotazioni.get(i).getDataStart()).compareTo(Data.dataToString(Data.getData())) > 0)
				listFuture.add(listPrenotazioni.get(i));
		
		return listFuture;
	}
	
	public List<Prenotazione> getPrenotazioniPassate(List<Prenotazione> listPrenotazioni) {
		List<Prenotazione> listPassate = listPrenotazioni;
		
	/*	for(int i=0; i<listPrenotazioni.size(); i++) 
			if(Data.dataToString(listPrenotazioni.get(i).getDataEnd()).compareTo(Data.dataToString(Data.getData())) < 0)
				listPassate.add(listPrenotazioni.get(i));*/
		
		return listPassate;
	}
	
	public List<Prenotazione> getPrenotazioniPresenti(List<Prenotazione> listPrenotazioni) {
		List<Prenotazione> listPresenti = new ArrayList<Prenotazione>();
		
		for(int i=0; i<listPrenotazioni.size(); i++) 
			if(Data.dataToString(listPrenotazioni.get(i).getDataStart()).compareTo(Data.dataToString(Data.getData())) < 0 && 
					Data.dataToString(listPrenotazioni.get(i).getDataEnd()).compareTo(Data.dataToString(Data.getData())) > 0)
				listPresenti.add(listPrenotazioni.get(i));
		
		return listPresenti;
	}

}
