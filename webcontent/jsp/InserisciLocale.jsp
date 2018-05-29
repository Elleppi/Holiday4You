<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="InserisciLocaleBean" scope="request" class="bean.InserisciLocaleBean" />
<jsp:setProperty name="InserisciLocaleBean" property="*" />
<%	

String nome = request.getParameter("nome");
String ruolo = null;
ruolo = request.getParameter("ruolo");
String ID = request.getParameter("ID");

int e = 0;
String s = "";

System.out.println("InserisciLocale");
System.out.println("IDLocatore: " + ID);
System.out.println("nome: " + nome);
System.out.println("ruolo: " + ruolo);
System.out.println("-----------");

if(InserisciLocaleBean.checkParameters(ID, nome, ruolo) < 0) {
	%>
	<jsp:forward page="Accesso.jsp"/>
	<%
}

if(ID != null) {
	InserisciLocaleBean.setiDlocatore(Integer.parseInt(ID));
}

if (request.getParameter("inserisci") != null) {
	
	 e = InserisciLocaleBean.convalidaInserimento();
	 
	if(e == 0) {
		%>
		<jsp:forward page="Avviso.jsp">
		
		<jsp:param name="ID" value="<%=ID%>"/>
		<jsp:param name="nome" value="<%=nome%>"/>
		<jsp:param name="ruolo" value="<%=ruolo%>"/>
		<jsp:param name="avviso" value="8"/>
		
		</jsp:forward>
		<%
	}
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
			<form method="POST" >
				<fieldset>
					<p style="text-align:center; font-size: large;" >Modulo di inserimento Locale</p>
					<legend></legend>
					<table>
						<tr>
							<td>
								<label for="indirizzo">Indirizzo:</label>
							</td>
							<td>
								<input type="text" name="indirizzo" id="indirizzo" onkeypress="return validateKeyStrokes(event)" placeholder="Inserisci un indirizzo" required>
								<input type="number" name="numCivico" id="numCivico" min="1" max="10000" value="" placeholder="N°"required>
							</td>
						</tr>
						<tr>
							<td>
								<label for="citta">Citt&agrave:</label>
							</td>
							<td>
								<input type="text" name="citta" id="citta" onkeypress="return validateKeyStrokes(event)" placeholder="Inserisci una città"required>
							</td>
						</tr>
						<tr>
							<td>
								<label for="provincia">Provincia:</label>
							</td>
							<td>
								<select name="provincia" required>
								<script>
								
								var arrayProvincie = ["","AG","AL","AN","AO","AP","AQ","AR","AT","AV","BA","BG","BI","BL","BN","BO","BR","BS","BT",
										"BZ","CA","CB","CE","CH","CI","CL","CN","CO","CR","CS","CT","CZ","EN","FC","FE","FG","FI","FM","FR","GE","GO","GR",
										"IM","IS","KR","LC","LE","LI","LO","LT","LU","MB","MC","ME","MI","MN","MO","MS","MT","NA","NO","NU","OG","OR","OT",
										"PA","PC","PD","PE","PG","PI","PN","PO","PR","PT","PU","PV","PZ","RA","RC","RE","RG","RI","RM","RN","RO","SA","SI",
										"SO","SP","SR","SS","SV","TA","TE","TN","TO","TP","TR","TS","TV","UD","VA","VB","VC","VE","VI","VR","VS","VT","VV"];
								
								for(var i = 0; i < 111; i++) {
									document.write('<option value="'+arrayProvincie[i]+'">'+arrayProvincie[i]+'</option>');
								}
								</script>
								  
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<label for="mQ">Dimensione (mQ):</label>
							</td>
							<td>
								<input type="number" name="mQ" id="mQ" step="1" min="1" max="1000" value="1" required>
							</td>
						</tr>
						<tr>
							<td>
								<label for="nBagni">Numero Bagni:</label>
							</td>
							<td>
								<input type="number" name="nBagni" id="nBagni" step="1" min="1" max="10" value="1" required>
							</td>
						</tr>
						<tr>
							<td>
								<label for="persone">Numero persone (max):</label>
							</td>
							<td>
								<input type="number" name="persone" id="persone" step="1" min="1" max="10" value="1" required>
							</td>
						</tr>
						<tr>
							<td>
								<label for="prezzo">Prezzo a Notte (Euro):</label>
							</td>
							<td>
								<input type="number" name="prezzo" id="prezzo" step="1" min="1" max="1000" value="1" required>
							</td>
						</tr>
						<tr>
							<td>
								<label for="animali">Animali</label>
							</td>
							<td>
								<input type="checkbox" name="animali" id="animali">
							</td>
						</tr>
						<tr>
							<td>
								<label for="postoAuto">Posto Auto</label>
							</td>
							<td>
								<input type="checkbox" name="postoAuto" id="postoAuto">
							</td>
						</tr>
						<tr>
							<td>
								<label for="giardino">Giardino</label>
							</td>
							<td>
								<input type="checkbox" name="giardino" id="giardino">
							</td>
						</tr>
						<tr>
			                <td>
			                    <label>Descrizione: </label>
			                </td>
			                <td>
			                    <textarea name="descrizione" id="descrizione" style="text-align:left;" placeholder="Inserisci una breve descrizione del locale (facoltativo)" rows="10" cols="30" maxlength="2000" wrap="soft" spellcheck="true"></textarea>
			                </td>
			            </tr>
					</table>
					<p style="text-align:center;">
						<input name="inserisci" type="submit" style="text-align:center; font-size: large;" 
								id="inserisci" value="Inserisci Locale">
					</p>
					<p style="text-align:center;"><%=s%></p>
				</fieldset>
			</form>
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
	
	if(ruolo != "null" && ruolo != "Locatario") {
		link.href = "ListaLocali.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
		link.appendChild(document.createTextNode("LISTA LOCALI"))
		list.appendChild(link);
		list.className = "active";
		menu.appendChild(list);
		
		list = document.createElement("li");
		link = document.createElement("a");
	}
	
	//Legale e Scout non hanno il pulsante prenotazioni
	link.href = "ListaPrenotazioni.jsp?ruolo=<%=ruolo%>&nome=<%=nome%>&ID=<%=ID%>";
	link.appendChild(document.createTextNode("Prenotazioni"))
	list.appendChild(link);
	menu.appendChild(list);
	
	list = document.createElement("li");
	link = document.createElement("a");
	
	//Locatore ha anche il pulsante Locali
	
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
}

function validateKeyStrokes(event) {
    var charCode = (event.which) ? event.which : event.keyCode;

    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 39 || charCode == 32 || 
    		(charCode >= 224 && charCode <= 229) || (charCode >= 232 && charCode <= 246) || (charCode >= 249 && charCode <= 252)) {
        return true;
    }

    return false;
}

function validateKeyStrokes3(event) {
    var charCode = (event.which) ? event.which : event.keyCode;

    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123)) {
        return true;
    }

    return false;
}
</script>
</html>