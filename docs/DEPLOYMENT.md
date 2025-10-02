# Quick Deployment Guide

## One-Command Deployment

The deploy script now handles everything automatically:

```bash
cd scripts
./deploy_plugin.sh
```

That's it! The script will:
1. ✓ Stop any running server
2. ✓ Build the plugin
3. ✓ Deploy it to `server/plugins/`
4. ✓ Start the server

## Common Scenarios

### Standard Development Workflow
```bash
# 1. Make your code changes
vim plugin/src/main/java/dev/soulsmp/...

# 2. Deploy and test
cd scripts
./deploy_plugin.sh

# 3. Server starts automatically with new plugin
# Test in-game, check logs, etc.
```

### Quick Config Testing
```bash
# Edit config without rebuilding
vim server/plugins/SoulSMP/config.yml

# Restart with existing plugin
./deploy_plugin.sh --skip-build
```

### Manual Control
```bash
# Deploy but don't start (for manual server control)
./deploy_plugin.sh --skip-restart

# Later, start manually:
cd ../server
./start.sh
```

## What Changed

### Before (Manual Steps)
```bash
# 1. Stop server
pkill -f paper

# 2. Build plugin
cd plugin
./gradlew build

# 3. Copy plugin
cp build/libs/*.jar ../server/plugins/SoulSMP.jar

# 4. Start server
cd ../server
./start.sh
```

### Now (Automated)
```bash
./deploy_plugin.sh
```

## Monitoring

### Watch Logs During Development
```bash
# In a separate terminal:
tail -f server/logs/latest.log
```

### Check Server Status
```bash
pgrep -f "paper.*jar"
# Returns PID if running, nothing if stopped
```

### Stop Server Manually
```bash
pkill -f "paper.*jar"
```

## Tips for Phase 4 Development

1. **Keep the script terminal open**
   - You'll see build output and server startup
   - Easy to spot compilation or runtime errors

2. **Use `--skip-build` for config/YAML changes**
   - Much faster (5-10 seconds vs 15-20 seconds)
   - Great for tuning resource values, cooldowns, etc.

3. **Test rapidly**
   ```bash
   # Make change → Deploy → Test in-game
   ./deploy_plugin.sh
   # Repeat as needed
   ```

4. **Check for errors**
   - Build errors: shown in script output
   - Runtime errors: check `server/logs/latest.log`
   - Plugin not loading: verify `server/plugins/SoulSMP.jar` exists

## Troubleshooting

### Script hangs on "Stopping server..."
**Problem:** Server won't stop gracefully  
**Solution:**
```bash
pkill -9 -f "paper.*jar"  # Force kill
./deploy_plugin.sh --skip-restart  # Deploy without starting
```

### "No plugin JAR found"
**Problem:** Build failed or no JAR exists  
**Solution:**
```bash
cd ../plugin
./gradlew build --info  # Verbose build
cd ../scripts
./deploy_plugin.sh --skip-build
```

### Plugin loads but features don't work
**Problem:** Runtime error after deployment  
**Solution:**
```bash
tail -100 ../server/logs/latest.log  # Check errors
# Fix code
./deploy_plugin.sh  # Redeploy
```

---

**Ready for Phase 4!** 🚀

The deployment workflow is now streamlined for rapid ability development. Make changes, run the script, test in-game—rinse and repeat!
