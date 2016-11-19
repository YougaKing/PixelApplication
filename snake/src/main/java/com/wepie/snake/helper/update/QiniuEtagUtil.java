package com.wepie.snake.helper.update;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

/**
 * Created by YougaKing on 2016/11/15.
 */

public class QiniuEtagUtil {
    public static final int BLOCK_SIZE = 4194304;
    private static final char[] hexDigits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    private static final String TAG = "QiniuEtagUtil";

    public static String data(byte[] paramArrayOfByte) {
        return data(paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    public static String data(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        try {
            return stream(new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2), paramInt2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String digest(String paramString) {
        try {
            byte[] bytes = paramString.getBytes();
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(bytes);
            bytes = digest.digest();
            int k = bytes.length;
            char[] chars = new char[k * 2];
            int i = 0;
            int j = 0;
            while (i < k) {
                int m = bytes[i];
                int n = j + 1;
                chars[j] = hexDigits[(m >>> 4 & 0xF)];
                j = n + 1;
                chars[n] = hexDigits[(m & 0xF)];
                i += 1;
            }
            paramString = new String(chars);
            return paramString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String digest(byte[] paramArrayOfByte) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(paramArrayOfByte);
            paramArrayOfByte = digest.digest();
            int k = paramArrayOfByte.length;
            char[] chars = new char[k * 2];
            int i = 0;
            int j = 0;
            while (i < k) {
                int m = paramArrayOfByte[i];
                int n = j + 1;
                chars[j] = hexDigits[(m >>> 4 & 0xF)];
                j = n + 1;
                chars[n] = hexDigits[(m & 0xF)];
                i += 1;
            }
            return new String(chars);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String file(File paramFile)
            throws IOException {
        return stream(new FileInputStream(paramFile), paramFile.length());
    }

    public static String file(String paramString)
            throws IOException {
        return file(new File(paramString));
    }

    public static String getSignString(Object paramObject) {
        if (!(paramObject instanceof Context)) {
            return "";
        }
        Context context = (Context) paramObject;
        PackageManager packageManager = context.getPackageManager();
        String packageName = "com.wepie.snake";
        Iterator<PackageInfo> infoIterator = packageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES)
                .iterator();
        while (infoIterator.hasNext()) {
            PackageInfo packageInfo = infoIterator.next();
            Log.i(TAG, "packageName:" + packageInfo.packageName);
            if (packageInfo.packageName.equals(packageName)) {
                try {
                    packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
                    return digest(packageInfo.signatures[0].toByteArray());
//                    return digest("67:8A:93:0B:98:29:B5:4A:44:F9:2A:84:09:16:F7:D1");
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static byte[] oneBlock(byte[] paramArrayOfByte, InputStream paramInputStream, int paramInt)
            throws IOException {

        try {
            MessageDigest digest = MessageDigest.getInstance("sha-1");
            for (byte b : paramArrayOfByte) {

                int j = paramArrayOfByte.length;
                if (paramInt == 0) {
                    break;
                }
                int i;
                if (j > paramInt) {
                    i = paramInt;
                    paramInputStream.read(paramArrayOfByte, 0, i);
                    digest.update(paramArrayOfByte, 0, i);
                    paramInt -= i;
                } else {
                    i = j;
                }
            }
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String resultEncode(byte[][] paramArrayOfByte) {
        int i = 22;
        byte[] bytes = paramArrayOfByte[0];
        int k = bytes.length;
        byte[] arrayOfByte = new byte[k + 1];
        if (paramArrayOfByte.length != 1) {
            i = -106;
            try {
                MessageDigest digest = MessageDigest.getInstance("sha-1");
                int m = paramArrayOfByte.length;
                int j = 0;
                while (j < m) {
                    digest.update(paramArrayOfByte[j]);
                    j += 1;
                }
                arrayOfByte = digest.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        arrayOfByte[0] = (byte) i;
        System.arraycopy(arrayOfByte, 0, arrayOfByte, 1, k);
        return QiniuUrlSafeBase64.encodeToString(arrayOfByte);
    }

    public static String stream(InputStream paramInputStream, long paramLong)
            throws IOException {
        if (paramLong == 0L) {
            return "Fto5o-5ea0sNMlW_75VgGJCv2AcJ";
        }
        byte[] arrayOfByte = new byte[65536];
        byte[][] arrayOfByte1 = new byte[(int) (4194304L + paramLong - 1L) / 4194304][];
        if (arrayOfByte1.length > 0) {
            for (int i = 0; i < arrayOfByte1.length; i++) {
                long l = paramLong - 4194304L * i;
                if (l > 4194304L) {
                    l = 4194304L;
                }
                arrayOfByte1[i] = oneBlock(arrayOfByte, paramInputStream, (int) l);
            }
        }
        return resultEncode(arrayOfByte1);
    }
}
