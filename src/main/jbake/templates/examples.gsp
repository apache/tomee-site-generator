<%include "header.gsp"%>
<%include "menu.gsp"%>

<div id="main-block" class="container section-padded">
<div class="row title">
  <div class='page-header'>
    <% if (content.containsKey('tomeepdf')) { %>
    <div class='btn-toolbar pull-right'>
      <div class='btn-group'>
        <a class="btn" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>${content.uri.replace('html', 'pdf')}">
          <i class="fa fa-file-pdf-o"></i> Download as PDF
        </a>
      </div>
    </div>
    <% } %>
    <h2>${content.title}</h2>
  </div>
</div>
<div class="row">
  <div class="col-md-12">
    <input id="example-search" placeholder="Search an example and click on it to browse it..." class="typeahead"/>
  </div>
  <div class="vspace">&nbsp;</div>
  <div class="col-md-12 examples text-center">
    <%
    def all = org.apache.tomee.website.Examples.loadAll()
    def keys = []
    keys.addAll(all.all.keySet())

    [keys.subList(0, (int) keys.size() / 2), keys.subList((int) keys.size() / 2, keys.size())].each { list ->
      keys.subList(0, (int) keys.size() / 2).each { tag ->
      examples = all.all[tag]
    %>
    <div class="col-sm-6">
      <h3>${tag}</h3>
      <ul class="list-unstyled">
        <% examples.each {example -> %>
        <li><a href="${example.name}.html">${example.name}</a></li>
        <% } %>
      </ul>
    </div>
    <% }} %>
  </div>
</div>
</div>

<style>
.typeahead {
    padding-left: 43px;
    padding-right: 43px;
    border-radius: 23px;
    border:1px #ccc solid;
    height: 46px;
    width: 100%;
    outline: none;
}
.typeahead:focus {
    border-color: #66afe9;
    outline: 0;
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102, 175, 233, 0.6);
}


/* typeahead styling*/
.tt-menu hr {
    margin-bottom: 5px;
    margin-top: 5px;
}
.tt-menu h3 {
    margin-bottom: 2px;
    margin-top: 2px;
    padding-bottom: 2px;
    padding-top: 2px;
    font-size: 18px;
    font-weight: bolder;
}
.tt-menu h2 {
    font-weight: bold;
}
span.twitter-typeahead .tt-menu,
span.twitter-typeahead .tt-dropdown-menu {
  z-index: 1000;
  display: none;
  width: 100%;
  min-width: 160px;
  padding: 5px 0;
  margin: 2px 0 0;
  list-style: none;
  text-align: left;
  background-color: #ffffff;
  border: 1px solid #cccccc;
  border: 1px solid rgba(0, 0, 0, 0.15);
  border-radius: 4px;
  -webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
  background-clip: padding-box;
}
span.twitter-typeahead h3 {
  padding-left: 15px;
  clear: both;
}
span.twitter-typeahead .tt-suggestion {
  display: block;
  padding: 3px 15px;
  clear: both;
  font-weight: normal;
  line-height: 1.42857143;
  color: #333333;
}
span.twitter-typeahead .tt-suggestion.tt-cursor,
span.twitter-typeahead .tt-suggestion:hover,
span.twitter-typeahead .tt-suggestion:focus {
  color: #ffffff;
  text-decoration: none;
  outline: 0;
  background-color: #337ab7;
}
.input-group.input-group-lg span.twitter-typeahead .form-control {
  height: 46px;
  padding: 10px 16px;
  line-height: 1.3333333;
  border-radius: 6px;
}
.input-group.input-group-sm span.twitter-typeahead .form-control {
  height: 30px;
  padding: 5px 10px;
  line-height: 1.5;
  border-radius: 3px;
}
span.twitter-typeahead {
  width: 100%;
}
.input-group span.twitter-typeahead {
  display: block !important;
  height: 34px;
}
.input-group span.twitter-typeahead .tt-menu,
.input-group span.twitter-typeahead .tt-dropdown-menu {
  top: 32px !important;
}
.input-group span.twitter-typeahead:not(:first-child):not(:last-child) .form-control {
  border-radius: 0;
}
.input-group span.twitter-typeahead:first-child .form-control {
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}
.input-group span.twitter-typeahead:last-child .form-control {
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
}
.input-group.input-group-sm span.twitter-typeahead {
  height: 30px;
}
.input-group.input-group-sm span.twitter-typeahead .tt-menu,
.input-group.input-group-sm span.twitter-typeahead .tt-dropdown-menu {
  top: 30px !important;
}
.input-group.input-group-lg span.twitter-typeahead {
  height: 46px;
}
.input-group.input-group-lg span.twitter-typeahead .tt-menu,
.input-group.input-group-lg span.twitter-typeahead .tt-dropdown-menu {
  top: 46px !important;
}
</style>
<script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/jquery-1.11.1.min.js"></script>
<script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/typeahead.bundle.min.js"></script>
<script>
(function () {
  var names = [];
  document.querySelectorAll('.examples li > a').forEach(function (s) {
    names.push(s.innerHTML);
  });
  var engine = new Bloodhound({
    datumTokenizer: Bloodhound.tokenizers.nonword,
    queryTokenizer: Bloodhound.tokenizers.nonword,
    local: names
  });
  var input = jQuery('#example-search');
  input.typeahead({ minLength: 1, highlight: true }, {
    name: 'Examples',
    source: engine,
    templates: {
      suggestion: function (item) {
        return '<a href="' + item + '.html">' + item + '</a>';
      }
    }
  });
  input.bind('typeahead:select', function (evt, item) {
    jQuery('li > a[href="' + item + '.html"]').click();
  });
})();

</script>

<%include "footer.gsp"%>

