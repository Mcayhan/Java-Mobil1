package com.example.myapplication.security;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class DebugChecker {

    public static boolean isDebugMode(Context context) {
        int flags = context.getApplicationInfo().flags;
        return (flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
}
