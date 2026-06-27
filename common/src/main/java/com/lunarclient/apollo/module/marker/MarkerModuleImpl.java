/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.module.marker;

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.marker.v1.BlockTarget;
import com.lunarclient.apollo.marker.v1.DangerMarker;
import com.lunarclient.apollo.marker.v1.DisplayMarkerMessage;
import com.lunarclient.apollo.marker.v1.EntityTarget;
import com.lunarclient.apollo.marker.v1.InfoMarker;
import com.lunarclient.apollo.marker.v1.InterestMarker;
import com.lunarclient.apollo.marker.v1.ItemTarget;
import com.lunarclient.apollo.marker.v1.NormalMarker;
import com.lunarclient.apollo.marker.v1.PlayerTarget;
import com.lunarclient.apollo.marker.v1.RemoveMarkerMessage;
import com.lunarclient.apollo.marker.v1.ResetMarkersMessage;
import com.lunarclient.apollo.module.marker.display.MarkerDescriptionDisplay;
import com.lunarclient.apollo.module.marker.display.MarkerDisplayCondition;
import com.lunarclient.apollo.module.marker.display.MarkerFlag;
import com.lunarclient.apollo.module.marker.display.MarkerOwnerDisplay;
import com.lunarclient.apollo.module.marker.target.BlockMarkerTarget;
import com.lunarclient.apollo.module.marker.target.EntityMarkerTarget;
import com.lunarclient.apollo.module.marker.target.ItemMarkerTarget;
import com.lunarclient.apollo.module.marker.target.MarkerTarget;
import com.lunarclient.apollo.module.marker.target.PlayerMarkerTarget;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.recipients.Recipients;
import java.awt.Color;
import java.time.Duration;
import lombok.NonNull;

import static com.lunarclient.apollo.util.Ranges.checkRange;

/**
 * Provides the marker module.
 *
 * @since 1.2.8
 */
public final class MarkerModuleImpl extends MarkerModule {

    @Override
    public void displayMarker(@NonNull Recipients recipients, @NonNull Marker marker) {
        DisplayMarkerMessage message = this.toProtobuf(marker);
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeMarker(@NonNull Recipients recipients, @NonNull String markerId) {
        RemoveMarkerMessage message = RemoveMarkerMessage.newBuilder()
            .setId(markerId)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeMarker(@NonNull Recipients recipients, @NonNull Marker marker) {
        this.removeMarker(recipients, marker.getId());
    }

    @Override
    public void resetMarkers(@NonNull Recipients recipients) {
        ResetMarkersMessage message = ResetMarkersMessage.getDefaultInstance();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    private DisplayMarkerMessage toProtobuf(Marker marker) {
        DisplayMarkerMessage.Builder builder = DisplayMarkerMessage.newBuilder()
            .setId(marker.getId())
            .setLocation(NetworkTypes.toProtobuf(marker.getLocation()))
            .setOwnerId(NetworkTypes.toProtobuf(marker.getOwnerId()))
            .setOwnerName(marker.getOwnerName())
            .setInGameNotification(marker.isInGameNotification())
            .setChatNotify(marker.isChatNotify())
            .setMiddleClickRemove(marker.isMiddleClickRemove());

        this.applyFlag(builder, marker.getFlag(), marker.getColor());
        builder.setTarget(this.toProtobuf(marker.getTarget()));

        Duration duration = marker.getDuration();
        if (duration != null) {
            builder.setDuration(NetworkTypes.toProtobuf(duration));
        }

        MarkerStyle style = marker.getStyle();
        if (style != null) {
            builder.setStyle(this.toProtobuf(style));
        }

        return builder.build();
    }

    private void applyFlag(DisplayMarkerMessage.Builder builder, MarkerFlag flag, Color color) {
        com.lunarclient.apollo.common.v1.Color protoColor = color == null ? null : NetworkTypes.toProtobuf(color);
        com.lunarclient.apollo.marker.v1.MarkerFlag.Builder flagBuilder = com.lunarclient.apollo.marker.v1.MarkerFlag.newBuilder();

        switch (flag) {
            case NORMAL: {
                NormalMarker.Builder normal = NormalMarker.newBuilder();
                if (protoColor != null) {
                    normal.setColor(protoColor);
                }
                flagBuilder.setNormal(normal.build());
                break;
            }

            case DANGER: {
                DangerMarker.Builder danger = DangerMarker.newBuilder();
                if (protoColor != null) {
                    danger.setColor(protoColor);
                }
                flagBuilder.setDanger(danger.build());
                break;
            }

            case INFO: {
                InfoMarker.Builder info = InfoMarker.newBuilder();
                if (protoColor != null) {
                    info.setColor(protoColor);
                }
                flagBuilder.setInfo(info.build());
                break;
            }

            case INTEREST: {
                InterestMarker.Builder interest = InterestMarker.newBuilder();
                if (protoColor != null) {
                    interest.setColor(protoColor);
                }
                flagBuilder.setInterest(interest.build());
                break;
            }

            default: {
                throw new IllegalArgumentException("Unknown marker flag: " + flag);
            }
        }

        builder.setFlag(flagBuilder.build());
    }

    private com.lunarclient.apollo.marker.v1.MarkerTarget toProtobuf(MarkerTarget target) {
        com.lunarclient.apollo.marker.v1.MarkerTarget.Builder builder = com.lunarclient.apollo.marker.v1.MarkerTarget.newBuilder();

        if (target instanceof ItemMarkerTarget) {
            builder.setItem(ItemTarget.newBuilder()
                .setItemStack(NetworkTypes.toProtobuf(((ItemMarkerTarget) target).getItemStack()))
                .build());
        } else if (target instanceof BlockMarkerTarget) {
            builder.setBlock(BlockTarget.newBuilder()
                .setItemStack(NetworkTypes.toProtobuf(((BlockMarkerTarget) target).getItemStack()))
                .build());
        } else if (target instanceof EntityMarkerTarget) {
            builder.setEntity(EntityTarget.newBuilder()
                .setEntityType(((EntityMarkerTarget) target).getEntityType())
                .build());
        } else if (target instanceof PlayerMarkerTarget) {
            PlayerMarkerTarget player = (PlayerMarkerTarget) target;
            builder.setPlayer(PlayerTarget.newBuilder()
                .setUuid(NetworkTypes.toProtobuf(player.getPlayerId()))
                .setName(player.getPlayerName())
                .build());
        } else {
            throw new IllegalArgumentException("Unknown marker target type: " + target.getClass().getName());
        }

        return builder.build();
    }

    private com.lunarclient.apollo.marker.v1.MarkerStyle toProtobuf(MarkerStyle style) {
        return com.lunarclient.apollo.marker.v1.MarkerStyle.newBuilder()
            .setScale(checkRange(style.getScale(), 0.5F, 2.0F, "MarkerStyle#scale"))
            .setAnimateMarkerOnHover(style.isAnimateMarkerOnHover())
            .setCompactMode(style.isCompactMode())
            .setTextShadow(style.isTextShadow())
            .setOwnerSuffix(style.getOwnerSuffix())
            .setOwnerDisplay(this.toProtobuf(style.getOwnerDisplay()))
            .setShowOwner(this.toProtobuf(style.getShowOwner()))
            .setShowCoordinates(this.toProtobuf(style.getShowCoordinates()))
            .setShowDistance(this.toProtobuf(style.getShowDistance()))
            .setShowDescription(this.toProtobuf(style.getShowDescription()))
            .setDescriptionDisplay(this.toProtobuf(style.getDescriptionDisplay()))
            .build();
    }

    private com.lunarclient.apollo.marker.v1.MarkerDisplayCondition toProtobuf(MarkerDisplayCondition condition) {
        return com.lunarclient.apollo.marker.v1.MarkerDisplayCondition.forNumber(condition.ordinal() + 1);
    }

    private com.lunarclient.apollo.marker.v1.MarkerOwnerDisplay toProtobuf(MarkerOwnerDisplay display) {
        return com.lunarclient.apollo.marker.v1.MarkerOwnerDisplay.forNumber(display.ordinal() + 1);
    }

    private com.lunarclient.apollo.marker.v1.MarkerDescriptionDisplay toProtobuf(MarkerDescriptionDisplay display) {
        return com.lunarclient.apollo.marker.v1.MarkerDescriptionDisplay.forNumber(display.ordinal() + 1);
    }

}
