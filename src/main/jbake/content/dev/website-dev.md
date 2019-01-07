Title: Working on the Website

The easiest way to edit is to simply click the blue pencil icon in the upper right.  Have fun!

## Offline Editing

Editing offline can be fantastic for making big changes or documenting while coding.  You simply need to
 check out the site source from svn:

    svn co https://svn.apache.org/repos/asf/tomee/site/trunk website-source

All the important source is in the `content` directory.  Just edit
and check in the `.mdtext` files and they will appear in the staging
site within seconds.

For example, `documentation.mdtext` shows up as:

[http://tomee.staging.apache.org/documentation.html]

## Publishing

Simply visit [https://cms.apache.org/tomee/publish](https://cms.apache.org/tomee/publish)

It will prompt you for user/pass information and make you review the changes.  Enter the optional log message and click "sumbit", then done!

The login information is cached for a while, so you won't have to enter user/pass information each time.


## Building Locally <small>optional</small>

Should you want to build the site locally, you can do so pretty easily with the right perl modules installed.

First check out the CMS source:

    svn co https://svn.apache.org/repos/infra/websites/cms/build apache-cms-source

The cpan modules you have to install may vary.  Here are the ones I installed:

    sudo cpan XML::RSS::Parser::Lite
    sudo cpan XML::RSS::Parser
    sudo cpan XML::Parser::Lite
    sudo cpan XML::Atom::Feed

What you need to install may be different, so perhaps just skip to the part
where we attempt to run the build locally and see what shows up missing and
install just those things.

    cd apache-cms-source
    ./build_site.pl  --source-base ~/path/to/website-source --target-base /tmp/site

All the generated html content will be under `/tmp/site/content`
