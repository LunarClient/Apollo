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
package com.lunarclient.apollo.util;

import com.google.common.io.ByteArrayDataInput;
import java.nio.charset.StandardCharsets;

/**
 * Represents a util for reading byte buffs.
 *
 * @since 1.1.9
 */
public final class ByteBufUtil {

    /**
     * Reads an int from the given input stream.
     *
     * @param in in the {@link ByteArrayDataInput} to read from
     * @return the read int
     * @throws IllegalArgumentException if the value length is invalid or too large
     * @since 1.1.9
     */
    public static int readVarInt(ByteArrayDataInput in) {
        int i = 0;
        int j = 0;

        while (true) {
            byte b = in.readByte();
            i |= (b & 0x7F) << j++ * 7;

            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }

            if ((b & 0x80) != 128) {
                break;
            }
        }

        return i;
    }

    /**
     * Reads a UTF-8 encoded string from the given input stream.
     *
     * @param in the {@link ByteArrayDataInput} to read from
     * @return the decoded string
     * @throws IllegalArgumentException if the value length is invalid or too large
     * @since 1.1.9
     */
    public static String readString(ByteArrayDataInput in) {
        int length = ByteBufUtil.readVarInt(in);
        byte[] bytes = new byte[length];
        in.readFully(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }

    private ByteBufUtil() {
    }

}
