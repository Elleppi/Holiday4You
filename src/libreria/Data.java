package libreria;

import java.util.Calendar;

public class Data {
	
	private int giorno;
	private int mese;
	private int anno;
	
	
	public Data() {
		
	}
	
	public Data(int giorno, int mese, int anno) {
		super();
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}


	//Ritorna il numero di giorni di differenza tra due date
	public static int giorniDifferenza(Data inizio, Data fine) {
		int nGiorni = 0;
		int annidiff = fine.getAnno() - inizio.getAnno();

		//febbraio è considerato sempre di 28
		int giorniInMese[] = {0,31,28+31,28+62,28+92,28+123,28+153,28+184,28+215,28+245,28+276,28+306};
		
		int giorniInizio = giorniInMese[inizio.getMese() - 1] + inizio.getGiorno();
		int giorniFine = giorniInMese[fine.getMese() - 1] + fine.getGiorno();
		
		nGiorni = 365*annidiff + giorniFine - giorniInizio;
		
		return nGiorni;
	}
	
	/**
	 * @return the giorno
	 */
	public int getGiorno() {
		return giorno;
	}
	/**
	 * @param giorno the giorno to set
	 */
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}
	/**
	 * @return the mese
	 */
	public int getMese() {
		return mese;
	}
	/**
	 * @param mese the mese to set
	 */
	public void setMese(int mese) {
		this.mese = mese;
	}
	/**
	 * @return the anno
	 */
	public int getAnno() {
		return anno;
	}
	/**
	 * @param anno the anno to set
	 */
	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	public static Data getData() {
		
		Data d = new Data();
		Calendar now = Calendar.getInstance();
		
		d.giorno = now.get(Calendar.DAY_OF_MONTH);
		d.mese = now.get(Calendar.MONTH) + 1;
		d.anno = now.get(Calendar.YEAR);
		
		return d;
		
	}
	
	//Ritorna FALSE se "end" < "start" 
	public static boolean correttezzaData(Data start, Data end) {
		String dataStart = dataToString(start); 
		String dataEnd = dataToString(end);
		
		if(dataStart.compareTo(dataEnd) > 0 || dataStart.compareTo(dataToString(getData())) < 0)
			return false;
		
		return true;
	}

	/*Trasforma il tipo di dato Data in Stringa (AAAAMMGG)*/
	public static String dataToString(Data d) {
		String s;
		String g, m, a;
		
		g = String.valueOf(d.getGiorno());
		if(g.length() == 1)
			g = "0" + g;
		
		m = String.valueOf(d.getMese());
		if(m.length() == 1)
			m = "0" + m;
		
		a = String.valueOf(d.getAnno());
		
		s = a + m + g;
		
		return s;
	} 
	
	/*Trasforma una data in formato stringa (AAAAMMGG) in formato Data*/
	public static Data stringInData(String s) {
		Data d = new Data();
		String g, m, a;
		
		a = s.substring(0, 4);
		m = s.substring(4, 6);
		g = s.substring(6);
		
		d.setGiorno(Integer.parseInt(g));
		d.setMese(Integer.parseInt(m));
		d.setAnno(Integer.parseInt(a));
		
		return d;
	}

	/*Verifica che le date richieste non siano all'interno delle date prenotate.
	 * Torna false in caso affermativo, true altrimenti*/
	public static boolean coincidenti(Data startRichiesta, Data endRichiesta, Data startPrenotata, Data endPrenotata) {
		String d1 = dataToString(startRichiesta);
		String d2 = dataToString(endRichiesta);
		String d3 = dataToString(startPrenotata);
		String d4 = dataToString(endPrenotata);
		
		if(d1.compareTo(d4) > 0)
			return false;
		else if(d2.compareTo(d3) < 0)
			return false;
		
		return true;
	}
	
	/*Prende come parametro un oggetto Data
	 * e la trasforma in una stringa nel formato gg/mm/aaaa*/
	public static String formattaData(Data d) {
		
		return String.valueOf(d.getGiorno()) + "/" + String.valueOf(d.getMese()) + "/" + String.valueOf(d.getAnno());
	}
	
	public static Data formattaDataReverse(String s) {
		int g = Integer.parseInt(s.substring(0, s.indexOf("/")));
		s = s.substring(s.indexOf("/") + 1);
		int m = Integer.parseInt(s.substring(0, s.indexOf("/")));
		s = s.substring(s.indexOf("/") + 1);
		int a = Integer.parseInt(s);
		
		Data d = new Data(g, m, a);
		
		return d;
	}
	
	/*Prende come parametro una stringa contenente una data nel formato aaaammgg
	 * e la trasforma in una stringa nel formato gg/mm/aaaa*/
	public String formattaStringData(String data) {
		
		String s = data.substring(6) + "/" + data.substring(4, 6) + "/" + data.substring(0, 4);
		
		return s;
	}
	
}
