# ServicePlugin

This small plugin provides service commands for developers:
- /stop - Just shuts the server down.
- /restart - Just shuts the server down.
- /reload - No bukkit nagging.

## Stop/Restart:

To properly use the /restart and /stop command a startscript like this is needed:

```
#!/bin/bash
# Start script started, we can create the loop-file
touch .restart

# Loop until file removed.
while [ -f .restart ] ;
do
    echo "=================================================="
    java -jar -DIReallyKnowWhatIAmDoingISwear=a craftbukkit-1.12.2-R0.1-SNAPSHOT.jar
done
```

The /stop command will delete the .restart file.

## Additional:

In case you are using a compile script, its possible to reload the server from external:
- Compile &&
- Upload &&
- Reload command: `ssh <server> 'echo "reload" > <serverPath>/cmd-insert'

A reload will be performed on file changes of file 'cmd-insert'

## Permissions:

Each command has its own permission: service.<commandname>

## Use?

You may find this plugin useful, if you disabled Vanilla and Bukkit commands for reasons :P (You wouldn't be alone)

Or if you want to overwrite them.
