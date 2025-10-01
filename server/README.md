# Soul SMP Local Paper Server

This folder contains a ready-to-run Paper 1.21.6 server for local testing.

## Contents
- `paper-1.21.6-48.jar` – Paper build 48 for Minecraft 1.21.6
- `start.sh` – launch script (uses `JAVA_OPTS` and `JAR` env overrides if desired)
- `eula.txt` – EULA acceptance (required by Mojang)

## Running the Server

```bash
cd server
./start.sh
```

By default the script allocates 2G heap (`-Xms2G -Xmx2G`). To change heap sizes:

```bash
JAVA_OPTS="-Xms1G -Xmx3G" ./start.sh
```

If you ever replace the Paper jar, either update `JAR` env variable or rename the file.

## Next Steps
- Place plugins into `server/plugins/` (create folder if missing)
- Edit `server.properties` once generated on first run (port, online-mode, etc.)
- Use this server to test the Soul SMP plugin during development

> First launch will take a bit longer while Paper downloads dependencies and generates the world.
