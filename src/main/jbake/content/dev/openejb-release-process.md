Title: OpenEJB Release Process

	Note: This information is largely obsolete and remains here for reference only.

{code:none}
mvn release:prepare -Dusername=dblevins -Dassemble

    
    {code:none}
    mvn release:perform -Dassemble -Dusername=dblevins
-DaltDeploymentRepository=dblevins::default::scp://people.apache.org/x1/home/dblevins/public_html/stage
-Dgpg.passphrase=xxxxx



    mvn clean deploy -Prelease -Dassemble -Dusername=dblevins
-DaltDeploymentRepository=dblevins::default::scp://people.apache.org/x1/home/dblevins/public_html/stage/repo
-Dgpg.passphrase=xxxxx


1. Create a copy of the trunk the branch will be based on.

{code:none}
svn copy -m "TomEE 1.5.1 branch" \
  https://svn.apache.org/repos/asf/tomee/tomee/trunk \
  https://svn.apache.org/repos/asf/tomee/tomee/branches/tomee-1.5.1

    
#  Merging things from trunk to the branch
    
    While fixing issues in the branch and trunk in parallel it may happen that
some changes in trunk have not been applied to the branch. Provided you're
in the branch's directory the following command applies a change from the
trunk  to the branch (`{-c 575845`} is the commit number with the fix and
the url points to a repo the change is in).
    
    {code:none}
    svn merge -c 575845 https://svn.apache.org/repos/asf/tomee/tomee/trunk


Here's a little script that can make merging easier


    #!/bin/bash
    
    for n in $@; do
      m=$(($n -1))
      LOG=/tmp/svn-commit-r$n.log
    
      cat /dev/null > $LOG
    
      echo "Merging r$n - http://svn.apache.org/viewvc?rev=$n&view=rev" >> $LOG
      echo "" >> $LOG
      echo "svn merge -r $m:$n
https://svn.apache.org/repos/asf/tomee/tomee/trunk ." >> $LOG
      echo "" >> $LOG
      svn log -r$n https://svn.apache.org/repos/asf/tomee/tomee/trunk >>
$LOG
    
      svn merge -r $m:$n
https://svn.apache.org/repos/asf/tomee/tomee/trunk .  &&
      svn ci -F $LOG
      echo "$n merged"
    done


<a name="OpenEJBReleaseProcess-Aggregationintoastage/3.xdirectory"></a>
#  Aggregation into a stage/3.x directory


    #!/bin/bash
    
    VER=3.0
    
    function package () {
        SOURCE=$1; DEST=${2:-$SOURCE}
        tar czf $DEST.tar.gz $SOURCE
        zip -9rq $DEST.zip $SOURCE
    }
    function shash {
        openssl $1 < $2 > $2.$1 ;
    }
    function sign {
        archive=$1
        gpg --armor --output $archive.asc --detach-sig $archive
        gpg --verify $archive.asc $archive
    }
    
    function fail () { echo $1 >&2; exit 1;}
    
    
    mkdir $VER
    (cd $VER
    
    svn export
http://svn.apache.org/repos/asf/openejb/tags/openejb-$VER/examples
openejb-examples-$VER
    package openejb-examples-$VER && rm -r openejb-examples-$VER
    
    svn export http://svn.apache.org/repos/asf/openejb/tags/openejb-$VER
openejb-$VER-src
    package openejb-$VER-src && rm -r openejb-$VER-src
    
    for archive in *.{zip,tar.gz}; do
        echo $archive
        shash md5 $archive
        sign $archive
    done || fail "Unable to sign or hash release archives"
    )
    
    scp -r $VER  people.apache.org:public_html/stage/
    
    # Copy standalone assembly in
    
    ssh people.apache.org "cp
~/public_html/stage/repo/org/apache/openejb/openejb-standalone/$VER/openejb-standalone-$VER.{zip,tar.gz}{,.asc,.md5}
~/public_html/stage/$VER/"
    
    
    echo $VER | ssh people.apache.org 'read VER && for n in
~/public_html/stage/repo/org/apache/openejb/openejb-standalone/$VER/openejb-standalone-$VER.{zip,tar.gz}{,.asc,.md5};
do cp $n ~/public_html/stage/$VER/$(basename
${n/openejb-standalone-$VER/openejb-$VER}); done'
    
    # Copy tomcat webapp assembly in
    
    echo $VER | ssh people.apache.org 'read VER && for n in
~/public_html/stage/repo/org/apache/openejb/openejb-tomcat-webapp/$VER/openejb-tomcat-webapp-$VER.war{,.asc,.md5};
do cp $n ~/public_html/stage/$VER/$(basename ${n/-tomcat-webapp-$VER/});
done'


<a name="OpenEJBReleaseProcess-Releasenotes"></a>
#  Release notes


    #set( $rpc =
$xmlrpc.connect("dblevins:xxxxx","http://issues.apache.org/jira/rpc/xmlrpc")
)
    #set( $version = $rpc.getVersion("OPENEJB", "3.0") )
    #set ( $versionId = $version.id )
    #set ( $jira =
$rss.fetch("http://issues.apache.org/jira/secure/IssueNavigator.jspa?view=rss&&pid=12310530&status=5&status=6&fixfor=${versionId}&tempMax=1000&reset=true&decorator=none")
)
    #set( $issues = $jira.issues )
    
    Apache OpenEJB $version
    
    $date
    
    New Features:
    
    #foreach ( $issue in $issues.equals("type", "New Feature").descending("id")
)
      * [$issue.key]
 $issue.summary
    #end
    
    Improvements:
    
    #foreach ( $issue in $issues.equals("type", "Improvement") )
      * [$issue.key]
 $issue.summary
    #end
    
    Bugs:
    
    #foreach ( $issue in $issues.equals("type", "Bug").sort("priority") )
      * [$issue.key]
 $issue.summary
    #end
    
    Tasks & Sub-Tasks:
    
    #foreach ( $issue in $issues.equals("type", "Task").sort("summary") )
      * [$issue.key]
 $issue.summary
    #end
    #foreach ( $issue in $issues.equals("type", "Sub-task").sort("summary") )
      * [$issue.key]
 $issue.summary
    #end
    
    
    
     - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    
    Unimplemented Features, bugs, limitations
    
    #set ( $jira =
$rss.fetch("http://issues.apache.org/jira/secure/IssueNavigator.jspa?view=rss&&pid=12310530&status=1&status=3&status=4&version=${versionId}&tempMax=1000&reset=true&decorator=none")
)
    #set( $issues = $jira.issues )
    
    #foreach ( $issue in $issues.sort("priority") )
      * [$issue.key]
 $issue.summary
    #end



<a name="OpenEJBReleaseProcess-README.htmlfile"></a>
#  README.html file

some way to dynamically update this would be great.

{code:html}
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>Apache OpenEJB 3.0</TITLE>
<META http-equiv=Content-Type content="text/html">
</HEAD>
<BODY>
<P>
<H3>Apache OpenEJB 3.0</H3>
<P></P>

<p>Packaging Details (or "What Should I Download?")
  <ul>
      <li>
	  OpenEJB Standlone Server:
	  <ul>
	      <li><a href="openejb-3.0.zip">openejb-3.0.zip</a></li>
	      <li><a href="openejb-3.0.tar.gz">openejb-3.0.tar.gz</a></li>
	  </ul>
      </li>
      <li>
	  OpenEJB for Tomcat 6 or Tomcat 5.5:
	  <ul>
	      <li><a href="openejb.war">openejb.war</a></li>
	  </ul>
      </li>
      <li>
	  EJB 3.0 and other examples:
	  <ul>
	      <li><a
href="openejb-examples-3.0.zip">openejb-examples-3.0.zip</a></li>
	      <li><a
href="openejb-examples-3.0.tar.gz">openejb-examples-3.0.tar.gz</a></li>
	  </ul>
      </li>
      <li>
	  Source:
	  <ul>
	      <li><a
href="openejb-3.0-src.zip">openejb-3.0-src.zip</a></li>
	      <li><a
href="openejb-3.0-src.tar.gz">openejb-3.0-src.tar.gz</a></li>
	  </ul>
      </li>
  </ul>
</p>

<P>Thank you for using <A href="http://tomee.apache.org/">OpenEJB</A>!.
</P>
<P><B>The Apache OpenEJB Project</B> <BR><A
href="http://tomee.apache.org/">http://tomee.apache.org/</A> </P>
<P>
<P></P></BODY></HTML>

    
#  Publishing
    
    When all voting is done
    
    {code:none}
    mvn stage:copy -Dsource="http://people.apache.org/~dblevins/stage/repo/" \
     
-Dtarget="scp://people.apache.org/www/people.apache.org/repo/m2-ibiblio-rsync-repository"
\
      -DsourceRepositoryId=apache.staging \
      -DtargetRepositoryId=apache.releases \
      -Dversion=3.0


{code:none}

$ mv 3.0 /www/www.apache.org/dist/openejb/

