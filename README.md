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

Next, if you want to create a new feature or modify a old feature, such as login system or posts, type
```
git flow feature start <feature_name>
```
For example, if we want to develop login system, we can type `git flow feature start login_system` to initialize a new branch called `feature/login_system`.

After you finish your work for the feature, you have to type
```
git rebase
```
that let git compares your code on your local computer to the remote repository and makes you update to the latest version in order to avoid of conflicts.

Next, type
```
git flow feature publish <feature_name>
```
