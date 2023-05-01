package com.lunarclient.apollo.example.utilities;

import com.lunarclient.apollo.common.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.icon.SimpleResourceLocationIcon;
import org.bukkit.Material;

public class IconExample {

    public static ItemStackIcon itemStackIconExample() {
        return ItemStackIcon.builder()
            .itemId(Material.ENDER_PEARL.getId())
            .build();
    }

    public static SimpleResourceLocationIcon simpleResourceLocationIconExample() {
        return SimpleResourceLocationIcon.builder()
            .resourceLocation("icons/server-logo.png")
            .size(1)
            .build();
    }

    public static AdvancedResourceLocationIcon advancedResourceLocationIconExample() {
        return AdvancedResourceLocationIcon.builder()
            .resourceLocation("icons/server-sprite.png")
            .width(512)
            .height(512)
            .minU(1)
            .maxU(1)
            .minV(1)
            .maxV(1)
            .build();
    }

}
