package bean;

public class AvvisoBean {
	
	private String avviso[] = {
			"La prenotazione � stata convalidata",
			"La prenotazione sar� annullata",
			"Il Locale � stato convalidato",
			"Il Locale non � stato convalidato",
			"La tua richiesta � stata spedita al rispettivo Locatore.\nTi verr� inviata una notifica al momento della convalida",
			"La registrazione � avvenuta con successo.\nOra � possibile effettuare il Login",
			"La tua richiesta � stata spedita all'Amministratore del Sistema.\nVerr� ricontattato per un eventuale colloquio",
			"La prenotazione � stata annullata",
			"La richiesta di inserimento del Locale � stata presa in carico.\nVerr� contattato per comunicarle l'esito dell'operazione",
			"La rimozione del locale dal Sistema � avvenuta con successo",
			"Il locale � stato convalidato con successo",
			"Come richiesto, il locale non � stato convalidato",
			"La richiesta di modifica del locale � stata presa in carico.\nVerr� contattato per comunicarle l'esito dell'operazione"};
	
	public AvvisoBean() {
		
	}
	
	public String caricaAvviso(int n) {
		return avviso[n];
	}

}
