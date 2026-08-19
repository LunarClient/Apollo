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
package com.lunarclient.apollo.example.util;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;

public final class ServerStatsUtil {

    private static final long MB_BYTES = 1024 * 1024;
    private static final OperatingSystemMXBean MX_BEAN = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    private static final Method GET_TPS_METHOD = findGetTpsMethod();

    public static double[] getTps() {
        if (GET_TPS_METHOD == null) {
            return null;
        }

        try {
            return (double[]) GET_TPS_METHOD.invoke(Bukkit.getServer());
        } catch (Throwable throwable) {
            return null;
        }
    }

    public static double getSystemLoadAverage() {
        return MX_BEAN.getSystemLoadAverage();
    }

    public static int getAvailableProcessors() {
        return MX_BEAN.getAvailableProcessors();
    }

    public static long getUsedRamMb() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / MB_BYTES;
    }

    public static long getMaxRamMb() {
        return Runtime.getRuntime().maxMemory() / MB_BYTES;
    }

    private static Method findGetTpsMethod() {
        try {
            return Bukkit.getServer() != null ? Bukkit.getServer().getClass().getMethod("getTPS") : null;
        } catch (Throwable throwable) {
            return null;
        }
    }

    private ServerStatsUtil() {
    }

}
