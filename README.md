# WhoMoved
Who Moved is a quick plugin for **Fabric 1.16.5** I wrote to log players opening chests, furnaces, hoppers and more.

I needed a plugin to log players opening containers to find thiefs and griefers but couldn't find anything that works on fabric.

The plugin logs: **Username**, **Block type**, **x**, **y**, **z** and **time**.
Everything is logged into a local sqlite DB saved in the `mods` folder under the directory `WhoMoved`.



# Building
Setup your enviroment with `Java 8` and use the command `.\gradlew build`


### Credits
I am using the `SQLite JDBC Driver` by [xerial]([asd](https://github.com/xerial/sqlite-jdbc))
