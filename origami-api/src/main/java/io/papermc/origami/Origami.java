package io.papermc.origami;

/**
 * Entry point for Origami-specific metadata.
 *
 * <p>Origami is a fork of <a href="https://github.com/PaperMC/Paper">Paper</a>. This class
 * exposes the fork's identity (name, URL, and version) in a stable, dependency-free way so
 * that both server internals and plugins can identify the platform they are running on.</p>
 *
 * <p>Everything here depends only on the JDK, which keeps it safe to evolve independently of
 * upstream Paper API changes.</p>
 */
public final class Origami {

    /** Human-readable name of this fork. */
    public static final String NAME = "Origami";

    /** Short tagline shown in banners and version strings. */
    public static final String TAGLINE = "Paper, folded sharper.";

    /** Project home page. */
    public static final String URL = "https://github.com/umutarchery/origami";

    private Origami() {
    }

    /**
     * Returns the implementation version of Origami, as baked into the jar manifest at build
     * time (the {@code Implementation-Version} attribute).
     *
     * @return the version string, or {@code "unknown"} if it could not be determined (for
     *     example when running from a raw classes directory rather than a packaged jar)
     */
    public static String version() {
        final String fromManifest = Origami.class.getPackage().getImplementationVersion();
        return fromManifest != null ? fromManifest : "unknown";
    }

    /**
     * Builds the one-line brand string Origami uses in logs and version output, e.g.
     * {@code "Origami 1.21.11-R0.1 (Paper, folded sharper.)"}.
     *
     * @return the formatted brand line
     */
    public static String brandLine() {
        return NAME + " " + version() + " (" + TAGLINE + ")";
    }
}
