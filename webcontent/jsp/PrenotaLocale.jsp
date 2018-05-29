<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="PrenotaLocaleBean" scope="request" class="bean.PrenotaLocaleBean" />

<%
	String nome = request.getParameter("nome");
	String ruolo = request.getParameter("ruolo");
	String ID = request.getParameter("ID");
	String IDlocale = request.getParameter("IDlocale");
	
	int e = 0;
    String s = "";
	
	System.out.println("PrenotaLocale");
	System.out.println("ID: " + ID);
	System.out.println("nome: " + nome);
	System.out.println("ruolo: " + ruolo);
	System.out.println("IDlocale: " + IDlocale);
	System.out.println("-----------");
	
	if(request.getParameter("inviaRichiestaPrenotazione") != null) {
		int p = Integer.parseInt(request.getParameter("nPartecipanti"));
        String dataStart = request.getParameter("dataI");
        String dataEnd = request.getParameter("dataF");
        String note = request.getParameter("note");
        
        PrenotaLocaleBean.setiDlocale(Integer.parseInt(IDlocale));
        PrenotaLocaleBean.setiDlocatario(Integer.parseInt(ID));
        PrenotaLocaleBean.setDataStart(dataStart);
        PrenotaLocaleBean.setDataEnd(dataEnd);
        PrenotaLocaleBean.setNote(note);

        for(int i=0; i<p; i++) {
        	String nm = request.getParameter("nomeP" + (i+1));
            String cm = request.getParameter("cognomeP" + (i+1));
            String cf = request.getParameter("cfP" + (i+1));
            
            PrenotaLocaleBean.addPartecipante(nm, cm, cf);
        }

        e = PrenotaLocaleBean.convalidaPrenotazione();
        
        if(e > 0) {
        	%>
			<jsp:forward page="Avviso.jsp">
			
			<jsp:param name="nome" value="<%=nome%>"/>
			<jsp:param name="ruolo" value="<%=ruolo%>"/>
			<jsp:param name="ID" value="<%=ID%>"/>
			<jsp:param name="IDlocale" value="<%=IDlocale%>"/>
			<jsp:param name="avviso" value="4"/>
			
			</jsp:forward>
	<%
        }
        else
        	s = PrenotaLocaleBean.errore(e);
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
<form method="POST">
<fieldset>
<legend>Compila Modulo Prenotazione</legend>
<table>
    <tr>
        <td>
            <label>Data inizio prenotazione: </label>
        </td>
        <td>
            <input type="date" name="dataI" id="dataI" onchange="attivaDataFine()" required/>
        </td>
    </tr>
    <tr>
        <td>
            <label>Data fine prenotazione: </label>
        </td>
        <td>
            <input type="date" name="dataF" id="dataF" required/>
        </td>
    </tr>
    <tr>
        <td>
            <label>Numero partecipanti: </label>
        </td>
        <td>
            <input type="number" name="nPartecipanti" id="nPartecipanti" value="0" min="0" onchange="aggiungiDettagli()"/>
        </td>
    </tr>
    <tr id="formPartecipanti">
    	<td id="datiPartecipanti"></td>
    	<td id="form"></td>
    </tr>
    <tr>
        <td>
            <label><br><br><br><br>Note: </label>
        </td>
        <td>
            <textarea name="note" id="note" rows="10" cols="30" maxlength="2000" wrap="soft" spellcheck="true"></textarea>
        </td>
    </tr>
</table>
</fieldset>
<input name="inviaRichiestaPrenotazione" type="submit" id="inviaRichiestaPrenotazione" value="INVIA RICHIESTA">
<input name="ID" type="hidden" value="<%=ID%>">
<input name="ruolo" type="hidden" value="<%=ruolo%>">
<input name="IDlocale" type="hidden" value="<%=IDlocale%>">
<input name="nome" type="hidden" value="<%=nome%>">
<p><%=s%></p>
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
	list.className = "active";
	menu.appendChild(list);
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "ListaPrenotazioni.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("Prenotazioni"))
	list.appendChild(link);
	menu.appendChild(list);
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "Notifiche.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("Notifiche"))
	list.appendChild(link);
	menu.appendChild(list);
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	link.href = "FAQ.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("FAQ"))
	list.appendChild(link);
	menu.appendChild(list);
	
	var data = new Date();
	var gg = data.getDate();
	var mm = data.getMonth() + 1;
	var yyyy = data.getFullYear();
	
	if(mm < 10)
		mm = "0" + mm;
	if(gg < 10)
		gg = "0" + gg;
	
	document.getElementById("dataI").value = yyyy + "-" + mm + "-" + gg;
	document.getElementById("dataI").stepUp(10);
	document.getElementById("dataI").min = document.getElementById("dataI").value;
	document.getElementById("dataI").stepUp(365);
	document.getElementById("dataI").max = document.getElementById("dataI").value;
	document.getElementById("dataI").value = "";
	
	document.getElementById("dataF").disabled = true;
	
}

function attivaDataFine() {
	document.getElementById("dataF").disabled = false;
	
	document.getElementById("dataF").value = document.getElementById("dataI").value;
	document.getElementById("dataF").stepUp(1);
	document.getElementById("dataF").min = document.getElementById("dataF").value;
	document.getElementById("dataF").stepUp(28);
	document.getElementById("dataF").max = document.getElementById("dataF").value;
	document.getElementById("dataF").value = "";
}

function aggiungiDettagli() {
    var number = document.getElementById("nPartecipanti").value;
    var elementi = document.getElementById("formPartecipanti");
    var datiPartecipanti = document.getElementById("datiPartecipanti");
    var form = document.getElementById("form");
    
    while (datiPartecipanti.hasChildNodes()) 
    	datiPartecipanti.removeChild(datiPartecipanti.lastChild);
    
    // Clear previous contents of the container
    while (form.hasChildNodes()) 
    	form.removeChild(form.lastChild);
    
    for (i=0;i<number;i++){
    	datiPartecipanti.appendChild(document.createElement("br"));
    	datiPartecipanti.appendChild(document.createElement("br"));
    	datiPartecipanti.appendChild(document.createTextNode("Dati Partecipante " + (i+1)));
    	datiPartecipanti.appendChild(document.createElement("br"));
    	datiPartecipanti.appendChild(document.createElement("br"));
    	datiPartecipanti.appendChild(document.createElement("br"));
    	
    	 var inputNome = document.createElement("input");
         inputNome.type = "text";
         inputNome.name = "nomeP" + (i+1);
         inputNome.id = "nomeP" + (i+1);
         inputNome.placeholder = "nome";
         inputNome.required = true;

         var inputCognome = document.createElement("input");
         inputCognome.type = "text";
         inputCognome.name = "cognomeP" + (i+1);
         inputCognome.id = "cognomeP" + (i+1);
         inputCognome.placeholder = "cognome";
         inputCognome.required = true;

         var inputCF = document.createElement("input");
         inputCF.type = "text";
         inputCF.name = "cfP" + (i+1);
         inputCF.id = "cfP" + (i+1);
         inputCF.class = "codiceFiscale";
         inputCF.placeholder = "codice fiscale";
         inputCF.required = true;
         
    	form.appendChild(inputNome);
    	form.appendChild(document.createElement("br"));
    	form.appendChild(inputCognome);
    	form.appendChild(document.createElement("br"));
    	form.appendChild(inputCF);
    	form.appendChild(document.createElement("br"));
    	form.appendChild(document.createElement("br"));
    }
}

</script>
</html>