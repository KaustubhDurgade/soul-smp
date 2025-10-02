#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
PLUGIN_DIR="$ROOT_DIR/plugin"

cd "$PLUGIN_DIR"

if [[ $# -eq 0 ]]; then
	./gradlew clean build
else
	./gradlew "$@"
fi
