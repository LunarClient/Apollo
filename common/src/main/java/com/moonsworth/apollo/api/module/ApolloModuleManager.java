package com.moonsworth.apollo.api.module;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.Listener;
import com.moonsworth.apollo.api.events.impl.packet.EventApolloReceivePacket;
import com.moonsworth.apollo.api.events.impl.player.EventApolloPlayerRegister;
import com.moonsworth.apollo.api.module.impl.*;
import com.moonsworth.apollo.api.protocol.ClientModSettings;
import com.moonsworth.apollo.api.protocol.ServerModList;
import lombok.Getter;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ApolloModuleManager implements Listener {

    private final Map<Class<? extends ApolloModule>, List<Consumer<ApolloModule>>> listeners = new HashMap<>();
    @Getter
    private final Map<Class<? extends ApolloModule>, ApolloModule> moduleMap = new HashMap<>();

    private final Map<String, ApolloModule> configureableModules = new HashMap<>();

    public ApolloModuleManager() {
        loadConfigurableModules();
        handle(EventApolloPlayerRegister.class, this::onPlayerLogin);
        handle(EventApolloReceivePacket.class, this::onPacketReceive);
    }

    public void onPacketReceive(EventApolloReceivePacket event) {
        moduleMap.values().stream().filter(ApolloModule::isEnabled).forEach(a -> a.handle(event.getPlayer(), event.getPacket()));
    }

    private void onPlayerLogin(EventApolloPlayerRegister event) {
        final List<String> modules = moduleMap.values().stream()
                .filter(ApolloModule::isEnabled)
                .filter(ApolloModule::notifyPlayers)
                .map(ApolloModule::getName)
                .collect(Collectors.toList());
        event.getPlayer().sendPacket(ServerModList.newBuilder().addAllMods(modules).build());
        this.updateSettings(event.getPlayer());
    }

    private void updateSettings(ApolloPlayer player) {
        final ClientModSettings.Builder settings = ClientModSettings.newBuilder();
        moduleMap.values().stream()
                .filter(ApolloModule::isEnabled)
                .filter(ApolloModule::notifyPlayers)
                .forEach(module -> module.applySettings(settings));
        if (settings.getModsCount() > 0) player.sendPacket(settings.build());
    }

    public void registerConfiguration(ApolloModule module) {
        configureableModules.put(module.getName(), module);
    }

    private void loadConfigurableModules() {
        registerConfiguration(new WaypointModule());
        registerConfiguration(new LegacyCombatModule());
        registerConfiguration(new StaffModModule());
        registerConfiguration(new ModSettingsModule());
        registerConfiguration(new EVNTModule());
        registerConfiguration(new ServerRuleModule());
    }

    /**
     * Registers a listener to be called when a module has been enabled.
     * If the module is currently enabled it will be called immediately.
     *
     * @param clazz          The apollo module
     * @param moduleConsumer The callback
     */
    public <T> void registerModuleListener(Class<T> clazz, Consumer<T> moduleConsumer) {
        getModule(clazz).ifPresentOrElse(moduleConsumer, () -> listeners.computeIfAbsent((Class<? extends ApolloModule>) clazz, aClass -> new ArrayList<>()).add((Consumer<ApolloModule>) moduleConsumer));
    }

    /**
     * Determine if a module is enabled without grabbing an instance of the module
     *
     * @param clazz The module class
     * @return the module enabled value
     */
    public boolean isModuleEnabled(Class<? extends ApolloModule> clazz) {
        return moduleMap.containsKey(clazz);
    }

    /**
     * Gets a module by the class specified.
     * If the module does not exist, the optional will be empty
     *
     * @param clazz The module to grab
     * @return An optional value containing the module.
     */
    public <T> Optional<T> getModule(Class<T> clazz) {
        return (Optional<T>) Optional.ofNullable(moduleMap.get(clazz));
    }

    /**
     * Registers the module for usage.
     * This will also invoke the module listeners after the module has been initialized.
     *
     * @param clazz The instance of the module
     */
    public void register(Class<? extends ApolloModule> clazz) {
        ApolloModule module;
        try {
            module = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return;
        }
        registerModule(module);
    }

    /**
     * Registers and enables a new module.
     *
     * @param module The module to enable
     */
    private void registerModule(ApolloModule module) {
        if (!module.runsOn().contains(Apollo.getPlatform().getKind())) {
            // We do not want to enable a module that isn't meant for this type.
            return;
        }
        module.enable();
        moduleMap.put(module.getClass(), module);
        List<Consumer<ApolloModule>> consumers = listeners.remove(module.getClass());
        if (consumers != null) {
            for (Consumer<ApolloModule> consumer : consumers) {
                consumer.accept(module);
            }
        }
    }

    /**
     * Loads all the configuration from the YML file.
     * <p>
     * Needs to be called after registering all the modules
     */
    public void loadConfiguration(Map<String, Object> config) {
        for (Map.Entry<String, ApolloModule> entry : configureableModules.entrySet()) {
            if (!config.containsKey(entry.getKey() + ".enabled")) {
                continue;
            }
            entry.getValue().load(config);
            if (entry.getValue() != null && Boolean.parseBoolean(config.get(entry.getKey() + ".enabled").toString())) {
                registerModule((ApolloModule) entry.getValue());
            }
        }

    }

}
