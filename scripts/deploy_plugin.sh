#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PLUGIN_DIR="$ROOT_DIR/plugin"
SERVER_DIR="$ROOT_DIR/server"
SERVER_PLUGINS_DIR="$SERVER_DIR/plugins"
OUTPUT_JAR_NAME="SoulSMP.jar"

SKIP_BUILD=false
SKIP_RESTART=false

while [[ $# -gt 0 ]]; do
  case $1 in
    --skip-build)
      SKIP_BUILD=true
      shift
      ;;
    --skip-restart)
      SKIP_RESTART=true
      shift
      ;;
    *)
      echo "Unknown argument: $1" >&2
      exit 1
      ;;
  esac
done

echo "=== Soul SMP Plugin Deployment ==="
echo ""

# Step 1: Kill any running Paper instances
echo "[1/5] Stopping any running Paper servers..."
if pgrep -f "paper.*jar" > /dev/null; then
  pkill -f "paper.*jar" && sleep 2
  echo "✓ Stopped running Paper server"
else
  echo "✓ No running Paper server found"
fi
echo ""

# Step 2: Build the plugin
if [[ "$SKIP_BUILD" == false ]]; then
  echo "[2/5] Building plugin..."
  "$ROOT_DIR/scripts/build_plugin.sh"
  echo "✓ Build complete"
else
  echo "[2/5] Skipping build (--skip-build flag)"
fi
echo ""

# Step 3: Find the latest JAR
echo "[3/5] Locating plugin JAR..."
LATEST_JAR=$(ls -t "$PLUGIN_DIR/build/libs"/*.jar 2>/dev/null | head -n1)

if [[ -z "$LATEST_JAR" ]]; then
  echo "✗ No plugin JAR found in $PLUGIN_DIR/build/libs" >&2
  exit 1
fi
echo "✓ Found: $(basename "$LATEST_JAR")"
echo ""

# Step 4: Delete old plugin and deploy new one
echo "[4/5] Deploying plugin..."
mkdir -p "$SERVER_PLUGINS_DIR"

# Remove old plugin if it exists
if [[ -f "$SERVER_PLUGINS_DIR/$OUTPUT_JAR_NAME" ]]; then
  rm "$SERVER_PLUGINS_DIR/$OUTPUT_JAR_NAME"
  echo "✓ Removed old plugin"
fi

# Copy new plugin
cp "$LATEST_JAR" "$SERVER_PLUGINS_DIR/$OUTPUT_JAR_NAME"
echo "✓ Deployed $(basename "$LATEST_JAR") → $OUTPUT_JAR_NAME"
echo ""

# Step 5: Start the server
if [[ "$SKIP_RESTART" == false ]]; then
  echo "[5/5] Starting Paper server..."
  echo "----------------------------------------"
  cd "$SERVER_DIR"
  exec ./start.sh
else
  echo "[5/5] Skipping server start (--skip-restart flag)"
  echo ""
  echo "=== Deployment Complete ==="
  echo "To start the server manually, run:"
  echo "  cd $SERVER_DIR && ./start.sh"
fi
