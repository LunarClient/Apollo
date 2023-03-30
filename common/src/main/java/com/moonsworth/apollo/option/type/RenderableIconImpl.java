package com.moonsworth.apollo.option.type;

import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.protocol.IconSpecificationsMessage;
import com.moonsworth.apollo.protocol.RenderableIconMessage;

public class RenderableIconImpl {

    public RenderableIconImpl() {
        NetworkOptions.register(RenderableIcon.class, RenderableIconMessage.getDefaultInstance(), new OptionConverter<RenderableIcon, RenderableIconMessage>() {
            @Override
            public RenderableIconMessage to(final RenderableIcon object) throws IllegalArgumentException {
                final OptionConverter<IconSpecifications, IconSpecificationsMessage> specificationsConverter = NetworkOptions.get(IconSpecifications.class);

                return RenderableIconMessage.newBuilder()
                    .setResourceLocation(object.getResourceLocation())
                    .setSize(object.getSize())
                    .setSpecification(specificationsConverter.to(object.getSpecifications()))
                    .build();
            }

            @Override
            public RenderableIcon from(final RenderableIconMessage message) throws IllegalArgumentException {
                final OptionConverter<IconSpecifications, IconSpecificationsMessage> specificationsConverter = NetworkOptions.get(IconSpecifications.class);

                return RenderableIcon.of(
                    message.getResourceLocation(),
                    message.getSize(),
                    specificationsConverter.from(message.getSpecification())
                );
            }
        });
    }
}
