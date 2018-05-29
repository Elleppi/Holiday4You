<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="LoginBean" scope="request" class="bean.LoginBean" />

<%
	String nome = request.getParameter("nome");
	String ruolo = null;
	ruolo = request.getParameter("ruolo");
	String ID = request.getParameter("ID");
	
	System.out.println("FAQ");
	System.out.println("ID: " + ID);
	System.out.println("nome: " + nome);
	System.out.println("ruolo: " + ruolo);
	System.out.println("-----------");
	
	String s = "";
	
	if (request.getParameter("signin") != null) {
		String usr = request.getParameter("username");
		LoginBean.setUsername(usr);
		String psw = request.getParameter("password");
		LoginBean.setPassword(psw);
		
		if(LoginBean.validate()) {
			ruolo = LoginBean.getRuolo();
			ID = String.valueOf(LoginBean.getId());
			nome = LoginBean.getNome();
			%>
			<jsp:forward page="UtenteRegistratoHome.jsp" >
			
			<jsp:param name="ID" value="<%=ID%>"/>
			<jsp:param name="nome" value="<%=nome%>"/>
			<jsp:param name="ruolo" value="<%=ruolo%>"/>
			
			</jsp:forward>
			<%
		}
		
		else 
			s = "Dati errati !!!";
	}
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Holiday4You</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="layout/styles/layout.css" type="text/css" />

<!-- liteAccordion is Homepage Only -->

</head>
<body id="top">
<div class="wrapper row1">
  <div id="header" class="clear">
    <div class="fl_left">
      <h1><a href="Accesso.jsp">Holiday4You</a></h1>
      <p id="benvenuto"></p>
    </div>
    <form method="POST" id="login">
	    <fieldset>
	    <legend>Login</legend>
	    <p><%=s%></p>
	    <div id="registrazione"></div>
	    <div id="registrazioneStaff"></div>
	    </fieldset>
    </form>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row2">
  <div id="topnav">
    <ul id="pulsanti">
    </ul>
    <div  class="clear"></div>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row4" style="TEXT-ALIGN: CENTER; COLOR: SILVER;">
	<p><b>Cosa accade quando un Locatore compila il modulo di inserimento di un nuovo Locale ?</b></p>
	<p>Un nuovo locale inserito da un utente registrato come Locatore viene immediatamente assegnato<br>ad uno Scout 
	e ad un Legale. Essi si impegneranno alla convalida dello stesso in accordo<br>con i requisiti qualitativi e legali prestabiliti.
	Al termine dei controlli dovuti dei rispettivi agenti<br>viene inviata una notifica al Locatore riguardante l'esito
	della convalida del Locale.<br>In caso di mancata convalida da parte di uno dei due agenti<br>(conseguentemente al non superamento 
	dei requisiti stabiliti) il Locale non verrà inserito nel Sistema.<br>Viceversa, in caso di convalida di entrambi gli agenti,
	il Locale verrà immediatamente inserito nel Sistema.</p>
	<p><b><br>Cosa accade quando un Locatario effettua una prenotazione ad un Locale ?</b></p>
	<p>Al momento della prenotazione, viene inviato al rispettivo Locatore il modulo compilato dal Locatario<br>il quale,
	dopo opportune scelte decisionali, provvederà ad una convalida dello stesso.<br>Verrà quindi notificato il Locatario 
	dell'esito della convalida.<br>In caso di avvenuta convalida da parte del Locatore, il Locatario ha a sua disposizione
	il 10% dei giorni rimanenti<br>alla data di inizio prenotazione per apportare modifiche al contratto stesso (ad esempio
	aggiungere, modificare, o eliminare<br>i dati relativi ai partecipanti). Scaduti questo termine,il contratto di prenotazione
	verrà inoltrato ad un Legale,<br>il quale, dopo opportune verifiche, provvederà a convalidare definitivamente
	la prenotazione in questione.<br>Da questo punto in poi il Locatario, dopo essere stato notificato della convalida
	da parte del Legale,<br>ha il 20% dei giorni rimanenti dal giorno della prenotazione a quello di inizio della prenotazione 
	per effettuare il pagamento	della stessa.<br>In caso di mancato pagamento entro il termine in questione, 
	la prenotazione verrà annullata irrimediabilmente.</p>
	<p><b><br>Posso modificare la prenotazione ad un Locale ?</b></p>
	<p>Un Locatario ha il 10% dei giorni rimanenti dalla data di inizio dell'affitto per poter<br>modificare la prenotazione. Della prenotazione
	possono essere cambiati i dati dei partecipanti<br>e la data di inizio e/o fine affitto. Per apportare modifiche più sostanziose<br>
	eliminare la prenotazione dalla stessa interfaccia, e crearne una nuova.</p>
	<p><b><br>NOTE AGGIUNTIVE:</b><br>Un locale può essere prenotato con un minimo di 10 giorni prima dalla data di<br>inizio 
	di prenotazione voluta.<br>Un Locale può essere prenotato fino ad un massimo di 12 mesi di anticipo.</p>
</div>
<div class="wrapper">
  <div id="copyright" class="clear">
    <p style="text-align: center;">Università degli Studi di Roma Tor Vergata - Ingegneria del Software e Progettazione Web - Lorenzo Paris, Giovanni D'Agostino</a></p>
    
  </div>
</div>
<!-- liteAccordion is Homepage Only --> 
</body>
<script>
window.onload = function() {
	
	var menu = document.getElementById("pulsanti");
	var benvenuto = document.getElementById("benvenuto");
	var link, list;
	var ruolo = null;
	ruolo = "<%=ruolo%>";
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	if(ruolo != "null") {
		benvenuto.appendChild(document.createTextNode("Bentornato <%=nome%>"));
		link.href = "UtenteRegistratoHome.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("HOMEPAGE"));
		list.appendChild(link);
		menu.appendChild(list);
	}
	else { 
		/*crea input password*/
		var login = document.getElementById("login");
		var input = document.createElement("input");
		input.type = "password";
		input.id = "password";
		input.name = "password";
		input.required = true;
		login.appendChild(input);
		
		/*crea input username*/
		input = document.createElement("input");
		input.type = "text";
		input.id = "username";
		input.name = "username";
		input.required = true;
		login.appendChild(input);
		
		/*crea link registrazione*/
		var registrazione = document.getElementById("registrazione");
		registrazione.appendChild(document.createTextNode("NON SEI REGISTRATO ? REGISTRATI "));
		var linkR = document.createElement("a");
		linkR.href = "RegistrazioneUtente.jsp";
		linkR.appendChild(document.createTextNode(" qui"));
		registrazione.appendChild(linkR);
		login.appendChild(registrazione);
		
		/*crea pulsante SIGN IN*/
		input = document.createElement("input");
		input.type = "image";
		input.src = "layout/images/sign_in.gif";
		input.id = "signin";
		input.name = "signin";
		login.appendChild(input);
		
		/*Crea pulsante hidden per il submit login*/
		input = document.createElement("input");
		input.type = "hidden";
		input.name = "signin";
		login.appendChild(input);
		
		/*Crea link registrazioneStaff*/
		var registrazioneStaff = document.getElementById("registrazioneStaff");
		registrazioneStaff.appendChild(document.createTextNode("VUOI LAVORARE CON NOI ? CLICCA "));
		var linkR = document.createElement("a");
		linkR.href = "RegistrazioneStaff.jsp";
		linkR.appendChild(document.createTextNode(" qui"));
		registrazioneStaff.appendChild(linkR);
		login.appendChild(registrazioneStaff);
		
		/*Scrivi Benvenuto*/
		benvenuto.appendChild(document.createTextNode("Benvenuto"));
		link.href = "Accesso.jsp";
		link.appendChild(document.createTextNode("HOMEPAGE"));
		list.appendChild(link);
		menu.appendChild(list);
	}
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "RicercaLocale.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("Ricerca Locale"));
	list.appendChild(link);
	menu.appendChild(list);
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	if(ruolo != "null" && ruolo != "Locatario") {
		link.href = "ListaLocali.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("LISTA LOCALI"))
		list.appendChild(link);
		menu.appendChild(list);
		
		list = document.createElement("li");
		link = document.createElement("a");
	}
	
	if(ruolo == "Locatario" || ruolo == "Locatore") { 
		//Legale e Scout non hanno il pulsante prenotazioni
		link.href = "ListaPrenotazioni.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("Prenotazioni"))
		list.appendChild(link);
		menu.appendChild(list);
		
		list = document.createElement("li");
		link = document.createElement("a");
	}
	
	// Pulsanti in comune a tutti gli attori
	
	if(ruolo != "null") {
		link.href = "Notifiche.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("Notifiche"))
		
		list.appendChild(link);
		menu.appendChild(list);
	}
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "FAQ.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("FAQ"));
	list.className = "active";
	list.appendChild(link);
	menu.appendChild(list);
}

</script>
</html>