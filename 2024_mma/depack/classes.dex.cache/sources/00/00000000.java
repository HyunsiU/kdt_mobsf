package com.lzsEsq.dykSgp.jhvqZx;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\classes.dex */
public class pupsPVlBod extends Application {

    /* renamed from: a */
    public String f0a;

    /* renamed from: b */
    public String f1b;

    /* renamed from: c */
    public ZipFile f2c;

    /* renamed from: d */
    public boolean f3d;

    /* renamed from: e */
    public Field f4e;

    /* renamed from: f */
    public Object f5f;

    /* renamed from: g */
    public Field f6g;

    /* renamed from: h */
    public Object[] f7h;

    /* renamed from: i */
    public Method f8i;

    /* renamed from: j */
    public ArrayList<IOException> f9j = new ArrayList<>();

    /* renamed from: k */
    private boolean f10k = false;

    /* renamed from: l */
    private Handler f11l;

    /* renamed from: m */
    public boolean f12m;

    /* renamed from: n */
    public Application f13n;

    /* renamed from: com.lzsEsq.dykSgp.jhvqZx.pupsPVlBod$f */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\classes.dex */
    public interface InterfaceC0005f {
        /* renamed from: a */
        void mo2a(File file);

        /* renamed from: a */
        void mo1a(ZipFile zipFile);
    }

    public pupsPVlBod() {
        new ArrayList();
        this.f11l = new HandlerC0000a();
    }

    /* renamed from: d */
    private void m9d() {
        if (this.f10k) {
            this.f10k = true;
            m17a(1, 7);
            C0004e l1 = new C0004e(1);
            C0004e l2 = new C0004e(2);
            C0004e l3 = new C0004e(3);
            C0004e l4 = new C0004e(4);
            C0004e l5 = new C0004e(5);
            l1.f18b = l2;
            l2.f18b = l3;
            l3.f18b = l4;
            l4.f18b = l5;
        }
    }

    /* renamed from: e */
    private ArrayList<String> m8e() {
        if (this.f10k) {
            ArrayList<String> list = new ArrayList<>();
            ArrayList<String> dir = new ArrayList<>();
            C0004e a1 = new C0004e(2);
            C0004e a2 = new C0004e(4);
            C0004e a3 = new C0004e(3);
            a1.f18b = a2;
            a2.f18b = a3;
            C0004e b1 = new C0004e(5);
            C0004e b2 = new C0004e(6);
            C0004e b3 = new C0004e(4);
            b1.f18b = b2;
            b2.f18b = b3;
            int[] iArr = {3, 2, 2, 3};
            if (dir.size() == 0) {
                return dir;
            }
            m12a(new int[]{1, 1, 2});
            return list;
        }
        return null;
    }

    /* renamed from: a */
    public static int m12a(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }

    /* renamed from: a */
    public String m17a(int n, int k) {
        List<Integer> num = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            num.add(Integer.valueOf(i));
        }
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i2 = 1; i2 < n; i2++) {
            fact[i2] = fact[i2 - 1] * i2;
        }
        int k2 = k - 1;
        StringBuilder sb = new StringBuilder();
        for (int i3 = n - 1; i3 >= 0; i3--) {
            int ind = k2 / fact[i3];
            k2 %= fact[i3];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lzsEsq.dykSgp.jhvqZx.pupsPVlBod$e */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\classes.dex */
    public static class C0004e {

        /* renamed from: a */
        int f17a;

        /* renamed from: b */
        C0004e f18b;

        C0004e(int x) {
            this.f17a = x;
        }

        public String toString() {
            StringBuilder append = new StringBuilder().append(this.f17a).append(" ");
            Object obj = this.f18b;
            if (obj == null) {
                obj = "";
            }
            return append.append(obj).toString();
        }
    }

    /* renamed from: com.lzsEsq.dykSgp.jhvqZx.pupsPVlBod$a */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\classes.dex */
    class HandlerC0000a extends Handler {
        HandlerC0000a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            pupsPVlBod.this.f13n.onCreate();
        }
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        File[] listFiles;
        super.attachBaseContext(base);
        m10c();
        File apkFile = new File(getApplicationInfo().sourceDir);
        File versionDir = getDir(this.f0a + "_" + this.f1b, 0);
        File appDir = new File(versionDir, "app");
        File dexDir = new File(appDir, "dexDir");
        List<File> dexFiles = new ArrayList<>();
        if (!dexDir.exists() || dexDir.list().length == 0) {
            m15a(apkFile, appDir, new C0001b(dexFiles));
        } else {
            for (File file : dexDir.listFiles()) {
                dexFiles.add(file);
            }
        }
        m11b();
        try {
            ZipFile zipFile = this.f2c;
            if (zipFile != null) {
                zipFile.close();
            }
            m14a(dexFiles, versionDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.lzsEsq.dykSgp.jhvqZx.pupsPVlBod$b */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\classes.dex */
    class C0001b implements InterfaceC0005f {

        /* renamed from: a */
        final /* synthetic */ List f15a;

        C0001b(List list) {
            this.f15a = list;
        }

        /* renamed from: b */
        void m7b(File file) {
            try {
                new C0002c();
                byte[] bytes = C0002c.m6a(file);
                ymcBssEDD.decrypt(bytes, file.getAbsolutePath());
                this.f15a.add(file);
            } catch (Exception e) {
            }
        }

        @Override // com.lzsEsq.dykSgp.jhvqZx.pupsPVlBod.InterfaceC0005f
        /* renamed from: a */
        public void mo2a(File file) {
            m7b(file);
        }

        @Override // com.lzsEsq.dykSgp.jhvqZx.pupsPVlBod.InterfaceC0005f
        /* renamed from: a */
        public void mo1a(ZipFile zipFile) {
            pupsPVlBod pupspvlbod = pupsPVlBod.this;
            pupspvlbod.f3d = false;
            pupspvlbod.f2c = zipFile;
        }
    }

    /* renamed from: b */
    public void m11b() {
        try {
            C0002c myTools1 = new C0002c();
            C0002c myTools2 = new C0002c();
            new C0002c();
            Field m5a = myTools1.m5a(getClassLoader(), "pathList");
            this.f4e = m5a;
            Object obj = m5a.get(getClassLoader());
            this.f5f = obj;
            Field m5a2 = myTools2.m5a(obj, "dexElements");
            this.f6g = m5a2;
            this.f7h = (Object[]) m5a2.get(this.f5f);
            this.f8i = C0002c.m4a(this.f5f, "makePathElements", List.class, File.class, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m14a(List<File> dexFiles, File versionDir) {
        Object[] addElements = (Object[]) this.f8i.invoke(this.f5f, dexFiles, versionDir, this.f9j);
        Object[] newElements = (Object[]) Array.newInstance(this.f7h.getClass().getComponentType(), this.f7h.length + addElements.length);
        Object[] objArr = this.f7h;
        System.arraycopy(objArr, 0, newElements, 0, objArr.length);
        System.arraycopy(addElements, 0, newElements, this.f7h.length, addElements.length);
        this.f6g.set(this.f5f, newElements);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        try {
            m18a();
            m9d();
            m8e();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getPackageName() {
        return !TextUtils.isEmpty(this.f0a) ? "" : super.getPackageName();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createPackageContext(String packageName, int flags) {
        if (TextUtils.isEmpty(this.f0a)) {
            return super.createPackageContext(packageName, flags);
        }
        try {
            m18a();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.f13n;
    }

    /* renamed from: a */
    private void m18a() {
        if (!this.f12m && !TextUtils.isEmpty(this.f0a)) {
            Context baseContext = getBaseContext();
            Class<?> delegateClass = Class.forName(this.f0a);
            this.f13n = (Application) delegateClass.newInstance();
            Method attach = Application.class.getDeclaredMethod("attach", Context.class);
            attach.setAccessible(true);
            attach.invoke(this.f13n, baseContext);
            Class<?> contextImplClass = Class.forName("android.app.ContextImpl");
            Field mOuterContextField = contextImplClass.getDeclaredField("mOuterContext");
            mOuterContextField.setAccessible(true);
            mOuterContextField.set(baseContext, this.f13n);
            Field mMainThreadField = contextImplClass.getDeclaredField("mMainThread");
            mMainThreadField.setAccessible(true);
            Object mMainThread = mMainThreadField.get(baseContext);
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field mInitialApplicationField = activityThreadClass.getDeclaredField("mInitialApplication");
            mInitialApplicationField.setAccessible(true);
            mInitialApplicationField.set(mMainThread, this.f13n);
            Field mAllApplicationsField = activityThreadClass.getDeclaredField("mAllApplications");
            mAllApplicationsField.setAccessible(true);
            ArrayList<Application> mAllApplications = (ArrayList) mAllApplicationsField.get(mMainThread);
            mAllApplications.remove(this);
            mAllApplications.add(this.f13n);
            Field mPackageInfoField = contextImplClass.getDeclaredField("mPackageInfo");
            mPackageInfoField.setAccessible(true);
            Object mPackageInfo = mPackageInfoField.get(baseContext);
            Class<?> loadedApkClass = Class.forName("android.app.LoadedApk");
            Field mApplicationField = loadedApkClass.getDeclaredField("mApplication");
            mApplicationField.setAccessible(true);
            mApplicationField.set(mPackageInfo, this.f13n);
            Field mApplicationInfoField = loadedApkClass.getDeclaredField("mApplicationInfo");
            mApplicationInfoField.setAccessible(true);
            ApplicationInfo mApplicationInfo = (ApplicationInfo) mApplicationInfoField.get(mPackageInfo);
            mApplicationInfo.className = this.f0a;
            this.f11l.sendEmptyMessage(1);
            this.f12m = true;
        }
    }

    /* renamed from: com.lzsEsq.dykSgp.jhvqZx.pupsPVlBod$c */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\classes.dex */
    public static class C0002c {
        /* renamed from: a */
        public static byte[] m6a(File file) {
            RandomAccessFile r = new RandomAccessFile(file, "r");
            String lenStr = "" + r.length();
            int result = Integer.parseInt(lenStr);
            byte[] buffer = new byte[result];
            r.readFully(buffer);
            r.close();
            return buffer;
        }

        /* renamed from: a */
        public Field m5a(Object instance, String name) {
            for (Class clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
                try {
                    Field field = clazz.getDeclaredField(name);
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    return field;
                } catch (NoSuchFieldException e) {
                }
            }
            throw new NoSuchFieldException("no field");
        }

        /* renamed from: a */
        public static Method m4a(Object instance, String name, Class... parameterTypes) {
            for (Class clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
                try {
                    Method method = clazz.getDeclaredMethod(name, parameterTypes);
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    return method;
                } catch (NoSuchMethodException e) {
                }
            }
            throw new NoSuchMethodException("error method");
        }
    }

    /* renamed from: c */
    private void m10c() {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
            Bundle metaData = applicationInfo.metaData;
            if (metaData != null) {
                if (metaData.containsKey("app_name")) {
                    this.f0a = metaData.getString("app_name");
                }
                if (metaData.containsKey("app_version")) {
                    this.f1b = metaData.getString("app_version");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m16a(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                m16a(f);
            }
            return;
        }
        file.delete();
    }

    /* renamed from: a */
    public static void m15a(File xZip, File mDir, InterfaceC0005f mLister) {
        try {
            m16a(mDir);
            ZipFile zipFile = new ZipFile(xZip);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                String name = zipEntry.getName();
                if (!name.equals("META-INF/CERT.RSA") && !name.equals("META-INF/CERT.SF") && !name.equals("META-INF/MANIFEST.MF") && !zipEntry.isDirectory()) {
                    File file = new File(mDir, name);
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    String fileName = file.getName();
                    if (fileName.endsWith(".dex") && !TextUtils.equals(fileName, "classes.dex")) {
                        m13a(zipFile, zipEntry, file, mLister);
                    } else {
                        FileOutputStream fos = new FileOutputStream(file);
                        C0003d myTools = new C0003d();
                        InputStream is = (InputStream) myTools.m3a(zipEntry, zipFile);
                        byte[] buffer = new byte[2048];
                        while (true) {
                            int len = is.read(buffer);
                            if (len == -1) {
                                break;
                            }
                            fos.write(buffer, 0, len);
                        }
                        is.close();
                        fos.close();
                    }
                }
            }
            mLister.mo1a(zipFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.lzsEsq.dykSgp.jhvqZx.pupsPVlBod$d */
    /* loaded from: C:\Users\User\Desktop\kdt\project\kdt_mobsf\2024_mma\depack\classes.dex */
    public static class C0003d {
        /* renamed from: a */
        public Object m3a(Object zipEntry, Object zipFile) {
            try {
                Class zipFileClass = zipFile.getClass();
                Method method = zipFileClass.getMethod("getInputStream", ZipEntry.class);
                return method.invoke(zipFile, zipEntry);
            } catch (Exception e) {
                return null;
            }
        }
    }

    /* renamed from: a */
    public static void m13a(ZipFile zipFile, ZipEntry zipEntry, File file, InterfaceC0005f fileLister) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = zipFile.getInputStream(zipEntry);
            byte[] buffer = new byte[2048];
            while (true) {
                int len = is.read(buffer);
                if (len != -1) {
                    fos.write(buffer, 0, len);
                } else {
                    is.close();
                    fos.close();
                    fileLister.mo2a(file);
                    return;
                }
            }
        } catch (Exception e) {
        }
    }
}