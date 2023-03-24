package com.moonsworth.apollo.option.type;

import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.protocol.RenderableStringMessage;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class RenderableStringImpl {

    public RenderableStringImpl() {
        NetworkOptions.register(RenderableString.class, RenderableStringMessage.getDefaultInstance(), new OptionConverter<RenderableString, RenderableStringMessage>() {
            @Override
            public RenderableStringMessage to(final RenderableString object) throws IllegalArgumentException {
                List<RenderableStringMessage.TextDecorators> decorators = object.getDecorators()
                    .stream().map(decorator -> RenderableStringMessage.TextDecorators.valueOf(decorator.name()))
                    .collect(Collectors.toList());

                // Is there a better way to do this?
                List<RenderableStringMessage> children = object.getChildren()
                    .stream().map(this::to)
                    .collect(Collectors.toList());

                return RenderableStringMessage.newBuilder()
                    .setContent(object.getContent())
                    .setColor(object.getColor().getRGB())
                    .addAllDecoration(decorators)
                    .addAllChildren(children)
                    .build();
            }

            @Override
            public RenderableString from(final RenderableStringMessage message) throws IllegalArgumentException {
                List<RenderableString.TextDecorators> decorators = message.getDecorationList()
                    .stream().map(decorator -> RenderableString.TextDecorators.valueOf(decorator.name()))
                    .collect(Collectors.toList());

                List<RenderableString> children = message.getChildrenList()
                    .stream().map(this::from)
                    .collect(Collectors.toList());

                return RenderableString.of(
                    message.getContent(),
                    new Color(message.getColor()),
                    decorators,
                    children
                );
            }
        });
    }
}
