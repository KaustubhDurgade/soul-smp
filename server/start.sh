#!/usr/bin/env bash
set -euo pipefail

JAVA_OPTS=${JAVA_OPTS:-"-Xms2G -Xmx2G"}
JAR=${JAR:-"paper-1.21.6-48.jar"}

cd "$(dirname "$0")"

echo "Starting Paper server (1.21.6) with $JAVA_OPTS"
exec java $JAVA_OPTS -jar "$JAR" nogui
