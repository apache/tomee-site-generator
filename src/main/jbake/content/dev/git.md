Title: GIT for TomEE Developers

###The GitFlow Workflow

Notes before you begin (mostly for SVN users):

* The '*develop*' repository is the equivalent of the SVN *trunk* directory.  
* The '*master*' repository is the equivalent of the SVN *tags* directory.    
* The '*origin*' is the actual GIT repository that contains the projects *master*, *develop* and other branches (Like trunk, branches and tags).  
* Unlike SVN a 'commit' is only a local action. The local commits are only published to the remote repository using 'push'.  
* Commit and push code to your own feature branch as often as you like, but only merge stable branches back into to the *develop* branch.  
* Only the release manager should manage the *master* repository
* Read the official Apache Committer documentation [here](https://gitbox.apache.org/#committers-getting-started)

<span style="color: rgb(128,0,0);">Run commands in the directory of the project you are working on, for example 'tomee':</span>

Always create a feature branch from *develop* using an '*extremely*' descriptive name, this should usually be the [JIRA](https://issues.apache.org/jira/browse/TOMEE) id or task name you want to work on:

><span style="color: rgb(51,51,153);">git checkout -b TOMEE-007 develop</span>
>
>Switched to a new branch 'TOMEE-007'  


><span style="color: rgb(51,51,153);">git status</span>
>
>nothing to commit, working directory clean

 Immediately push the new branch to the repository so everyone can see it remotely (and possibly collaborate):

><span style="color: rgb(51,51,153);">git push -u origin TOMEE-007</span>
>
>Branch TOMEE-007 set up to track remote branch TOMEE-007 from origin.

Once that is done then you just need the simple push for subsequent calls on this branch:

><span style="color: rgb(51,51,153);">git push</span>

<span style="color: rgb(0,128,0);">Work like mad on the JIRA issue calling commit and add as often as you like...</span>

If others are working on your branch also remember to pull their changes (Or just as good practice):

><span style="color: rgb(51,51,153);">git pull</span>
>
><span style="color: rgb(51,51,153);">git commit</span>
>
><span style="color: rgb(51,51,153);">git push</span>

Finally, to push the completed (or significant non-breaking progress on the) feature to *develop* at any time (ensuring *develop* is up to date first):

><span style="color: rgb(51,51,153);">git pull origin develop</span>

><span style="color: rgb(51,51,153);">git checkout develop</span>

><span style="color: rgb(51,51,153);">git merge --no-ff TOMEE-007</span>

Once the completed feature is merged and the JIRA resolved then the branch can and 'should' be deleted before pushing:

><span style="color: rgb(51,51,153);">git branch -d TOMEE-007</span>

><span style="color: rgb(51,51,153);">git push origin develop</span>

<a name="git-gui"></a>
###The GUI Way

Now we have learned to do it the hard way, time to look at the simplified version. The GitHub native tools!

For the latest mac version go here: [Mac Latest](https://mac.github.com/), 

And windows here: [Win Latest](https://windows.github.com/)

These tools will probably not save you much time over the command line, but provide a much better visualization of ongoing processes.

The steps are and remain as described above:

1. From *develop* create a new branch called, for example TOMEE-007
1. 'Publish' (push -u) the new branch
1. Work on the branch and commit regularly
1. Synchronize (pull and push) changes in the the branch when stable
1. Switch to *develop* and synchronize (pull)
1. Merge the branch into *develop*, i.e. 'TOMEE-007' + 'develop'  = 'develop' <span style="color: rgb(255,102,0);">*See note below</span>
1. Commit *develop*
1. Synchronize *develop* (pull and push)
1. Delete the branch TOMEE-007

<span style="color: rgb(255,102,0);">*Note</span>: You can actually merge the current *develop* (or any other 'synchronized' branch) into your branch first to make sure it is stable,i.e. 'develop' + 'TOMEE-007' = 'TOMEE-007' - This is really cool!