# 1.3.0

<div align="center"><h1>Informational Update</h1></div>

<p>Many new commands mostly were implemented in the release! links to the pull requests and issues:</p>
PR: #14 

Issues: #5 , #7 , #8 , #9 , #10 , #11 , #12 , #13

## ⚡ Highlights

* Database storage of serverId's
* Removed setPrefix command
* Loads of new info commands
* categories (internal)
* semantic versioning

## 🕵️‍♂️ detailed changelog:
Here is a detailed list of all changes made to the final pull request:

### ➕ additions:
* fixed hack in Magic8Ball command, see [here](https://github.com/DisBots-Studios-Inc/Spark/pull/14/commits/4911830975181e11bcf7ac0318e6e9a985b8d49d)
* added Server model
* added category in sdcf-4j
* added ServerInfo command
* added BotInfo command
* added Github command
* added Support command
* added Kill command (dev only)
* added Uptime command
* updated javacord
* fixed log4j logging to file

### ➖ deletions:
* removed setPrefix command
* removed mongo documents in favour of POJOS
* updated old logger

<hr />

Contributors in this release:

* @YT-GameWorks

# 0.2
<div align="center"><h1>Command framework update</h1></div>

<p>Many new features and bug fixes were implemented! links to the pull requests and issues:</p>
Pr: [#2](https://github.com/DisBots-Studios-Inc/Spark/pull/2)

Issue: [#3](https://github.com/DisBots-Studios-Inc/Spark/issues/3)

## ⚡ Highlights

* sdcf4j command support
* added utility classes
* added help command

## 🕵️‍♂️ detailed changelog:
Here is a detailed list of all changes made to the final pull request:

### ➕ additions:
* added sdcf4j and sdcf4j-javacord as projects for a multi-project build, for customization
* added GNU public license v3
* added a lot of JavaDoc
* added MongoDB util
* added log colors util
* added embedMaker util
* added help command
* added SetPrefix command (partial)
* copyright on each file

### ➖ deletions:
* deprecated old CommandHandler
* updated old emoji handler
* removed JANSI library
* updated old color palette

<hr />

Contributors in this release:

* @YT-GameWorks
* @Aktindo

#0.1

minimal features... basic database support