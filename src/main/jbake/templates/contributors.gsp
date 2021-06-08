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
              <ul>
                <%
                    org.apache.tomee.website.contributors.Contributors.load(content.body).each { contributor ->
                %>
                  <div class="col-sm-4">
                    <div class="photo col-sm-5">
                      <img src="${contributor.avatar}" style="width:140px">
                    </div>
                    <div class="col-sm-7">
                        <h5 class="contributor-name" style="font-size:1.0em;"><a href="${contributor.github}">${contributor.name}</a></h5>
                      <p></p>
                    </div>
                  </div>
              <% } %>
              </ul>
            </div>
        </div>
    </div>
<%include "footer.gsp"%>
