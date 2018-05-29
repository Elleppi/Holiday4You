package bean;

import attori.UtenteRegistrato;
import control.LoginController;

public class LoginBean {
	private UtenteRegistrato ur;
	private String username;
	private String password;

	public LoginBean() {
		this.username = "";
		this.password = "";
	}
	
	/**
	 * @return the ur
	 */
	public UtenteRegistrato getUr() {
		return ur;
	}



	/**
	 * @param ur the ur to set
	 */
	public void setUr(UtenteRegistrato ur) {
		this.ur = ur;
	}



	public void setUsername(String user) {
		this.username = user;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public String getPassword() {
		return this.password;
	}

	public void setNome(String nome) {
		this.ur.setNome(nome);
	}

	public String getNome() {
		return this.ur.getNome();
	}

	public void setCognome(String cogn) {
		this.ur.setCognome(cogn);
	}

	public String getCognome() {
		return this.ur.getCognome();
	}

	/**
	 * @return the ruolo
	 */
	public String getRuolo() {
		return this.ur.getRuolo();
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(String ruolo) {
		this.ur.setRuolo(ruolo);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.ur.getID();
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.ur.setID(id);
	}

	public boolean validate() {
		LoginController lc = LoginController.getInstance();
		
		ur = new UtenteRegistrato();
		
		this.ur = lc.login(this.username, this.password);
		
		if (this.ur.getID() != -1) 
			return true;
		
		return false;
	}
}
