<%include "header.gsp"%>
	<%include "menu.gsp"%>

    <div id="main-block" class="container main-block">
        <div class="row title">
            <div class='page-header'>
              <%if (content.containsKey('tomeepdf')) {%>
              <div class='btn-toolbar pull-right'>
                <div class='btn-group'>
                    <a class="btn" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>${content.uri.replace('html', 'pdf')}"><i class="fa fa-file-pdf-o"></i> Download as PDF</a>
                </div>
              </div>
              <% } %>
              <h2>${content.title}</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 contributors">
              <div class="text-center" style="padding-bottom: 2em;">We want to thank the following individuals for contributing to Apache TomEE. An up to date contributor list can be found <a href="https://github.com/apache/tomee/graphs/contributors">here</a>. The current list of committers can be found <a href="https://projects.apache.org/committee.html?tomee">here</a>.</div>
              <div class="contributor-grid">
                <%
                    def palette = ['#3a1c71','#6a3093','#1f4068','#2c5364','#283c86','#0f2027','#5614b0','#11324d','#16222a','#373b44','#1d2671','#3b1f5b']
                    org.apache.tomee.website.contributors.Contributors.load(content.body).each { contributor ->
                        def displayName = (contributor.name ?: '').trim()
                        def parts = displayName.split('\\s+').findAll { it }
                        def initials
                        if (parts.size() >= 2) {
                            initials = (parts[0][0] + parts[-1][0]).toUpperCase()
                        } else if (displayName.length() >= 2) {
                            initials = displayName.substring(0, 2).toUpperCase()
                        } else {
                            initials = displayName.toUpperCase()
                        }
                        def color = palette[Math.abs(displayName.hashCode()) % palette.size()]
                %>
                  <a class="contributor-card" href="${contributor.github}" rel="noopener" target="_blank">
                    <span class="contributor-badge" style="background:${color};" aria-hidden="true">${initials}</span>
                    <span class="contributor-name">${displayName}</span>
                  </a>
              <% } %>
              </div>
            </div>
        </div>
    </div>
<%include "footer.gsp"%>
