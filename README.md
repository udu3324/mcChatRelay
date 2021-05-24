<p align="center">
<img src="https://raw.githubusercontent.com/udu3324/mcChatRelay/master/src/pkg/Main/icon.png" width="150" alt="icon">
</p>
<p align="center">
	  mcChatRelay
</p>
<p align="center">
	  <img src="https://img.shields.io/badge/java-8-blue">
	  <img src="https://img.shields.io/badge/price-free-green">
	  <img src="https://img.shields.io/github/issues/udu3324/mcchatrelay">
</p>
<p align="center">
  <a href="http://forthebadge.com/"><img src="https://forthebadge.com/images/badges/as-seen-on-tv.svg"/></a>
  <a href="http://forthebadge.com/"><img src="https://forthebadge.com/images/badges/built-with-grammas-recipe.svg"/></a>
</p>
<h2>mcChatRelay</h2>
mcChatRelay is a java application that relays messages from a server to discord. Minecraft will be 
required to run a instance first, so the program can hook to its latest.log file. You can also parse 
messages sent in chat to send special messages to discord. For example, events, giveaways, alerts, 
and more. It is recommended to have some type of reconnect mod to keep the minecraft instance online. 

<h2>Minecraft Versions</h2>
mcChatRelay can work on about every minecraft version. It is as long as if there is a log fie it 
can hook up to and read off of. 

<h2>Dependencies</h2>
In the repository, dependencies are already installed and is in a package called ExternalLibraries. 
All you need is to have JDK 8 installed and have Intellij IDEA to build and run.

<h3>Dependencies Used</h3>
<p>
JDA 4.2.1
<br>
Apache Commons IO 2.8.0
</p>

<h2>Setup</h2>
<p>
1, Make sure you have JDK 8
<br>
2, Clone the repository and open it in Intellij IDEA
<br>
3, Open Main.java (in src/pkg/main)
<br>
4, Change the 6 variables (mcLogLocation, token, status, prefix,  staffRoleID, and minecraftChatChannel)
<br>
5, Run Main.java
</p>
<h2>After Setup</h2>
After you have set up and ran mcChatRelay, you will need to do the session command in a channel, or the bots DMs. 