#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PLUGIN_DIR="$ROOT_DIR/plugin"
SERVER_PLUGINS_DIR="$ROOT_DIR/server/plugins"
OUTPUT_JAR_NAME="SoulSMP.jar"

SKIP_BUILD=false

while [[ $# -gt 0 ]]; do
  case $1 in
    --skip-build)
      SKIP_BUILD=true
      shift
      ;;
    *)
      echo "Unknown argument: $1" >&2
      exit 1
      ;;
  esac
done

if [[ "$SKIP_BUILD" == false ]]; then
  "$ROOT_DIR/scripts/build_plugin.sh"
fi

LATEST_JAR=$(ls -t "$PLUGIN_DIR/build/libs"/*.jar 2>/dev/null | head -n1)

if [[ -z "$LATEST_JAR" ]]; then
  echo "No plugin JAR found in $PLUGIN_DIR/build/libs" >&2
  exit 1
fi

mkdir -p "$SERVER_PLUGINS_DIR"
cp "$LATEST_JAR" "$SERVER_PLUGINS_DIR/$OUTPUT_JAR_NAME"

echo "Deployed $(basename "$LATEST_JAR") to $SERVER_PLUGINS_DIR/$OUTPUT_JAR_NAME"
