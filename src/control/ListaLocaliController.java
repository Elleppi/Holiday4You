package control;

import java.util.ArrayList;
import java.util.List;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import libreria.Locale;

public class ListaLocaliController {

	private static ListaLocaliController instance;
	
	public static ListaLocaliController getInstance() {
        if (instance == null)
            instance = new ListaLocaliController();
        return instance;
    }
	
	public ListaLocaliController() {

	}
	
	
	public synchronized int checkParameters(String id, String nome, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		UtenteRegistrato ur;
		
		if(id == null || nome == null || ruolo == null)
			return -1;
		
		try{
			ur = gdb.datiUtenteRegistrato(Integer.parseInt(id));
		} catch(NumberFormatException nfe) {
			return -1;
		}
		
		if(!ur.getNome().equals(nome)) 
			return -1;
		if(!ur.getRuolo().equals(ruolo))
			return -1;
	
		
		return 0;
	}
	
	public List<Locale> getLocali(int iDAttore, String ruolo) {
		GestoreDB gdb = GestoreDB.getInstance();
		
		List<Locale> lista;

		lista = gdb.getListaLocali(iDAttore, ruolo);

		return lista;
		
	}
	

}
