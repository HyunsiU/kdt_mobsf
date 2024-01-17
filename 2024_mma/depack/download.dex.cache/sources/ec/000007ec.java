package com.ldjSxw.heBbQd;

import android.content.Context;
import android.support.multidex.C0118a;
import android.support.multidex.MultiDexApplication;

/* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
public class MainApplication extends MultiDexApplication {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.multidex.MultiDexApplication, android.content.ContextWrapper
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            C0118a.m1517j(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}