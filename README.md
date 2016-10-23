# UCSD RideShare

## Tools for Cooperation

#### 1. Oh-my-zsh (zsh)

For MacOS and Linux users, I personally recommend [Oh-my-zsh](https://github.com/robbyrussell/oh-my-zsh) in order to check which
branch you are at and the repository status quickly. There are also plenty of [feature](http://code.joejag.com/2014/why-zsh.html) here.

If you're Windows users, you can try to install this one: [Babun](http://babun.github.io/), which also helps you when you're working with git.

#### 2. [git-flow](https://github.com/nvie/gitflow)

Git-flow is a extension tool of git that helps us work with git more efficiently and decrease the probability of making conflicts. It's based on this [model](http://nvie.com/posts/a-successful-git-branching-model/) by Vincent Driessen. Please, please at least install this tool.

## Work Flow
After installing the git-flow and clone our repository to your computer, the first thing to do is
```
git flow init
```
to create some branches to separate our outcome product and product which is still under developing. We just need the default setting branches so just click "enter" to the end of the messages.

Then, we usually work under the "develop" branch, so first we need to switch to that branch.

```
git checkout develop
```

Every time you are going to modify your local repository, you should use this command:

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

```
git add .
git commit -m "<One line to show what you've done>"
```

After you finish your work for the feature, you have to type
```
git rebase
```
that let git compares your code on your local computer to the remote repository and makes you update to the latest version in order to avoid of conflicts.

Next, type
```
git flow feature publish <feature_name>
```
So that it can publish what you just modified with that branch to our remote repository.

Next, login your GitHub and link to our repository, and then make a pull request. choose the base as "develop", and choose compare as the branch you just publish, and then click "create pull request" button. Then you're done with your work for now.

Now, others should check the feature is workable and meets our needs. Once someone thinks it's great, he/she can click the "merge" button from the "pull request" area. Our remote "develop" will automatically be merged with the branch which was published by others.

Once your pull request has already been merged with our remote develop branch, you can delete the remote feature branch by using the command in order to keep our remote repository clean:
```
git push origin :<branch_name>
```

Then, you can go back to your "local" develop branch by:
```
git checkout develop
```
and do the `git pull` to keep your local develop branch with the latest version.

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
