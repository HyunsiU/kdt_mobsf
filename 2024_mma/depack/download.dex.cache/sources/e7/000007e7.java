package com.ldjSxw.heBbQd;

import android.content.Intent;
import android.os.Bundle;
import com.ldjSxw.heBbQd.base.BaseActivity;
import com.ldjSxw.heBbQd.p017a.C0740b;

/* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
public class IntroActivity extends BaseActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ldjSxw.heBbQd.base.BaseActivity, android.support.p016v7.app.AppCompatActivity, android.support.p014v4.app.FragmentActivity, android.support.p014v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        Intent intent;
        super.onCreate(savedInstanceState);
        setContentView(2131296286);
        if (!isTaskRoot() && (intent = getIntent()) != null && intent.hasCategory("android.intent.category.LAUNCHER") && "android.intent.action.MAIN".equals(intent.getAction())) {
            finish();
            return;
        }
        String firstOpenDate = C0740b.m56b(this, "K_FIRST_OPEN_DATE");
        if (firstOpenDate.equalsIgnoreCase("")) {
            C0740b.m35w(this, "K_FIRST_OPEN_DATE", C0740b.m54d());
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        new Thread(new RunnableC0731a()).start();
    }

    /* renamed from: com.ldjSxw.heBbQd.IntroActivity$a */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\download.dex */
    class RunnableC0731a implements Runnable {
        RunnableC0731a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(0L);
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                IntroActivity.this.startActivity(intent);
                IntroActivity.this.finish();
            } catch (Exception e) {
            }
        }
    }
}