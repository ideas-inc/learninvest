<%@ page import="java.lang.*,java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Edit User Profile</title>
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
        <link rel="stylesheet" href="css/signup.css">
        
        
    
        <script src="js/vendor/modernizr-2.8.3.min.js"></script>
    </head>
    <body class="hero">
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <!-- Add your site or application content here -->
        
        
        <div class="logo container-fluid">
        <center>
                <img class="logo-img img-responsive" src="/project/images/learninvest.PNG">
        </center>
        </div>
		
		
		<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/vts"
     user="root"  password="muthu"/> 

<sql:query dataSource="${snapshot}" var="result">
select * from user;
</sql:query>

<% String uname=(String)session.getAttribute("uname");  
    %>

	<c:set var="user" value="${sessionScope.uname}" />
	
        
        
        
        <div class="container">
        <div class="stext">
        <center>        
        <p>Enter the new details: </p>
        </center>
        </div>
		
		<c:forEach var="row" items="${result.rows}">
        <c:if test="${row.uname==user}"> 
       
       <c:set var="email" value="${row.email}" />
       <c:set var="mobile" value="${row.mobile}" />
       <c:set var="first" value="${row.first}" />
       <c:set var="last" value="${row.last}" />
       
	     </c:if>
		</c:forEach>
         	   
        
        <div>
        <form class="form-horizontal col-xs-offset-2 col-xs-8 " role="form" name="login" method="POST" data-toggle="validator" action="EditUserProfile">
         
            <div class="form-group">
                <label class="control-label col-xs-2" for="uname"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></label>
                      <div class="col-xs-10">
                    <input type="password" name="password" placeholder="New Password" required>
                   </div>
                    <div class="help-block with-errors"></div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-2" for="uname"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></label>
                  <div class="col-xs-10">
                    <input type="password" name="repassword" placeholder="Re-Enter New Password" required>
                   </div>
                    <div class="help-block with-errors"></div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-2" for="uname"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></label>
                  <div class="col-xs-10">
                    <input type="text" name="first" placeholder="First Name" value="${first}" required>
                   </div>
                    <div class="help-block with-errors"></div>
            </div>
            
            <div class="form-group">
                <label class="control-label col-xs-2" for="uname"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></label>
                  <div class="col-xs-10">
                    <input type="text" name="last" placeholder="Last Name" value="${last}" required>
                   </div>
                    <div class="help-block with-errors"></div>
            </div>
            
            <div class="form-group">
                <label class="control-label col-xs-2" for="uname"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></label>
                  <div class="col-xs-10">
                    <input type="email" name="email" placeholder="Email Id" value="${email}" required>
                   </div>
                    <div class="help-block with-errors"></div>
            </div>    
            
            
            <div class="form-group">
                <label class="control-label col-xs-2" for="uname"><span class="glyphicon glyphicon-phone" aria-hidden="true"></span></label>
                  <div class="col-xs-10">
                    <input type="tel" name="mobile" placeholder="Mobile Number" value="${mobile}" required>
                   </div>
                    <div class="help-block with-errors"></div>
            </div>    
            
            
           <!-- <div class="form-group">
                <label class="control-label col-xs-2" for="uname"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></label>
                  <div class="col-xs-10">
                    <input type="date" name="dob" placeholder="DOB" required>
                   </div>
                    <div class="help-block with-errors"></div>
            </div>-->   
            
             <div class="form-group">
                <div class="col-sm-offset-2">
                <input class="but" type="submit" value="Save Changes to Profile" style="width:300px;">
                </div>
            </div>
        </form>
        </div>
       
            
        
        </div>
        
        <footer>
        <center>
            <p><span class="">&copy;Copyright 2016 IDEAS INC.</span></p>
            <span> Website Design and Development : <a href="#">Muthu Annamalai CT   </a> &amp; <a href="#">  Manish Chandra C</a></span>
        </center>   
        </footer>   
        
        

        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>

        <!-- Google Analytics: change UA-XXXXX-X to be your site's ID. -->
        <script>
            (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
            function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
            e=o.createElement(i);r=o.getElementsByTagName(i)[0];
            e.src='https://www.google-analytics.com/analytics.js';
            r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
            ga('create','UA-XXXXX-X','auto');ga('send','pageview');
        </script>
        
        <script src="./js/bootstrap.min.js"></script>
        <script src="js/jquery.easing.min.js"></script>
        <script src="js/scrolling-nav.js"></script>
        <script src="js/main.js"></script>
        
    </body>
</html>
