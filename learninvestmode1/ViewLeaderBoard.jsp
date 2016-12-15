<%@ page import="java.lang.*,java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 
<html>
<head>

<meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Leader Board</title>
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
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>
</head>

<body> 

        <div class="logo container-fluid">
        <center>
                <img class="logo-img img-responsive" src="/project/images/learninvest.PNG">
        </center>
        </div>

<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/vts"
     user="root"  password="muthu"/> 
	 

<sql:query dataSource="${snapshot}" var="result">
select * from user order by pvalue desc;
</sql:query>


<h2 class="text-center">USER LEADERBOARD</h2>
<p class="text-center visible-xs" style="font-size:15px;">(Scroll Right to view Complete Table)</p>
<div class="container">
<div class="col-sm-offset-1 col-sm-9 stock-table-div table-responsive">
<table class="table table-bordered text-center">
<tbody style="font-family: 'Bree Serif', serif !important;">
<tr>   
    <th>Username</th>
    <th>First name</th>
    <th>Last name</th>
    <th>Portfolio Value</th>
    <th>Cash</th>
	<th>Profit/Loss %</th>
</tr>



<c:forEach var="row" items="${result.rows}">

<tr>

   <td><c:out value="${row.uname}"/></td>
   <td><c:out value="${row.first}"/></td>
   <td><c:out value="${row.last}"/></td>
   <td><i class="fa fa-inr"></i> <c:out value="${row.pvalue}"/></td>
   <td><i class="fa fa-inr"></i> <c:out value="${row.cash}"/></td>
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
 <center>
 <a href="admin-dash.jsp"><input style="font-size:20px;" class="bbut" type="button" value="Admin Dashboard"></a>
 </center>
 
 <br/><br/>
  <footer>
        <center>
            <p><span class="">&copy;Copyright 2016 IDEAS INC.</span></p>
            <span> Website Design and Development : <a href="#">Muthu Annamalai CT   </a> &amp; <a href="#">  Manish Chandra C</a></span>
        </center>   
  </footer>   
        
 </body>
</html>
