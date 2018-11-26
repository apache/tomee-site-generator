# rsync

`rsync` is basically the only copy command you'll ever need on a linux/unix system.  It can copy in the same machine or across machines.  It can retain file permissions and owners.  You can run it over and over again say if the first execution failed or if there are new files to be copied over.

## same machine copy

`rsync -av fromdir/ todir/`

Will copy the contents of `fromdir/` to the directory `todir/`

To make a little example, some fancy bash syntax for creating a directory structure.

    $ mkdir -p one/{green,blue}/{square,circle}{1..3}
    $ find .
    .
    ./one
    ./one/blue
    ./one/blue/circle1
    ./one/blue/circle2
    ./one/blue/circle3
    ./one/blue/square1
    ./one/blue/square2
    ./one/blue/square3
    ./one/green
    ./one/green/circle1
    ./one/green/circle2
    ./one/green/circle3
    ./one/green/square1
    ./one/green/square2
    ./one/green/square3

So to copy `one` to a new directory `two` we just do this

`rsync -av one/ two/`

Which gives us this as output

    $ rsync -av one/ two/
    building file list ... done
    created directory two
    ./
    blue/
    blue/circle1/
    blue/circle2/
    blue/circle3/
    blue/square1/
    blue/square2/
    blue/square3/
    green/
    green/circle1/
    green/circle2/
    green/circle3/
    green/square1/
    green/square2/
    green/square3/

    sent 301 bytes  received 110 bytes  822.00 bytes/sec
    total size is 0  speedup is 0.00

Now we have the following directory structure:

    $ find .
    .
    ./one
    ./one/blue
    ./one/blue/circle1
    ./one/blue/circle2
    ./one/blue/circle3
    ./one/blue/square1
    ./one/blue/square2
    ./one/blue/square3
    ./one/green
    ./one/green/circle1
    ./one/green/circle2
    ./one/green/circle3
    ./one/green/square1
    ./one/green/square2
    ./one/green/square3
    ./two
    ./two/blue
    ./two/blue/circle1
    ./two/blue/circle2
    ./two/blue/circle3
    ./two/blue/square1
    ./two/blue/square2
    ./two/blue/square3
    ./two/green
    ./two/green/circle1
    ./two/green/circle2
    ./two/green/circle3
    ./two/green/square1
    ./two/green/square2
    ./two/green/square3

## beware the slashes

There's a strange thing when not using the slashes at the end.  To keep things sane, just always use the slashes at the end of the two file paths and things will always be easy to remember.

