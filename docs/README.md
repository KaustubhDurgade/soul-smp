# Documentation Index

Welcome to the Soul SMP documentation! This index helps you find the right documentation for your needs.

---

## 🎮 For Players

### Getting Started
Start here if you're new to Soul SMP:
- [**Commands Reference**](commands.md) - Learn all `/souls` commands
- [**UI Examples**](ui-examples.md) - Understand the HUD displays

### Reference
- **Commands** - Full command syntax and permissions
- **UI Displays** - ActionBar and BossBar explanations

---

## 🛠️ For Server Admins

### Setup & Configuration
- [**Deployment Guide**](DEPLOYMENT.md) - How to install and update the plugin
- [**Configuration Reference**](configuration.md) - Complete `config.yml` documentation
- [**Soul Catalog**](catalog.md) - How to define and customize souls

### Administration
- [**Commands Reference**](commands.md) - Admin commands section
- **Permissions** - Found in commands.md

---

## 💻 For Developers

### Getting Started
1. Read the main [README.md](../README.md)
2. Review [Project Structure](PROJECT-STRUCTURE.md)
3. Set up your development environment with [Deployment Guide](DEPLOYMENT.md)

### Technical Documentation
- [**Project Structure**](PROJECT-STRUCTURE.md) - Repository organization
- [**Deployment Workflow**](DEPLOYMENT.md) - Build and deploy process
- [**Phase 3 Summary**](PHASE3-COMPLETE.md) - Latest milestone details

### API Documentation
- [**Configuration API**](configuration.md) - `ConfigManager` usage
- [**Command System**](commands.md) - How commands are implemented
- **Resource System** - See `plugin/src/main/java/dev/soulsmp/core/resource/`
- **UI System** - See `plugin/src/main/java/dev/soulsmp/core/ui/`

### Design Documents
Located in [`../plans/`](../plans/):
- [**Master Roadmap**](../plans/roadmaps/soul-smp-master-roadmap.md)
- [**System Architecture**](../plans/system%20architecture.md)
- [**Wrath Soul**](../plans/roadmaps/wrath-base.md)

---

## 📚 Documentation by Topic

### Commands & UI
| Document | Description |
|----------|-------------|
| [commands.md](commands.md) | Complete `/souls` command reference with permissions |
| [ui-examples.md](ui-examples.md) | Visual examples of ActionBar/BossBar displays |

### Configuration
| Document | Description |
|----------|-------------|
| [configuration.md](configuration.md) | Full `config.yml` reference with all keys |
| [catalog.md](catalog.md) | Soul catalog schema and examples |

### Development
| Document | Description |
|----------|-------------|
| [PROJECT-STRUCTURE.md](PROJECT-STRUCTURE.md) | Repository layout and navigation |
| [DEPLOYMENT.md](DEPLOYMENT.md) | Build, deploy, and testing workflow |
| [PHASE3-COMPLETE.md](PHASE3-COMPLETE.md) | Phase 3 completion summary |
| [phase3-completion.md](phase3-completion.md) | Technical details of Phase 3 |

---

## 🔍 Quick Find

**Looking for...**

- How to use the `/souls` command? → [commands.md](commands.md)
- How to configure souls? → [catalog.md](catalog.md)
- How to deploy the plugin? → [DEPLOYMENT.md](DEPLOYMENT.md)
- Where is the code? → [PROJECT-STRUCTURE.md](PROJECT-STRUCTURE.md)
- What's implemented? → [PHASE3-COMPLETE.md](PHASE3-COMPLETE.md)
- Config file syntax? → [configuration.md](configuration.md)
- UI display modes? → [ui-examples.md](ui-examples.md)

---

## 📖 Reading Order

### For New Players
1. Commands Reference
2. UI Examples

### For New Admins
1. Deployment Guide
2. Configuration Reference
3. Soul Catalog
4. Commands Reference (admin section)

### For New Developers
1. Main README
2. Project Structure
3. Deployment Workflow
4. Phase 3 Summary
5. Browse `/plans` for design docs

---

## 🆘 Getting Help

**Common Issues:**
- Build errors? → Check [DEPLOYMENT.md](DEPLOYMENT.md) troubleshooting
- Config errors? → Verify syntax in [configuration.md](configuration.md)
- Command not working? → Check permissions in [commands.md](commands.md)
- Plugin won't load? → See DEPLOYMENT.md FAQ

**Where to ask:**
- Check this documentation first
- Review relevant roadmap in `/plans/roadmaps/`
- Check server logs in `server/logs/latest.log`
- Open an issue on GitHub

---

## 📝 Contributing to Docs

When adding documentation:
1. **File Naming:** Use `lowercase-with-dashes.md` for topics, `UPPERCASE.md` for milestones
2. **Location:** User docs in `/docs`, design docs in `/plans`
3. **Index:** Add your document to this index
4. **Links:** Use relative paths for cross-references
5. **Format:** Follow existing document structure

---

**Last Updated:** Phase 3 Complete (October 2025)
