package io.papermc.origami;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Entry point for Origami-specific metadata.
 *
 * <p>Origami is a fork of <a href="https://github.com/PaperMC/Paper">Paper</a>. This class
 * exposes the fork's identity (name, URL, and version) in a stable, dependency-free way so
 * that both server internals and plugins can identify the platform they are running on.</p>
 */
public final class Origami {

    /** Human-readable name of this fork. */
    public static final @NotNull String NAME = "Origami";

    /** Short tagline shown in banners and version strings. */
    public static final @NotNull String TAGLINE = "Paper, folded sharper.";

    /** Project home page. */
    public static final @NotNull String URL = "https://origami.umutarchery.xyz";

    private Origami() {
    }

    /**
     * Returns the implementation version of Origami, as baked into the jar manifest at build
     * time (the {@code Implementation-Version} attribute).
     *
     * @return the version string, or {@code "unknown"} if it could not be determined (for
     *     example when running from a raw classes directory rather than a packaged jar)
     */
    public static @NotNull String version() {
        final @Nullable String fromManifest = Origami.class.getPackage().getImplementationVersion();
        return fromManifest != null ? fromManifest : "unknown";
    }

    /**
     * Builds the one-line brand string Origami uses in logs and version output, e.g.
     * {@code "Origami 1.21.11-R0.1 (Paper, folded sharper.)"}.
     *
     * @return the formatted brand line
     */
    public static @NotNull String brandLine() {
        return NAME + " " + version() + " (" + TAGLINE + ")";
    }
}
