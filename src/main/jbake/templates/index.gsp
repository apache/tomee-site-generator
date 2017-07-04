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
                            <h2 class="white typed">The Embedded or Remote EE Application Server</h2>
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
                        <h5 class="white heading hide-hover">TomEE for administrators</h5>
                        <div class="bottom">
                            <h4 class="white heading small-heading no-margin regular">I'm an admin</h4>
                            <a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>admin/index.html" class="btn btn-white-fill expand">Learn more...</a>
                        </div>
                    </div>
				</div>
				<div class="col-md-4">
					<div class="intro-table intro-table2 intro-table-hover2 intro-table-hover">
						<h5 class="white heading hide-hover">TomEE for developers</h5>
						<div class="bottom">
							<h4 class="white heading small-heading no-margin regular">I'm a developer</h4>
							<a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>developer/index.html" class="btn btn-white-fill expand">Learn more...</a>
						</div>
					</div>
				</div>
				<div class="col-md-4">
                    <div class="intro-table intro-table3 intro-table-hover3 intro-table-hover">
                        <h5 class="white heading hide-hover">Advanced usages</h5>
                        <div class="bottom">
                            <h4 class="white heading small-heading no-margin regular">Too easy, let's go further</h4>
                            <a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>advanced/index.html" class="btn btn-white-fill expand">Learn more...</a>
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</section>
  </div>
<%include "footer.gsp"%>
