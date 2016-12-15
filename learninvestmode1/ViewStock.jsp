<%@ page import="java.lang.*,java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 


<html>
<head>
<title>Stock Details</title>
<meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Learn.Invest</title>
	<link rel="stylesheet" href="font-awesome-4.6.3/css/font-awesome.min.css">
    <link rel="icon" type="image/png" href="/project/images/logonav.png">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="apple-touch-icon" href="apple-touch-icon.png">
    <!-- Place favicon.ico in the root directory -->
    <link href='css/breeserif.css' rel='stylesheet' type='text/css'>
    <link href="css/cinzel.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/dashboard.css">
    <link rel="stylesheet" href="css/viewstock.css">
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>
</head>

<body> 
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/vts"
     user="root"  password="muthu"/> 

<sql:query dataSource="${snapshot}" var="result">
select * from stock;
</sql:query>


<nav class="navbar navbar-default navbar-fixed-top nv" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header page-scroll">

             
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                <a href="#" class="">
            <img src="img/logonav.png" id="nav-logo"></img>
             </a><span class="nvbtext">LEARN.INVEST</span></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <center>
                    <ul class="nav navbar-nav navbar-right" id="nav-list">
					    <li><a class="nav-link" href="notifications.jsp"> <i class="fa fa-bell"></i> NOTIFICATIONS</a></li>
						<li><a class="nav-link" href="dashboard.jsp"><i class="fa fa-users"></i>DASHBOARD</a></li>
                        <li><a class="nav-link" href="leaderboard.jsp"> <i class="fa fa-trophy"></i> LEADERBOARD</a></li>
						<li><a class="nav-link" href="UserProfile.jsp"> <i class="fa fa-user"> </i>VIEW PROFILE</a></li>
                        <li><a class="nav-link active" href="ViewStock.jsp"> <i class="fa fa-line-chart"> </i>VIEW STOCK</a></li>
                        <li><a class="nav-link" href="LogOut"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> LOGOUT</a></li>
                    </ul>
                </center>
            </div>
            
        </div>
    </nav>


<p class="text-center vtxt1"> LIST OF AVAILABLE STOCKS FROM NATIONAL STOCK EXCHANGE (NSE)
</p>
<p class="text-center vtxt2"> ALL PRICE VALUES IN (INR)
</p>

<p class="text-center"  style="font-size:12px;"> (CLICK PANEL TITLE TO TOGGLE MIN/MAX)
</p>
<!--
<table border="1" width="100%">
<tr>   
    <th>Company</th>
    <th>Ticker</th>
    <th>Unit Price</th>
    <th>Previous Close</th>
    <th>Day's High</th>
    <th>Day's Low</th>
    <th>Trade volume</th>
</tr>
<c:forEach var="row" items="${result.rows}">
<tr>
   <td><c:out value="${row.company}"/></td>
   <td><c:out value="${row.ticker}"/></td>
   <td><c:out value="${row.price}"/></td>
   <td><c:out value="${row.pc}"/></td>
   <td><c:out value="${row.high}"/></td>
   <td><c:out value="${row.low}"/></td>
   <td><c:out value="${row.tradevol}"/></td>
   
</tr> 
</c:forEach>
 </table>
 -->

<c:set var="count" value="0" scope="page" />

<div class="container">
<c:forEach var="row" items="${result.rows}">
  <c:set var="count" value="${count + 1}" scope="page"/>
  <div class="panel-group" id="accordion">
    <div class="panel panel-default">
	 <a style="text-decoration:none;" data-toggle="collapse" data-parent="#accordion" href="#${count}">
      <div style="position:relative;background-color:#00803F;color:#fefefe;" class="panel-heading">
        <h4 class="panel-title">
         
            <c:out value="${row.company}"/>
        </h4>
	
		<i style="position:absolute;top:8px;right:8px;" class="fa fa-caret-down"></i>
      </div>
	  </a>
      <div id="${count}" class="panel-collapse collapse in">
        <div class="panel-body">
          <p>Company : <span><c:out value="${row.company}"/></span> </p>
          <p>Ticker : <span><c:out value="${row.ticker}"/></span></p>
          <p>Unit Price : <i class="fa fa-inr"></i> <span><c:out value="${row.price}"/></span></p>
          <p>Previous Close :  <i class="fa fa-inr"></i> <span><c:out value="${row.pc}"/></span></p>
          <p>Day's High : <i class="fa fa-inr"></i> <span><c:out value="${row.high}"/></span></p>
          <p>Day's Low : <i class="fa fa-inr"></i> <span><c:out value="${row.low}"/></span></p>
          <p>Trade volume : <span><c:out value="${row.tradevol}"/></span></p>
		  <p>Sector : <span><c:out value="${row.sector}"/></span></p>
        </div>
      </div>
    </div>
  </div> 
</c:forEach>
</div>


<footer>
        <center>
            <p><span class="">&copy;Copyright 2016 IDEAS INC.</span></p>
            <span> Website Design and Development : <a href="#">Muthu Annamalai CT   </a> &amp; <a href="#">  Manish Chandra C</a></span>
        </center>
    </footer>
    <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script>
    window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')
    </script>
    <script src="js/plugins.js"></script>
    <script src="js/main.js"></script>
    <!-- Google Analytics: change UA-XXXXX-X to be your site's ID. -->
    <script>
    (function(b, o, i, l, e, r) {
        b.GoogleAnalyticsObject = l;
        b[l] || (b[l] =
            function() {
                (b[l].q = b[l].q || []).push(arguments)
            });
        b[l].l = +new Date;
        e = o.createElement(i);
        r = o.getElementsByTagName(i)[0];
        e.src = 'https://www.google-analytics.com/analytics.js';
        r.parentNode.insertBefore(e, r)
    }(window, document, 'script', 'ga'));
    ga('create', 'UA-XXXXX-X', 'auto');
    ga('send', 'pageview');
    </script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/scrolling-nav.js"></script>
    <script src="js/main.js"></script>

</body> 
</html>