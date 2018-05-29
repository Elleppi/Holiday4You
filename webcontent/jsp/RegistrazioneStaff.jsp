<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="RegistrazioneBean" scope="request" class="bean.RegistrazioneBean" />

<jsp:setProperty name="RegistrazioneBean" property="*" />
<jsp:useBean id="LoginBean" scope="request" class="bean.LoginBean" />


<%
int e = 0;
String s = "";

String s2 = "";
int formValido = 0;

if (request.getParameter("signin") != null) {
	String usr = request.getParameter("username");
	LoginBean.setUsername(usr);
	String psw = request.getParameter("password");
	LoginBean.setPassword(psw);
	
	if(LoginBean.validate()) {
		String ruolo = LoginBean.getRuolo();
		int ID = LoginBean.getId();
		String nome = LoginBean.getNome();
		%>
		<jsp:forward page="UtenteRegistratoHome.jsp" >
		
		<jsp:param name="ID" value="<%=ID%>"/>
		<jsp:param name="nome" value="<%=nome%>"/>
		<jsp:param name="ruolo" value="<%=ruolo%>"/>
		
		</jsp:forward>
		<%
	}
	
	else
		s2 = "Dati errati !!!";
}

if (request.getParameter("registra") != null) {
	
	if(formValido == 1) {
		e = RegistrazioneBean.convalidaRegistrazione();
	
		s = RegistrazioneBean.errore(e);
		 
		if(e == 0) {
			if(request.getParameter("ruolo").equals("Legale"))
				RegistrazioneBean.setRuolo("Legale");
			else
				RegistrazioneBean.setRuolo("Scout");
			%>
			<jsp:forward page="Avviso.jsp">
			<jsp:param name="avviso" value="6"/>
			</jsp:forward>
			<%
		}
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
  			<p>Benvenuto</p>
  		</div>
  		<form method="POST" id="login">
		    <fieldset>
		    <legend>Login</legend>
		    <p><%=s2%></p>
		    <div id="registrazione"></div>
		    <div id="registrazioneStaff"></div>
		    </fieldset>
	    </form>
	</div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row2">
  <div id="topnav">
    <ul>
      <li><a href="Accesso.jsp">Homepage</a></li>
      <li><a href="RicercaLocale.jsp">Ricerca Locale</a></li>
      <li><a href="FAQ.jsp">FAQ</a></li>
    </ul>
    <div  class="clear"></div>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row4">
	<div id="container" class="clear">
	<div id="content">
		<form method="POST" onsubmit="return validateForm()">
			<fieldset>
				<legend>Modulo di Registrazione</legend>
				<table>
					<tr>
						<td>
							<label for="nome">Nome</label>
						</td>
						<td>
							<input type="text" name="nome" id="nome" onkeypress="return validateKeyStrokes(event)" maxlength="20" placeholder="Nome" required>
						</td>
					</tr>
					<tr>
						<td>
							<label for="cognome">Cognome</label>
						</td>
						<td>
							<input type="text" name="cognome" id="cognome" onkeypress="return validateKeyStrokes(event)" maxlength="20" placeholder="Cognome" required>
						</td>
					</tr>
					<tr>
						<td>
							<label for="codiceFiscale">Codice Fiscale</label>
						</td>
						<td>
							<input type="text" name="codiceFiscale" id="codiceFiscale" class="codiceFiscale" 
								size="20" minlength="16" maxlength="16" placeholder="Codice fiscale" required>
							<style>
								.codiceFiscale {text-transform: uppercase;}
							</style>
						</td>
					</tr>
						<td>
		                    <label>Data di nascita</label>
		                </td>
		                <td>
		                    <input type="date" name="dataNascita" id="dataNascita" required/>
		                </td>	
					<tr>
						<td>
							<label for="user">Username</label>
						</td>
						<td>
							<input type="text" name="user" id="user" maxlength="20" minlength="4" 
								placeholder="min 4 max 20" size="20" required>
						</td>
					</tr>
					<tr>
						<td>
							<label for="psw">Password</label>
						</td>
						<td>
							<input type="password" name="psw" id="psw" maxlength="10" minlength="4" 
								placeholder="min 4 max 10" required>
						</td>
					</tr>
					<tr>
						<td>
							<label for="confermaPsw">Conferma Password</label>
						</td>
						<td>
							<input type="password" name="confermaPsw" id="confermaPsw" maxlength="10" minlength="4" placeholder="Conferma password" required>
						</td>
					</tr>
					<tr>
						<td>
							<label for="telefono">Telefono</label>
						</td>
						<td>
							<input type="text" name="telefono" id="telefono" onkeypress="return validateKeyStrokesNumber(event)"
									maxlength="10" minlength="10" placeholder="Telefono" required>
						</td>
					</tr>
					<tr>
						<td>
							<label for="eMail">eMail</label>
						</td>
						<td>
							<input type="email" name="eMail" id="eMail" placeholder="Indirizzo eMail" required>
						</td>
					</tr>
					<tr>
						<td>
							Scout <input type="radio" name="ruolo" value="Scout" checked>
							Legale <input type="radio" name="ruolo" value="Lagale">
						</td>
						<td>
						</td>
					</tr>
				</table>
				<br>
				<input name="registra" type="submit" id="registra" value="Registra">
				<p><%=s%></p>
			</fieldset>
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
	var benvenuto = document.getElementById("benvenuto");
	var link, list;
	var ruolo = null;
	
	list = document.createElement("li");
	link = document.createElement("a");
	

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

}


function validateKeyStrokes(event) {
    var charCode = (event.which) ? event.which : event.keyCode;

    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 39 || charCode == 32 || 
    		(charCode >= 224 && charCode <= 229) || (charCode >= 232 && charCode <= 246) || (charCode >= 249 && charCode <= 252)) {
        return true;
    }

    return false;
}


function validateKeyStrokesNumber(event) {
    var charCode = (event.which) ? event.which : event.keyCode;

    if (charCode > 47 && charCode < 58) {
        return true;
    }

    return false;
}

function validateForm() {
    var pass1 = document.getElementById("psw").value;
    var pass2 = document.getElementById("confermaPsw").value;
    
    if (pass1 != pass2) {
        document.getElementById("psw").style.borderColor = "#E34234";
        document.getElementById("confermaPsw").style.borderColor = "#E34234";
        alert("Password non confermata");
        return false;
    }
    
    //Definisco un pattern per il confronto
    var pattern = /^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$/;

    var CodiceFiscale = document.getElementById("codiceFiscale");

    //utilizzo il metodo search per verificare che il valore inserito nel campo
    //di input rispetti la stringa di verifica (pattern)
    if (CodiceFiscale.value.search(pattern) == -1) {
    	document.getElementById("codiceFiscale").style.borderColor = "#E34234";
    	alert("Codice Fiscale errato");
    	return false;
    }
    
    var oggi = new Date();
    var dataNascita = document.getElementById("dataNascita").value;
    var anno = oggi.getFullYear() - dataNascita.substring(0, 4);
    var m = oggi.getMonth() - (dataNascita.substring(5, 7) - 1);
    if (m < 0 || (m == 0 && oggi.getDate() < dataNascita.substring(8, 10))) {
    	anno--;
    }

    if(anno < 18) {
    	alert("Devi avere almeno 18 anni per poterti registrare");
    	return false;
    }
    
    <%formValido=1;%>
    return true;
}

</script>
</html>