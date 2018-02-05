package pl.fulllegitcode.util;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

import java.util.Locale;

public class FlcUtil {

    public static final int WAKE_LOCK_TIMEOUT = 60 * 60 * 1000;

    private static PowerManager.WakeLock _wakeLock;

    public static String acquireWakeLock(Context context) {
        return acquireWakeLock(WAKE_LOCK_TIMEOUT, context);
    }

    public static String acquireWakeLock(int timeout, Context context) {
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            _wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "FlcUtil");
            _wakeLock.acquire(timeout);
            if (_wakeLock != null && _wakeLock.isHeld()) {
                Log.d("FlcUtil", String.format(Locale.ENGLISH, "wake lock acquired. timeout=%d", timeout));
                return null;
            } else {
                Log.e("FlcUtil", String.format(Locale.ENGLISH, "acquire wake lock failed. wakeLock=%s", _wakeLock.toString()));
                return "wake lock is null or not held ;)";
            }
        } catch (NullPointerException e) {
            Log.e("FlcUtil", String.format(Locale.ENGLISH, "acquire wake lock error. message=%s", e.getMessage()));
            return e.getMessage();
        }
    }

    public static String releaseWakeLock() {
        if (_wakeLock == null) {
            return "wake lock does not exist";
        }
        if (!_wakeLock.isHeld()) {
            return "wake lock is not held";
        }
        _wakeLock.release();
        return null;
    }

}
