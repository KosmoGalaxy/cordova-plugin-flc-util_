package pl.fulllegitcode.util;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONException;

import java.util.Locale;
import java.util.concurrent.ExecutorService;

public class FlcUtilPlugin extends CordovaPlugin {

    public static final String ACTION_ACQUIRE_WAKE_LOCK = "acquireWakeLock";
    public static final int WAKE_LOCK_TIMEOUT = 60 * 60 * 1000;

    private PowerManager.WakeLock _wakeLock;

    private Context _context;
    public Context getContext() {
        return _context;
    }
    public void setContext(Context context) {
        _context = context;
    }

    private ExecutorService _threadPool;
    public ExecutorService getThreadPool() { return _threadPool; }
    public void setThreadPool(ExecutorService threadPool) { _threadPool = threadPool; }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        _context = cordova.getContext();
    }

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(ACTION_ACQUIRE_WAKE_LOCK)) {
            _acquireWakeLockThread(!args.isNull(0) ? args.getInt(0) : WAKE_LOCK_TIMEOUT, callbackContext);
            return true;
        }
        return false;
    }

    public String acquireWakeLock() {
        return acquireWakeLock(WAKE_LOCK_TIMEOUT);
    }

    public String acquireWakeLock(int timeout) {
        try {
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
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

    private void _acquireWakeLockThread(final int timeout, final CallbackContext callbackContext) {
        _threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String error = acquireWakeLock(timeout);
                if (error != null) {
                    callbackContext.error(error);
                    return;
                }
                callbackContext.success();
            }
        });
    }

}
