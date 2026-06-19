# Contributing to Origami

Thanks for wanting to fold some Paper. This guide covers the mechanics of working in an
Origami (paperweight-patcher) checkout.

## One-time setup

```bash
./gradlew applyAllPatches
```

This clones the pinned upstream Paper commit (`paperRef` in `gradle.properties`),
decompiles Minecraft, and applies every Origami patch. After it finishes you'll have
`paper-api/` and `paper-server/` directories — these are the **materialised upstream
sources**. They are git-ignored on purpose: you never commit them directly.

## The three kinds of change

### 1. New code (easiest)

Adding a command, a config option, or a brand-new API type? Just create `.java` files:

- API additions → `origami-api/src/main/java/io/papermc/origami/...`
- Server additions → `origami-server/src/main/java/io/papermc/origami/...`

These are normal tracked files. Commit them like any other source. No patch dance.

### 2. Changing an existing Paper / CraftBukkit file

1. Edit the file in place under `paper-server/` or `paper-api/`.
2. Run `./gradlew rebuildPatches`.
3. paperweight writes/updates a `.patch` under the matching `paper-patches/` directory.
4. Commit the generated patch file (not the materialised source).

### 3. Changing `net.minecraft` internals

The decompiled vanilla sources live under `paper-server/src/minecraft/`. They are not
redistributable, so changes are stored as patches:

1. Edit the file under `paper-server/src/minecraft/...`.
2. `git add` and commit the change **inside that materialised tree** (paperweight needs a
   commit to diff against).
3. Run `./gradlew rebuildPatches`.
4. The diff is captured under `origami-server/minecraft-patches/`. Commit that.

If the class you want to edit isn't present, add it to `build-data/dev-imports.txt` and
re-run `applyAllPatches`.

## Access transformers

`build-data/fork.at` is **auto-generated** by `rebuildPatches`. Don't hand-edit it; widen
access by annotating in the relevant patch and letting the tooling regenerate it.

## Before you push

```bash
./gradlew applyAllPatches   # make sure everything still applies cleanly
./gradlew build             # compile + tests
```

Keep one logical change per patch, and give feature patches a descriptive
`Subject:` line — that becomes the patch's filename and the commit message upstream-style.

## Rebasing onto a newer Paper

1. Bump `paperRef` and the version fields in `gradle.properties`.
2. `./gradlew applyAllPatches` — fix any patches that conflict.
3. `./gradlew rebuildPatches` to refresh them, then commit.
