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

public class FlcUtilPlugin extends CordovaPlugin {

    private Context _context;
    private PowerManager.WakeLock _wakeLock;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        _context = cordova.getContext();
    }

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        return super.execute(action, args, callbackContext);
    }

    public Context getContext() {
        return _context;
    }

    public void setContext(Context context) {
        _context = context;
    }

    public void acquireWakeLock(int type) {
        try {
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
            _wakeLock = powerManager.newWakeLock(type, "FlcUtil");
            _wakeLock.acquire(3600000);
            if (_wakeLock != null && _wakeLock.isHeld()) {
                Log.d("FlcUtil", String.format(Locale.ENGLISH, "wake lock acquired. type=%s", type));
            } else {
                Log.e("FlcUtil", String.format(Locale.ENGLISH, "acquire wake lock failed. wakeLock=%s", _wakeLock.toString()));
            }
        } catch (NullPointerException e) {
            Log.e("FlcUtil", String.format(Locale.ENGLISH, "acquire wake lock error. message=%s", e.getMessage()));
        }
    }

}
