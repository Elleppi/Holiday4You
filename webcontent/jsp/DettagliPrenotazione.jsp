<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="DettagliPrenotazioneBean" scope="request" class="bean.DettagliPrenotazioneBean" />

<%	
	String nome = request.getParameter("nome");
	String ruolo = request.getParameter("ruolo");
	String ID = request.getParameter("ID");
	String IDprenotazione = request.getParameter("IDprenotazione");
	
	System.out.println("DettagliPrenotazione");
	System.out.println("ID: " + ID);
	System.out.println("nome: " + nome);
	System.out.println("ruolo: " + ruolo);
	System.out.println("IDprenotazione: " + IDprenotazione);
	System.out.println("-----------");
	
	DettagliPrenotazioneBean.loadPrenotazione(IDprenotazione, ID, nome, ruolo);
	
	if(DettagliPrenotazioneBean.getErrore() < 0) {%>
		<jsp:forward page="Accesso.jsp"/>
	<%}
	
	String indirizzo = DettagliPrenotazioneBean.getIndirizzoLocale();
	String prezzoANotte = DettagliPrenotazioneBean.getPrezzoANotteLocale();
	String dataPrenotazione = DettagliPrenotazioneBean.getDataPrenotazione();
	String dataStart = DettagliPrenotazioneBean.getDataStart();
	String dataEnd = DettagliPrenotazioneBean.getDataEnd();
	String note = DettagliPrenotazioneBean.getNote();
	String partecipanti = DettagliPrenotazioneBean.getDatiPartecipanti();
	String convalidaLocatore = DettagliPrenotazioneBean.getConvalidaLocatore();
	String convalidaLegale = DettagliPrenotazioneBean.getConvalidaLegale();
	String pagata = DettagliPrenotazioneBean.getPagata();
	String avviso = null;
	
	if(request.getParameter("convalidatoLocatore") != null) {
		System.out.println("convalidatoLocatore");
		DettagliPrenotazioneBean.convalidaPrenotazione(ruolo, Integer.parseInt(ID));
		%>
		<jsp:forward page="Avviso.jsp" >
		
		<jsp:param name="ID" value="<%=ID%>"/>
		<jsp:param name="nome" value="<%=nome%>"/>
		<jsp:param name="ruolo" value="<%=ruolo%>"/>
		<jsp:param name="avviso" value="0"/>
		
		</jsp:forward>
		<%
		
	}
	if(request.getParameter("nonConvalidatoLocatore") != null) {
		System.out.println("nonConvalidatoLocatore");
		DettagliPrenotazioneBean.nonConvalidaPrenotazione(ruolo, Integer.parseInt(ID));
		%>
		<jsp:forward page="Avviso.jsp" >
		
		<jsp:param name="ID" value="<%=ID%>"/>
		<jsp:param name="nome" value="<%=nome%>"/>
		<jsp:param name="ruolo" value="<%=ruolo%>"/>
		<jsp:param name="avviso" value="1"/>
		
		</jsp:forward>
		<%
	}
	if(request.getParameter("convalidatoLegale") != null) {
		System.out.println("convalidatoLegale");
		DettagliPrenotazioneBean.convalidaPrenotazione(ruolo, Integer.parseInt(ID));
		%>
		<jsp:forward page="Avviso.jsp" >
		
		<jsp:param name="ID" value="<%=ID%>"/>
		<jsp:param name="nome" value="<%=nome%>"/>
		<jsp:param name="ruolo" value="<%=ruolo%>"/>
		<jsp:param name="avviso" value="0"/>
		
		</jsp:forward>
		<%
	}
	if(request.getParameter("nonConvalidatoLegale") != null) {
		System.out.println("nonConvalidatoLegale");
		DettagliPrenotazioneBean.nonConvalidaPrenotazione(ruolo, Integer.parseInt(ID));
		%>
		<jsp:forward page="Avviso.jsp" >
		
		<jsp:param name="ID" value="<%=ID%>"/>
		<jsp:param name="nome" value="<%=nome%>"/>
		<jsp:param name="ruolo" value="<%=ruolo%>"/>
		<jsp:param name="avviso" value="1"/>
		
		</jsp:forward>
		<%
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
<div class="wrapper row4" style="TEXT-ALIGN: CENTER; COLOR: SILVER;" id = "dati">
	<p>INDIRIZZO LOCALE: <%=indirizzo%></p>
	<p>PREZZO A NOTTE: <%=prezzoANotte%></p>
	<p>DATA PRENOTAZIONE: <%=dataPrenotazione%></p>
	<p>DATA INIZIO AFFITTO: <%=dataStart%></p>
	<p>DATA FINE AFFITTO: <%=dataEnd%></p>
	<p>NOTE: <%=note%></p>
	<p>PARTECIPANTI:<br><br><%=partecipanti%></p>
	<p>CONVALIDA LOCATORE: <%=convalidaLocatore%></p>
	<p>CONVALIDA LEGALE: <%=convalidaLegale%></p>
	<p>PAGATA: <%=pagata%></p>
	<p id="testo" style="color: red"></p>
	<form method="POST" id="form"/>
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
	var container = document.getElementById("dati");
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
	
	if(ruolo == "Locatore") {
		link.href = "ListaLocali.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("LISTA LOCALI"))
		list.appendChild(link);
		menu.appendChild(list);
		
		list = document.createElement("li");
		link = document.createElement("a");
		
		if(<%=DettagliPrenotazioneBean.getP().getConvalidaLegale() == 0%> && 
				<%=DettagliPrenotazioneBean.isTimer()%>) {
			var form = document.getElementById("form");
			var convalida = document.createElement("input");
			
			convalida.type = "submit";
			convalida.value = "CONVALIDA";
			convalida.name = "convalidatoLocatore";
			
			form.appendChild(convalida);
			container.appendChild(form);
			
			var nonConvalida = document.createElement("input");
			
			nonConvalida.type = "submit";
			nonConvalida.value = "NON CONVALIDARE";
			nonConvalida.name = "nonConvalidatoLocatore";
			
			form.appendChild(nonConvalida);
			container.appendChild(form);
		}
		else if(<%=!DettagliPrenotazioneBean.isTimer()%>){
			var p = document.getElementById("testo");
			p.appendChild(document.createTextNode("Il termine ultimo per effettuare la convalida è scaduto "
					+ "pertanto la prenotazione è stata annullata"));
		}
		
	}
	
	if(ruolo == "Locatario" && <%=DettagliPrenotazioneBean.isFutura()%> && <%=DettagliPrenotazioneBean.isConvalidaLegale()%>) {
		
		var form = document.getElementById("form");
		var convalida = document.createElement("input");
		
		convalida.type = "submit";
		convalida.value = "MODIFICA";
		convalida.name = "modifica";
		
		form.appendChild(convalida);
		container.appendChild(form);
		
		var nonConvalida = document.createElement("input");
		
		nonConvalida.type = "submit";
		nonConvalida.value = "ANNULLA";
		nonConvalida.name = "annulla";
		
		form.appendChild(nonConvalida);
		container.appendChild(form);
	}
	/*Dopo che è stato convalidato dal Legale, è possibile
	 * effettuare il pagamento
	 */
	else if(ruolo == "Locatario" && <%=DettagliPrenotazioneBean.isConvalidaLegale()%>) {
		if(<%=!DettagliPrenotazioneBean.isTimer()%> && <%=!DettagliPrenotazioneBean.getP().isPagata()%>) {
			var p = document.getElementById("testo");
			p.appendChild(document.createTextNode("Il termine ultimo per effettuare il pagamento è scaduto "
					+ "pertanto la prenotazione è stata annullata"));
		}
		else if(<%=!DettagliPrenotazioneBean.getP().isPagata()%>) {
			var form = document.getElementById("form");
			var pagamento = document.createElement("input");
			
			pagamento.type = "submit";
			pagamento.value = "EFFETTUA PAGAMENTO";
			pagamento.name = "pagamento";
			
			form.appendChild(pagamento);
			container.appendChild(form);
		}
	}
	else if(ruolo == "Locatario" && <%=!DettagliPrenotazioneBean.isConvalidaLocatore()%>) {
		var p = document.getElementById("testo");
		p.appendChild(document.createTextNode("La prenotazione non è stata accettata dal Locatore, "
				+ "pertanto la prenotazione è stata annullata"));
	}
	
	if(ruolo == "Legale" && <%=DettagliPrenotazioneBean.getP().getConvalidaLegale() == 0%>) {
		var form = document.getElementById("form");
		var convalida = document.createElement("input");
		
		convalida.type = "submit";
		convalida.value = "CONVALIDA";
		convalida.name = "convalidatoLegale";
		
		form.appendChild(convalida);
		container.appendChild(form);
		
		var nonConvalida = document.createElement("input");
		
		nonConvalida.type = "submit";
		nonConvalida.value = "NON CONVALIDARE";
		nonConvalida.name = "nonConvalidatoLegale";
		
		form.appendChild(nonConvalida);
		container.appendChild(form);
	}
	
	if(ruolo != "Scout" && ruolo != "Legale") { //Legale e Scout non hanno il pulsante Prenotazioni
		
		link.href = "ListaPrenotazioni.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("Prenotazioni"))
		list.appendChild(link);
		list.className = "active";
		menu.appendChild(list);
		
	}
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "Notifiche.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("Notifiche"))
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