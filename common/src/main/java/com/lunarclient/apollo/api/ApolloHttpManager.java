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
package com.lunarclient.apollo.api;

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.async.Future;
import com.lunarclient.apollo.async.future.UncertainFuture;
import io.leangen.geantyref.TypeToken;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Manages Apollo http requests & responses.
 *
 * @since 1.0.0
 */
public final class ApolloHttpManager {

    private static final String BASE_URL = "https://api.lunarclientprod.com/apollo/";

    /**
     * The executor for http requests.
     *
     * @since 1.0.0
     */
    private final ExecutorService requestExecutor;

    /**
     * Constructs the {@link ApolloHttpManager}.
     *
     * @since 1.0.0
     */
    public ApolloHttpManager() {
        this.requestExecutor = Executors.newSingleThreadExecutor();
    }

    /**
     * Asynchronously sends an API request and returns a {@link Future} for the API response.
     *
     * @param <T> The type of ApiResponse associated with this request.
     * @param request The API request to be sent.
     * @return A {@link Future} representing the result of the API request.
     *
     * @since 1.0.0
     */
    public <T extends ApiResponse> Future<T> request(ApiRequest<T> request) {
        UncertainFuture<T> future = new UncertainFuture<>();

        this.requestExecutor.submit(() -> {
            try {
                URL url = new URL(BASE_URL + request.getRoute());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod(request.getType().toString());
                connection.setConnectTimeout(5_000);
                connection.setReadTimeout(5_000);

                try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
                    ApolloManager.GSON.toJson(request, out);
                }

                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    Throwable error = new Throwable("Error code: " + responseCode);
                    future.getFailure().forEach(handler -> handler.handle(error));
                    return;
                }

                ApiResponse response;
                try (InputStreamReader in = new InputStreamReader(connection.getInputStream())) {
                    response = ApolloManager.GSON.fromJson(in, TypeToken.get(this.getClass()).getType());
                }

                if (response == null) {
                    Throwable error = new Throwable("Failed to parse json");
                    future.getFailure().forEach(handler -> handler.handle(error));
                    return;
                }

                future.getSuccess().forEach(handler -> handler.handle((T) response));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return future;
    }

}
