<%include "header.gsp"%>

	<%include "menu.gsp"%>

    <div id="main-block" class="container section-padded">
        <div class="row title">
            <div class='page-header'>
              <h2>Last posts</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <%
                last = published_posts.size() - 1
                published_posts.eachWithIndex {post, idx ->
                %>
                    <a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>${post.uri}"><h1>${post.title}</h1></a>
                    <%if (post.date) {%><p><small>${post.date}</small></p><% } %>
                    <p>${post.body}</p>
                    <% if (idx != last) { %><hr /><% } %>
                <%}%>

                <!--p>Older posts are available in the <a href="/${config.archive_file}">archive</a>.</p>-->
            </div>
        </div>
    </div>

<%include "footer.gsp"%>