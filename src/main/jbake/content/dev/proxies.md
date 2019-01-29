Title: Proxies
<a name="Proxies-Where'stheJavasourcefortheproxies?"></a>
#  Where's the Java source for the proxies?

The short answer is we do not generate java code containing any ejb logic
at all as other platforms like WebSphere and WebLogic do.  We use dynamic
proxy generation via java.lang.reflect.Proxy.

Most of the commercial platforms predate dynamic proxy generation and not
only statically generate java files which are then compiled and included in
the app but decided as long as they were generating java files that they
would jam-pack them with as many optimizations as they could.  The result
was that significant portions of your application data such as transaction
attributes and database queries were stuck in generated vendor code.  We
don't have any equivalent to that.

Dynamic proxies such as java.lang.reflect.Proxy or cglib byte code in
memory, not java source, which is immediately turned into a class
definition and used.  The basic paradigm is that you give the library the
interfaces you want implemented and some sort of Handler object.  The
library will give you back a proxy instance that does nothing more than
call your handler every time a method is called.  Now it's possible with
some trickery to pull the byte code for any java.lang.Class instance out of
the classloader and then use some sort of java decompiler library to turn
that into some sort of java source file, however there's no real motivation
to do so as the VM generated proxies are quite dumb and the code that does
all the work is not generated and available for download or svn checkout.

If you are coming from a commercial vendor and simply looking for a good
place to begin your debugging, look for implementations
java.lang.reflect.InvocationHandler and slap a breakpoint in the "invoke"
method and you'll be all set.


