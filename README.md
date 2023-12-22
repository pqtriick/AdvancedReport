# AdvancedReport

A plugin for your minecraft server that allows users to create reports

# Features
This plugin allows you to:
* Add up to 36 custom report types
* Live chat between players
* Save chatlogs of reportchat
* Everything GUI based

## Permissions:

* AR.seereport | To see / close reports

## Commands:

* /cancelreport | Cancel your active report
* /report | Create a report (GUI based)
* /reportchat | Communicate in reportchat with staff / player
* /closereport | Close report as a staff member (Requires AR.seereport)
* /viewreports | Show all open reports in quene (Requires AR.seereport)

# Config
In the plugin directory you will find:
* Chatlog folder with chatlog file
  Exampe:
  ```
  log:
  '0': 'Zitrone_btw:  Message3 | 22:33:42'
  '1': 'Zitrone_btw:  Nessage3 | 22:33:42'
  '2': 'Zitrone_btw:  Message2 | 22:33:40'
  '3': 'Zitrone_btw:  Message2 | 22:33:40'
  '4': 'Zitrone_btw:  Message1 | 22:33:39'
  '5': 'Zitrone_btw:  Message1 | 22:33:39'
  ```
* Config file to create custom report types
  Exampe:
    ```
    UIID:
    '12':
      name: §cʀᴇᴘᴏʀᴛ ᴘʟᴀʏᴇʀ
      lore: §7» §cClick to open report
      material: RED_CONCRETE
      type: Player Report
    '13':
      name: §9ʀᴇᴘᴏʀᴛ ʙᴜɢ
      lore: §7» §9Click to open report
      material: BLUE_CONCRETE
      type: Bug Report
  ```
* Messages file to edit all messages in the plugin

**If any file was edited make sure to save it and restart the plugin otherwise the changed won't have any effect**

# Pictures

    

  
