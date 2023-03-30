package com.moonsworth.apollo.option.type;

import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.protocol.IconSpecificationsMessage;

public class IconSpecificationsImpl {

    public IconSpecificationsImpl() {
        NetworkOptions.register(IconSpecifications.class, IconSpecificationsMessage.getDefaultInstance(), new OptionConverter<IconSpecifications, IconSpecificationsMessage>() {
            @Override
            public IconSpecificationsMessage to(final IconSpecifications object) throws IllegalArgumentException {
                return IconSpecificationsMessage.newBuilder()
                    .setWidth(object.getWidth())
                    .setHeight(object.getHeight())
                    .setMinU(object.getMinU())
                    .setMaxU(object.getMaxU())
                    .setMinV(object.getMinV())
                    .setMaxV(object.getMaxV())
                    .build();
            }

            @Override
            public IconSpecifications from(final IconSpecificationsMessage message) throws IllegalArgumentException {
                return IconSpecifications.of(
                    message.getWidth(),
                    message.getHeight(),
                    message.getMinU(),
                    message.getMaxU(),
                    message.getMinV(),
                    message.getMaxV()
                );
            }
        });
    }
}
