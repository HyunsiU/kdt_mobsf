package com.ldjSxw.heBbQd;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.ldjSxw.heBbQd.base.BaseActivity;
import com.ldjSxw.heBbQd.p017a.C0740b;
import java.util.List;
import org.apache.commons.p018io.IOUtils;

/* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
public class ScanActivity extends BaseActivity {
    int nowPosition = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ldjSxw.heBbQd.base.BaseActivity, android.support.p016v7.app.AppCompatActivity, android.support.p014v4.app.FragmentActivity, android.support.p014v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(2131296291);
        C0740b.m35w(this, "K_LAST_SCAN_CHECK_DATE", C0740b.m55c());
        TextView textView1 = (TextView) findViewById(2131165351);
        PackageManager pm = getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        Handler handlersetTextView = new HandlerC0738a(textView1, packages);
        handlersetTextView.sendEmptyMessageDelayed(0, 30L);
    }

    /* renamed from: com.ldjSxw.heBbQd.ScanActivity$a */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    class HandlerC0738a extends Handler {

        /* renamed from: a */
        final /* synthetic */ TextView f1276a;

        /* renamed from: b */
        final /* synthetic */ List f1277b;

        HandlerC0738a(TextView textView, List list) {
            this.f1276a = textView;
            this.f1277b = list;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            TextView textView = this.f1276a;
            textView.setText(ScanActivity.this.getResources().getString(2131427393) + " " + this.f1277b.size() + ScanActivity.this.getResources().getString(2131427396) + " " + ScanActivity.this.nowPosition + ScanActivity.this.getResources().getString(2131427389) + "\n\n" + ((PackageInfo) this.f1277b.get(ScanActivity.this.nowPosition)).packageName + IOUtils.LINE_SEPARATOR_UNIX + ScanActivity.this.getResources().getString(2131427390));
            ScanActivity scanActivity = ScanActivity.this;
            int i = scanActivity.nowPosition + 1;
            scanActivity.nowPosition = i;
            if (i >= this.f1277b.size()) {
                Intent intent = new Intent(ScanActivity.this, ResultActivity.class);
                ScanActivity.this.startActivity(intent);
                ScanActivity.this.finish();
                return;
            }
            sendEmptyMessageDelayed(0, 30L);
        }
    }
}