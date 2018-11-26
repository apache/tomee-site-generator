Title: Tips and Suggestions
> 

<a name="TipsandSuggestions-Usefulinformationforcontributors"></a>
## Useful information for contributors

<a name="TipsandSuggestions-JIRAusage"></a>
### JIRA usage
It's good to leverage JIRA, but everything that happens there should be
considered invisible for the most part and only reviewed on a random and
infrequent basis.


A good way to bring jira "discussion" back to the list is to follow up on the list rather than posting more jira comments.  Maybe by having the first followup comment be a link to Nabble's "forum" view of the list.  Optionally, you can Cc jira@apache.org and include "[jira](jira.html)
 OPENEJB-XXX" anywhere in your subject line and your email will get added
as a jira comment.


For those looking to contribute, there are some good habits to put into
use.  We use a permission scheme called "Interactive Permissions."  It's
description has some good information:

 Interactive Permissions

 This permissions model differs from the Standard Permissions
 model in that people in the Contributor role must interact with
 the dev list to get to get issues assigned to them and issues
 closed. This isn't a trust issue, more that there are a few
 benefits.

 1.  Active contributors hitting the list to begin and end work
     shows other people not yet active how to get involved.

 2.  Adds more "touch points" between Contributors and
     Committers. It should be possible to "see" the active
     contributors even if you are not watching the JIRA or SVN
     notifications. It's also hoped that the practice of
     announcing your intentions on the dev list will persist
     even when the Contributor becomes a Committer.

 3.  Gives Committers the opportunity to help, mentor or
     otherwise get the Contributor going in the right direction
     before a task is started; potentially resulting in fewer
     rejected patches, less waisted Contributor time, and more
     collaborative development.

 4.  Overall brings more communication to the dev list before
     all work is done and all decisions made giving more
     potential to collaboration.


{+}If you'd like to get added to the openejb-contributors JIRA group, just
ping the list with your JIRA id and someone will add you.{+}

<a name="TipsandSuggestions-Commits"></a>
### Commits

*Contributed by*: David Blevins
+Here is an email from David Blevins explaining the things he keeps in mind
during commits.+ *{+}Definitely worth a read{+}{*}+:+

I generally *try never to reformat a file and make changes at the same
time* as it makes it impossible for anyone to see what I've changed. Needle
in a haystack.	I *try to get the reformatting as its own commit* before or
after I change a file.

A lot of times I end up tweaking a bunch of little unrelated things while
working on a bigger change. *I tend to like to "clear them out" in separate
commits as to isolate the big change in one commit*. Sometimes I'm not so
good at that and other times I'm really meticulous about it.

*Include the JIRA number and title (if there is a jira)*.  I try never to
say "Fixed FOO-2543" all by itself.  Reviewing the last 10 changes on a
file is a super big PITA when all you see is numbers to other systems. *I
shoot for more or less "Fixed <issue-number> <issue-title>
<how-did-i-fix-it>"* Sometimes the "how did i fix it" part is obvious by
the title, other times not.  Sometimes I'm too tired and the wife is
impatiently waiting for me to leave the computer :-)
As far as jiras go, there doesn't have to be a jira for absolutely every
commit -- what's the point of that.  I *try to make sure there's a jira for
anything we'd definitely want to see in the release notes*.  Sometimes I
don't get the jira created till long after the change, towards release time
when creating the release notes :-) It's pretty easy to forget stuff that
way though :-)

As far as jira titles, I always *try and make them short and succinct* for
the future release notes.

<a name="TipsandSuggestions-SVN+JIRA"></a>
### SVN + JIRA


<a name="TipsandSuggestions-*Contributedby*:Vamsi"></a>
#### *Contributed by*: Vamsi

I had trouble figuring out if a revision is related to a JIRA and what all
revisions corresponded to a particular JIRA.  So, I always include the JIRA
number (if there is an associated JIRA) in the svn comment and once
committed, I add the revision number to the JIRA comment.  It may be a
minute or two more to do this.	But, it saves a lot of time if you ever
have to investigate a regression or if there are multiple revisions for a
JIRA you know where to find the revision numbers instead of having to mine
the svn logs.

Some files may be missing $Rev$ and $Date$ in the header.  Whenever I
modify an existing file, I always check if I have to add these missing
$Rev$ $Date$ and add those so that the file will have them from my next
commit onwards.

<a name="TipsandSuggestions-*Contributedby*:DavidBlevins"></a>
#### *Contributed by*: David Blevins

> If you put "\[jira\](jira\.html)
>  OPENEJB-XXX" anywhere in your subject line and cc jira@apache.org our
> email gets added as a comment with all '> quoted' text stripped out.
PS: XXX is the JIRA issue number

<a name="TipsandSuggestions-Details"></a>
#### Details

The following subject lines did work:
- Subject: \[jira\](jira\.html)
 OPENEJB-670
- Subject: Some other subject prefix (\[jira\](jira\.html)
 OPENEJB-670)
- Subject: Re: Some other subject prefix (\[jira\](jira\.html)
 OPENEJB-670)

The following subject lines did *not* work:
- Subject: OPENEJB-670
- Subject: Some other subject prefix (jira: OPENEJB-670)
- Subject: Some other subject prefix (jira OPENEJB-670)
- Subject: Some other subject prefix (OPENEJB-670)

It appears that as long as the subject contains "\[jira\](jira\.html)
 FOO-XXX" the email contents will be added as comments.

It also appears that all quoted text regardless of place in the email is
stripped out and the remaining text is added as the comment.

The exact email bodies, white space and all, for the "reply text" comments
above are:

<one>
Reply text at the top.

-- David

On Aug 23, 2007, at 1:02 PM, David Blevins wrote:

Testing adding comments via email with this subject line "Some other subject prefix (\[jira\](jira\.html)
 OPENEJB-670)"

</one>

<two>

On Aug 23, 2007, at 1:02 PM, David Blevins wrote:

Testing adding comments via email with this subject line "Some other subject prefix (\[jira\](jira\.html)
 OPENEJB-670)"

Reply text at the bottom.

--
David

</two>

<three>
On Aug 23, 2007, at 1:02 PM, David Blevins wrote:

Testing adding comments via email

Some reply text

with this subject line "Some other subject prefix (\[jira\](jira\.html)
 OPENEJB-670)"

Some more reply text

--
David

</three>

### scp, ssh, and rsync examples for working with files and directories on
people.apache.org

*Contributed by*: David Blevins

*copying a file to apache from locahost*
scp topsecret.tar.gz kmalhi@people.apache.org:
scp index.html kmalhi@people.apache.org:public_html/

*copying a file from apache to localhost -- reverse of the above*
scp kmalhi@people.apache.org:topsecret.tar.gz ./
scp kmalhi@people.apache.org:public_html/index.html ./

*copying directories*
scp \-r kmalhi@people.apache.org:public_html ./
scp \-r public_html kmalhi@people.apache.org:

*When the directory exists, better to rsync*

*pull a dir from apache*
rsync \-v \-e ssh \-lzrptog kmalhi@people.apache.org:public_html ./
rsync \-v \-e ssh \-lzrptog
kmalhi@people.apache.org:/home/kmalhi/public_html ./

*the two above commands do the exact same thing*

*push the same dir to apache*
rsync \-v \-e ssh \-lzrptog ./public_html kmalhi@people.apache.org:
rsync \-v \-e ssh \-lzrptog ./public_html
kmalhi@people.apache.org:/home/kmalhi/

*the two above commands do the exact same thing*

*sometimes useful to execute commands over ssh (piping works too)*
ssh people.apache.org ls /home \| tee home-dirs-on-apache.txt
echo \-e 'Hello, me\n\nHow am I doing today?' \| ssh
kmalhi@people.apache.org mail \-s 'Greetings' kmalhi@apache.org

*For Putty users*
*Contributed by:* Mohammad
I have Putty on my linux machine and it is easier to use, Putty has a
Windows version too, I issue this command to upload a file to my home dir
on people.apache.org

    pscp [[file-name]
 | [dir]
] mnour@people.apache.org:~/

and if you want to access your home page, you should create a dir with the
name public_html, and then upload files to it.

<a name="TipsandSuggestions-Acoolsubversiontip"></a>
### A cool subversion tip

*Contributed by*: Jacek Laskowski.

*It'll let you forget about some svn administrative commands you'd
otherwise have to type in on the command line.*
Edit/Add the following configurations to \~/.subversion/config:

    [miscellany]
    global-ignores = *.log *.save *.o *.lo *.la #*# .*~ *~ .#* .DS_Store
    build dist target *.ipr *.iml *.iws .project .classpath .settings
    enable-auto-props = yes
    
    [auto-props]
    *.c = svn:eol-style=native;svn:keywords=Date Author Id Revision HeadURL
    *.cpp = svn:eol-style=native;svn:keywords=Date Author Id Revision HeadURL
    *.h = svn:eol-style=native;svn:keywords=Date Author Id Revision HeadURL
    *.dsp = svn:eol-style=CRLF
    *.dsw = svn:eol-style=CRLF
    *.sh = svn:executable;svn:eol-style=native;svn:keywords=Date Revision
    *.cmd = svn:mime-type=text/plain;svn:eol-style=CRLF
    *.bat = svn:mime-type=text/plain;svn:eol-style=CRLF
    Makefile = svn:eol-style=native;svn:keywords=Date Author Id Revision
HeadURL
    *.obj = svn:mime-type=application/octet-stream
    *.bin = svn:mime-type=application/octet-stream
    *.bmp = svn:mime-type=image/bmp
    *.class = svn:mime-type=application/java
    *.doc = svn:mime-type=application/msword
    *.exe = svn:mime-type=application/octet-stream
    *.gif = svn:mime-type=image/gif
    *.gz = svn:mime-type=application/x-gzip
    *.jar = svn:mime-type=application/java-archive
    *.jelly = svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
    Revision
    *.jpg = svn:mime-type=image/jpeg
    *.jpeg = svn:mime-type=image/jpeg
    *.pdf = svn:mime-type=application/pdf
    *.png = svn:mime-type=image/png
    *.tgz = svn:mime-type=application/octet-stream
    *.tif = svn:mime-type=image/tiff
    *.tiff = svn:mime-type=image/tiff
    *.zip = svn:mime-type=application/zip
    *.txt = svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
Revision
    *.xml = svn:mime-type=text/xml;svn:eol-style=native;svn:keywords=Date
Revision
    *.ent = svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
Revision
    *.dtd = svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
Revision
    *.vsl = svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
Revision
    *.xsd = svn:mime-type=text/xml;svn:eol-style=native;svn:keywords=Date
Revision
    *.xsl = svn:mime-type=text/xml;svn:eol-style=native;svn:keywords=Date
Revision
    *.wsdl = svn:mime-type=text/xml;svn:eol-style=native;svn:keywords=Date
Revision
    *.htm = svn:mime-type=text/html;svn:eol-style=native;svn:keywords=Date
Revision
    *.html = svn:mime-type=text/html;svn:eol-style=native;svn:keywords=Date
Revision
    *.css = svn:mime-type=text/css;svn:eol-style=native;svn:keywords=Date
Revision
    *.js = svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
Revision
    *.jsp = svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
Revision
    *.txt = svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
Revision
    *.java = svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
    Revision
    *.properties =
svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
    Revision
    *.sql = svn:mime-type=text/plain;svn:eol-style=native;svn:keywords=Date
Revision


<a name="TipsandSuggestions-Maventips"></a>
### Maven tips

*Contributed by:* Jacek and David
*I want to make a small change in a module , for example openejb-core and
then want to build a snapshot of openejb-standalone, start the server and
test my change. What is the easiest and fastest way of doing it?*
Run the following from within openejb-core

    mvn -Dtest=none install

Now run the following from within openejb-standalone

    mvn -Dtest=none clean package

*So what if I wanted to do the above in a single command?*
It's possible with $\{module\} and $\{assemble\} properties and *create a
profile or a plugin*

Another option is and *if you're in bash*, here's what could be done

    # camping out in assembly/openejb-standalone/
    $ (cd ../../container/openejb-core && mvn clean install -Dtest=skip) && mvn
clean install && ./try.sh

That's one command, parens and all. The first "$" is the prompt,don't type
that.  Then just edit ./try.sh to do what you're looking for on the
standalone zip.

The parens in bash are great as nothing done in there lasts -- at least no
changes to the environment.  So you can 'cd' around all you want and not
have to 'cd' back when done.

For example

    $ export FOO=hello && (export FOO=byebye) && echo $FOO
    hello
    
    $ cd ~ && echo $PWD && (cd /tmp && echo $PWD) && echo $PWD
    /Users/dblevins
    /tmp
    /Users/dblevins

As well, several commands can be combined with '&&'.  Far better than just
separating commands with ';' as if one of the commands fail, the remaining
commands will not be executed.

*Suggestion from Dain*
> &nbsp;I suggest that you always do a full build (with tests) before
> committing.  I do this even if the change is small because I have been
> burned too many times by trivial changes. At the very least I suggest you
> run
> 
>     mvn -Dtest=none clean install
> 
> Using \-Dtest=none instead of \-Dmaven.test.skip=true causes maven to
> compile the test classes so you know that your change doesn't have compile
> errors.
> If you want to be thorough, run with \-Dassemble.

<a name="TipsandSuggestions-JAXBUsage"></a>
### JAXB Usage
{note:title=TODO}
Create a write up from here
http://www.nabble.com/jaxb-question-td18023648.html
{note}
