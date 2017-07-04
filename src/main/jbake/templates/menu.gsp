    <nav class="navbar">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>/#">
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
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>developer/index.html">Developer</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>admin/index.html">Admin</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>advanced/index.html">Advanced</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>security/index.html">Security</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>examples/index-ng.html">Examples</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>blog/index.html">Blog</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>community/index.html">Community</a></li>
                    <li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>download-ng.html">Downloads</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
