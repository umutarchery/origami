# 🦢 Origami

> Paper, folded sharper.

[![Build Origami](https://github.com/umutarchery/origami/actions/workflows/build.yml/badge.svg)](https://github.com/umutarchery/origami/actions/workflows/build.yml)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-brightgreen)](https://papermc.io)
[![Upstream](https://img.shields.io/badge/upstream-Paper-blue)](https://github.com/PaperMC/Paper)

**Origami** is a fork of [Paper](https://github.com/PaperMC/Paper) — the high-performance
Minecraft server software. It is built with the official
[`paperweight`](https://github.com/PaperMC/paperweight) patcher toolchain, so every change
Origami makes lives as a clean, reviewable patch on top of upstream Paper rather than a
tangled copy of the whole server.

The name says the goal: take a great sheet of Paper and fold it into something a little
sharper and more refined, without throwing away what makes the original good. Origami
tracks Paper closely and rebases onto new releases, so you get upstream fixes and the
Origami touches on top.

---

## Why a fork?

Paper already does the heavy lifting. A fork makes sense when you want to:

- ship server behaviour that is too opinionated or niche to ever be merged upstream,
- expose extra API for the plugins you run,
- patch `net.minecraft` internals directly (something a plugin simply cannot do).

If you only need to *react* to events, write a plugin instead — it is far easier to
maintain. Reach for a fork when you need to change the server itself.

---

## Project layout

```
origami/
├─ build.gradle.kts          # paperweight-patcher config: how Paper is pulled & patched
├─ settings.gradle.kts       # module wiring (origami-api, origami-server)
├─ gradle.properties         # fork version + the upstream Paper commit we build on (paperRef)
│
├─ build-data/
│  ├─ dev-imports.txt         # extra vanilla/library classes to import into the workspace
│  └─ fork.at                 # access transformers (auto-generated; don't hand-edit)
│
├─ origami-api/               # additions / patches to the Paper *API*
│  ├─ src/main/java/...       # brand-new API classes Origami adds
│  └─ paper-patches/          # patches against existing paper-api files
│
└─ origami-server/            # additions / patches to the *server*
   ├─ src/main/java/...       # brand-new server classes (package io.papermc.origami)
   ├─ paper-patches/          # patches against CraftBukkit / Paper server files
   └─ minecraft-patches/      # patches against decompiled net.minecraft sources
      ├─ features/            # commit-style feature patches (git am format)
      └─ sources/             # per-file source patches
```

After you run the build for the first time, paperweight materialises the upstream
sources into `paper-api/` and `paper-server/` (both git-ignored). Those are Paper's files;
you don't edit them directly — you patch them, and the patches live under the directories
above.

---

## Building

Requirements:

- **JDK 21 or newer** (the project targets Java 21; this repo was developed against
  Temurin 25 with a Java 21 toolchain).
- **Git** on your `PATH`.
- Roughly 3–4 GB of free RAM for the build and a few GB of disk for the upstream checkout.

Everything runs through the bundled Gradle wrapper — no system Gradle needed.

```bash
# 1. Pull Paper, apply all Origami patches, and decompile/set up the workspace
./gradlew applyAllPatches

# 2. Compile and run tests
./gradlew build

# 3. Produce a runnable server jar (Paperclip downloads vanilla on first launch)
./gradlew createMojmapPaperclipJar
```

On Windows PowerShell use `.\gradlew` instead of `./gradlew`.

The runnable jar lands in `origami-server/build/libs/`. Launch it like any Paper jar:

```bash
java -jar origami-server/build/libs/origami-paperclip-*.jar nogui
```

---

## Running a dev server

`paperweight` wires up a `runServer` task so you can boot Origami straight from the source
tree, no jar packaging needed:

```bash
./gradlew runServer
```

It creates a throwaway server under `run/`. Accept the EULA there once
(`eula=true` in `run/eula.txt`) and you're in.

---

## Adding a feature

There are three kinds of change, and each has a home:

1. **Brand-new code** (a new command, a config option, a new API interface)
   → just add `.java` files under `origami-server/src/...` or `origami-api/src/...`
   in the `io.papermc.origami` package. No patch needed.

2. **Editing an existing Paper / CraftBukkit file**
   → edit the materialised file under `paper-server/` or `paper-api/`, then run
   `./gradlew rebuildPatches`. paperweight writes the diff into the matching
   `paper-patches/` directory for you.

3. **Editing `net.minecraft` internals**
   → edit the file under `paper-server/src/minecraft/...`, commit it, then run
   `./gradlew rebuildPatches`. The diff is captured under
   `origami-server/minecraft-patches/`.

The example patches already in this repo (a comment in `BlockUtil`, the
`DefaultUncaughtExceptionHandler` feature patch, a `CraftBlockSoundGroup` tweak) are
harmless demonstrations of each mechanism — delete them once you add real changes.

### Rebasing onto a newer Paper

1. Find the upstream Paper commit you want to build on.
2. Update `paperRef` (and the version fields) in `gradle.properties`.
3. Run `./gradlew applyAllPatches`. Resolve any patches that no longer apply cleanly,
   then `./gradlew rebuildPatches`.

---

## Useful Gradle tasks

| Task | What it does |
| --- | --- |
| `applyAllPatches` | Pull Paper + apply every Origami patch (run this first) |
| `rebuildPatches` | Regenerate patch files from your edits to the materialised sources |
| `build` | Compile both modules and run tests |
| `runServer` | Boot a dev server from source under `run/` |
| `createMojmapPaperclipJar` | Build the distributable Paperclip server jar |
| `cleanCache` | Wipe the paperweight cache if a checkout gets into a bad state |

---

## Credits & licence

Origami stands entirely on the shoulders of [PaperMC](https://papermc.io) and the wider
Bukkit/Spigot/Paper lineage. All upstream code remains under its original licences
(GPL-3.0 for the server, MIT for the API). Origami's own additions are provided under the
same terms as the files they touch.

This is not an official PaperMC project.
