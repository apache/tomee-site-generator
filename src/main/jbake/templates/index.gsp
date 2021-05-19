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
                            <h2 class="white">Annoucment of compatibility</h2>
                        </div>
                    </div>
                    <div style="opacity: 0;" class="row animated fadeInUp text-left header-links">
                        <div class="col-md-3 no-padding">
                        </div>
                        <div class="col-md-3 no-padding">
                            <a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>/docs.html">
                                <div class="link-block">
                                    <i class="icon_documents_alt white circled-icon"></i>
                                    <div class="bottom">
                                        <h4 class="white">Documentation</h4>
                                        <h5 class="white">Learn more about Apache TomEE</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-3 no-padding">
                            <a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>/community/index.html">
                                <div class="link-block">
                                    <i class="icon_group white circled-icon"></i>
                                    <div class="bottom">
                                        <h4 class="white">Community</h4>
                                        <h5 class="white">How can I contribute to TomEE?</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-3 no-padding">
                            <a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>/download-ng.html">
                                <div class="link-block">
                                    <i class="icon_download white circled-icon"></i>
                                    <div class="bottom">
                                        <h4 class="white">Downloads</h4>
                                        <h5 class="white">How can I download Apache TomEE?</h5>
                                    </div>
                                </div>
                            </a>
                        </div>
			        </div>
                </div>
            </div>
        </div>
    </header>
    <section>
		<div class="container">
			<div class="row animated fadeInUp d-flex section" style="opacity: 0;">
				<div class="col-md-9 text-center m-auto">
                        <h3 class="lead muted">Apache TomEE, pronounced “Tommy”, is an all-Apache Jakarta EE 9.1 certified application server extends Apache Tomcat that is assembled from a vanilla Apache Tomcat zip file. We start with Apache Tomcat, add our jars, and zip up the rest.
                            The result is Tomcat plus EE features - TomEE.</h3>
				</div>
			</div>
            <div class="row animated fadeInUp d-flex section no-padding" style="opacity: 0;">  
				<div class="col-md-6 d-flex f-column-start accented-block accent-violet f-space-between">
                    <div class="block-content">
                        <h5 class="muted">Final Release - javax namespace</h5>
                        <h4 class="muted">apache-tomee-8.0.4</h4>
                        <p>More academics should blog, post videos, post audio, post lectures, offer articles and more. You'll enjoy it: I've had threats and blackmail, abuse, smears and formal complaints with forged documentation.</p>
                    </div>
                    <a class="btn btn-accent btn-primary square"><i class="icon_plus"></i>Learn more</a>
				</div>
                <div class="col-md-6 d-flex f-column-end accented-block accent-red f-space-between">
                    <div class="block-content">
                        <h5 class="muted">Latest Release - Jakarta namespace</h5>
                        <h4 class="muted">apache-tomee-9.0.0-M1</h4>
                        <p>
                            More academics should blog, post videos, post audio, post lectures, offer articles and more.
                            <br>
                            <div class="compatible-badge">Jakarte EE Compatible</div>
                        </p>
                    </div>
                    <a class="btn btn-accent btn-primary square"><i class="icon_plus"></i>Learn more</a>
				</div>
			</div>
            <div class="row animated fadeInUp d-flex section" style="opacity: 0;">  
				<div class="col-md-6 d-flex f-space-between">
                    <div class="flavours-block">
                        <div class="flavours-title">
                            Flavors
                        </div>
                        <div class="flavour flavour-plus">
                            <div class="flavour-icon">+</div>
                            <div class="flavour-content">
                                <div class="flavour-artifact">APACHE TOMEE</div>
                                <div class="flavour-identifier">PLUS</div>
                            </div>
                        </div>
                        <div class="flavour flavour-plume">
                            <div class="flavour-icon">P</div>
                            <div class="flavour-content">
                                <div class="flavour-artifact">APACHE TOMEE</div>
                                <div class="flavour-identifier">PLUME</div>
                            </div>
                        </div>
                        <div class="flavour flavour-webprofile">
                            <div class="flavour-icon">W</div>
                            <div class="flavour-content">
                                <div class="flavour-artifact">APACHE TOMEE</div>
                                <div class="flavour-identifier">WEBPROFILE</div>
                            </div>
                        </div>
                        <div class="flavour flavour-microprofile">
                            <div class="flavour-icon">M</div>
                            <div class="flavour-content">
                                <div class="flavour-artifact">APACHE TOMEE</div>
                                <div class="flavour-identifier">MICROPROFILE</div>
                            </div>
                        </div>
                    </div>
				</div>
                <div class="col-md-6 d-flex f-column-start accented-block accent-orange f-space-between">
                    <div class="block-content">
                        <h5 class="muted">What is your flavor?</h5>
                        <h4 class="muted">TomEE Flavors</h4>
                        <p>
                            Apache TomEE comes with four different flavors, Web Profile, Plus and Plume. Apache TomEE Web Profile delivers Servlets, JSP, JSF, JTA, JPA, CDI, Bean Validation and EJB Lite. Apache TomEE JAX-RS (RESTfull Services) delivers the Web Profile plus JAX-RS (RESTfull Services).
                        </p>
                    </div>
                    <a class="btn btn-accent btn-primary square"><i class="icon_plus"></i>Learn more</a>
				</div>
			</div>    
        </div>
    </section>
    <section class="committers-info-section">
        <div class="container">
            <div class="row animated fadeInUp" style="opacity: 0;">  
				<div class="col-md-3">
                    <div class="info-count">450</div>
                    <div class="info-title">Committers</div>
				</div>
                <div class="col-md-3">
                    <div class="info-count">10k</div>
                    <div class="info-title">Coffees</div>
				</div>
                <div class="col-md-3">
                    <div class="info-count">300k</div>
                    <div class="info-title">Lines of code</div>
				</div>
                <div class="col-md-3">
                    <div class="info-count">120k</div>
                    <div class="info-title">Commits</div>
				</div>
			</div>
        </div>
    </section>
    <section>
		<div class="container">
            <div class="row animated fadeInUp d-flex section" style="opacity: 0;">  
				<div class="col-md-6 d-flex f-column-start accented-block accent-violet f-space-between">
                    <div class="block-content">
                        <h5 class="muted">Learn TomEE with</h5>
                        <div class="jakarta-ee-logo"></div>
                        <p>Jakarta EE, powered by participation, is focused on enabling community-driven collaboration and open innovation for the cloud. </p>
                    </div>
                    <a class="btn btn-accent btn-primary square"><i class="icon_plus"></i>Learn more</a>
				</div>
                <div class="col-md-6 d-flex f-column-end accented-block accent-red f-space-between">
                    <div class="block-content">
                        <h5 class="muted">Learn TomEE with</h5>
                        <div class="microprofile-logo"></div>
                        <p>
                            MicroProfile is an open forum that optimizes Enterprise Java for a microservice architecture by innovating across multiple implementations and collaborating on common areas of interest with a goal of standardization.
                        </p>
                    </div>
                    <a class="btn btn-accent btn-primary square"><i class="icon_plus"></i>Learn more</a>
				</div>
			</div>
		</div>
	</section>
  </div>
<%include "footer.gsp"%>
