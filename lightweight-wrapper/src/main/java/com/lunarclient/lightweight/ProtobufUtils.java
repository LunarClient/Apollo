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
package com.lunarclient.lightweight;

import com.google.common.reflect.ClassPath;
import com.google.protobuf.Any;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.TypeRegistry;
import com.google.protobuf.util.JsonFormat;
import com.lunarclient.apollo.waypoint.v1.DisplayWaypointMessage;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Utility class for converting between protocol
 * buffer messages and their JSON representations.
 *
 * @since 1.1.5
 */
public final class ProtobufUtils {

    private static final Pattern PROTO_MESSAGE_PATTERN = Pattern.compile("^com\\.lunarclient\\.apollo.*Message$");

    private static final JsonFormat.Printer PRINTER;
    private static final JsonFormat.Parser PARSER;

    static {
        TypeRegistry.Builder builder = TypeRegistry.newBuilder();
        try {
            ClassPath.from(DisplayWaypointMessage.class.getClassLoader())
                .getAllClasses().stream()
                .map(ClassPath.ClassInfo::getName)
                .filter(name -> PROTO_MESSAGE_PATTERN.matcher(name).matches())
                .forEach(className -> {
                    try {
                        Class<?> clazz = Class.forName(className);
                        Descriptors.Descriptor descriptor = (Descriptors.Descriptor) clazz
                            .getMethod("getDescriptor")
                            .invoke(null);

                        builder.add(descriptor);
                    } catch (Exception ignored) { }
                });
        } catch (IOException ignored) { }

        TypeRegistry registry = builder.build();

        PRINTER = JsonFormat.printer().usingTypeRegistry(registry);
        PARSER = JsonFormat.parser().usingTypeRegistry(registry);
    }

    /**
     * Converts a JSON string representation of a protocol buffer message to an {@link Any} type.
     *
     * @param data the JSON string to convert
     * @return an Any type containing the protocol buffer message
     * @throws RuntimeException if the input string cannot be parsed into a protocol buffer message
     * @since 1.1.5
     */
    public static Any stringToAny(String data) {
        Any.Builder toMerge = Any.newBuilder();

        try {
            PARSER.merge(data, toMerge);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

        return toMerge.build();
    }

    /**
     * Converts an {@link Any} type to its JSON string representation.
     *
     * @param any the Any type to convert
     * @return a JSON string representation of the protocol buffer message
     * @throws RuntimeException if the Any type cannot be converted to a JSON string
     * @since 1.1.5
     */
    public static String anyToString(Any any) {
        try {
            return PRINTER.print(any);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a {@link GeneratedMessageV3} message to its JSON string representation.
     *
     * @param message the protocol buffer message to convert
     * @return a JSON string representation of the protocol buffer message
     * @throws RuntimeException if the message cannot be converted to a JSON string
     * @since 1.1.5
     */
    public static String messageToString(GeneratedMessageV3 message) {
        return anyToString(Any.pack(message));
    }

    private ProtobufUtils() {
    }

}
