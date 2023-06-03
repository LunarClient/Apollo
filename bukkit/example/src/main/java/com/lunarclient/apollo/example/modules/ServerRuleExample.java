package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;

public class ServerRuleExample {

    private final ServerRuleModule serverRuleModule = Apollo.getModuleManager().getModule(ServerRuleModule.class);

    public void setAntiPortalTraps(boolean value) {
        this.serverRuleModule.getOptions().set(ServerRuleModule.ANTI_PORTAL_TRAPS, value);
    }

    public void setNametagRenderDistanceExample(int value) {
        this.serverRuleModule.getOptions().set(ServerRuleModule.NAMETAG_RENDER_DISTANCE, value);
    }
}