<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Apache TomEE</title>
	<meta name="description" content="Apache TomEE is a light JavaEE server with a lot tooling" />
	<meta name="keywords" content="tomee,asf,apache,javaee,jee,shade,embedded,test,junit,applicationcomposer,maven,arquillian" />
	<meta name="author" content="Luka Cvetinovic for Codrops" />
	<link rel="icon" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>favicon.ico">
	<link rel="icon"  type="image/png" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>favicon.png">
	<meta name="msapplication-TileColor" content="#80287a">
	<meta name="theme-color" content="#80287a">
	<link rel="stylesheet" type="text/css" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/normalize.css">
	<link rel="stylesheet" type="text/css" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/owl.css">
	<link rel="stylesheet" type="text/css" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/animate.css">
	<link rel="stylesheet" type="text/css" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>fonts/font-awesome-4.1.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>fonts/eleganticons/et-icons.css">
	<link rel="stylesheet" type="text/css" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/jqtree.css">
	<link rel="stylesheet" type="text/css" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/idea.css">
	<link rel="stylesheet" type="text/css" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/cardio.css">

	<script type="text/javascript">

      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-2717626-1']);
      _gaq.push(['_setDomainName', 'apache.org']);
      _gaq.push(['_trackPageview']);

      (function() {
        var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
      })();

    </script>
</head>

<body>
    <div class="preloader">
		<img src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>img/loader.gif" alt="Preloader image">
	</div>