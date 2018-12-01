<%include "header.gsp"%>
	<%include "menu.gsp"%>
  <div id="main-block">
    <header id="intro">
        <div class="container">
            <div class="table">
                <div class="header-text">
                    <div class="row">
                        <div class="col-md-12 text-center index-title">
                            Apache TomEE
                        </div>
                        <div class="col-md-12 text-center">
                            <h2 class="white typed">MicroProfile and Jakarta EE on Tomcat</h2>
                            <span class="typed-cursor">|</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <section>
		<div class="cut cut-top" style="border-right-width: 1899px;"></div>
		<div class="container">
			<div class="row intro-tables animated fadeInUp" style="opacity: 0;">
				<div class="col-md-4">
				    <div class="intro-table intro-table1 intro-table-hover1 intro-table-hover">
                        <h5 class="white heading hide-hover">Learn more about TomEE</h5>
                        <div class="bottom">
                            <h4 class="white heading small-heading no-margin regular">Documentation</h4>
                            <a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>/docs.html" class="btn btn-white-fill expand">Learn more...</a>
                        </div>
                    </div>
				</div>
				<div class="col-md-4">
					<div class="intro-table intro-table2 intro-table-hover2 intro-table-hover">
						<h5 class="white heading hide-hover">How can I contribute to TomEE?</h5>
						<div class="bottom">
							<h4 class="white heading small-heading no-margin regular">Community</h4>
							<a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>/community/index.html" class="btn btn-white-fill expand">Learn more...</a>
						</div>
					</div>
				</div>
				<div class="col-md-4">
                    <div class="intro-table intro-table3 intro-table-hover3 intro-table-hover">
                        <h5 class="white heading hide-hover">How can I download TomEE?</h5>
                        <div class="bottom">
                            <h4 class="white heading small-heading no-margin regular">Downloads</h4>
                            <a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>/download-ng.html" class="btn btn-white-fill expand">Learn more...</a>
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</section>
  </div>
<%include "footer.gsp"%>
