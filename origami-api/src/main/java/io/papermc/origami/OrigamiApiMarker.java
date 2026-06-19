package io.papermc.origami;

/**
 * Marker type so the {@code origami-api} source set is never empty before any
 * real API additions land. Origami's public API additions live alongside the
 * Paper API and are exposed under the {@code io.papermc.origami} package.
 */
public final class OrigamiApiMarker {

    private OrigamiApiMarker() {
    }
}
