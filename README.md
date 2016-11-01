# UCSD RideShare

## Useful Information

1. [Android Studio Environment Setup](https://github.com/sandrazd459/CSE110_PR_Project/wiki/Android-Environment-Setting)

2. [Tools for Cooperation with Git](https://github.com/sandrazd459/CSE110_PR_Project/wiki/Tools-for-Cooperation-with-Git)

## Work Process without git-flow

If you think installing git-flow is a pain, you still can work without it.

After cloning our repository to your computer, the first thing to do is
```
cd CSE110_PR_Project
git checkout develop
git pull
git checkout -b <your name>/<the feature you wanna do>
```

Then use `git branch` to make sure you're in the branch you just created. That branch belongs to you, no one will affect you code until you're going to merge back to the develop branch. But someone else will do the review and merge stuff, so the only commands you need is:

1. Check which files you modified before you commit: `git status`
2. Add new or modified files: `git add .`
3. Commit to your local repository: `git commit -m "<one line message tells what you did>"`

After finishing the feature, you can now push your branch to the remote repository with

```
git push --set-upstream origin <the branch name>
```
Next, login your GitHub and link to our repository, and then [make a pull request](http://imgur.com/HaKYlHG). choose the base as "develop", and choose compare as the branch you just publish, and then click "[create pull request](http://imgur.com/FsAl2qv)" button. Then you're done with your work for now.

After you've been told you code is merged into the main branch, you need to do:

```
git checkout develop
git pull
git branch -D <the branch you made>
git remote prune origin
```

Then you can create another branch to work on the next feature.

## Work Flow with git-flow

After installing the git-flow and clone our repository to your computer, the first thing to do is
```
cd CSE110_PR_Project
git flow init
```
to create some branches to separate our outcome product and product which is still under developing. We just need the default setting branches so just click "enter" to the end of the messages.

Then, we usually work under the "develop" branch, so first we need to switch to that branch.

```
git checkout develop
```

"Every time" you are going to modify your local repository, you should use this command:

```
git pull
```
to keep your develop branch of your local repository at the latest version.

Next, if you want to create a new feature or modify a old feature, such as login system or posts, type
```
git flow feature start <feature_name>
```
For example, if we want to develop login system, we can type `git flow feature start login_system` to initialize a new branch called `feature/login_system`.

When you finish some works, you can commit it into your local repository by

Next, type
```
git flow feature publish <feature_name>
```
So that it can publish what you just modified with that branch to our remote repository.

Next, login your GitHub and link to our repository, and then [make a pull request](http://imgur.com/HaKYlHG). choose the base as "develop", and choose compare as the branch you just publish, and then click "[create pull request](http://imgur.com/FsAl2qv)" button. Then you're done with your work for now.


After you work has been merged into the main branch, you can go back to your "local" develop branch by:
```
git checkout develop
git pull
```
to keep your local develop branch with the latest version.

Next, you don't need the <feature_name> branch anymore, so you can type:
```
git branch -D feature/<feature_name>
```
to delete the branch you made in your local repository.

Finally, in order to make sure your local repository and our remote repository keep on the same track, you can type the following command:
```
git remote prune origin
```

That's our work flow which allows us to work with git and GitHub without conflicts. If you still have questions, please message me (Hank Liu), I will try my best to help you :D
