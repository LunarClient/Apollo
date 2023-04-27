package com.lunarclient.apollo.player.ui;

import java.util.EnumSet;
import java.util.Set;
import lombok.Value;

/**
 * Represents a staff mod that can be enabled on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class StaffMods {

    /**
     * Returns a {@link Set} of {@link Mod}s.
     *
     * @return the mods set
     * @since 1.0.0
     */
    Set<Mod> mods;

    /**
     * Represents the staff module.
     *
     * @since 1.0.0
     */
    public enum Mod {

        XRAY;

        private static final Set<Mod> MODULE_CACHE = EnumSet.allOf(Mod.class);

        /**
         * Returns a {@link Set} of {@link Mod}s.
         *
         * @return the set of modules
         * @since 1.0.0
         */
        public static Set<Mod> getMods() {
            return Mod.MODULE_CACHE;
        }

    }
}
