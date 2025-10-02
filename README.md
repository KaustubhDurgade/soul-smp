# Soul SMP Plugin

[![Build](https://github.com/KaustubhDurgade/soul-smp/actions/workflows/build.yml/badge.svg)](https://github.com/KaustubhDurgade/soul-smp/actions/workflows/build.yml)

Soul SMP is a custom Paper plugin that brings a souls-and-paths progression system to multiplayer Minecraft. This repository houses both the runtime plugin and the supporting design documents that drive development.

## Project Layout

| Path | Purpose |
| --- | --- |
| `plugin/` | Source code for the Paper plugin, including Gradle build files. |
| `server/` | Local development server configuration and runtime data (ignored in Git). |
| `plans/` | Design documents, registries, and roadmaps guiding implementation. |
| `scripts/` | Utility scripts for validating registries and managing builds/deploys. |

## Getting Started

1. Install Java 21 (matching the Paper 1.21 runtime).
2. From the `plugin/` directory, run `./gradlew clean build`.
3. Copy the generated JAR from `plugin/build/libs/` into your server's `plugins/` folder (or use `scripts/deploy_plugin.sh`).

## Development Scripts

- `scripts/build_plugin.sh` – Runs the Gradle build from the repository root and drops the resulting JAR in `plugin/build/libs/`.
- `scripts/deploy_plugin.sh` – Builds the plugin (if requested) and copies the latest JAR into `server/plugins/` for quick local testing.

## Documentation

A curated index of planning materials lives in [`plans/README.md`](plans/README.md). Key roadmaps include:

- [`plans/roadmaps/soul-smp-master-roadmap.md`](plans/roadmaps/soul-smp-master-roadmap.md)
- [`plans/roadmaps/wrath-base.md`](plans/roadmaps/wrath-base.md)

## Contributing

1. Branch from `main`.
2. Run the build and ensure all tests pass.
3. Open a pull request referencing relevant roadmap tasks.

## License

This project is currently proprietary. Distribution or reuse requires explicit permission from the maintainers.
