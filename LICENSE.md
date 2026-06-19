# Licensing

Origami is a fork of [Paper](https://github.com/PaperMC/Paper), which is itself
descended from Spigot, CraftBukkit, and Bukkit. Origami does not relicense any of
that work — every file keeps the licence it carries upstream.

## How the licences split

- **Server (`origami-server`, the Paper/CraftBukkit/`net.minecraft` side):**
  licensed under the **GNU General Public License v3.0**, matching Paper's server
  module. See <https://www.gnu.org/licenses/gpl-3.0.html>.

- **API (`origami-api`, the Bukkit/Paper API side):**
  licensed under the **MIT License**, matching Paper's API module.

- **Patches under `origami-server/` and `origami-api/`:**
  each patch is a derivative of the upstream file it modifies and is therefore
  covered by that file's original licence (GPL-3.0 for server patches, MIT for
  API patches).

- **Brand-new Origami source files** (under the `io.papermc.origami` package):
  released under the same licence as the module they live in — GPL-3.0 for the
  server, MIT for the API.

## Upstream notices

Origami redistributes and builds upon code owned by the PaperMC team and the
wider Bukkit/Spigot lineage. All upstream copyright notices and licence headers
are preserved. For the authoritative upstream licence text see:

- Paper: <https://github.com/PaperMC/Paper/blob/main/LICENSE.md>

## Not affiliated

Origami is an independent fork and is **not** an official PaperMC project, nor is
it endorsed by PaperMC, Mojang, or Microsoft. "Minecraft" is a trademark of
Mojang AB.
