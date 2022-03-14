<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Apache TomEE</title>
	<meta name="description"
		  content="Apache TomEE is a lightweight, yet powerful, JavaEE Application server with feature rich tooling." />
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
		<!-- Matomo -->
		var _paq = window._paq = window._paq || [];
		/* tracker methods like "setCustomDimension" should be called before "trackPageView" */
		/* We explicitly disable cookie tracking to avoid privacy issues */
		_paq.push(['disableCookies']);
		_paq.push(['trackPageView']);
		_paq.push(['enableLinkTracking']);
		(function () {
			var u = "//matomo.privacy.apache.org/";
			_paq.push(['setTrackerUrl', u + 'matomo.php']);
			_paq.push(['setSiteId', '5']);
			var d = document, g = d.createElement('script'), s = d.getElementsByTagName('script')[0];
			g.async = true;
			g.src = u + 'matomo.js';
			s.parentNode.insertBefore(g, s);
		})();
		<!-- End Matomo Code -->
    </script>
</head>

<body>
    <div class="preloader">
		<img src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>img/loader.gif" alt="Preloader image">
	</div>