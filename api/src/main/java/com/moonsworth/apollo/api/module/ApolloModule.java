package com.moonsworth.apollo.api.module;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.Event;
import com.moonsworth.apollo.api.events.EventBus;
import com.moonsworth.apollo.api.module.receive.ApolloPacketReceiver;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.OptionProperty;
import com.moonsworth.apollo.api.protocol.ClientModSettings;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Getter
public abstract class ApolloModule extends ApolloPacketReceiver {

    private final List<ApolloOption<?>> options = options();

    private final String name;
    private boolean enabled;

    public ApolloModule(String name) {
        this.enabled = false;
        this.name = name;

        Runnable consumer = () -> Apollo.getApolloPlayerManager().getApolloPlayers().forEach(this::updateSettings);
        options.forEach(option -> {
            option.onUpdate(o -> {
                if (Apollo.getApolloPlayerManager() != null) {
                    consumer.run();
                }
            });
        });
    }

    /**
     * Enables this ApolloModule.
     * NOTE: Modules are enabled as they're created. Some modules will load before others!
     * <p>
     * Called then the module is set to be enabled.
     * This happens before enable, but after init in ApolloConsumer.
     */
    public void enable() {
        this.enabled = true;
        registerAllEvents();
        this.onEnable();
    }

    /**
     * Called when the module is enabled.
     * Not required to have logic, but avaible.
     */
    public void onEnable() {

    }

    public abstract List<ApolloOption<?>> options();

    /**
     * Determines if we notify the players in a bulk packet when they login.
     * This is very useful for things like LegacyCombatModule where it directly determines gameplay
     * factors from the moment the user logs in, but less useful for things like NotificationModule
     * where the user only needs to know it's enabled once it takes an action.
     *
     * @return The result of if this should contain a combat module
     */
    public abstract boolean notifyPlayers();

    /**
     * Determines where the specified module can run
     *
     * @return Where the module can run
     */
    public abstract List<ApolloPlatform.Kind> runsOn();

    /**
     * Applies the module configuration settings that need to be sent to the
     * client, into the {@link ClientModSettings} packet.
     *
     * @param settings the client mod settings
     */
    public boolean applySettings(ClientModSettings.Builder settings) {
        if (this.notifyPlayers() && this.isEnabled()) {
            ClientModSettings.ModSettings.Builder modSettings = ClientModSettings.ModSettings.newBuilder();
            for (ApolloOption<?> option : getOptions()) {
                if (option.get().equals(option.getDefault())) continue;
                if (option.getProperty() != OptionProperty.CLIENT) continue;
                modSettings.putSettings(option.getId(), option.get().toString());
            }
            if (modSettings.getSettingsCount() > 0) {
                settings.putMods(this.getName(), modSettings.build());
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the module configuration settings for the specified
     * {@link ApolloPlayer}.
     *
     * @param player the player to update the settings for
     */
    public void updateSettings(ApolloPlayer player) {
        final ClientModSettings.Builder settings = ClientModSettings.newBuilder();
        if (this.applySettings(settings)) player.sendPacket(settings.build());
    }

    /**
     * All the events this mod needs.
     * Events get registered or unregistered when the mod gets enabled or disabled.
     */
    private final Map<Class<Event>, Consumer<Event>> events = new HashMap<>();

    public void registerAllEvents() {
        for (Map.Entry<Class<Event>, Consumer<Event>> entry : events.entrySet()) {
            Class<Event> k = entry.getKey();
            Consumer<Event> v = entry.getValue();
            EventBus.getBus().register(k, v);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends Event> void handle(Class<T> clazz, Consumer<T> consumer) {
        events.put((Class<Event>) clazz, (Consumer<Event>) consumer);
    }

    /**
     * Called at the start of loading configuration.
     * Used to load extra configuration that isn't quite an option
     *
     * @param configuration The config map
     */
    protected void loadConfiguration(Map<String, Object> configuration) {
    }

    /**
     * Loads the items from a config file.
     *
     * @param configuration The data from the config section in YAML
     */
    public void load(Map<String, Object> configuration) {
        loadConfiguration(configuration);
        for (ApolloOption<?> option : getOptions()) {
            if (!configuration.containsKey(getName() + "." + option.getId())) {
                continue;
            }
            try {
                option.load(configuration.get(getName() + "." + option.getId()).toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

