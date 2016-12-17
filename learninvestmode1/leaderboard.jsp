<%@ page import="java.lang.*,java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<html>
<head>

<meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta http-equiv="refresh" content="10" />
    <title>Leaderboard</title>
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
select * from user order by pvalue desc;
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
						<li><a class="nav-link active" href="leaderboard.jsp"> <i class="fa fa-trophy"></i> LEADERBOARD</a></li>
						<li><a class="nav-link" href="UserProfile.jsp"> <i class="fa fa-user"> </i>VIEW PROFILE</a></li>
                        <li><a class="nav-link" href="ViewStock.jsp"> <i class="fa fa-line-chart"></i> VIEW STOCK</a></li>
                        <li><a class="nav-link" href="LogOut"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> LOGOUT</a></li>
                    </ul>
                </center>
            </div>

        </div>
    </nav>


<h2 style="margin-top:120px;" class="text-center"><i class="fa fa-trophy"></i> USER LEADERBOARD</h2>
<p class="text-center visible-xs" style="font-size:15px;">(Scroll Right to view Complete Table)</p>
<div class="container">
<div class="col-sm-offset-1 col-sm-9 table-responsive">
<table class="table table-bordered text-center">
<tbody style="font-family: 'Bree Serif', serif !important;">
<tr>
    <th>Rank</th>
	<th>Username</th>
    <th>First name</th>
    <th>Last name</th>
    <th>Portfolio Value </th>
	<th>Profit/Loss %</th>
</tr>

<c:set var="rank" value="0" />

<c:forEach var="row" items="${result.rows}">

<tr>
   <c:set var="rank" value="${rank + 1}" />
   <td> <c:out value="${rank}"/>  </td>
   <td><c:out value="${row.uname}"/></td>
   <td><c:out value="${row.first}"/></td>
   <td><c:out value="${row.last}"/></td>
   <td> <i class="fa fa-inr"></i> <c:out value="${row.pvalue}"/></td>
   <c:set var="d" value="${(row.pvalue-row.capital)}" />
   <c:set var="c" value="${row.capital}" />
   <c:set var="e" value="${d*100}" />
   <td> <jsp:text> ${e/c} %</jsp:text> </td>

</tr>

</c:forEach>
</tbody>
 </table>
 </div>
 </div>





<br/>
<br/>
<br/>

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
