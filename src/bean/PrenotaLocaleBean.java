package bean;
import java.util.List;

import attori.*;
import control.PrenotaLocaleController;
import libreria.Data;
import libreria.Prenotazione;

public class PrenotaLocaleBean {
	
	private Prenotazione p;

	private int errore;
	
	private static String errPrenotazioneEsistente = "Esiste già una prenotazione esistente nell'intorno di quella data";
	private static String errTroppePersone = "Il numero di persone inserito ha superato il limite imposto dal Locatore";
	private static String errPartecipantiUguali = "Hai inserito 2 partecipanti uguali"; 
	
	public PrenotaLocaleBean() {
		this.p = new Prenotazione();
		this.errore = 0;
	}
	
	/*Costruttore utilizzato per jUnit*/
	public PrenotaLocaleBean(int iDlocatario, int iDlocale, Data dataStart, Data dataEnd, String note, List<Partecipante> partecipanti) {
		this.p.setIdLocatario(iDlocatario);
		this.p.setIdLocale(iDlocale);
		this.p.setDataStart(dataStart);
		this.p.setDataEnd(dataEnd);
		this.p.setNote(note);
		this.p.setPartecipanti(partecipanti);
	}
	
	/**
	 * @return the errore
	 */
	public int getErrore() {
		return this.errore;
	}

	/**
	 * @param errore the errore to set
	 */
	public void setErrore(int errore) {
		this.errore = errore;
	}

	/**
	 * @return the dataStart
	 */
	public Data getDataStart() {
		return this.p.getDataStart();
	}

	/**
	 * @param dataStart the dataStart to set
	 */
	public void setDataStart(String dataStart) {
		
		Data d = new Data();
		
		d.setAnno(Integer.parseInt(dataStart.substring(0, 4)));
		d.setMese(Integer.parseInt(dataStart.substring(5, 7)));
		d.setGiorno(Integer.parseInt(dataStart.substring(8)));
		
		this.p.setDataStart(d);
	}

	/**
	 * @return the dataEnd
	 */
	public Data getDataEnd() {
		return this.p.getDataEnd();
	}

	/**
	 * @param dataEnd the dataEnd to set
	 */
	public void setDataEnd(String dataEnd) {
		Data d = new Data();
		
		d.setAnno(Integer.parseInt(dataEnd.substring(0, 4)));
		d.setMese(Integer.parseInt(dataEnd.substring(5, 7)));
		d.setGiorno(Integer.parseInt(dataEnd.substring(8)));
		
		this.p.setDataEnd(d);
	}

	/*Aggiunge i dati relativi ad un partecipante nella listaPartecipanti */
	public void addPartecipante(String nome, String cognome, String codiceFiscale) {
		Partecipante p = new Partecipante(nome, cognome, codiceFiscale);
		this.p.getPartecipanti().add(p);
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return this.p.getNote();
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.p.setNote(note);
	}

	/**
	 * @return the iDlocatario
	 */
	public int getiDlocatario() {
		return p.getIdLocatario();
	}

	/**
	 * @param iDlocatario the iDlocatario to set
	 */
	public void setiDlocatario(int iDlocatario) {
		this.p.setIdLocatario(iDlocatario);
	}

	/**
	 * @return the iDlocale
	 */
	public int getiDlocale() {
		return this.p.getIdLocale();
	}

	/**
	 * @param iDlocale the iDlocale to set
	 */
	public void setiDlocale(int iDlocale) {
		this.p.setIdLocale(iDlocale);
	}

	public String errore(int e) {
		String errore = "";
		
		switch (e) {
		case -3:
			errore = errPrenotazioneEsistente;
			break;
		case -4:
			errore = errTroppePersone;
			break;
		case -7:
			errore = errPartecipantiUguali;
			break;
		}
		
		return errore;
	}
	
	public void check(int ID, String nome, String ruolo) {
		PrenotaLocaleController plc = PrenotaLocaleController.getInstance();
		
		if(plc.verificaUtente(ID, nome, ruolo) < 0)
			this.errore = -1;
	}
	
	public int convalidaPrenotazione() {
		PrenotaLocaleController plc = PrenotaLocaleController.getInstance();
		
		return plc.prenota(this.p);
	}
	
}
