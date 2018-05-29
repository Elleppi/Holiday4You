package control;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import feature.GestoreNotifiche;

public class LoginController {

    private static LoginController instance;

    public static LoginController getInstance() {
        if (instance == null)
            instance = new LoginController();
        return instance;
    }

    private LoginController() {
    }

    /**
     * Carica l'utente corrispondente alla coppia username/password in input
     *
     * @param username username
     * @param password password
     * @return l'utente loggato oppure null se nessun utente corrisponde alla coppia username/password
     */
    public synchronized UtenteRegistrato login(String username, String password) {
    	GestoreDB gdb = GestoreDB.getInstance();
    	
    	return gdb.logIn(username, password);
    	
    }
}
