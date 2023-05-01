package com.lunarclient.apollo.example.utilities;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.common.Component;

import java.awt.*;
import java.util.List;

public class ComponentExample {

    public static Component componentExample() {
        return Component.builder()
            .content("Hello world!")
            .color(Color.GREEN)
            .decorators(List.of(Component.TextDecorators.BOLD, Component.TextDecorators.UNDERLINED))
            .children(Lists.newArrayList())
            .build();
    }

}
