<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="ListaPrenotazioniBean" scope="request" class="bean.ListaPrenotazioniBean" />

<%
	String nome = request.getParameter("nome");
	String ruolo = request.getParameter("ruolo");
	String ID = request.getParameter("ID");
	
	System.out.println("ListaPrenotazioni");
	System.out.println("ID: " + ID);
	System.out.println("nome: " + nome);
	System.out.println("ruolo: " + ruolo);
	System.out.println("-----------");
	
	ListaPrenotazioniBean.setListe(ID, nome, ruolo);
	
	if(ListaPrenotazioniBean.getErrore() < 0) {%>
		<jsp:forward page="Accesso.jsp"/>
	<%}
	
	int prenotazioniPassate = ListaPrenotazioniBean.sizePassate();
	int prenotazioniPresenti = ListaPrenotazioniBean.sizePresenti();
	int prenotazioniFuture = ListaPrenotazioniBean.sizeFuture();
	
	String pa = "";
	String pre = "";
	String fu = "";
	
	if(prenotazioniPassate == 0)
		pa = "Non esistono prenotazioni passate";
	if(prenotazioniPresenti == 0)
		pre = "Non esistono prenotazioni presenti";
	if(prenotazioniFuture == 0)
		fu = "Non esistono prenotazioni future";
	
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
<div class="wrapper row4">
<div id="container" class="clear">
<div id="content">
	<header><h6 id="listaLocali">PRENOTAZIONI PASSATE</h6></header>
	<p style="text-align: center; font-size: medium"><%=pa%></p>
	<ol id="passate" style="color: rgb(0, 124, 255); font-size: medium;">
	</ol>
</div>
</div>
</div>
<div class="wrapper row4">
<div id="container" class="clear">
<div id="content">
	<header><h6 id="listaLocali">PRENOTAZIONI PRESENTI</h6></header>
	<p style="text-align: center; font-size: medium"><%=pre%></p>
	<ol id="presenti" style="color: rgb(0, 124, 255); font-size: medium;">
	</ol>
</div>
</div>
</div>
<div class="wrapper row4">
<div id="container" class="clear">
<div id="content">
	<header><h6 id="listaLocali">PRENOTAZIONI FUTURE</h6></header>
	<p style="text-align: center; font-size: medium"><%=fu%></p>
	<ol id="future" style="color: rgb(0, 124, 255); font-size: medium;">
	</ol>
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
	var link, list;
	var ruolo = null;
	ruolo = "<%=ruolo%>";
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "UtenteRegistratoHome.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("HOMEPAGE"));
	list.appendChild(link);
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
		link.appendChild(document.createTextNode("LISTA LOCALI"))
		list.appendChild(link);
		menu.appendChild(list);
		
		list = document.createElement("li");
		link = document.createElement("a");
	}
	
	if(ruolo != "Scout" && ruolo != "Legale") { //Legale e Scout non hanno il pulsante Prenotazioni
		
		link.href = "ListaPrenotazioni.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("Prenotazioni"))
		list.appendChild(link);
		list.className = "active";
		menu.appendChild(list);
		
		list = document.createElement("li");
		link = document.createElement("a");
		
		link.href = "Notifiche.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("Notifiche"))
		list.appendChild(link);
		menu.appendChild(list);
	}
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "FAQ.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("FAQ"));
	list.appendChild(link);
	menu.appendChild(list);
	
	var passate = document.getElementById("passate");
	
	<%   for (int i=0;i<prenotazioniPassate;i+=2){ %>
    // Create an <input> element, set its type and name attributes
	list = document.createElement("li");
    link = document.createElement("a");
    link.href = "DettagliPrenotazione.jsp?IDprenotazione=<%=ListaPrenotazioniBean.getPrenotazionePassata(i)%>&ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";

    link.appendChild(document.createTextNode("<%=ListaPrenotazioniBean.getPrenotazionePassata(i+1)%>"));
    list.appendChild(link);
   
    passate.appendChild(list);
    // Append a line break 
    passate.appendChild(document.createElement("br"));
<%  } %>

	var presenti = document.getElementById("presenti");

	<%   for (int i=0;i<prenotazioniPresenti;i+=2){ %>
	// Create an <input> element, set its type and name attributes
	list = document.createElement("li");
	link = document.createElement("a");
	link.href = "DettagliPrenotazione.jsp?IDprenotazione=<%=ListaPrenotazioniBean.getPrenotazionePresente(i)%>&ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	
	link.appendChild(document.createTextNode("<%=ListaPrenotazioniBean.getPrenotazionePresente(i+1)%>"));
	list.appendChild(link);
	
	presenti.appendChild(list);
	// Append a line break 
	presenti.appendChild(document.createElement("br"));
	<%  } %>

	var future = document.getElementById("future");
	
	/*Il primo elemento della lista contiene l'IDprenotazione, 
     * il secondo elemento contiene la stringa visualizzata*/
	<%   for (int i=0;i<prenotazioniFuture;i+=2){ %>
	// Create an <input> element, set its type and name attributes
	list = document.createElement("li");
	link = document.createElement("a");
	link.href = "DettagliPrenotazione.jsp?IDprenotazione=<%=ListaPrenotazioniBean.getPrenotazioneFutura(i)%>&ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	
	link.appendChild(document.createTextNode("<%=ListaPrenotazioniBean.getPrenotazioneFutura(i+1)%>"));
	list.appendChild(link);
	
	future.appendChild(list);
	// Append a line break 
	future.appendChild(document.createElement("br"));
	<%  } %>
}

</script>
</html>