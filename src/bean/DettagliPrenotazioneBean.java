package bean;

import control.DettagliPrenotazioneController;
import libreria.Data;
import libreria.Prenotazione;

public class DettagliPrenotazioneBean {
	private Prenotazione p;
	private String ruolo;
	private int errore;
	
	public Prenotazione getP() {
		return p;
	}

	public void setP(Prenotazione p) {
		this.p = p;
	}

	public DettagliPrenotazioneBean() {
		this.p = null;
		this.ruolo = null;
		errore = 0;
	}
	
	/**
	 * @return the errore
	 */
	public int getErrore() {
		return errore;
	}

	/**
	 * @param errore the errore to set
	 */
	public void setErrore(int errore) {
		this.errore = errore;
	}

	public String getIndirizzoLocale() {
		return this.p.getLocale().getIndirizzo() + " (" + getProvinciaLocale() + ")";
	}
	
	public String getProvinciaLocale() {
		return this.p.getLocale().getProvincia();
	}
	
	public String getPrezzoANotteLocale() {
		return String.valueOf(this.p.getLocale().getPrezzoANotte()) + " euro";
	}
	
	public String getDataStart() {
		return Data.formattaData(this.p.getDataStart());
	}
	
	public String getDataEnd() {
		return Data.formattaData(this.p.getDataEnd());
	}
	
	public String getDataPrenotazione() {
		return Data.formattaData(this.p.getDataPrenotazione());
	}
	
	public String getNote() {
		if(!this.p.getNote().equals("NULL"))
			return this.p.getNote();
		else
			return "";
	}
	
	public String getConvalidaLocatore() {
		if(this.p.getConvalidaLocatore() == 0)
			return "non ancora convalidato";
		else if(this.p.getConvalidaLocatore() == 1)
			return "convalidato";
		else
			return "non convalidato";
	}
	
	public String getConvalidaLegale() {
		if(this.p.getConvalidaLegale() == 0)
			return "non ancora convalidato";
		else if(this.p.getConvalidaLegale() == 1)
			return "convalidato";
		else
			return "non convalidato";
	}
	
	public String getPagata() {
		if(this.p.isPagata())
			return "sì";
		else
			return "no";
	}
	
	public boolean isFutura() {
		if(Data.dataToString(this.p.getDataStart()).compareTo(Data.dataToString(Data.getData())) > 0)
			return true;
		else
			return false;
	}
	
	public String getDatiPartecipanti() {
		String s = "";
		
		if(this.p.getPartecipanti().size() == 0)
			return "Non ci sono partecipanti";
		
		String nome, cognome, cf;
		
		for(int i=0; i<this.p.getPartecipanti().size(); i++) {
			nome = this.p.getPartecipanti().get(i).getNome();
			cognome = this.p.getPartecipanti().get(i).getCognome();
			cf = this.p.getPartecipanti().get(i).getCodiceFiscale();
			
			s += "Nome: " + nome + "<br>Cognome: " + cognome + "<br>Codice Fiscale: "
					+ cf + "<br><br>";
		}
		
		return s;
	}
	
	public boolean isTimer() {
		DettagliPrenotazioneController dpc = DettagliPrenotazioneController.getInstance();
		
		return dpc.getTimer(this.ruolo, this.p);
	}
	
	public boolean isConvalidaLegale() {
		if(this.p.getConvalidaLegale() == 1 || this.p.getConvalidaLegale() == 0)
			return true;
		else if(this.p.getConvalidaLegale() == 2)
			return false;
		
		return false;
	}
	
	public boolean isConvalidaLocatore() {
		if(this.p.getConvalidaLocatore() == 1)
			return true;
		if(this.p.getConvalidaLocatore() == 2)
			return false;
		
		return false;
	}
	
	public void convalidaPrenotazione(String ruolo, int IDutente) {
		DettagliPrenotazioneController dpc = DettagliPrenotazioneController.getInstance();
		
		dpc.convalidaPrenotazione(ruolo, true, this.p, IDutente);
	}
	
	public void nonConvalidaPrenotazione(String ruolo, int IDutente) {
		DettagliPrenotazioneController dpc = DettagliPrenotazioneController.getInstance();
		
		dpc.convalidaPrenotazione(ruolo, false, this.p, IDutente);
	}
	
	public Prenotazione getPrenotazione() {
		return this.p;
	}

	public void eliminaPrenotazione() {
		DettagliPrenotazioneController dpc = DettagliPrenotazioneController.getInstance();
		
		dpc.annullaPrenotazione(this.p);
	}
	
	public void loadPrenotazione(String IDprenotazione, String IDutente_registrato, String nome, String ruolo) {
		DettagliPrenotazioneController dpc = DettagliPrenotazioneController.getInstance();
		
		Prenotazione p1 = dpc.caricaDettagliPrenotazione(IDprenotazione, IDutente_registrato, nome, ruolo);
		
		if(p1 == null)
			this.errore = -1;
		else {
			this.p = p1;
			this.ruolo = ruolo;
		}
		
	}

}
