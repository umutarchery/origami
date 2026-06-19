package io.papermc.origami;

/**
 * Marker type so the {@code origami-server} source set is never empty before any
 * real server additions land. Server-side Origami code that is not a patch to
 * upstream Paper lives under the {@code io.papermc.origami} package.
 */
public final class OrigamiServerMarker {

    private OrigamiServerMarker() {
    }
}
