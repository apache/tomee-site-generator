<div style="margin-bottom: 30px;"></div>
<footer>
		<div class="container">
			<div class="row">
				<div class="col-sm-6 text-center-mobile">
					<h3 class="white">Be simple.  Be certified. Be Tomcat.</h3>
					<h5 class="light regular light-white">"A good application in a good server"</h5>
					<ul class="social-footer">
						<li><a href="https://www.facebook.com/ApacheTomEE/"><i class="fa fa-facebook"></i></a></li>
						<li><a href="https://twitter.com/apachetomee"><i class="fa fa-twitter"></i></a></li>
					</ul>
					<h5 class="light regular light-white">
						<a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>privacy-policy.html" class="white">Privacy Policy</a>
					</h5>
				</div>
				<div class="col-sm-6 text-center-mobile">
					<div class="row opening-hours">
						<div class="col-sm-3 text-center-mobile">
							<h5><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/docs/" class="white">Documentation</a></h5>
							<ul class="list-unstyled">
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/docs/admin/configuration/index.html" class="regular light-white">How to configure</a></li>
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/docs/admin/file-layout.html" class="regular light-white">Dir. Structure</a></li>
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/docs/developer/testing/index.html" class="regular light-white">Testing</a></li>
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/docs/admin/cluster/index.html" class="regular light-white">Clustering</a></li>
							</ul>
						</div>
						<div class="col-sm-3 text-center-mobile">
							<h5><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/examples/" class="white">Examples</a></h5>
							<ul class="list-unstyled">
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/examples/simple-cdi-interceptor.html" class="regular light-white">CDI Interceptor</a></li>
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/examples/rest-cdi.html" class="regular light-white">REST with CDI</a></li>
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/examples/ejb-examples.html" class="regular light-white">EJB</a></li>
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/examples/jsf-managedBean-and-ejb.html" class="regular light-white">JSF</a></li>
							</ul>
						</div>
						<div class="col-sm-3 text-center-mobile">
							<h5><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>community/index.html" class="white">Community</a></h5>
							<ul class="list-unstyled">
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>community/contributors.html" class="regular light-white">Contributors</a></li>
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>community/social.html" class="regular light-white">Social</a></li>
								<li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>community/sources.html" class="regular light-white">Sources</a></li>
							</ul>
						</div>
						<div class="col-sm-3 text-center-mobile">
							<h5><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>security/index.html" class="white">Security</a></h5>
							<ul class="list-unstyled">
								<li><a href="https://apache.org/security" target="_blank" class="regular light-white">Apache Security</a></li>
								<li><a href="https://apache.org/security/projects.html" target="_blank" class="regular light-white">Security Projects</a></li>
								<li><a href="https://cve.mitre.org" target="_blank" class="regular light-white">CVE</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row bottom-footer text-center-mobile">
				<div class="col-sm-12 light-white">
					<p>Copyright &copy; 1999-2022 The Apache Software Foundation, Licensed under the Apache License, Version 2.0. Apache TomEE, TomEE, Apache, the Apache feather logo, and the Apache TomEE project logo are trademarks of The Apache Software Foundation. All other marks mentioned may be trademarks or registered trademarks of their respective owners.</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- Holder for mobile navigation -->
	<div class="mobile-nav">
        <ul>
          <li><a hef="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/docs/admin/index.html">Administrators</a>
          <li><a hef="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/docs/developer/index.html">Developers</a>
          <li><a hef="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>latest/docs/advanced/index.html">Advanced</a>
          <li><a hef="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>community/index.html">Community</a>
        </ul>
		<a href="#" class="close-link"><i class="arrow_up"></i></a>
	</div>
	<!-- Scripts -->
	<script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/jquery-1.11.1.min.js"></script>
	<script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/owl.carousel.min.js"></script>
	<script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/bootstrap.min.js"></script>
	<script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/wow.min.js"></script>
	<script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/typewriter.js"></script>
	<script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/jquery.onepagenav.js"></script>
	<script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/tree.jquery.js"></script>
	<script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/highlight.pack.js"></script>
    <script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/main.js"></script>
		</body>

</html>
