<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="RicercaLocaleBean" scope="request" class="bean.RicercaLocaleBean" />

<jsp:setProperty name="RicercaLocaleBean" property="*" />
<jsp:useBean id="LoginBean" scope="request" class="bean.LoginBean" />

<%
	String nome = request.getParameter("nome");
	String ruolo = request.getParameter("ruolo");
	String ID = request.getParameter("ID");
	
	System.out.println("RicercaLocale");
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
		
		else if (request.getParameter("cerca") == null)
			s = "Dati errati !!!";
	}
	
	String error = "";
	
	if(request.getParameter("cerca") != null) {
		RicercaLocaleBean.check(ID, nome, ruolo);
		if(RicercaLocaleBean.getErrore() < 0) {%>
			<jsp:forward page="Accesso.jsp"/>
		<%}
		String trovati = RicercaLocaleBean.ricercaLocale();
		if(trovati.length() != 0) {
			%>
			<jsp:forward page="RisultatiRicercaLocale.jsp" >
			<jsp:param name="value" value="<%=trovati%>"/>
			<jsp:param name="ID" value="<%=ID%>"/>
			<jsp:param name="nome" value="<%=nome%>"/>
			<jsp:param name="ruolo" value="<%=ruolo%>"/>
			</jsp:forward>
	<% }
		else
			error = "Nessun locale è stato trovato";
	} %>

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
<div class="wrapper row4">
<div id="container" class="clear">
<div id="content">
<form method="POST">
<fieldset>
	<legend>Criteri di Ricerca</legend>
	<table>
		<tr>
			<td>
				<label for"provincia">Provincia </label>
			</td>
			<td>
				<input type="text" name="provincia" id="provincia">
			</td>
		</tr>
		<tr>
			<td>
				<label for"mQ">Metri Quadri (minimo) </label>
			</td>
			<td>
				<input type="number" name="mQ" id="mQ" value="50" min="50" step="10">
			</td>
		</tr>
		<tr>
			<td>
				<label for"nBagni">Numero Bagni (minimo) </label>
			</td>
			<td>
				<input type="number" name="nBagni" id="nBagni" value="1" min="1" step="1">
			</td>
		</tr>
		<tr>
			<td>
				<label for"personeMax">Numero Persone Ammesse (minimo) </label>
			</td>
			<td>
				<input type="number" name="nPersone" id="nPersone" value="1" min="1" step="1">
			</td>
		</tr>
		<tr>
			<td>
				<label for"animali">Animali ammessi </label>
			</td>
			<td>
				<input type="checkbox" name="animali" id="animali">
			</td>
		</tr>
		<tr>
			<td>
				<label for"giardino">Giardino </label>
			</td>
			<td>
				<input type="checkbox" name="giardino" id="giardino">
			</td>
		</tr>
		<tr>
			<td>
				<label for"postoAuto">Posto Auto </label>
			</td>
			<td>
				<input type="checkbox" name="postoAuto" id="postoAuto">
			</td>
		</tr>
		<tr>
			<td>
				<label for"prezzoANotte">Prezzo a Notte (massimo) </label>
			</td>
			<td>
				<input type="number" name="prezzoANotte" id="prezzoANotte" value="25" min="25" step="5">
			</td>
		</tr>
	</table>
	<br>
	<input name="cerca" type="submit" id="cerca" value="CERCA">
	<p style="font-size: large;text-align: center;color: red;"><%=error%></p>
</fieldset>
</form>
</div>
</div>
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
	list.className = "active";
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
	link.appendChild(document.createTextNode("FAQ"))
	list.appendChild(link);
	menu.appendChild(list);
}

</script>
</html>