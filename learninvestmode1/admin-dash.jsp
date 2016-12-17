<!doctype html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Admin Dashboard</title>
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
        
        
        
        <div class="container-fluid">
        
        
        <div class="row">
		<center>
		<h2>ADMIN DASHBOARD</h2>
		
        <form class="form-horizontal col-xs-12" role="form" name="login" method="post" data-toggle="validator" action="SendMessage">
            <div class="form-group">
                <div>
                    <input style="font-family: 'Bree Serif';width:300px" name="info" placeholder="Type Notification..." required/>
                </div>
                <div class="help-block with-errors"></div>
            </div>    
            
            <div class="form-group">
                <div>
                <input class="but" type="submit" value="Send notification">
                </div>
            </div>
        </form>
		
		
		<form class="form-horizontal col-xs-12 role="form" name="login" method="post" data-toggle="validator" action="AdminViewStock.jsp">
             
            
            <div class="form-group">
                <div>
                <input class="but" type="submit" value="View All Stocks">
                </div>
            </div>
        </form>
		
		<form class="form-horizontal col-xs-12 role="form" name="login" method="post" data-toggle="validator" action="ChangeStockPrice.jsp">
             
            
            <div class="form-group">
                <div>
                <input class="but" type="submit" value="Change Stock Price">
                </div>
            </div>
        </form>
        
		
		<form class="form-horizontal col-xs-12 role="form" name="login" method="post" data-toggle="validator" action="ViewLeaderBoard.jsp">
             
            
            <div class="form-group">
                <div>
                <input class="but" type="submit" value="View Leader Board">
                </div>
            </div>
        </form>
		
		
		<form class="form-horizontal col-xs-12 role="form" name="login" method="post" data-toggle="validator" action="ViewNotifications.jsp">
             
            
            <div class="form-group">
                <div>
                <input class="but" type="submit" value="View Notifications">
                </div>
            </div>
        </form>
		
		
		<form class="form-horizontal col-xs-12 role="form" name="login" method="post" data-toggle="validator" action="AddStock.jsp">
             
            
            <div class="form-group">
                <div>
                <input class="but" type="submit" value="Add Stock">
                </div>
            </div>
        </form>
		
		
		<form class="form-horizontal col-xs-12 role="form" name="login" method="post" data-toggle="validator" action="DeleteStock.jsp">
             
            
            <div class="form-group">
                <div>
                <input class="but" type="submit" value="Delete Stock">
                </div>
            </div>
        </form>
		
		
             
            
            <div class="form-group">
                <div>
               <a href="http://139.59.46.171/learninvestmode1/adminmanual.pdf"  target="_blank" > <input class="but" type="submit" value="Admin Manual"> </a>
                </div>
            </div>
            
		
		
		<form class="form-horizontal col-xs-12 role="form" name="login" method="post" data-toggle="validator" action="AdminLogOut">
             
            
            <div class="form-group">
                <div>
                <input class="but" type="submit" value="Log Out">
                </div>
            </div>
        </form>
		
        
         </center> 
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
