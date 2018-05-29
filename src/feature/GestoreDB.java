package feature;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import attori.Partecipante;
import attori.UtenteRegistrato;
import libreria.Data;
import libreria.Locale;
import libreria.Notifica;
import libreria.Prenotazione;

public class GestoreDB {
	
	private static String pathDB;
	private static String usrDB;
	private static String pswDB;
	private static Connection conn;
	private static Statement stat;
	private static ResultSet rs;
	
	private static GestoreDB instance;
	
	public static GestoreDB getInstance() {
		if(instance == null)
			instance = new GestoreDB();
		
		return instance;
	}
	
	private GestoreDB() {
		GestoreDB.pathDB = "jdbc:mysql://localhost:3306/holiday4you";
		GestoreDB.usrDB = "root";
		GestoreDB.pswDB = "04091990";
		GestoreDB.conn = null;
		GestoreDB.stat = null;
		GestoreDB.rs = null;
	}
	
	/*Apre la connessione al DB*/
	private void creaStatement() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(pathDB, usrDB, pswDB);
			stat = (Statement) conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*Trasforma un booleano in valori inseribili nel DB MySQL*/
	public int booleanToDB(boolean b) {
		
		if(b)
			return 1;
		
		else
			return 0;
		
	}
	
	/*Trasforma un valore di ritorno dal DB mySQL in booleano*/
	public static boolean DBtoBoolean(int i) {
		
		if(i == 0)
			return false;
		else
			return true;
	}
	
	/*Salva utente_registrato nel DB*/
	public void salvaUtenteRegistrato(UtenteRegistrato ur) {
		boolean attivo;
		
		if(ur.getRuolo().equals("Locatore") || ur.getRuolo().equals("Locatario"))
			attivo = true;
		else
			attivo = false;
		
		creaStatement();
		
		String query = "INSERT INTO utente_registrato("
				+ "nome,"
				+ "cognome,"
				+ "codiceFiscale,"
				+ "dataNascita,"
				+ "ruolo,"
				+ "username,"
				+ "password, "
				+ "telefono, "
				+ "eMail, "
				+ "attivo) "
				+ "VALUES ("
				+ "'" + ur.getNome() + "',"
			    + "'" + ur.getCognome() + "',"
			    + "'" + ur.getCodiceFiscale() + "',"
			    + "'" + Data.dataToString(ur.getDataNascita()) + "',"
			    + "'" + ur.getRuolo() + "',"
			    + "'" + ur.getUsername() + "',"
			    + "'" + ur.getPassword() + "'"
			  	+ "'" + ur.getnTelefono() + "'"
			    + "'" + ur.geteMail() + "'"
			    + booleanToDB(attivo) + ");";
		
		try {
			stat.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERRORE NELLA QUERY");
			System.out.println("query: " + query);
		}
		
		closeConnection();
		
	}
	
	/*Controlla nel DB se è già presente un utente_registrato 
	 * con lo stesso CF e stesso ruolo*/
	public int verificaCF(String cf, String ruolo) {
		int e = 0;
		
		creaStatement();
		
		String query = "SELECT * "
				+ "FROM utente_registrato "
				+ "WHERE codiceFiscale = '" + cf + "' AND ruolo = '" + ruolo + "';";
		
		try {
			rs = stat.executeQuery(query);
			
			/*Se è già presente un altro utente con lo stesso CF 
			 * registrato con lo stesso ruolo, ritorna errore
			 */
			
			if(rs.first()) {
				e = 1;
			}
			else
				e = 0;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		closeConnection();
		
		return e;
	}

	/*Verifica nel DB se è già presente un utente_registrato 
	 * con lo stesso username*/
	public int verificaUSR(String username) {
		int e = 0;
		creaStatement();
		
		String query = "SELECT * "
				+ "FROM utente_registrato "
				+ "WHERE username = '" + username + "';";
		
		try {
			rs = stat.executeQuery(query);
			
			/*Se è già presente un altro utente con lo stesso CF 
			 * registrato con lo stesso ruolo, ritorna FALSE
			 */
			if(rs.first()) 
				e = 2;
			else
				e = 0;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		closeConnection();
		
		return e;
	}
	
	/*Chiudi connessione col DB*/
	public void closeConnection() {
		try {
			conn.close();
			stat.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*Verifica se quel locale ha già una prenotazione attiva per quelle date*/
	public boolean verificaDataPrenotazione(int IDlocale, Data dataStart, Data dataEnd, int iDprenotazione) {
		boolean b = true;
		
		creaStatement();
		
		String query = "SELECT dataStart, dataEnd, IDprenotazione "
				+ "FROM prenotazione "
				+ "WHERE IDlocale = " + IDlocale + ";";
		
		try {
			rs = stat.executeQuery(query);
			
			//se non ci sono prenotazioni attive su quel locale, imposta b = true
			if(rs.wasNull()) 
				b = true;
			
			else {
				while(rs.next()) {
					String start = rs.getString("dataStart");
					String end = rs.getString("dataEnd");
					
					if(!Data.coincidenti(dataStart, dataEnd, Data.stringInData(start), Data.stringInData(end)))
						b = true;
					else if(rs.getInt("IDprenotazione") != iDprenotazione)
						b = false;
				}
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}

	/*Verifica se il Locatario ha sforato il numero di partecipanti 
	 * in base a quanto stabilito dal Locatore*/
	public boolean verificaNumeroPartecipanti(int IDlocale, int partecipanti) {
		boolean b = true;
		
		creaStatement();
		
		String query = "SELECT personeMax FROM locale "
				+ "WHERE IDlocale = " + IDlocale + ";";
		
		try {
			rs = stat.executeQuery(query);
			rs.next();
			
			if(partecipanti > rs.getInt("personeMax")) {
				b = false;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		
		return b;
	}

	/*Salva prenotazione nel DB, in attesa di essere approvata dal Locatore.
	 * Ritorna l'IDprenotazione associata nel DB*/
	public int salvaPrenotazione(Prenotazione pre) {
		creaStatement();
		int IDprenotazione = 0;
		
		String query = "INSERT INTO prenotazione("
				+ "IDlocale,"
				+ "IDutente_registrato,"
				+ "nPartecipanti,"
				+ "dataStart,"
				+ "dataEnd,"
				+ "dataPrenotazione,"
				+ "note,"
				+ "convalidaLocatore,"
				+ "convalidaLegale,"
				+ "pagata) "
				+ "VALUES ("
			    + pre.getIdLocale() + ", "
				+ pre.getIdLocatario() + ", "
				+ pre.getPartecipanti().size() + ", "
				+ "'" + Data.dataToString(pre.getDataStart()) + "', "
				+ "'" + Data.dataToString(pre.getDataEnd()) + "', "
				+ "'" + Data.dataToString(pre.getDataPrenotazione()) + "', "
				+ "'" + pre.getNote() + "', "
				+ pre.getConvalidaLocatore() + ", "
				+ pre.getConvalidaLegale() + ", "
				+ booleanToDB(pre.isPagata()) + ");";
		
		try {
			stat.execute(query);
			rs = stat.executeQuery("SELECT IDprenotazione FROM prenotazione");
			rs.last();
			IDprenotazione = rs.getInt("IDprenotazione");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERRORE NELLA QUERY");
			System.out.println("query: " + query);
		}
		
		closeConnection();
		
		if(pre.getPartecipanti().size() > 0)
			salvaPartecipanti(pre.getPartecipanti(), IDprenotazione);
		
		return IDprenotazione;
	}

	/*Salva partecipanti allegati alla prenotazione*/
	public void salvaPartecipanti(List<Partecipante> partecipanti, int IDprenotazione) {
		creaStatement();
		String append;
		
		String query = "INSERT INTO partecipante ("
				+ "IDprenotazione,"
				+ "nome,"
				+ "cognome,"
				+ "codiceFiscale) "
				+ "VALUE ("
				+ IDprenotazione + ",";
		
		for(int i=0; i<partecipanti.size(); i++) {
			append = "";
			append = query + "'" + partecipanti.get(i).getNome() + "',"
					+ "'" + partecipanti.get(i).getCognome() + "',"
					+ "'" + partecipanti.get(i).getCodiceFiscale() + "');";
			try {
				stat.execute(append);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		closeConnection();
	}
	
	/*Torna una lista di locali associati a determinati parametri di ricerca*/
	public List<Locale> ricercaLocali(Locale l) {
		
		List<Locale> listaLocali = new ArrayList<>();
		Locale loc;
		
		creaStatement();
		
		String query = "SELECT * FROM locale WHERE ";
		
		if(l.getProvincia() != null)
			query += "provincia = '" + l.getProvincia() + "' AND ";
		
		query += "mQ >= " + l.getmQ() + " AND "
				+ "nBagni >= " + l.getnBagni() + " AND "
				+ "personeMax >= " + l.getPersoneMax() + " AND ";
		
		if(l.getAnimali())
			query += "animali = " + booleanToDB(l.getAnimali()) + " AND ";
		if(l.getGiardino())
			query += "giardino = " + booleanToDB(l.getGiardino()) + " AND ";
		if(l.getPostoAuto())
			query += "postoAuto = " + booleanToDB(l.getPostoAuto()) + " AND ";
		
		query += "prezzoANotte <= " + l.getPrezzoANotte() + " AND "
			    + "attivo = 1;";

		try {
			rs = stat.executeQuery(query);
			
			while(rs.next()) {
				loc = new Locale(rs.getInt("IDutente_registrato"), rs.getString("indirizzo"), 
						rs.getString("città"), rs.getString("provincia"), rs.getInt("mQ"), rs.getInt("nBagni"), 
						rs.getInt("personeMax"), DBtoBoolean(rs.getInt("animali")), 
						DBtoBoolean(rs.getInt("giardino")), DBtoBoolean(rs.getInt("postoAuto")), 
						rs.getInt("prezzoANotte"), rs.getString("descrizione"),
						Data.stringInData(rs.getString("dataInserimento")), rs.getInt("IDscout"), 
						rs.getInt("IDlegale"));
				loc.setID(rs.getInt("IDlocale"));

				//Aggiungi il locale alla lista
				listaLocali.add(loc);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY !!");
			System.out.println("query: " + query);
			e.printStackTrace();
		}
		
		closeConnection();
		
		return listaLocali;
	}
	
	public int salvaLocale(Locale l) {
		
		int IDlocale = 0;
		creaStatement();
		
		String query = "INSERT INTO locale("
				+ "IDutente_registrato,"
				+ "indirizzo,"
				+ "città,"
				+ "provincia,"
				+ "mQ,"
				+ "nBagni,"
				+ "personeMax,"
				+ "animali,"
				+ "giardino,"
				+ "postoAuto,"
				+ "prezzoANotte,"
				+ "descrizione,"
				+ "dataInserimento,"
				+ "convalidaScout,"
				+ "convalidaLegale,"
				+ "IDscout,"
				+ "IDlegale,"
				+ "attivo) "
				+ "VALUES ("
			    + l.getIDlocatore() + ", "
				+ "'" + l.getIndirizzo() + "', "
				+ "'" + l.getCittà() + "', "
				+ "'" + l.getProvincia() + "', "
				+ l.getmQ() + ", "
				+ l.getnBagni() + ", "
				+ l.getPersoneMax() + ", "
				+ l.getAnimali() + ", "
				+ l.getGiardino() + ", "
				+ l.getPostoAuto() + ", "
				+ l.getPrezzoANotte() + ", "
				+ "'" + l.getDescrizione() + "', "
				+ "'" + Data.dataToString(l.getDataInserimento()) + "', "
				+ l.getConvalidaScout() + ", "
				+ l.getConvalidaLegale() + ", "
				+ l.getIDScout() + ", "
				+ l.getIDLegale() + ", "
				+ booleanToDB(l.isAttivo()) + ");";
		
		
		
		try {
			stat.execute(query);
			rs = stat.executeQuery("SELECT IDlocale FROM locale");
			rs.last();
			IDlocale = rs.getInt("IDlocale");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERRORE NELLA QUERY");
			System.out.println("query: " + query);
		}
		
		closeConnection();
		
		return IDlocale;
	}
	
	
	public int modificaLocale(Locale l) {
		
		int IDlocatore = 0;
		
		String query = "SELECT * FROM locale WHERE IDlocale = " + l.getID() + ";";
		
		try {
			stat.execute(query);
			
			IDlocatore = rs.getInt("IDutente_registrato");
			
			query = "UPDATE locale SET "
					+ "nBagni = '" + l.getnBagni() + "', "
					+ "personeMax = '" + l.getPersoneMax() + "' "
					+ "prezzoANotte = " + l.getPrezzoANotte() + ", "
					+ "animali = " + l.getAnimali() + ", "
					+ "postoAuto = " + l.getPostoAuto() + ", "
					+ "giardino = " + l.getGiardino() + ", "
					+ "descrizione = '" + l.getDescrizione() + "', "
					+ "WHERE IDlocale = " + l.getID() + ";";
		
		} catch (SQLException e) {
			System.out.println("query: " + query);
			System.out.println("ERRORE NELLA QUERY !!");
			e.printStackTrace();
		}
		
		return IDlocatore;
	}
	

	public UtenteRegistrato logIn(String username, String password) {
		
		UtenteRegistrato ur = new UtenteRegistrato();
		
		creaStatement();
		
		String query = "SELECT * FROM utente_registrato WHERE "
				+ "username = '" + username + "' AND "
				+ "password = '" + password + "';";
		
		try {
			rs = stat.executeQuery(query);
			
			if(rs.first()) {
				rs.first();
				ur = new UtenteRegistrato(rs.getString("nome"), rs.getString("cognome"), rs.getString("codiceFiscale"), Data.stringInData(rs.getString("dataNascita")), 
												rs.getString("ruolo"), username, password, rs.getString("telefono"), rs.getString("eMail"));
				ur.setID(rs.getInt("IDutente_registrato"));
				ur.setAttivo(rs.getBoolean("attivo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERRORE NELLA QUERY");
			System.out.println("query: " + query);
		}
		
		closeConnection();
		
		return ur;
	}

	/*Torna una lista di prenotazioni associate ad un locale*/
	public List<Prenotazione> getPrenotazioniLocale(int IDlocale) {
		List<Prenotazione> listPre = new ArrayList<>();
		Prenotazione p = null;
		
		creaStatement();
		
		String query = "SELECT * FROM prenotazione WHERE "
				+ "IDlocale = " + IDlocale + ";";
		
		try {
			rs = stat.executeQuery(query);
			
			while(rs.next()) {
				p = new Prenotazione();
				p.setIdLocale(rs.getInt("IDlocale"));
				p.setDataPrenotazione(Data.stringInData(rs.getString("dataPrenotazione")));
				p.setDataStart(Data.stringInData(rs.getString("dataStart")));
				p.setDataEnd(Data.stringInData(rs.getString("dataEnd")));
				p.setIdPrenotazione(rs.getInt("IDprenotazione"));
				
				listPre.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		
		return listPre;
	}

	
	public UtenteRegistrato datiUtenteRegistrato(int iD) {
		UtenteRegistrato ur = null;
		
		creaStatement();
		
		String query = "SELECT * FROM utente_registrato WHERE IDutente_registrato = " + iD + ";";
		
		try {
			rs = stat.executeQuery(query);
			
			if(!rs.next()) 
				return null;
			else 
				ur = new UtenteRegistrato(rs.getString("nome"), rs.getString("cognome"), rs.getString("codiceFiscale"),	Data.stringInData(rs.getString("dataNascita")),
										rs.getString("ruolo"), rs.getString("username"), rs.getString("password"), rs.getString("telefono"), rs.getString("eMail"));
			ur.setID(iD);
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY");
			System.out.println("query: " + query);
			e.printStackTrace();
		}
		
		closeConnection();
		
		return ur;
	}
	
	/*Torna il locale relativo a IDlocale*/
	public Locale getLocale(int IDlocale) {
		Locale l = null;
		
		creaStatement();
		
		String query = "SELECT * FROM locale WHERE IDlocale = " + IDlocale + ";";
		
		try {
			rs = stat.executeQuery(query);
			rs.first();
			
			l = new Locale(rs.getInt("IDutente_registrato"), rs.getString("indirizzo"), 
					rs.getString("città"), rs.getString("provincia"), rs.getInt("mQ"), rs.getInt("nBagni"), 
					rs.getInt("personeMax"), DBtoBoolean(rs.getInt("animali")), 
					DBtoBoolean(rs.getInt("giardino")), DBtoBoolean(rs.getInt("postoAuto")), 
					rs.getInt("prezzoANotte"), rs.getString("descrizione"), 
					Data.stringInData(rs.getString("dataInserimento")), rs.getInt("IDscout"), 
					rs.getInt("IDlegale"));
			
			l.setID(IDlocale);
			l.setConvalidaScout(rs.getInt("convalidaScout"));
			l.setConvalidaLegale(rs.getInt("convalidaLegale"));
			l.setAttivo(DBtoBoolean(rs.getInt("attivo")));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERRORE NELLA QUERY");
			System.out.println("query: " + query);
		}
		
		closeConnection();
		
		return l;
	}

	public List<Locale> getListaLocali(int iDuser, String ruolo) {
		List<Locale> lista = new ArrayList<>();
		
		creaStatement();
		String query = "";
		
		if(ruolo.equals("Locatore")) {
			query = "SELECT * FROM locale WHERE "
					+ "IDutente_registrato = " + iDuser + ";";
		}
		else if(ruolo.equals("Scout")) {
			query = "SELECT * FROM locale WHERE "
					+ "IDscout = " + iDuser + " " + 
					"AND convalidaScout = 0" + ";";
		}
		else if(ruolo.equals("Legale")) {
			query = "SELECT * FROM locale WHERE "
					+ "IDlegale = " + iDuser + " " + 
					"AND convalidaLegale = 0" + ";";
		}
		
		try {
			rs = stat.executeQuery(query);
			
			while(rs.next()) {
				Locale l = new Locale();
				l.setID(rs.getInt("IDlocale"));
				l.setIndirizzo(rs.getString("indirizzo"));
				l.setCittà(rs.getString("città"));
				l.setAttivo(rs.getBoolean("attivo"));
				
				lista.add(l);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		
		return lista;
	}
	
	
	
	public void convalidaLocale(int iDlocale, String ruolo, boolean attivo, boolean convalida) {
		creaStatement();
		
		String query;
		
		query = "UPDATE locale SET ";
		
		if(convalida) {
			if(ruolo.equals("Scout")) {
				query += "convalidaScout = " + 1;
				if(attivo)
					query += " , attivo = " + 1;
			}
			else {
				query += "convalidaLegale = " + 1;
				if(attivo)
					query += " , attivo = " + 1;
			}
		}
		else {
			if(ruolo.equals("Scout")) {
				query += "convalidaScout = " + 2;
			}
			else {
				query += "convalidaLegale = " + 2;
			}
		}
		
		query += " WHERE IDlocale = " + iDlocale + ";";
		
		try {
			stat.execute(query);
		} catch (SQLException e) {
			System.out.println("query: " + query);
			System.out.println("ERRORE NELLA QUERY !!");
			e.printStackTrace();
		}
		
		closeConnection();
		
	}
	
	

	/*Ritorna una lista di Prenotazioni che verrà in seguito modificata in una serie di stringhe*/
	public List<Prenotazione> caricaPrenotazioni(String iDutente_registrato, String ruolo) {
		List<Prenotazione> l = new ArrayList<>();
		Prenotazione p;
		String query = "";
		
		creaStatement();
		
		if(ruolo.equals("Locatario")) {
			query = "SELECT * FROM prenotazione WHERE IDutente_registrato = " + iDutente_registrato + ";";
		}
		else if(ruolo.equals("Locatore")) {
				query = "SELECT * FROM prenotazione P, locale L "
						+ "WHERE L.IDutente_registrato = " + iDutente_registrato + " "
						+ "AND P.IDlocale = L.IDlocale;";
		}
		try {
			rs = stat.executeQuery(query);
			
			while(rs.next()) {
				p = new Prenotazione();
				p.setIdPrenotazione(rs.getInt("IDprenotazione"));
				p.setnPartecipanti(rs.getInt("nPartecipanti"));
				p.setDataPrenotazione(Data.stringInData(rs.getString("dataPrenotazione")));
				p.setDataStart(Data.stringInData(rs.getString("dataStart")));
				p.setDataEnd(Data.stringInData(rs.getString("dataEnd")));
				
				l.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		
		return l;
	}


	/*Carica prenotazione in base al proprio IDprenotazione*/
	public Prenotazione caricaPrenotazione(int iDprenotazione) {
		Prenotazione p = new Prenotazione();;
		Partecipante pa;
		Locale l = new Locale();
		List<Partecipante> lp = new ArrayList<>();
		
		creaStatement();
		
		String query = "SELECT P.IDlocale, P.dataStart, P.dataEnd, P.dataPrenotazione, P.nPartecipanti, "
				+ "P.note, P.convalidaLocatore, P.convalidaLegale, P.pagata, P.IDutente_registrato, PA.nome, "
				+ "PA.cognome, PA.codiceFiscale, PA.IDpartecipante, L.indirizzo, L.provincia, L.prezzoANotte, "
				+ "L.IDutente_registrato, L.IDlegale "
				+ "FROM prenotazione AS P "
				+ "INNER JOIN partecipante AS PA "
				+ "INNER JOIN locale AS L "
				+ "WHERE PA.IDprenotazione = P.IDprenotazione "
				+ "AND P.IDprenotazione = " + iDprenotazione + " "
				+ "AND L.IDlocale = P.IDlocale;";

		try {
			rs = stat.executeQuery(query);
			
			if(rs.first()) {
				l.setID(rs.getInt("IDlocale"));
				l.setIDlocatore(rs.getInt("IDutente_registrato"));
				l.setIDLegale(rs.getInt("IDlegale"));
				l.setIndirizzo(rs.getString("indirizzo"));
				l.setProvincia(rs.getString("provincia"));
				l.setPrezzoANotte(rs.getInt("prezzoANotte"));
				pa = new Partecipante(rs.getString("nome"), rs.getString("cognome"), rs.getString("codiceFiscale"));
				pa.setiDpartecipante(rs.getInt("IDpartecipante"));
				lp.add(pa);
				
				p.setLocale(l);
				p.setIdPrenotazione(iDprenotazione);
				p.setIdLocatario(rs.getInt("IDutente_registrato"));
				p.setPartecipanti(lp);
				p.setDataStart(Data.stringInData(rs.getString("dataStart")));
				p.setDataEnd(Data.stringInData(rs.getString("dataEnd")));
				p.setDataPrenotazione(Data.stringInData(rs.getString("dataPrenotazione")));
				p.setnPartecipanti(rs.getInt("nPartecipanti"));
				p.setNote(rs.getString("note"));
				p.setConvalidaLocatore(rs.getInt("convalidaLocatore"));
				p.setConvalidaLegale(rs.getInt("convalidaLegale"));
				p.setPagata(GestoreDB.DBtoBoolean(rs.getInt("pagata")));
			}
			
			while(rs.next()) {
				pa = new Partecipante(rs.getString("nome"), rs.getString("cognome"), rs.getString("codiceFiscale"));
				pa.setiDpartecipante(rs.getInt("IDpartecipante"));
				lp.add(pa);
			}
			
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY");
			System.out.println("query: " + query);
			e.printStackTrace();
		}
		
		return p;
	}

	/*Ritorna una lista di utenti registrati (solo ID, nLocali ed nPrenotazioni) in base al ruolo*/
	public List<UtenteRegistrato> caricaRuolo(String ruolo) {
		List<UtenteRegistrato> l = new ArrayList<>();
		UtenteRegistrato ur;
		
		creaStatement();
		
		String query = "SELECT IDutente_registrato, nLocali, nPrenotazioni "
				+ "FROM utente_registrato "
				+ "WHERE ruolo = '" + ruolo + "';";
		
		try {
			rs = stat.executeQuery(query);
			
			while(rs.next()) {
				ur = new UtenteRegistrato();
				
				ur.setID(rs.getInt("IDutente_registrato"));
				ur.setnLocali(rs.getInt("nLocali"));
				ur.setnPrenotazioni(rs.getInt("nPrenotazioni"));
				
				l.add(ur);
			}
			
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY !!");
			System.out.println("query: " + query);
			e.printStackTrace();
		}
		
		closeConnection();
		
		return l;
	}

	public void salvaNotifica(Notifica n) {
		creaStatement();
		
		String query = "INSERT INTO notifica("
				+ "IDoggetto, "
				+ "categoria, "
				+ "IDmittente, "
				+ "IDdestinatario, "
				+ "testo, "
				+ "dataSpedizione, "
				+ "letta) VALUES ("
				+ n.getIDoggetto() + ", "
				+ "'" + n.getCategoria() + "', "
				+ n.getIDmittente() + ", "
				+ n.getIDdestinatario() + ", "
				+ "'" + n.getTesto() + "', "
				+ "'" + Data.dataToString(n.getDataInvioNotifica()) + "', "
				+ booleanToDB(n.isLetta()) + ");";
		
		try {
			stat.execute(query);
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY !!");
			System.out.println("query: " + query);
			e.printStackTrace();
		}
		
		closeConnection();
		
	}

	/*Carica notifiche in ricezione o spediti da un utente*/
	public List<Notifica> caricaNotifiche(int IDutente_registrato) {
		Notifica n;
		List<Notifica> ln = new ArrayList<>();
		
		creaStatement();
		
		String query = "SELECT * FROM notifica "
				+ "WHERE IDdestinatario = " + IDutente_registrato + " OR "
				+ "IDmittente = " + IDutente_registrato + ";";
		
		try {
			rs = stat.executeQuery(query);
			
			while(rs.next()) {
				n = new Notifica(rs.getInt("IDoggetto"), rs.getString("categoria"), 
						rs.getInt("IDmittente"), rs.getInt("IDdestinatario"), rs.getString("testo"), 
						Data.stringInData(rs.getString("dataSpedizione")), DBtoBoolean(rs.getInt("letta")));
				
				n.setIDnotifica(rs.getInt("IDnotifica"));
				
				ln.add(n);
			}
			
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY !!");
			System.out.println("query: " + query);
			e.printStackTrace();
		}
		
		closeConnection();
		
		return ln;
	}

	public Prenotazione getPrenotazione(int idPrenotazione) {
		creaStatement();
		Prenotazione p = new Prenotazione();
		
		String query = "SELECT * FROM prenotazione "
				+ "WHERE IDprenotazione = " + idPrenotazione + ";";
		
		try {
			rs = stat.executeQuery(query);
			
			p.setDataPrenotazione(Data.stringInData(rs.getString("dataPrenotazione")));
			p.setDataStart(Data.stringInData(rs.getString("dataStart")));
			
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY !!");
			System.out.println("query: " + query);
			e.printStackTrace();
		}
		
		closeConnection();
		
		return null;
	}

	/*Aggiorna convalida prenotazione in base al ruolo*/
	public void aggiornaConvalidaPrenotazione(String ruolo, int iDprenotazione, boolean convalida) {
		creaStatement();
		
		String query;
		
		query = "UPDATE prenotazione SET ";
		
		if(ruolo.equals("Locatore")) {
			query += "convalidaLocatore = ";
			if(convalida)
				query += 1;
			else
				query += 2;
		}
		else {
			query += "convalidaLegale = ";
			if(convalida)
				query += 1;
			else
				query += 2;
		}
		
		query += " WHERE IDprenotazione = " + iDprenotazione + ";";
		
		try {
			stat.execute(query);
		} catch (SQLException e) {
			System.out.println("query: " + query);
			System.out.println("ERRORE NELLA QUERY !!");
			e.printStackTrace();
		}
		
		closeConnection();
		
	}

	/*Incrementa il campo relativo nella tabella utente_registrato*/
	public void incrementaCampo(int IDutente_registrato, String campo) {
		creaStatement();
		int valore = 0;
		
		String query = "SELECT " + campo + " FROM utente_registrato "
				+ "WHERE IDutente_registrato = " + IDutente_registrato  + ";";
		
		try {
			rs = stat.executeQuery(query);
			
			if(rs.first())
				valore = rs.getInt(campo);
			
			query = "UPDATE utente_registrato "
					+ "SET " + campo + " = " + (valore + 1)
					+ " WHERE IDutente_registrato = " + IDutente_registrato + ";";
			
			stat.execute(query);
			
		} catch (SQLException e) {
			System.out.println("query: " + query);
			System.out.println("ERRORE NELLA QUERY !!");
			e.printStackTrace();
		}
		
		closeConnection();
		
	}

	
	/*Decrementa il campo relativo nella tabella utente_registrato*/
	public void decrementaCampo(int IDutente_registrato, String campo) {
		creaStatement();
		int valore = 0;
		
		String query = "SELECT " + campo + " FROM utente_registrato "
				+ "WHERE IDutente_registrato = " + IDutente_registrato  + ";";
		
		try {
			rs = stat.executeQuery(query);
			
			if(rs.first())
				valore = rs.getInt(campo);
			
			query = "UPDATE utente_registrato "
					+ "SET " + campo + " = " + (valore - 1)
					+ " WHERE IDutente_registrato = " + IDutente_registrato + ";";
			
			stat.execute(query);
			
		} catch (SQLException e) {
			System.out.println("query: " + query);
			System.out.println("ERRORE NELLA QUERY !!");
			e.printStackTrace();
		}
		
		closeConnection();
		
	}
	
	
	
	/*Elimina prenotazione dal DB con i relativi partecipanti e notifiche*/
	public void eliminaPrenotazione(Prenotazione prenotazione) {
		creaStatement();
		
		//elimina prenotazione
		String query = "DELETE FROM prenotazione "
				+ "WHERE IDprenotazione = " + prenotazione.getIdPrenotazione() + ";";
		
		try {
			stat.execute(query);
			
			//elimina partecipanti
			query = "DELETE FROM partecipanti "
					+ "WHERE IDprenotazione = " + prenotazione.getIdPrenotazione() + ";";
			stat.execute(query);
			
			//elimina notifica al Locatore
			query = "DELETE FROM notifica "
					+ "WHERE IDoggetto = " + prenotazione.getIdPrenotazione() + " AND " 
							+ "categoria = prenotazione" + ";";
			stat.execute(query);
			
		} catch (SQLException e) {
			System.out.println("query: " + query);
			System.out.println("ERRORE NELLA QUERY !!");
			e.printStackTrace();
		}
		
	}

	public int rimuoviLocale(int IDlocale) {
		
		creaStatement();
		
		int IDlocatore = 0;
		
		String query = "SELECT * FROM locale WHERE IDlocale = " + IDlocale + ";";
		
		try {
			stat.execute(query);
			
			IDlocatore = rs.getInt("IDutente_registrato");
			
			//elimina locale
			query = "DELETE FROM locale "
					+ "WHERE IDlocale = " + IDlocale + ";";
			
			stat.execute(query);
			
			//elimina prenotazioni
			query = "DELETE FROM prenotazione "
					+ "WHERE IDlocale = " + IDlocale + ";";
			
			stat.execute(query);
			
			//elimina notifica al Locatore
			query = "DELETE FROM notifica "
					+ "WHERE IDoggetto = " + IDlocale + " AND " 
					+ "categoria = Locale" + ";";
			
			stat.execute(query);
			
		} catch (SQLException e) {
			System.out.println("query: " + query);
			System.out.println("ERRORE NELLA QUERY !!");
			e.printStackTrace();
		}
		
		return IDlocatore;
	}

	public void modificaPrenotazione(Prenotazione p) {
		creaStatement();
		
		String query = "UPDATE prenotazione SET "
				+ "dataStart = '" + Data.dataToString(p.getDataStart()) + "', "
				+ "dataEnd = '" + Data.dataToString(p.getDataEnd()) + "', "
				+ "convalidaLocatore = " + 0 + ", "
				+ "convalidaLegale = " + 0 + " "
				+ "WHERE IDprenotazione = " + p.getIdPrenotazione() + ";";
		
		try {
			stat.execute(query);
			
			modificaPartecipante(p);
			
		} catch (SQLException e) {
			System.out.println("ERRORE NELLA QUERY");
			System.out.println("query: " + query);
			e.printStackTrace();
		}
		
	}

	public void modificaPartecipante(Prenotazione p) {
		creaStatement();
		
		for(int i=0; i<p.getPartecipanti().size(); i++) {
			String query = "UPDATE partecipante SET "
					+ "nome = '" + p.getPartecipanti().get(i).getNome() + "', "
					+ "cognome = '" + p.getPartecipanti().get(i).getCognome() + "', "
					+ "codiceFiscale = '" + p.getPartecipanti().get(i).getCodiceFiscale() + "' "
					+ "WHERE IDpartecipante = " + p.getPartecipanti().get(i).getiDpartecipante() + ";";
			
			try {
				stat.execute(query);
			} catch (SQLException e) {
				System.out.println("query: " + query);
				System.out.println("ERRORE NELLA QUERY !!");
				e.printStackTrace();
			}
		}
		
	}

	public List<UtenteRegistrato> getListaStaff() {
		List<UtenteRegistrato> lista = new ArrayList<>();
		
		creaStatement();
		
		String query = "SELECT * FROM utente_registrato WHERE "
				+ "ruolo = 'Scout' OR ruolo = 'Legale'" + ";";

		
		try {
			rs = stat.executeQuery(query);
			
			while(rs.next()) {
				UtenteRegistrato ur = new UtenteRegistrato(rs.getString("nome"), rs.getString("cognome"), rs.getString("codiceFiscale"), 
											Data.stringInData(rs.getString("dataNascita")), rs.getString("ruolo"), rs.getString("username"), 
											rs.getString("password"), rs.getString("telefono"), rs.getString("eMail"));
				ur.setID(rs.getInt("IDutente_registrato"));
				ur.setAttivo(rs.getBoolean("attivo"));
				
				lista.add(ur);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		
		return lista;
	}

	public void rimuoviStaff(int IDuser) {
		
		creaStatement();
		
		String query = "DELETE * FROM utente_registrato WHERE IDutente_registrato = " + IDuser + ";";
		
		try {
			stat.execute(query);
			
		} catch (SQLException e) {
			System.out.println("query: " + query);
			System.out.println("ERRORE NELLA QUERY !!");
			e.printStackTrace();
		}
		
	}

	public void assumiStaff(int IDuser) {
		creaStatement();
		
		String query;
		
		query = "UPDATE utente_registrato SET attivo = " + 1 + " WHERE IDutente_registrato = " + IDuser + ";";
		
		try {
			stat.execute(query);
		} catch (SQLException e) {
			System.out.println("query: " + query);
			System.out.println("ERRORE NELLA QUERY !!");
			e.printStackTrace();
		}
		
		closeConnection();
		
	}

	public List<Locale> getLocaliStaff(int IDuser, String ruolo) {
		List<Locale> lista = new ArrayList<>();
		
		creaStatement();
		String query = "";
		
		if(ruolo.equals("Scout")) {
			query = "SELECT * FROM locale WHERE "
					+ "IDscout = " + IDuser + ";";
		}
		else if(ruolo.equals("Legale")) {
			query = "SELECT * FROM locale WHERE "
					+ "IDlegale = " + IDuser + ";";
		}
		
		try {
			rs = stat.executeQuery(query);
			
			while(rs.next()) {
				Locale l = new Locale();
				l.setID(rs.getInt("IDlocale"));
				
				lista.add(l);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		
		return lista;
	}

	public void assegnaStaff(int IDlocale, int IDstaff, String ruolo) {
		creaStatement();
		
		String query;
		
		query = "UPDATE locale SET ";
		
		if(ruolo.equals("Scout")) {
			query += "IDscout = " + IDstaff;
		}
		else {
			query += "IDlegale = " + IDstaff;
		}
		
		query += " WHERE IDlocale = " + IDlocale + ";";
		
		try {
			stat.execute(query);
		} catch (SQLException e) {
			System.out.println("query: " + query);
			System.out.println("ERRORE NELLA QUERY !!");
			e.printStackTrace();
		}
		
		closeConnection();
				
	}

}
