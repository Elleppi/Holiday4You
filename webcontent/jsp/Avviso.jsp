<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="AvvisoBean" scope="request" class="bean.AvvisoBean" />

<%
	String nome = request.getParameter("nome");
	String ruolo = null;
	ruolo = request.getParameter("ruolo");
	String ID = request.getParameter("ID");
	
	System.out.println("Avviso");
	System.out.println("ID: " + ID);
	System.out.println("nome: " + nome);
	System.out.println("ruolo: " + ruolo);
	System.out.println("-----------");
	
	int numberAvviso = Integer.parseInt(request.getParameter("avviso"));
	
	String avviso = AvvisoBean.caricaAvviso(numberAvviso);
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
      <p>Bentornato <%=nome%></p>
    </div>
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
<p style="font-size: medium;"><%=avviso%></p>
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
	var link, list;
	var ruolo = null;
	ruolo = "<%=ruolo%>";
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	if(ruolo != "null") {
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
	link.appendChild(document.createTextNode("FAQ"))
	list.appendChild(link);
	menu.appendChild(list);
}

</script>
</html>