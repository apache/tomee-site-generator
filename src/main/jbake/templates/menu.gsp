    <nav class="navbar">
		<div class="container">
		  <div class="row">          <div class="col-md-12">

			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">
				    <span>

				    <% if (content.uri && content.uri == '/index.html') { %>
				        <img
                            src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>img/logo.png"
                            data-active-url="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>img/logo-active.png">
                    <% } else { %>
                        <img src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>img/logo-active.png">
                    <% } %>

                    </span>
				    Apache TomEE
                </a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right main-nav">
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>docs.html">Documentation</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>community/index.html">Community</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>security/index.html">Security</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>download-ng.html">Downloads</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		   </div></div>
		</div>
		<!-- /.container-fluid -->
	</nav>
