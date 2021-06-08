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
				<a class="navbar-brand" href="/" title="Apache TomEE">
				    <span>

				    <% if (content.uri && content.uri == '/index.html') { %>
				        <img
                            src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>img/apache_tomee-logo-white.svg"
                            data-active-url="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>img/apache_tomee-logo.svg"
							onerror="this.src='<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>img/apache_tomee-logo.jpg'"
							height="50"
							>
                    <% } else { %>
                        <img 
							src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>img/apache_tomee-logo.svg"
							onerror="this.src='<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>img/apache_tomee-logo.jpg'"
							height="50"
							>
                    <% } %>

                    </span>
                </a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right main-nav">
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>docs.html">Documentation</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>community/index.html">Community</a></li>
					<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>security/security.html">Security</a></li>
					<li><a class="btn btn-accent accent-orange no-shadow" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>download.html">Downloads</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		   </div></div>
		</div>
		<!-- /.container-fluid -->
	</nav>
