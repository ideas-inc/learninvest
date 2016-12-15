<%@ page import="java.lang.*,java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 
<html>
<head>

<meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Add Stock</title>
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
    <link rel="stylesheet" href="css/dashboard.css">
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>
<link rel="stylesheet" href="css/main.css">
</head>

<body> 

        <div class="logo container-fluid">
        <center>
                <img class="logo-img img-responsive" src="/project/images/learninvest.PNG">
        </center>
        </div>


   
<br/>
<br/>

<div class="container">

<form method="post" action="AddStock" style="    border: 2px solid #00803F;padding: 5%;font-family: 'Bree Serif', serif;">
<h2 class="text-center">NEW STOCK DETAILS</h2>
<label for="company"> Enter the company name :  
</label><br/>
<input type="text" name="company" required>
<br/><br/>

<label for="ticker"> Enter ticker of the company's stock :  
</label><br/>
<input type="text" name="ticker" required>
<br/><br/>

<label for="price">Ticker price (INR) :
</label><br/>
<input type="number" step="0.001" min="0.000" name="price" required>
<br/><br/>

<label > Previous closing price (INR) :
</label><br/>
<input type="number" step="0.001" min="0.000" name="pc" required>
<br/><br/>

<label > Day's high price (INR) :
</label><br/>
<input type="number" step="0.001" min="0.000" name="high" required>
<br/><br/>

<label > Day's low price (INR) :
</label><br/>
<input type="number" step="0.001" min="0.000" name="low" required>
<br/><br/>

<label > Trade volume units :
</label><br/>
<input type="number" min="0" name="tradevol" required>
<br/><br/>

<label > Sector :
</label><br/>
<input type="text" name="sector" required>
<br/><br/>
<center>
<input type="submit" value="Add new stock" class="bbut">    
</center>
</form>
</div>

<br/>
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
  