package bean;

public class AvvisoBean {
	
	private String avviso[] = {
			"La prenotazione è stata convalidata",
			"La prenotazione sarà annullata",
			"Il Locale è stato convalidato",
			"Il Locale non è stato convalidato",
			"La tua richiesta è stata spedita al rispettivo Locatore.\nTi verrà inviata una notifica al momento della convalida",
			"La registrazione è avvenuta con successo.\nOra è possibile effettuare il Login",
			"La tua richiesta è stata spedita all'Amministratore del Sistema.\nVerrà ricontattato per un eventuale colloquio",
			"La prenotazione è stata annullata",
			"La richiesta di inserimento del Locale è stata presa in carico.\nVerrà contattato per comunicarle l'esito dell'operazione",
			"La rimozione del locale dal Sistema è avvenuta con successo",
			"Il locale è stato convalidato con successo",
			"Come richiesto, il locale non è stato convalidato",
			"La richiesta di modifica del locale è stata presa in carico.\nVerrà contattato per comunicarle l'esito dell'operazione"};
	
	public AvvisoBean() {
		
	}
	
	public String caricaAvviso(int n) {
		return avviso[n];
	}

}
