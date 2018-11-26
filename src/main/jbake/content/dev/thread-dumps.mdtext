Title: Thread Dumps
<a name="ThreadDumps-Java5tools"></a>
# Java 5 tools

This should work on any Java 5 or newer platform.


    $ jps
    3392 Jps
    3387 JConsole



    $ jstack 3387
    Attaching to process ID 3387, please wait...
    Debugger attached successfully.
    Client compiler detected.
    JVM version is 1.5.0_16-133
    Thread t@59139: (state = BLOCKED)
    - java.lang.Object.wait(long) @bci=0 (Interpreted frame)
    - javax.swing.TimerQueue.run() @bci=14, line=236 (Interpreted frame)
    - java.lang.Thread.run() @bci=11, line=613 (Interpreted frame)



<a name="ThreadDumps-OSsignals"></a>
# OS signals

In the case of an OS signal the thread dump is printed to the console
window that started the java process.  If you use javaw to start the
process, the dump is lost.

On Windows, typing *ctrl-break* in the console window that started the Java
process.
On Unix, the command *kill \-QUIT <pid>* or typing *ctrl-&#92;* in the
console window that started the Java process.

