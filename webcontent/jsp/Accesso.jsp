<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="LoginBean" scope="request" class="bean.LoginBean" />

<%
	String s = "";
	
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
			s = "Dati errati !!!";
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
        <p><%=s%></p>
        <input type="password" id="password" name="password" required/>
        <input type="text" id="username" name="username" required/>
        <div id="registrazione">NON SEI REGISTRATO ? REGISTRATI <a href="RegistrazioneUtente.jsp"> qui</a></div>
        <input type="image" src="layout/images/sign_in.gif" id="signin"/>
        <input type="hidden" name="signin">
        <div id="registrazioneStaff">VUOI LAVORARE CON NOI ? CLICCA <a href="RegistrazioneStaff.jsp"> qui</a></div>
      </fieldset>
    </form>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row2">
  <div id="topnav">
    <ul>
      <li class="active"><a href="">Homepage</a></li>
      <li><a href="RicercaLocale.jsp">Ricerca Locale</a></li>
      <li><a href="FAQ.jsp">FAQ</a></li>
    </ul>
    <div  class="clear"></div>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row4">
<div id="container" class="clear">
	<img id="imgBanner" src="" alt="" height="500" width="960"/>
</div>
</div>
<div class="wrapper row3">
</div>
<div class="wrapper">
  <div id="copyright" class="clear">
    <p style="text-align: center;">Università degli Studi di Roma Tor Vergata - Ingegneria del Software e Progettazione Web - Lorenzo Paris, Giovanni D'Agostino</a></p>
    
  </div>
</div>
<!-- liteAccordion is Homepage Only --> 
</body>
<script type = "text/javascript">
	var picPaths = ['image/casa1.jpg','image/casa2.jpg','image/casa3.jpg','image/casa4.jpg',
	                'image/casa5.jpg', 'image/casa6.jpg', 'image/casa7.jpg'];
	var curPic = -1;
	//preload the images for smooth animation
	var imgO = new Array();
	for(i=0; i < picPaths.length; i++) {
	    imgO[i] = new Image();
	    imgO[i].src = picPaths[i];
	}
	
	function swapImage() {
	    curPic = (++curPic > picPaths.length-1)? 0 : curPic;
	    imgCont.src = imgO[curPic].src;
	    setTimeout(swapImage,5000);
	}
	
	window.onload=function() {
	    imgCont = document.getElementById('imgBanner');
	    swapImage();
	}
</script>
</html>