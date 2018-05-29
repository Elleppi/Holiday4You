<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="RisultatiRicercaLocaleBean" scope="request" class="bean.RisultatiRicercaLocaleBean" />

<jsp:useBean id="LoginBean" scope="request" class="bean.LoginBean" />

<%
	String nome = request.getParameter("nome");
	String ruolo = null;
	ruolo = request.getParameter("ruolo");
	String ID = request.getParameter("ID");
	
	System.out.println("RisultatiRicercaLocale");
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
	
	if(ruolo != null)
		s = "";
	
	String trovati = request.getParameter("value");
	
	int nTrovati = 0;
	
	if(trovati != null){
		RisultatiRicercaLocaleBean.decodificaStringa(trovati);
		nTrovati = RisultatiRicercaLocaleBean.sizeList();
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
<div class="wrapper row4">
<div id="container" class="clear">
<div id="content">
	<header><h3 id="listaLocali"><!--LISTA LOCALI--></h3></header>
	<ol id="listaLocaliTrovati" style="color: rgb(0, 124, 255); font-size: medium;">
	<!--	<li> <a href="#">elemento 1</li>
		<li> <a href="#">elemento 2</li>
		<li> <a href="#">elemento 3</li> -->
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
		linkR.href = "RegistrazionePage.jsp";
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
		linkR.href = "RegistrazioneStaffPage.jsp";
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
		
		link.href = "ListaPrenotazioni.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("Prenotazioni"))
		list.appendChild(link);
		menu.appendChild(list);
		
		list = document.createElement("li");
		link = document.createElement("a");
	}
	
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
	
	 var link, list;
     var container = document.getElementById("listaLocaliTrovati");
     var header = document.getElementById("listaLocali");
     header.appendChild(document.createTextNode("LOCALI TROVATI"));
     
     /*Il primo elemento della lista contiene l'IDlocale, 
      * il secondo elemento contiene la stringa visualizzata*/
  <%   for (int i=0;i<nTrovati;i+=2){ %>
         // Create an <input> element, set its type and name attributes
     	list = document.createElement("li");
         link = document.createElement("a");
         link.href = "DettagliLocale.jsp?IDlocale=<%=RisultatiRicercaLocaleBean.getLocale(i)%>&ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";

         link.appendChild(document.createTextNode("<%=RisultatiRicercaLocaleBean.getLocale(i+1)%>"));
         list.appendChild(link);
        
         container.appendChild(list);
         // Append a line break 
         container.appendChild(document.createElement("br"));
   <%  } %>
}

</script>
</html>