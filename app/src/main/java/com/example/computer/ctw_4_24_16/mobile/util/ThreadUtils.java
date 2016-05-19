package com.example.computer.ctw_4_24_16.mobile.util;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtils {
    private ThreadUtils() {
    }

    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(runnable);
        } else {
            runnable.run();
        }
    }
}
