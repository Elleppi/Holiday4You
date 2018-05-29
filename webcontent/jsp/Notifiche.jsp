<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="NotificheBean" scope="request" class="bean.NotificheBean" />

<%	
	String ID = request.getParameter("ID");
	String nome = request.getParameter("nome");
	String ruolo = request.getParameter("ruolo");
	
	System.out.println("Notifiche");
	System.out.println("ID: " + ID);
	System.out.println("nome: " + nome);
	System.out.println("ruolo: " + ruolo);
	System.out.println("-----------");
	
	String s = "";
	String r = "";
	
	NotificheBean.loadStringheNotifiche(ID, nome, ruolo);
	
	if(NotificheBean.getErrore() < 0) {%>
		<jsp:forward page="Accesso.jsp"/>
	<%}
	
	int notificheRicevute = NotificheBean.sizeRicevute();
	int notificheSpedite = NotificheBean.sizeSpedite();
	
	if(notificheRicevute == 0) 
		r = "Al momento non ci sono notifiche per te";
	if(notificheSpedite == 0)
		s = "Non hai ancora spedito notifiche";
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
	<header><h6>NOTIFICHE RICEVUTE</h6></header>
	<p style="text-align: center; font-size: medium"><%=r%></p>
	<ol id="notificheRicevute" style="color: rgb(0, 124, 255); font-size: medium;">
	</ol>
</div>
</div>
</div>
<div class="wrapper row4">
<div id="container" class="clear">
<div id="content">
	<header><h6>NOTIFICHE SPEDITE</h6></header>
	<p style="text-align: center; font-size: medium"><%=s%></p>
	<ol id="notificheSpedite" style="color: rgb(0, 124, 255); font-size: medium;">
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
	link.href = "Notifiche.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("Notifiche"))
	list.appendChild(link);
	list.className = "active";
	menu.appendChild(list);
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "FAQ.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("FAQ"))
	list.appendChild(link);
	menu.appendChild(list);
	
	<%if(notificheRicevute > 0) { %>
		var ricevute = document.getElementById("notificheRicevute");
		<%for (int i=0;i<notificheRicevute*3;i+=3){ %>
			// Create an <input> element, set its type and name attributes
			list = document.createElement("li");
			link = document.createElement("a");
			var tipo = "<%=NotificheBean.getNotificaRicevuta(i+1)%>";
			
			if(tipo == "prenotazione")
				link.href = "DettagliPrenotazione.jsp?IDprenotazione=<%=NotificheBean.getNotificaRicevuta(i)%>&ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
			else if(tipo == "locale")
				link.href = "DettagliLocale.jsp?IDlocale=<%=NotificheBean.getNotificaRicevuta(i)%>&ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
			else if(tipo == "eliminazione")
				link.href = "Avviso.jsp?avviso=7&ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
			/*else if(tipo == "assunzione")*/
				
			link.appendChild(document.createTextNode("<%=NotificheBean.getNotificaRicevuta(i+2)%>"));
			list.appendChild(link);
			
			ricevute.appendChild(list);
			// Append a line break 
			ricevute.appendChild(document.createElement("br"));
	<%  } 
	} %>
	
	<%if(notificheSpedite > 0) {%>
		var spedite = document.getElementById("notificheSpedite");
		<%for (int i=0;i<notificheSpedite*3;i+=3){ %>
			// Create an <input> element, set its type and name attributes
			list = document.createElement("li");
			link = document.createElement("a");
			var tipo = "<%=NotificheBean.getNotificaSpedita(i+1)%>";
			
			if(tipo == "prenotazione")
				link.href = "DettagliPrenotazione.jsp?IDprenotazione=<%=NotificheBean.getNotificaSpedita(i)%>&ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
			else if(tipo == "locale")
				link.href = "DettagliLocale.jsp?IDlocale=<%=NotificheBean.getNotificaSpedita(i)%>&ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
			/*else if(tipo == "assunzione")*/
				
			link.appendChild(document.createTextNode("<%=NotificheBean.getNotificaSpedita(i+2)%>"));
			list.appendChild(link);
			
			spedite.appendChild(list);
			// Append a line break 
			spedite.appendChild(document.createElement("br"));
		<%  } 
		} %>
}
</script>
</html>