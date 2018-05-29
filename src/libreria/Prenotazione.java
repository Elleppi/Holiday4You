package libreria;

import java.util.ArrayList;
import java.util.List;

import attori.Partecipante;

public class Prenotazione {
	
	private int iDprenotazione;
	private int iDlocatario;
	private int iDlocale;
	private Locale locale;
	private List<Partecipante> partecipanti;
	private int nPartecipanti;
	private Data dataStart;
	private Data dataEnd;
	private Data dataPrenotazione;
	private String note;
	private int convalidaLocatore;
	private int convalidaLegale;
	private boolean pagata;
	
	public Prenotazione() {
		this.iDprenotazione = 0;
		this.iDlocatario = 0;
		this.iDlocale = 0;
		this.locale = null;
		this.partecipanti = new ArrayList<>();
		this.nPartecipanti = 0;
		this.dataStart = null;
		this.dataEnd = null;
		this.dataPrenotazione = null;
		this.note = null;
		this.convalidaLocatore = 0;
		this.convalidaLegale = 0;
		this.pagata = false;
	}
	
	public Prenotazione(int IDlocatario, int IDlocale, List<Partecipante> partecipanti, Data dataStart,
			Data dataEnd, Data dataPrenotazione, String note) {
		super();
		this.iDlocatario = IDlocatario;
		this.iDlocale = IDlocale;
		this.partecipanti = partecipanti;
		this.dataStart = dataStart;
		this.dataEnd = dataEnd;
		this.dataPrenotazione = dataPrenotazione;
		this.note = note;
		
	}
	
	/**
	 * @return the iD
	 */
	public int getIdPrenotazione() {
		return iDprenotazione;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setIdPrenotazione(int iD) {
		this.iDprenotazione = iD;
	}

	/**
	 * @return the iDlocatario
	 */
	public int getIdLocatario() {
		return iDlocatario;
	}

	/**
	 * @param iDlocatario the iDlocatario to set
	 */
	public void setIdLocatario(int iDlocatario) {
		this.iDlocatario = iDlocatario;
	}

	/**
	 * @return the iDlocale
	 */
	public int getIdLocale() {
		return this.iDlocale;
	}

	/**
	 * @param iDlocale the iDlocale to set
	 */
	public void setIdLocale(int iDlocale) {
		this.iDlocale = iDlocale;
	}

	/**
	 * @return the partecipanti
	 */
	public List<Partecipante> getPartecipanti() {
		return partecipanti;
	}

	/**
	 * @param partecipanti the partecipanti to set
	 */
	public void setPartecipanti(List<Partecipante> partecipanti) {
		this.partecipanti = partecipanti;
	}

	/**
	 * @return the dataStart
	 */
	public Data getDataStart() {
		return dataStart;
	}

	/**
	 * @param dataStart the dataStart to set
	 */
	public void setDataStart(Data dataStart) {
		this.dataStart = dataStart;
	}

	/**
	 * @return the dataEnd
	 */
	public Data getDataEnd() {
		return dataEnd;
	}

	/**
	 * @param dataEnd the dataEnd to set
	 */
	public void setDataEnd(Data dataEnd) {
		this.dataEnd = dataEnd;
	}

	/**
	 * @return the dataPrenotazione
	 */
	public Data getDataPrenotazione() {
		return dataPrenotazione;
	}

	/**
	 * @param dataPrenotazione the dataPrenotazione to set
	 */
	public void setDataPrenotazione(Data dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * @return the convalidaLocatore
	 */
	public int getConvalidaLocatore() {
		return convalidaLocatore;
	}

	/**
	 * @param convalidaLocatore the convalidaLocatore to set
	 */
	public void setConvalidaLocatore(int convalidaLocatore) {
		this.convalidaLocatore = convalidaLocatore;
	}

	/**
	 * @return the convalidaLegale
	 */
	public int getConvalidaLegale() {
		return convalidaLegale;
	}

	/**
	 * @param convalidaLegale the convalidaLegale to set
	 */
	public void setConvalidaLegale(int convalidaLegale) {
		this.convalidaLegale = convalidaLegale;
	}

	/**
	 * @return the pagata
	 */
	public boolean isPagata() {
		return pagata;
	}

	/**
	 * @param pagata the pagata to set
	 */
	public void setPagata(boolean pagata) {
		this.pagata = pagata;
	}

	/**
	 * @return the nPartecipanti
	 */
	public int getnPartecipanti() {
		return nPartecipanti;
	}

	/**
	 * @param nPartecipanti the nPartecipanti to set
	 */
	public void setnPartecipanti(int nPartecipanti) {
		this.nPartecipanti = nPartecipanti;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	

}
