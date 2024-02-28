/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.mods.impl.Mod2dItems;
import com.lunarclient.apollo.mods.impl.Mod3dSkins;
import com.lunarclient.apollo.mods.impl.ModArmorstatus;
import com.lunarclient.apollo.mods.impl.ModAutoTextHotkey;
import com.lunarclient.apollo.mods.impl.ModBlockOutline;
import com.lunarclient.apollo.mods.impl.ModBossbar;
import com.lunarclient.apollo.mods.impl.ModChat;
import com.lunarclient.apollo.mods.impl.ModChunkBorders;
import com.lunarclient.apollo.mods.impl.ModClock;
import com.lunarclient.apollo.mods.impl.ModColorSaturation;
import com.lunarclient.apollo.mods.impl.ModCombo;
import com.lunarclient.apollo.mods.impl.ModCooldowns;
import com.lunarclient.apollo.mods.impl.ModCoordinates;
import com.lunarclient.apollo.mods.impl.ModCps;
import com.lunarclient.apollo.mods.impl.ModCrosshair;
import com.lunarclient.apollo.mods.impl.ModDamageTint;
import com.lunarclient.apollo.mods.impl.ModDayCounter;
import com.lunarclient.apollo.mods.impl.ModDirectionHud;
import com.lunarclient.apollo.mods.impl.ModFog;
import com.lunarclient.apollo.mods.impl.ModFov;
import com.lunarclient.apollo.mods.impl.ModFps;
import com.lunarclient.apollo.mods.impl.ModFreelook;
import com.lunarclient.apollo.mods.impl.ModGlintColorizer;
import com.lunarclient.apollo.mods.impl.ModHitColor;
import com.lunarclient.apollo.mods.impl.ModHitbox;
import com.lunarclient.apollo.mods.impl.ModHorseStats;
import com.lunarclient.apollo.mods.impl.ModHurtCam;
import com.lunarclient.apollo.mods.impl.ModHypixelBedwars;
import com.lunarclient.apollo.mods.impl.ModHypixelMod;
import com.lunarclient.apollo.mods.impl.ModItemCounter;
import com.lunarclient.apollo.mods.impl.ModItemPhysics;
import com.lunarclient.apollo.mods.impl.ModItemTracker;
import com.lunarclient.apollo.mods.impl.ModKeystrokes;
import com.lunarclient.apollo.mods.impl.ModLighting;
import com.lunarclient.apollo.mods.impl.ModMemory;
import com.lunarclient.apollo.mods.impl.ModMenuBlur;
import com.lunarclient.apollo.mods.impl.ModMinimap;
import com.lunarclient.apollo.mods.impl.ModMomentum;
import com.lunarclient.apollo.mods.impl.ModMotionBlur;
import com.lunarclient.apollo.mods.impl.ModMumbleLink;
import com.lunarclient.apollo.mods.impl.ModNametag;
import com.lunarclient.apollo.mods.impl.ModNeu;
import com.lunarclient.apollo.mods.impl.ModNickHider;
import com.lunarclient.apollo.mods.impl.ModOneSevenVisuals;
import com.lunarclient.apollo.mods.impl.ModPackDisplay;
import com.lunarclient.apollo.mods.impl.ModPackOrganizer;
import com.lunarclient.apollo.mods.impl.ModParticleChanger;
import com.lunarclient.apollo.mods.impl.ModPing;
import com.lunarclient.apollo.mods.impl.ModPlaytime;
import com.lunarclient.apollo.mods.impl.ModPotionEffects;
import com.lunarclient.apollo.mods.impl.ModPvpInfo;
import com.lunarclient.apollo.mods.impl.ModQuickplay;
import com.lunarclient.apollo.mods.impl.ModReachDisplay;
import com.lunarclient.apollo.mods.impl.ModReplaymod;
import com.lunarclient.apollo.mods.impl.ModSaturation;
import com.lunarclient.apollo.mods.impl.ModSba;
import com.lunarclient.apollo.mods.impl.ModScoreboard;
import com.lunarclient.apollo.mods.impl.ModScreenshot;
import com.lunarclient.apollo.mods.impl.ModScrollableTooltips;
import com.lunarclient.apollo.mods.impl.ModServerAddress;
import com.lunarclient.apollo.mods.impl.ModShinyPots;
import com.lunarclient.apollo.mods.impl.ModShulkerPreview;
import com.lunarclient.apollo.mods.impl.ModSkyblock;
import com.lunarclient.apollo.mods.impl.ModSnaplook;
import com.lunarclient.apollo.mods.impl.ModSoundChanger;
import com.lunarclient.apollo.mods.impl.ModStopwatch;
import com.lunarclient.apollo.mods.impl.ModTab;
import com.lunarclient.apollo.mods.impl.ModTeamView;
import com.lunarclient.apollo.mods.impl.ModTimeChanger;
import com.lunarclient.apollo.mods.impl.ModTitles;
import com.lunarclient.apollo.mods.impl.ModTntCountdown;
import com.lunarclient.apollo.mods.impl.ModToggleSneak;
import com.lunarclient.apollo.mods.impl.ModUhcOverlay;
import com.lunarclient.apollo.mods.impl.ModWaila;
import com.lunarclient.apollo.mods.impl.ModWaypoints;
import com.lunarclient.apollo.mods.impl.ModWeatherChanger;
import com.lunarclient.apollo.mods.impl.ModWorldeditCui;
import com.lunarclient.apollo.mods.impl.ModZoom;
import java.util.Arrays;
import java.util.List;

/**
 * Mod container.
 *
 * @since %release_version%
 */
public final class Mods {

    /**
     * List of all current mod classes.
     *
     * @since %release_version%
     */
    public static final List<Class<?>> ALL_MODS = Arrays.asList(
            ModReplaymod.class,
            ModOneSevenVisuals.class,
            ModFps.class,
            ModCps.class,
            ModSba.class,
            ModToggleSneak.class,
            ModZoom.class,
            ModHypixelMod.class,
            ModHypixelBedwars.class,
            ModQuickplay.class,
            ModArmorstatus.class,
            ModKeystrokes.class,
            ModCoordinates.class,
            ModDayCounter.class,
            ModCrosshair.class,
            ModPotionEffects.class,
            ModDirectionHud.class,
            ModTitles.class,
            ModWaypoints.class,
            ModHitColor.class,
            ModScoreboard.class,
            ModItemCounter.class,
            ModPing.class,
            ModMotionBlur.class,
            ModPackOrganizer.class,
            ModChat.class,
            ModTab.class,
            ModNametag.class,
            ModShulkerPreview.class,
            ModScrollableTooltips.class,
            ModUhcOverlay.class,
            ModParticleChanger.class,
            ModNickHider.class,
            ModCooldowns.class,
            ModWorldeditCui.class,
            ModClock.class,
            ModStopwatch.class,
            ModPlaytime.class,
            ModMemory.class,
            ModCombo.class,
            ModReachDisplay.class,
            ModTimeChanger.class,
            ModServerAddress.class,
            ModSaturation.class,
            ModColorSaturation.class,
            ModItemPhysics.class,
            ModTntCountdown.class,
            ModItemTracker.class,
            ModShinyPots.class,
            Mod3dSkins.class,
            ModGlintColorizer.class,
            ModMomentum.class,
            ModBlockOutline.class,
            ModScreenshot.class,
            ModFov.class,
            ModFog.class,
            ModAutoTextHotkey.class,
            ModMumbleLink.class,
            Mod2dItems.class,
            ModBossbar.class,
            ModFreelook.class,
            ModPvpInfo.class,
            ModSnaplook.class,
            ModTeamView.class,
            ModPackDisplay.class,
            ModMenuBlur.class,
            ModMinimap.class,
            ModHitbox.class,
            ModLighting.class,
            ModWeatherChanger.class,
            ModChunkBorders.class,
            ModSoundChanger.class,
            ModWaila.class,
            ModNeu.class,
            ModHurtCam.class,
            ModDamageTint.class,
            ModSkyblock.class,
            ModHorseStats.class
        );

    private Mods() {
    }

}
