package bean;

import java.util.List;

import control.DettagliLocaleController;
import control.ModificaLocaleController;
import libreria.Data;
import libreria.Locale;
import libreria.Prenotazione;

public class DettagliLocaleBean {
	private Locale l;
	private List<Prenotazione> p;
	private int IDutenteRegistrato;
	
	public DettagliLocaleBean() {
		
	}
	
	/**
	 * @return the iDlocatore
	 */
	public String getIDutenteRegistrato() {
		return String.valueOf(this.IDutenteRegistrato);
	}

	/**
	 * @param iDutenteRegistrato the iDutenteRegistrato to set
	 */
	public void setIDutenteRegistrato(int iDutenteRegistrato) {
		IDutenteRegistrato = iDutenteRegistrato;
	}
	
	/**
	 * @return the iD
	 */
	public String getIDlocatore() {
		return String.valueOf(this.l.getIDlocatore());
	}
	
	/**
	 * @return the l
	 */
	public Locale getL() {
		return this.l;
	}
	
	/**
	 * @return the iD
	 */
	public String getID() {
		return String.valueOf(this.l.getID());
	}


	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return "INDIRIZZO: " + this.l.getIndirizzo();
	}

	/**
	 * @return the città
	 */
	public String getCittà() {
		return "CITTA': " + this.l.getCittà();
	}
	
	public String getProvincia() {
		return "PROVINCIA: " + this.l.getProvincia();
	}

	/**
	 * @return the mQ
	 */
	public String getmQ() {
		return "METRI QUADRI: " + String.valueOf(this.l.getmQ());
	}

	/**
	 * @return the nBagni
	 */
	public String getnBagni() {
		return "NUMERO BAGNI: " + String.valueOf(this.l.getnBagni());
	}

	/**
	 * @return the personeMax
	 */
	public String getPersoneMax() {
		return "NUMERO PERSONE AMMESSE: " + String.valueOf(this.l.getPersoneMax());
	}

	/**
	 * @return the animali
	 */
	public String getAnimali() {
		String s = "ANIMALI: ";
		
		if(this.l.getAnimali())
			s += "ammessi";
		else
			s += "non ammessi";
		
		return s;
	}

	/**
	 * @return the giardino
	 */
	public String getGiardino() {
		String s = "GIARDINO: ";
		if(this.l.getGiardino())
			s += "sì";
		else
			s += "no";
		
		return s;
	}

	/**
	 * @return the postoAuto
	 */
	public String getPostoAuto() {
		String s = "POSTO AUTO: ";
		
		if(this.l.getPostoAuto())
			s += "sì";
		else
			s += "no";
		
		return s;
	}

	/**
	 * @return the prezzoANotte
	 */
	public String getPrezzoANotte() {
		return "PREZZO A NOTTE: " + this.l.getPrezzoANotte() + " euro";
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return "DESCRIZIONE: " + this.l.getDescrizione();
	}

	/**
	 * @return the dataInserimento
	 */
	public String getDataInserimento() {
		String s = "DATA INSERIMENTO LOCALE: ";
		Data d = this.l.getDataInserimento();
		
		return s += String.valueOf(d.getGiorno()) + "/" + 
		String.valueOf(d.getMese()) + "/" + String.valueOf(d.getAnno());
	}

	/**
	 * @return the convalidaScout
	 */
	public String getConvalidaScout() {
		String s = "CONVALIDA SCOUT: ";
		
		if(this.l.getConvalidaScout() == 1)
			s += "convalidato";
		else
			s += "non convalidato";
		
		return s;
	}

	/**
	 * @return the convalidaLegale
	 */
	public String getConvalidaLegale() {
		String s = "CONVALIDA LEGALE: ";
		
		if(this.l.getConvalidaLegale() == 1)
			s += "convalidato";
		else
			s += "non convalidato";
		
		return s;
	}
	
	/**
	 * @return the iD
	 */
	public String getIDScout() {
		return String.valueOf(this.l.getIDScout());
	}
	
	/**
	 * @return the iD
	 */
	public String getIDLegale() {
		return String.valueOf(this.l.getIDLegale());
	}

	/**
	 * @return the attivo
	 */
	public String getAttivo() {
		String s = "LOCALE ATTIVO: ";
		
		if(this.l.isAttivo())
			s += "attivo";
		else
			s += "non attivo";
		
		return s;
	}

	/**
	 * @return the p
	 */
	public List<Prenotazione> getP() {
		return p;
	}

	public String getPrenotazioni() {
		Data start; 
		Data end; 
		String s = "DATE PRENOTAZIONI FUTURE:<br>";
		
		for(int i=0; i<this.p.size(); i++) {
			if(Data.dataToString(this.p.get(i).getDataStart()).compareTo(Data.dataToString(Data.getData())) > 0) {
				start = this.p.get(i).getDataStart();
				end = this.p.get(i).getDataEnd();
				s += start.getGiorno() + "/" + start.getMese() + "/" + start.getAnno() + " -> " +
						end.getGiorno() + "/" + end.getMese() + "/" + end.getAnno() + "<br>";
			}
		}
		
		return s;
	}
	
	public void setLocale(String IDlocale) {
		DettagliLocaleController dlc = DettagliLocaleController.getInstance();
		
		this.l = dlc.cercaLocale(Integer.parseInt(IDlocale));
		this.p = dlc.cercaPrenotazioni(Integer.parseInt(IDlocale));
	}
	
	
	public boolean rimuoviLocale(String IDlocale) {
		DettagliLocaleController dlc = DettagliLocaleController.getInstance();
		
		if(dlc.rimuoviLocale(Integer.parseInt(IDlocale)))
			return true;
		else
			return false;
	}
	
	public void convalidaLocale(String IDlocale, String ruolo) {
		DettagliLocaleController dlc = DettagliLocaleController.getInstance();
		
		dlc.convalidaLocale(Integer.parseInt(IDlocale), ruolo);
		
	}
	
	public void nonConvalidaLocale(String IDlocale, String ruolo) {
		DettagliLocaleController dlc = DettagliLocaleController.getInstance();
		
		dlc.nonConvalidaLocale(Integer.parseInt(IDlocale), ruolo);
		
	}

	public int checkParameters(String id, String nome, String ruolo, String idlocale) {
		
		DettagliLocaleController mlc = DettagliLocaleController.getInstance();
		
		return mlc.checkParameters(id, nome, ruolo, idlocale);
		
	}
	
}
