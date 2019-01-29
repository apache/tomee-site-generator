Title: Contribution Tips

<a name="ContributionTips-Firststeps"></a>
# First steps

1. Subscribe to the [developer mailing list](mailto:dev-subscribe@tomee.apache.org)
   and say Hi
1. Get the source code with svn
    - svn https://svn.apache.org/repos/asf/tomee/tomee/trunk
1. Build the code (maven 3.0.5 or better required)
    - mvn clean install

Play around with the examples under the examples/ directory.  Some of the
neater ones are (ordered simple to complex):

 -  [simple-stateless](http://tomee.apache.org/examples-trunk/simple-stateless/README.html)
 -  [simple-stateful](http://tomee.apache.org/examples-trunk/simple-stateful/README.html)
 -  [simple-singleton](http://tomee.apache.org/examples-trunk/simple-singleton/README.html)
 -  [simple-mdb](http://tomee.apache.org/examples-trunk/simple-mdb/README.html)
 -  [async-methods](http://tomee.apache.org/examples-trunk/async-methods/README.html)
 -  [schedule-methods](http://tomee.apache.org/examples-trunk/schedule-methods/README.html)
 -  [injection-of-env-entry](http://tomee.apache.org/examples-trunk/injection-of/README.html)
 -  [injection-of-ejbs](http://tomee.apache.org/examples-trunk/injection-of/README.html)
 -  [injection-of-datasource](http://tomee.apache.org/examples-trunk/injection-of-datasource/README.html)
 -  [injection-of-entitymanager](http://tomee.apache.org/examples-trunk/injection-of-entitymanager/README.html)
 -  [testcase-injection](http://tomee.apache.org/examples-trunk/testcase-injection/README.html)
 -  [testing-transactions](http://tomee.apache.org/examples-trunk/testing-transactions/README.html)
 -  [transaction-rollback](http://tomee.apache.org/examples-trunk/transaction-rollback/README.html)
 -  [testing-security](http://tomee.apache.org/examples-trunk/testing-security/README.html)
 -  [testing-security-2](http://tomee.apache.org/examples-trunk/testing-security-2/README.html)
 -  [simple-webservice](http://tomee.apache.org/examples-trunk/simple-webservice/README.html)


<a name="ContributionTips-Whatistheprocess?"></a>
# What is the process?

    public void contributeToOpenSource() {

        boolean stillInterestedAndHavingFun = true;
        int taskSize = 1; // start small!

        contributing:
        while (stillInterestedAndHavingFun) {

            Task task = findSomethingInteresting(taskSize++);

            if (!task.hasJira()) {
                createJira(task);
            } else {
                requestToBeAssignedToJira(task.jiraId());
            }

            while (task.inProgress()) {

                chatOnListALittleGetCleverIdeas(task, new Ideas(task));
                hackALittle(task);

                if (task.tooHard() || task.notFun()) {
                    // no big deal, try again with something else
                    taskSize--;
                    continue contributing;
                }
            }

            File patchFile = createSvnOrGitPatch(task);
            attachToJira(task.jiraId(), patchFile);
            askForReviewOnList(task.jiraId());

            while (!committed(patchFile)) {

                try {
                    pokeAtSometingElse();
                    helpOnUserList();
                    dayDream();
                } catch (MoreThanAWeekException e) {
                    // Assume it fell off the radar -- happens.
                    // Evidence we need more committers.
                    bumpThreadOnList(task);
                }
            }
        }

    }


After a while when people feel comfortable with you as contributor, they
vote you in as a committer and ... big surprise ... there's almost no
change in the daily routine.  You get access to svn and pretty much
everything else stays the same.  Instead of submitting patches, now you
have to help review them and commit them.  Instead of learning how to
contribute to an open source project, now you have to learn how to help
others get involved.  And of course it doesn't happen all at once, you
never stop learning these things and you never stop wishing you had more
time.

No one cares how much code you can write or how fast you can write it.	We
all just contribute what we can when we can and there are no expectations
on how much, how often, or where.

It's very much about the journey and there is no real end as long as you're
having fun and learning.

Probably finding something to do when you do have time is the hardest part
... that never changes.

<a name="ContributionTips-BeBrave"></a>
# Be Brave

Don't assume everything has already been discussed a million times and
you're the only one who doesn't know and so you shouldn't bother anyone and
should just figure it out on your own.	That thinking is your enemy.  Don't
do that or you will get nowhere ... very slowly.  So slowly that now you
feel you really can't ask about it because surely everyone assumes you know
it or have done it by now.  That thinking is a terrible trap.  Ask
questions.  Post your thoughts.

Don't worry about asking "stupid" questions on the list -- even simple
questions have great value.  They often lead to surprisingly good
discussions.  They also have a profound impact on the people around you,
the ones you don't see.

There are always a handful of people silently reading the list and wishing
they could participate, but are less brave.  Whenever someone like you
finally does show up and asks basic questions and shows it's ok, we usually
get another 1 or 2 new faces who suddenly find the courage to speak up.

Maybe it's like Karaoke; if the people singing sound like you when you
sing, there are better odds you might get up and sign too. Seeing people
like yourself do the things you want to do is inspiring.

<a name="ContributionTips-StartSmall"></a>
# Start Small

You may suddenly get a creative surge and see many many things that could
be done.  One thing you learn about open source is that you never know when
life is going to intervene and you have to stop.  So it's always really
good to get a little tiny thing working, checked in, and just grow it
iteratively as time permits.  It is a practice that is key for people of
any skill level.  And it goes wonderfully with Open Source as it adds
plenty of space for new ideas.	Stone soup starts with the stone, not the
soup!

So no matter how big the idea or task, ask yourself "do I really need all
of this to get started?".  Start with the tiniest possible version.  And
then cut it down again :)

Code is easier to grow than change.  And with today's refactoring tools
even change is pretty easy.  What's hard is taking a big piece of code and
jamming it into another big piece of code.  Don't work too long in
isolation.

Start small, get it checked in (or patch submitted) and work iteratively.

<a name="ContributionTips-Thingsthatalwaysneeddoing"></a>
# Things that always need doing

 - Final variables & fields are preferred where possible, but a lot of the
code is old.  Feel free to add them and hand the code back.
 - If you have any skills with code coverage tools, then you'll probably
find way too much to do!  Tests are always welcome.
 - There are over a 1,000 TODO comments in the code.  Maybe some should be
deleted.  Maybe some could be completed.  They probably all should have a
JIRA id on them.
 - Pick a random class, see if you can figure out what it is doing and
javadoc it.
 - Add @Override where applicable
 - Intellij has an 'Inspect Code' feature.  Yikes does it produce a lot of
output.
 - No doubt there is some exception handling that can be greatly improved.

Obviously, one could get quite bored doing just the above.  But sometimes
the above tasks can lead to more fun and exciting things.  Anything that
gets you in and looking at code and actually touching and changing it
usually results in questions, discussions and ideas... then little passions
and late nights and lack of sleep and caffeine abuse.


<a name="ContributionTips-Thingstoavoid"></a>
# Things to avoid

<a name="ContributionTips-Hugepatches"></a>
#### Huge patches

Huge patches are hard to digest.  Try to avoid them whenever possible.	Any
step forward is a good one.  Small steps allow people to see where you're
headed and give input.	That's true regardless if you are a committer or
contributor.

<a name="ContributionTips-Becarefulwithreformatting"></a>
#### Be careful with reformatting

Try to never mix logic changes with code reformatting.	It makes it nearly
impossible for others to see what the actual change was.

 - If you are a committer and want to reformat something, do the reformat
as a separate commit before or after the real change.  As long as they are
separate and clearly marked it should be easy for people to see what is
going on.
 - If you are a contributor and want to reformat something, maybe suggest
it on the list, but avoid submitting patches that are just reformatting.

