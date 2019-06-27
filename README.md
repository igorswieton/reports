# reports
Reports is a report management plugin designed for management of single spigot servers alike. Plugin is currently under heavy development, so stay tuned.

# How it works
If you want to report a player, you type the respective command `(check: Commands)` in the chat and finally an anvil gui opens, where you have to type in the respective reason. After that your report will be saved in a MySQL database and all team members `(check: Permissions)` will be notified about your report. If a team member now decides to control a report and then closes it, the report will be deleted from the database.

note: current gamemode, inventory, and location will be saved before a control start. That's why you do not have to worry about your game data going down.

# Current supported features
- MySQL support
- Anvil GUI
- Notifications of all team members `(check Permissions)`
- Bug-free controlling of a reported player
- Adapted command tab-completion in the chat

# TODO
- Improve join notification `number of open reports`
- More configuration options (?)
- Pulishing `(Teleport sounds, Bug fixing, ...)`
- List command
- Code cleanup (!)

# Permissions
There is only one permission: `report.*`

If a player has this permission, he is able to see, control and close a report.
Everyone on the server is able to report an other player. 

# Commands
- /report (victim) `allows to report a player`
- /report control `allows to control a reported player`
- /report close `allows to close a controlling process`
  
# Installation
To download the jar I recommend you to download it from [here](https://www.spigotmc.org/resources/reports.68754/).

Of course, you have the option to download the repository and build it by yourself via [Gradle](https://gradle.org/).

## Setup guide

```xml
1. Download the jar file (the plugin).

2. Move the file to your plugins folder.

3. start the server.
3.1. Now an error message should appear in the console. 

4. Stop your server.

5. Open the config.yml file.
5.1. This is the way you can find it:
5.2. Open the "plugins" folder..
5.3. open the "reports" folder.
5.4. open the config.yml file.

6. Enter your MySQL data.

7. Close the config.yml file.

8. Start your server again.
```

# Contribution
If you want to contribute, just open a [pull request](https://github.com/igorswieton/reports/pulls).

# Example previews

## GUI
![GUI preview](https://i.ibb.co/gM5MHHP/gui-example.png)

## Confirmation
![Apply preview](https://i.ibb.co/T8fBD85/apply-example.png)

## Notification
![Notification preview](https://i.ibb.co/48QLLYF/notification-example.png)

## Control start
![Start preview](https://i.ibb.co/grw83KP/start-example.png)

## Control close
![Close preview](https://i.ibb.co/FwLZBDZ/close-example.png)

## MySQL
![MySQL preview](https://i.ibb.co/H26z6b2/mysql.png)

# Contact
If you want to contact me, you can always reach me over Discord (**Igor'#8266**).
