<%include "header.gsp"%>
	<%include "menu.gsp"%>

    <div id="main-block" class="container section-padded">
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
              <div class="text-center" style="padding-bottom: 2em;">A <i class="fa fa-star-o" style="color:#F38F24;"></i> means the contributor is also a committer.</div>
              <ul>
                <%
                    org.apache.tomee.website.Contributors.load(content.body).each {contributor ->
                %>
                  <div class="col-sm-4">
                    <div class="photo col-sm-5">
                      <img alt="${contributor.name}" src="${contributor.gravatar}" style="width:140px">
                      <% if (contributor.committer){ %><i class="pull-right fa fa-star-o" style="color:#F38F24;"></i><% } %>
                    </div>
                    <div class="col-sm-7">
                      <h3 class="contributor-name" style="font-size:1.4em;">${contributor.name}</h3>
                      <p></p>
                      <ul class="list-inline">
                      <%contributor.link.each {l ->%>
                      <li><a href="${l.url}">${l.name}</a></li>
                      <%}%>
                    </div>
                  </div>
              <% } %>
              </ul>
            </div>
        </div>
    </div>
<%include "footer.gsp"%>
