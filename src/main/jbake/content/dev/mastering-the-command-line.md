# Linux/OSX awesomeness

Not specific to this projec, but can be incredibly useful ways to maximizes your time.  Mastering the command line is the best way to get things done, quick and cheaply.

Reapeat after me:

 - I will write a script for any tasks I do regularly

# Core commands

Some very big time-saving commands

## In shell scripts

 - **basename** - what is the file name again? not the path, just the file name
 - **dirname** - what directory is this in?
 - **readllink -f** - what is the absolute path of this file?
 - **cat** - spit out the contents of one or more files
 - **cp** - copy file or dir
 - **scp** - copy file or dir to another machine
 - **rsync** - both the above, but so much more awesome
    - rsync -av here there
    - rsync -e ssh -av here user@host:there
 - **cut** - wish I could i could chop out this column in the middle here
 - **date** - would be nice if I had some way to make this log file name a little more unique
 - **echo** - System.out.println()
 - **xargs** - can be useful but doesn't respect paths with spaces. I only use it sometimes
 - **find** - find some files and potentially execute a command against them
 - **egrep** - search for text in a file or from STDIN.  better than grep
 - **mkdir** - makes a dir, with -p will make parent dirs too
 - **mv** - move a file
 - **head** - spit out the first N lines of a file
 - **tail** - spit out the last N lines of a file
 - **tee** - I want to see the output of this command, but I also want to write it to a file.  Oh! tee is awesome!
 - **perl** - edit a stream:  cat file.txt
    - edit a file: perl -i -pe 's/this/that/'
 - **pwd** - where am I?
 - **rm** - delete
 - **read (bash)** - I dig this out for strings with spaces. paths have spaces in them sometimes
    - ls *.txt
    - find . -name "*.txt"
 - **export (bash)** - why can't my script find this command? i use this to debug scripts 'export > /tmp/script-env.txt'
 - **for (bash)** - my favorite bash construct
    - for n in one two three; do echo "$n"; done
    - (side note, always quote your variables like "$n" vs $n, trust me it's a good habit)
 - **while  (bash)** - my second favorite bash construct
    - loop forever and do something: while true; do my_command; sleep 10; done
 - **sleep** - Thread.sleep().  Only accepts seconds.  I typically do the math with bash sytax
    - sleep for an hour:  sleep $(( 60 * 60 ))
    - sleep for a day:  sleep $(( 60 * 60 * 24 ))
 - **sort** - sort lines of text
 - **uniq** - trim out the duplicates
    - show me only things that had duplicates: uniq -d
    - ( I always 'sort
 - **ssh** - awesomeness.  can execute commands remotely too
    - ssh people.apache.org pwd
    - ssh people.apache.org 'pwd
 - **sudo** - I wish I were a fish.  I wish I were a fish.
 - **tar** - package up some stuff
 - **wget** - download this url
    - wget http://apache.org
    - wget -q -O - http://apache.org
 - **tr** - echo $PATH
 - **uname** - what system am I on?

## OSX commands

 - **pbpaste** - paste the contents of the clipboard to stdout
 - **pbcopy** - copy stdin to the clipbboard

## running shell scripts

 - **bash** - quick way to run a script if it isn't already executable
 - **chmod** - this file should be executable
 - **clear** - my terminal is messy
 - **diff/patch** - comparing or merging file contents
 - **ps/kill** - my script is running too long, i want to force it to stop
 - **less** - paging through command line output
 - **nohup** - I don't want this script to die if my connection is interrupted
 - **which** - where does this command live?
 - **crontab** - schedule something to run regularly (i use 'bash -l foo.sh' to run stuff in my crontab file)
 - **shutdown** - shutdown or restart a machine
 - **top** - what the heck is chewing up my cpu?
 - **uname** - what system am I on?

# I never use

Some people like it.
sed -- i always use "perl -pe 's/find/replace/'")

Getting used to using perl is better.  This page was originally written thinking markdown had table support
like confluence does.  Soo all lines where using "| command | description |" formatting.  Markdown doesn't
have that, so I changed it to a list syntax with the following command on my mac:

`pbpaste | perl -ne '@f = split(" *\\| *", $_); print " - **$f[1]** - $f[2]\n"' | pbcopy`


