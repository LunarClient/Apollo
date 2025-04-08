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
package com.lunarclient.apollo.example.proto.module;

import com.google.protobuf.ListValue;
import com.google.protobuf.Value;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.example.module.impl.AutoTextHotkeyExample;
import com.lunarclient.apollo.example.proto.util.ProtobufPacketUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AutoTextHotkeyProtoExample extends AutoTextHotkeyExample {

    @Override
    public void setBlockedTextInputs(List<String> blockedTextInputs) {
        ListValue listValue = ListValue.newBuilder().addAllValues(
            blockedTextInputs.stream()
                .map(input -> Value.newBuilder().setStringValue(input).build())
                .collect(Collectors.toList())
            ).build();

        Map<String, Value> properties = new HashMap<>();
        properties.put("block-text-inputs", Value.newBuilder().setBoolValue(true).build());
        properties.put("blocked-text-inputs", Value.newBuilder().setListValue(listValue).build());

        ConfigurableSettings settings = ProtobufPacketUtil.createModuleMessage("auto_text_hotkey", properties);
        ProtobufPacketUtil.broadcastPacket(settings);
    }

}
