<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="ListaLocaliBean" scope="request" class="bean.ListaLocaliBean" />

<%
	String nome = request.getParameter("nome");
	String ruolo = null;
	ruolo = request.getParameter("ruolo");
	String ID = request.getParameter("ID");
	
	System.out.println("ListaLocali");
	System.out.println("ID: " + ID);
	System.out.println("nome: " + nome);
	System.out.println("ruolo: " + ruolo);
	System.out.println("-----------");

	if(ListaLocaliBean.checkParameters(ID, nome, ruolo) < 0) {
		%>
		<jsp:forward page="Accesso.jsp"/>
		<%
	}
	
	if(ID != null) {
		ListaLocaliBean.setListaLocali(ID, ruolo);
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
<div class="wrapper row4">
	<div id="container" class="clear">
		<div id="content">
			<header><h3 id="listaLocali"><!--LISTA LOCALI--></h3></header>
			<ol id="listaLocaliTrovati" style="color: rgb(0, 124, 255); font-size: large;">
				<!--	<li> <a href="#">elemento 1</li>
				<li> <a href="#">elemento 2</li>
				<li> <a href="#">elemento 3</li> -->
			</ol>
		</div>
	</div>
</div>
<form id="form" action= "InserisciLocale.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>" method="POST">
</form>
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
		list.className = "active";
		menu.appendChild(list);
		
		list = document.createElement("li");
		link = document.createElement("a");
	}
	
	if(ruolo == "Locatario" || ruolo == "Locatore") {
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


	var link, list;
	var container = document.getElementById("listaLocaliTrovati");
	var header = document.getElementById("listaLocali");
	header.appendChild(document.createTextNode("LISTA LOCALI"));
	

<% 	for (int i=0;i<ListaLocaliBean.getListaLocali().size();i++){ %>
		// Create an <input> element, set its type and name attributes
		list = document.createElement("li");
	    link = document.createElement("a");
	    link.href = "DettagliLocale.jsp?IDlocale=<%=ListaLocaliBean.getListaLocali().get(i).getID()%>&ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	
	    link.appendChild(document.createTextNode("<%=ListaLocaliBean.getStringaLocale(i)%>"));
	    list.appendChild(link);
	    
	    container.appendChild(list);
	    // Append a line break 
	    container.appendChild(document.createElement("br"));
<% 	} %>

	/*crea pulsante NUOVO LOCALE per il locatore*/
	var form = document.getElementById("form");
	form.style = "text-align:center";
	
	var input = document.createElement("input");
	if(ruolo == "Locatore") {
		input.type = "submit";
		input.value = "NUOVO LOCALE";
		input.name = "nuovoLocale";
		input.style = "text-align:center; background-color: silver; border: silver; color: #03274B; font-size: large;";
 
		form.appendChild(input);
		container.appendChild(form);	
	}
}


</script>
</html>