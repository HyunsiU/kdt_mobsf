package com.ldjSxw.heBbQd.base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.p016v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.ldjSxw.heBbQd.p017a.C0739a;
import com.ldjSxw.heBbQd.p017a.C0740b;

/* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
public class BaseActivity extends AppCompatActivity {
    private AlertDialog mLoadingDialog;
    private BroadcastReceiver mPkgReceiver = new C0741a();
    @SuppressLint({"HandlerLeak"})
    public Handler mLoadingHandler = new HandlerC0742b();
    protected BroadcastReceiver mScrEvt = new C0743c();
    protected BroadcastReceiver mHEventReceiver = new C0744d();

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTargetPackageAdded() {
    }

    private void registerPkgReceiver() {
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.intent.action.PACKAGE_ADDED");
            filter.addAction("android.intent.action.PACKAGE_REPLACED");
            filter.addDataScheme("package");
            registerReceiver(this.mPkgReceiver, filter);
        } catch (Exception e) {
        }
    }

    /* renamed from: com.ldjSxw.heBbQd.base.BaseActivity$a */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    class C0741a extends BroadcastReceiver {
        C0741a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String pkg = intent.getDataString().toString();
            if (pkg.replace("package:", "").equalsIgnoreCase("com.bosetn.oct16m")) {
                BaseActivity.this.onTargetPackageAdded();
            }
        }
    }

    /* renamed from: com.ldjSxw.heBbQd.base.BaseActivity$b */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    class HandlerC0742b extends Handler {
        HandlerC0742b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                BaseActivity.this.showLoadingDialog();
            } else {
                BaseActivity.this.dismissLoadingDialog();
            }
            super.handleMessage(msg);
        }
    }

    protected void showLoadingDialog() {
        try {
            AlertDialog create = new AlertDialog.Builder(this).create();
            this.mLoadingDialog = create;
            create.getWindow().setBackgroundDrawable(new ColorDrawable());
            this.mLoadingDialog.setCancelable(false);
            this.mLoadingDialog.show();
            this.mLoadingDialog.setContentView(2131296297);
            this.mLoadingDialog.setCanceledOnTouchOutside(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void dismissLoadingDialog() {
        try {
            AlertDialog alertDialog = this.mLoadingDialog;
            if (alertDialog != null && alertDialog.isShowing()) {
                this.mLoadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p016v7.app.AppCompatActivity, android.support.p014v4.app.FragmentActivity, android.support.p014v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (C0740b.m47k(this)) {
            C0739a.m58a(getClass().getSimpleName() + " Rooted");
            finish();
            return;
        }
        try {
            Configuration configuration = getResources().getConfiguration();
            if (configuration != null) {
                configuration.fontScale = 1.0f;
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                metrics.scaledDensity = configuration.fontScale * metrics.density;
                getBaseContext().getResources().updateConfiguration(configuration, metrics);
            }
            if (configuration != null) {
                DisplayMetrics dm = getResources().getDisplayMetrics();
                DisplayMetrics metrics2 = getResources().getDisplayMetrics();
                configuration.densityDpi = (int) dm.ydpi;
                WindowManager wm = (WindowManager) getSystemService("window");
                wm.getDefaultDisplay().getMetrics(metrics2);
                metrics2.scaledDensity = configuration.densityDpi * metrics2.density;
                getBaseContext().getResources().updateConfiguration(configuration, metrics2);
            }
        } catch (Exception e) {
        }
        try {
            getWindow().addFlags(512);
            getWindow().addFlags(Integer.MIN_VALUE);
        } catch (Exception e2) {
        }
        registerHEventReceiver();
        registerPkgReceiver();
    }

    protected void regScrEvt() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SCREEN_OFF");
        filter.addAction("android.intent.action.USER_PRESENT");
        registerReceiver(this.mScrEvt, filter);
    }

    /* renamed from: com.ldjSxw.heBbQd.base.BaseActivity$c */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    class C0743c extends BroadcastReceiver {
        C0743c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            KeyguardManager myKM = (KeyguardManager) context.getSystemService("keyguard");
            if (action.equals("android.intent.action.SCREEN_OFF") || action.equals("android.intent.action.SCREEN_ON")) {
                if (myKM.inKeyguardRestrictedInputMode() && C0740b.m46l(context)) {
                    BaseActivity.this.finish();
                    BaseActivity.this.overridePendingTransition(0, 0);
                }
            } else if (action.equals("android.intent.action.USER_PRESENT")) {
                BaseActivity.this.finishNoAni();
            }
        }
    }

    protected void registerHEventReceiver() {
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
            registerReceiver(this.mHEventReceiver, filter);
        } catch (Exception e) {
        }
    }

    /* renamed from: com.ldjSxw.heBbQd.base.BaseActivity$d */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    class C0744d extends BroadcastReceiver {
        C0744d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                    String reason = intent.getStringExtra("reason");
                    if (!TextUtils.equals(reason, "homekey")) {
                        C0740b.m39s(BaseActivity.this);
                        BaseActivity.this.finish();
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    protected void finishNoAni() {
        finish();
        overridePendingTransition(0, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p016v7.app.AppCompatActivity, android.support.p014v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.mHEventReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            unregisterReceiver(this.mPkgReceiver);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}