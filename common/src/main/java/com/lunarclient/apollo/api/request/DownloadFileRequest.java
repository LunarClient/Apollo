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
package com.lunarclient.apollo.api.request;

import com.lunarclient.apollo.api.ApiRequest;
import com.lunarclient.apollo.api.ApiRequestType;
import com.lunarclient.apollo.api.ApiServiceType;
import com.lunarclient.apollo.api.response.DownloadFileResponse;
import java.nio.file.Path;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents the apollo download request.
 *
 * @since 1.0.9
 */
@ToString
@Getter
@Builder(toBuilder = true)
public final class DownloadFileRequest implements ApiRequest<DownloadFileResponse> {

    /**
     * The url to download the file from.
     *
     * @since 1.0.9
     */
    private final String url;

    /**
     * The target path to download the file to.
     *
     * @since 1.0.9
     */
    private final Path target;

    @Override
    public ApiServiceType getService() {
        return null;
    }

    @Override
    public ApiRequestType getType() {
        return null;
    }

    @Override
    public String getRoute() {
        return null;
    }

}
