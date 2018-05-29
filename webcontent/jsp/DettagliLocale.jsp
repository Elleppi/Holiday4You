<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="DettagliLocaleBean" scope="request" class="bean.DettagliLocaleBean" />

<jsp:useBean id="LoginBean" scope="request" class="bean.LoginBean" />

<%
	String nome = request.getParameter("nome");
	String ruolo = request.getParameter("ruolo");
	String ID = request.getParameter("ID");
	String IDlocale = request.getParameter("IDlocale");
	
	System.out.println("DettagliLocale");
	System.out.println("ID: " + ID);
	System.out.println("nome: " + nome);
	System.out.println("ruolo: " + ruolo);
	System.out.println("IDlocale: " + IDlocale);
	System.out.println("-----------");
	
	if(DettagliLocaleBean.checkParameters(ID, nome, ruolo, IDlocale) < 0) {
		%>
		<jsp:forward page="Accesso.jsp"/>
		<%
	}
	
	String s = "";
	String message = "";
	
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
			<jsp:forward page="RicercaLocale.jsp" >
			
			<jsp:param name="ID" value="<%=ID%>"/>
			<jsp:param name="nome" value="<%=nome%>"/>
			<jsp:param name="ruolo" value="<%=ruolo%>"/>
			
			</jsp:forward>
			<%
		}
		
		else
			s = "Dati errati !!!";
	}
	
	if(request.getParameter("modifica") != null) {

			%>
			<jsp:forward page="ModificaLocale.jsp">
			
			<jsp:param name="ID" value="<%=ID%>"/>
			<jsp:param name="nome" value="<%=nome%>"/>
			<jsp:param name="ruolo" value="<%=ruolo%>"/>
			<jsp:param name="IDlocale" value="<%=IDlocale%>"/>
			
			</jsp:forward>
			<%

	}
	if(request.getParameter("rimuovi") != null) {
		if(DettagliLocaleBean.rimuoviLocale(IDlocale)) {
			%>
			<jsp:forward page="Avviso.jsp">
			
			<jsp:param name="ID" value="<%=ID%>"/>
			<jsp:param name="nome" value="<%=nome%>"/>
			<jsp:param name="ruolo" value="<%=ruolo%>"/>
			<jsp:param name="avviso" value="9"/>
			
			</jsp:forward>
			<%
		}
		else {
			message = "Non è possibile rimuovere il locale in quanto risulta già prenotato";
		}
		
	}
	
	if(request.getParameter("convalida") != null) {
		DettagliLocaleBean.convalidaLocale(IDlocale, ruolo);
		
		%>
		<jsp:forward page="Avviso.jsp">
		
		<jsp:param name="ID" value="<%=ID%>"/>
		<jsp:param name="nome" value="<%=nome%>"/>
		<jsp:param name="ruolo" value="<%=ruolo%>"/>
		<jsp:param name="avviso" value="10"/>
		
		</jsp:forward>
		<%
		
	}
	if(request.getParameter("nonConvalida") != null) {
		DettagliLocaleBean.nonConvalidaLocale(IDlocale, ruolo);
		
		%>
		<jsp:forward page="Avviso.jsp">
		
		<jsp:param name="ID" value="<%=ID%>"/>
		<jsp:param name="nome" value="<%=nome%>"/>
		<jsp:param name="ruolo" value="<%=ruolo%>"/>
		<jsp:param name="avviso" value="11"/>
		
		</jsp:forward>
		<%
		
	}

	DettagliLocaleBean.setLocale(IDlocale);
	String IDlocatore = DettagliLocaleBean.getIDlocatore();
	String indirizzo = DettagliLocaleBean.getIndirizzo();
	String città = DettagliLocaleBean.getCittà();
	String provincia = DettagliLocaleBean.getProvincia();
	String mQ = DettagliLocaleBean.getmQ();
	String nBagni = DettagliLocaleBean.getnBagni();
	String personeMax = DettagliLocaleBean.getPersoneMax();
	String animali = DettagliLocaleBean.getAnimali();
	String giardino = DettagliLocaleBean.getGiardino();
	String postoAuto = DettagliLocaleBean.getPostoAuto();
	String prezzoANotte = DettagliLocaleBean.getPrezzoANotte();
	String descrizione = DettagliLocaleBean.getDescrizione();
	String prenotazioni = DettagliLocaleBean.getPrenotazioni();
	String convalidaScout = "";
	String convalidaLegale = "";
	String IDscout = DettagliLocaleBean.getIDScout();
	String IDlegale = DettagliLocaleBean.getIDLegale();
	String attivo = "";
	String prenotazione = "";
	String dataInserimento = ""; 
	if(ruolo == "Locatore") {
		convalidaScout = DettagliLocaleBean.getConvalidaScout();
		convalidaLegale = DettagliLocaleBean.getConvalidaLegale();
		attivo = DettagliLocaleBean.getAttivo();
		dataInserimento = DettagliLocaleBean.getDataInserimento();
	}
	
	if(request.getParameter("prenota") != null) {
		%>
		<jsp:forward page="PrenotaLocale.jsp" >
		
		<jsp:param name="nome" value="<%=nome%>"/>
		<jsp:param name="ruolo" value="<%=ruolo%>"/>
		<jsp:param name="ID" value="<%=ID%>"/>
		<jsp:param name="IDlocale" value="<%=IDlocale%>"/>
		
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
<div class="wrapper row4" style="TEXT-ALIGN: CENTER; COLOR: SILVER;" id="dati">
	<p><%=indirizzo%></p>
	<p><%=città%></p>
	<p><%=provincia%></p>
	<p><%=mQ%></p>
	<p><%=nBagni%></p>
	<p><%=personeMax%></p>
	<p><%=animali%></p>
	<p><%=giardino%></p>
	<p><%=postoAuto%></p>
	<p><%=prezzoANotte%></p>
	<p><%=descrizione%></p>
	<ul><%=prenotazioni%></ul>
	<p><%=dataInserimento%></p>
	<p><%=convalidaScout%></p>
	<p><%=convalidaLegale%></p>
	<p><%=attivo%></p>
	<form method="POST" id="form"></form>
</div>
<div>
	<p id="message" style="text-align:center;"><%=message%></p>
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
	var container = document.getElementById("dati");
	var link, list;
	var ruolo = null;
	var IDutenteRegistrato = null;
	var IDlocatore = null;
	var IDscout = null;
	var	IDlegale = null;
	var convScout = null;
	var convLegale = null;
	ruolo = "<%=ruolo%>";
	IDutenteRegistrato = "<%=ID%>";
	IDlocatore = "<%=IDlocatore%>";
	IDscout = "<%=IDscout%>";
	IDlegale = "<%=IDlegale%>";
	convScout = "<%=DettagliLocaleBean.getL().getConvalidaScout()%>";
	convLegale = "<%=DettagliLocaleBean.getL().getConvalidaLegale()%>";
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	if(ruolo != "null") {
		benvenuto.appendChild(document.createTextNode("Bentornato <%=nome%>"));
		link.href = "UtenteRegistratoHome.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("HOMEPAGE"));
		list.appendChild(link);
		menu.appendChild(list);
		
		var par = document.getElementById("message");
		par.style.color = "red";
		par.style.fontSize = "large";
		
		/*crea pulsanti PRENOTA o MODIFICA per il locatario e locatore*/
		var form = document.getElementById("form");

		if(ruolo == "Locatario") {
			var input = document.createElement("input");
			form.action = "PrenotaLocale.jsp";
			form.method = "POST";
			var btn1 = document.createElement("input");
			var btn2 = document.createElement("input");
			var btn3 = document.createElement("input");
			var btn4 = document.createElement("input");
			btn1.name = "ID";
			btn1.value = "<%=ID%>";
			btn1.type = "hidden";
			btn2.name = "ruolo";
			btn2.value = "<%=ruolo%>";
			btn2.type = "hidden";
			btn3.name = "nome";
			btn3.value = "<%=nome%>";
			btn3.type = "hidden";
			btn4.name = "IDlocale";
			btn4.value = "<%=IDlocale%>";
			btn4.type = "hidden";
			input.type = "submit";
			input.value = "PRENOTA";
			input.name = "prenota";
			form.appendChild(input);
			form.appendChild(btn1);
			form.appendChild(btn2);
			form.appendChild(btn3);
			form.appendChild(btn4);
			container.appendChild(form);
		} 
		else if(ruolo == "Locatore" && IDutenteRegistrato == IDlocatore){
			var input1 = document.createElement("input");
			input1.type = "submit";
			input1.value = "MODIFICA";
			input1.name = "modifica";
			form.appendChild(input1);
			container.appendChild(form);
			var input2 = document.createElement("input");
			input2.type = "submit";
			input2.value = "RIMUOVI";
			input2.name = "rimuovi";
			input2.style = "margin-left: 20px";
			form.appendChild(input2);
			container.appendChild(form);
		}
		else if(ruolo == "Scout" && IDutenteRegistrato == IDscout && convScout == 0) {
			var input1 = document.createElement("input");
			input1.type = "submit";
			input1.value = "CONVALIDA";
			input1.name = "convalida";
			form.appendChild(input1);
			container.appendChild(form);
			var input2 = document.createElement("input");
			input2.type = "submit";
			input2.value = "NON CONVALIDA";
			input2.name = "nonConvalida";
			input2.style = "margin-left: 20px";
			form.appendChild(input2);
			container.appendChild(form);
		}
		else if(ruolo == "Legale" && IDutenteRegistrato == IDlegale && convLegale == 0) {
			var input1 = document.createElement("input");
			input1.type = "submit";
			input1.value = "CONVALIDA";
			input1.name = "convalida";
			form.appendChild(input1);
			container.appendChild(form);
			var input2 = document.createElement("input");
			input2.type = "submit";
			input2.value = "NON CONVALIDA";
			input2.name = "nonConvalida";
			input2.style = "margin-left: 20px";
			form.appendChild(input2);
			container.appendChild(form);
		}
		else {
			var p = document.createElement("p");
			p.style.color = "red";
			p.style.fontSize = "large";
			p.appendChild(document.createTextNode("Per prenotare questo locale devi essere "
					+ "registrato come Locatario ed aver effettuato il Login"));
			container.appendChild(p);
		}

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
		
		var p = document.createElement("p");
		p.style.color = "red";
		p.style.fontSize = "large";
		p.appendChild(document.createTextNode("Per prenotare questo locale devi essere "
				+ "registrato come Locatario ed aver effettuato il Login"));
		container.appendChild(p);
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
}

</script>
</html>