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
package com.lunarclient.apollo.example;

import com.lunarclient.apollo.example.command.AutoTextHotkeyCommand;
import com.lunarclient.apollo.example.command.BeamCommand;
import com.lunarclient.apollo.example.command.BorderCommand;
import com.lunarclient.apollo.example.command.ChatCommand;
import com.lunarclient.apollo.example.command.ColoredFireCommand;
import com.lunarclient.apollo.example.command.CombatCommand;
import com.lunarclient.apollo.example.command.CooldownCommand;
import com.lunarclient.apollo.example.command.EntityCommand;
import com.lunarclient.apollo.example.command.GlintCommand;
import com.lunarclient.apollo.example.command.GlowCommand;
import com.lunarclient.apollo.example.command.HologramCommand;
import com.lunarclient.apollo.example.command.InventoryCommand;
import com.lunarclient.apollo.example.command.LimbCommand;
import com.lunarclient.apollo.example.command.ModSettingsCommand;
import com.lunarclient.apollo.example.command.NametagCommand;
import com.lunarclient.apollo.example.command.NickHiderCommand;
import com.lunarclient.apollo.example.command.NotificationCommand;
import com.lunarclient.apollo.example.command.PayNowCommand;
import com.lunarclient.apollo.example.command.RichPresenceCommand;
import com.lunarclient.apollo.example.command.SaturationCommand;
import com.lunarclient.apollo.example.command.ServerRuleCommand;
import com.lunarclient.apollo.example.command.StaffModCommand;
import com.lunarclient.apollo.example.command.StopwatchCommand;
import com.lunarclient.apollo.example.command.TeamCommand;
import com.lunarclient.apollo.example.command.TebexCommand;
import com.lunarclient.apollo.example.command.TitleCommand;
import com.lunarclient.apollo.example.command.TntCountdownCommand;
import com.lunarclient.apollo.example.command.TransferCommand;
import com.lunarclient.apollo.example.command.VignetteCommand;
import com.lunarclient.apollo.example.command.WaypointCommand;
import com.lunarclient.apollo.example.module.impl.AutoTextHotkeyExample;
import com.lunarclient.apollo.example.module.impl.BeamExample;
import com.lunarclient.apollo.example.module.impl.BorderExample;
import com.lunarclient.apollo.example.module.impl.ChatExample;
import com.lunarclient.apollo.example.module.impl.ColoredFireExample;
import com.lunarclient.apollo.example.module.impl.CombatExample;
import com.lunarclient.apollo.example.module.impl.CooldownExample;
import com.lunarclient.apollo.example.module.impl.EntityExample;
import com.lunarclient.apollo.example.module.impl.GlintExample;
import com.lunarclient.apollo.example.module.impl.GlowExample;
import com.lunarclient.apollo.example.module.impl.HologramExample;
import com.lunarclient.apollo.example.module.impl.InventoryExample;
import com.lunarclient.apollo.example.module.impl.LimbExample;
import com.lunarclient.apollo.example.module.impl.ModSettingsExample;
import com.lunarclient.apollo.example.module.impl.NametagExample;
import com.lunarclient.apollo.example.module.impl.NickHiderExample;
import com.lunarclient.apollo.example.module.impl.NotificationExample;
import com.lunarclient.apollo.example.module.impl.PayNowExample;
import com.lunarclient.apollo.example.module.impl.RichPresenceExample;
import com.lunarclient.apollo.example.module.impl.SaturationExample;
import com.lunarclient.apollo.example.module.impl.ServerRuleExample;
import com.lunarclient.apollo.example.module.impl.StaffModExample;
import com.lunarclient.apollo.example.module.impl.StopwatchExample;
import com.lunarclient.apollo.example.module.impl.TeamExample;
import com.lunarclient.apollo.example.module.impl.TebexExample;
import com.lunarclient.apollo.example.module.impl.TitleExample;
import com.lunarclient.apollo.example.module.impl.TntCountdownExample;
import com.lunarclient.apollo.example.module.impl.TransferExample;
import com.lunarclient.apollo.example.module.impl.VignetteExample;
import com.lunarclient.apollo.example.module.impl.WaypointExample;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter @Setter
public abstract class ApolloExamplePlugin extends JavaPlugin {

    @Getter
    private static ApolloExamplePlugin instance;

    private AutoTextHotkeyExample autoTextHotkeyExample;
    private BeamExample beamExample;
    private BorderExample borderExample;
    private ChatExample chatExample;
    private ColoredFireExample coloredFireExample;
    private CombatExample combatExample;
    private CooldownExample cooldownExample;
    private EntityExample entityExample;
    private GlintExample glintExample;
    private GlowExample glowExample;
    private HologramExample hologramExample;
    private InventoryExample inventoryExample;
    private LimbExample limbExample;
    private ModSettingsExample modSettingsExample;
    private NametagExample nametagExample;
    private NickHiderExample nickHiderExample;
    private NotificationExample notificationExample;
    private PayNowExample payNowExample;
    private RichPresenceExample richPresenceExample;
    private SaturationExample saturationExample;
    private ServerRuleExample serverRuleExample;
    private StaffModExample staffModExample;
    private StopwatchExample stopwatchExample;
    private TeamExample teamExample;
    private TebexExample tebexExample;
    private TitleExample titleExample;
    private TntCountdownExample tntCountdownExample;
    private TransferExample transferExample;
    private VignetteExample vignetteExample;
    private WaypointExample waypointExample;

    @Override
    public void onEnable() {
        instance = this;

        this.registerCommonCommands();
        this.registerCommonModulesExamples();

        this.enable();
        this.registerCommands();
        this.registerModuleExamples();
        this.registerListeners();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommonCommands() {
        this.getCommand("autotexthotkey").setExecutor(new AutoTextHotkeyCommand());
        this.getCommand("beam").setExecutor(new BeamCommand());
        this.getCommand("border").setExecutor(new BorderCommand());
        this.getCommand("chat").setExecutor(new ChatCommand());
        this.getCommand("coloredfire").setExecutor(new ColoredFireCommand());
        this.getCommand("combat").setExecutor(new CombatCommand());
        this.getCommand("cooldown").setExecutor(new CooldownCommand());
        this.getCommand("entity").setExecutor(new EntityCommand());
        this.getCommand("glint").setExecutor(new GlintCommand());
        this.getCommand("glow").setExecutor(new GlowCommand());
        this.getCommand("hologram").setExecutor(new HologramCommand());
        this.getCommand("inventory").setExecutor(new InventoryCommand());
        this.getCommand("limb").setExecutor(new LimbCommand());
        this.getCommand("modsettings").setExecutor(new ModSettingsCommand());
        this.getCommand("nametag").setExecutor(new NametagCommand());
        this.getCommand("nickhider").setExecutor(new NickHiderCommand());
        this.getCommand("notification").setExecutor(new NotificationCommand());
        this.getCommand("paynow").setExecutor(new PayNowCommand());
        this.getCommand("richpresence").setExecutor(new RichPresenceCommand());
        this.getCommand("saturation").setExecutor(new SaturationCommand());
        this.getCommand("serverrule").setExecutor(new ServerRuleCommand());
        this.getCommand("staffmod").setExecutor(new StaffModCommand());
        this.getCommand("stopwatch").setExecutor(new StopwatchCommand());
        this.getCommand("team").setExecutor(new TeamCommand());
        this.getCommand("tebex").setExecutor(new TebexCommand());
        this.getCommand("title").setExecutor(new TitleCommand());
        this.getCommand("tntcountdown").setExecutor(new TntCountdownCommand());
        this.getCommand("transfer").setExecutor(new TransferCommand());
        this.getCommand("vignette").setExecutor(new VignetteCommand());
        this.getCommand("waypoint").setExecutor(new WaypointCommand());
    }

    private void registerCommonModulesExamples() {
        this.glintExample = new GlintExample();
        this.inventoryExample = new InventoryExample();
        this.saturationExample = new SaturationExample();
    }

    public void enable() {

    }

    public void registerCommands() {

    }

    public void registerModuleExamples() {

    }

    public void registerListeners() {

    }

    public abstract ApolloExampleType getType();

}
