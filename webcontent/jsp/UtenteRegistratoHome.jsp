<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="UtenteRegistratoHomeBean" scope="request" class="bean.UtenteRegistratoHomeBean" />

<%	
	String ID = request.getParameter("ID");
	String nome = request.getParameter("nome");
	String ruolo = request.getParameter("ruolo");
	
	if(ID != null) {
		UtenteRegistratoHomeBean.loadLocatarioHome(Integer.parseInt(ID), nome, ruolo);
		if(UtenteRegistratoHomeBean.getErrore() < 0) {%>
			<jsp:forward page="Accesso.jsp"/>
		<%}
		
	}
		
	
	String cognome = UtenteRegistratoHomeBean.getCognome();
	String codiceFiscale = UtenteRegistratoHomeBean.getCodiceFiscale();
	String username = UtenteRegistratoHomeBean.getUsername();
	int notifiche = 0;
	
	System.out.println("UtenteRegistratoHome");
	System.out.println("ID: " + ID);
	System.out.println("nome: " + nome);
	System.out.println("ruolo: " + ruolo);
	System.out.println("-----------");
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
	<p>NOME: <%=nome%></p>
	<p>COGNOME: <%=cognome%></p>
	<p>CODICE FISCALE: <%=codiceFiscale%></p>
	<p>RUOLO: <%=ruolo%></p>
	<p>USERNAME: <%=username%></p>
	
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
	
	console.log("onLoad");
	
	var menu = document.getElementById("pulsanti");
	var benvenuto = document.getElementById("benvenuto");
	var link, list;
	var ruolo = null;
	ruolo = "<%=ruolo%>";
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "UtenteRegistratoHome.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("HOMEPAGE"));
	list.appendChild(link);
	list.className = "active";
	menu.appendChild(list);
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "RicercaLocale.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("Ricerca Locale"));
	list.appendChild(link);
	menu.appendChild(list);
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	if(ruolo != "Locatario") {
		link.href = "ListaLocali.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("LISTA LOCALI"));
		list.appendChild(link);
		menu.appendChild(list);
		
		list = document.createElement("li");
		link = document.createElement("a");
	}
	
	if(ruolo == "Locatario" || ruolo == "Locatore") {
		//Legale e Scout non hanno il pulsante prenotazioni
		link.href = "ListaPrenotazioni.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("Prenotazioni"));
		list.appendChild(link);
		menu.appendChild(list);
		
		list = document.createElement("li");
		link = document.createElement("a");
		
	}

	// Pulsanti in comune a tutti gli attori
	link.href = "Notifiche.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("Notifiche"));
	list.appendChild(link);
	menu.appendChild(list);
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "FAQ.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("FAQ"));
	list.appendChild(link);
	menu.appendChild(list);
}
</script>
</html>