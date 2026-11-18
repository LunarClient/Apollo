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
package com.lunarclient.apollo.common.pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Represents a pattern that the client matches against plain text, for
 * example a chat message without formatting codes.
 *
 * <p>Patterns ignore the case of the letters, unless
 * {@link #withCaseSensitive(boolean)} makes them case sensitive.</p>
 *
 * @since 1.3.1
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TextPattern {

    /**
     * Creates a pattern that matches when the Java regular expression finds
     * a match anywhere in the text.
     *
     * <p>Use a leading "^" to only match the start of the text.</p>
     *
     * @param regex the regular expression
     * @return the text pattern
     * @since 1.3.1
     */
    public static TextPattern regex(@NonNull String regex) {
        return new TextPattern(TextPatternType.REGEX, regex, false);
    }

    /**
     * Creates a pattern that matches when the text contains the value.
     *
     * @param value the value
     * @return the text pattern
     * @since 1.3.1
     */
    public static TextPattern contains(@NonNull String value) {
        return new TextPattern(TextPatternType.CONTAINS, value, false);
    }

    /**
     * Creates a pattern that matches when the text starts with the value.
     *
     * @param value the value
     * @return the text pattern
     * @since 1.3.1
     */
    public static TextPattern startsWith(@NonNull String value) {
        return new TextPattern(TextPatternType.STARTS_WITH, value, false);
    }

    /**
     * Returns the {@link TextPatternType} of this pattern.
     *
     * @return the pattern type
     * @since 1.3.1
     */
    private final TextPatternType type;

    /**
     * Returns the {@link String} value of this pattern, the regular
     * expression for {@link TextPatternType#REGEX}.
     *
     * @return the pattern value
     * @since 1.3.1
     */
    private final String value;

    /**
     * Returns whether this pattern matches the case of the letters.
     *
     * @return true when the pattern is case sensitive
     * @since 1.3.1
     */
    private final boolean caseSensitive;

    /**
     * Returns a copy of this pattern with the given case sensitivity.
     *
     * @param caseSensitive whether the copy matches the case of the letters
     * @return the copy of the text pattern
     * @since 1.3.1
     */
    public TextPattern withCaseSensitive(boolean caseSensitive) {
        return new TextPattern(this.type, this.value, caseSensitive);
    }

}
