# webchat
like IRC, but using HTTP


##Running the server
_Requirements_
`Predis`
`some PHP server`

simply host the files, and go (all links are relitave, so it should work)


_Extras_

also included are the files
`clearLog.service`,`clearLog.timer`, and `clearLog.sh`

these files are used to clear tle log (as the name implies) every day.
to enable this functonality, the folowing must be executed.
```bash
sudo cp ./clearLog.sh /usr/sbin/clearLog.sh
sudo cp ./clearLog.service /etc/systemd/system/clearLog.service
sudo cp ./clearLog.timer /etc/systemd/system/clearLog.timer
sudo systemctl enable clearLog.timer
```

__NOTE:__ you must have `systemd` on your system for the scripts to work.