package control;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import libreria.Data;

public class RegistrazioneController {
	
	private static RegistrazioneController instance;
	
	private RegistrazioneController() {
		
	}
	
	public static RegistrazioneController getInstance() {
		if (instance == null)
            instance = new RegistrazioneController();
        
		return instance;
	}

	public synchronized int Registra(String nome, String cognome, String codiceFiscale, String dataNascita, String username, 
			String password, String ruolo, String nTelefono, String eMail) {
		GestoreDB gdb = GestoreDB.getInstance();
		
		int err = 0;
		
		err = gdb.verificaCF(codiceFiscale, ruolo);
		if(err != 0)
			return err;
				
		err = gdb.verificaUSR(username);
		if(err != 0)
			return err;

		UtenteRegistrato ur = new UtenteRegistrato(nome, cognome, codiceFiscale, Data.stringInData(dataNascita), ruolo,
													username, password, nTelefono, eMail);
		
		gdb.salvaUtenteRegistrato(ur); 

		
		return err;
	}
	

	
	
	
}
