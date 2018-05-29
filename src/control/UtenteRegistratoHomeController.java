package control;

import attori.UtenteRegistrato;
import feature.GestoreDB;

public class UtenteRegistratoHomeController {
	
	private static UtenteRegistratoHomeController instance;
	
	public UtenteRegistratoHomeController() {
		
	}
	
	public static UtenteRegistratoHomeController getInstance() {
		if (instance == null)
            instance = new UtenteRegistratoHomeController();
        
		return instance;
	}

	public synchronized UtenteRegistrato setLocatarioParameter(int iD, String nome, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		
		UtenteRegistrato ur = gdb.datiUtenteRegistrato(iD);
		
		if(ur == null || !ur.getNome().equals(nome) || !ur.getRuolo().equals(ruolo))
			return null;
		
		return ur;
	}

}
