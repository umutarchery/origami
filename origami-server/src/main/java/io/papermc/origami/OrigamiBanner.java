package io.papermc.origami;

import java.util.List;

/**
 * Produces the Origami startup banner.
 *
 * <p>This is intentionally self-contained and depends only on the JDK and the
 * {@link Origami} metadata class, so it can be wired into the server's boot sequence by a
 * small patch without dragging in any other server internals. Call {@link #lines()} to get
 * the banner as a list of lines, or {@link #print(java.io.PrintStream)} to write it out.</p>
 */
public final class OrigamiBanner {

    private OrigamiBanner() {
    }

    /**
     * Returns the banner as individual lines (no trailing newlines), ready to be logged or
     * printed. The fork version is pulled from {@link Origami#version()}.
     *
     * @return an immutable list of banner lines
     */
    public static List<String> lines() {
        return List.of(
            "   ___       _                   _ ",
            "  / _ \\ _ __(_)__ _ __ _ _ __ ___(_)",
            " | (_) | '_ \\ / _` / _` | '  \\/ -_) |",
            "  \\___/| .__/_\\__, \\__,_|_|_|_\\___|_|",
            "       |_|    |___/   " + Origami.TAGLINE,
            "",
            "  " + Origami.brandLine(),
            "  " + Origami.URL
        );
    }

    /**
     * Writes the banner to the given stream, one line per row.
     *
     * @param out the stream to print to (must not be {@code null})
     */
    public static void print(final java.io.PrintStream out) {
        for (final String line : lines()) {
            out.println(line);
        }
    }
}
