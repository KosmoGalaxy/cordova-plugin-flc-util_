package pl.fulllegitcode.util;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

public class FlcUtilPlugin extends CordovaPlugin {

    public static final String ACTION_ACQUIRE_WAKE_LOCK = "acquireWakeLock";
    public static final String ACTION_RELEASE_WAKE_LOCK = "releaseWakeLock";

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(ACTION_ACQUIRE_WAKE_LOCK)) {
            _acquireWakeLockThread(!args.isNull(0) ? args.getInt(0) : FlcUtil.WAKE_LOCK_TIMEOUT, callbackContext);
            return true;
        }
        if (action.equals(ACTION_RELEASE_WAKE_LOCK)) {
            _releaseWakeLockThread(callbackContext);
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        FlcUtil.releaseWakeLock();
        super.onDestroy();
    }

    private void _acquireWakeLockThread(final int timeout, final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                String error = FlcUtil.acquireWakeLock(timeout, cordova.getContext());
                if (error != null) {
                    callbackContext.error(error);
                    return;
                }
                callbackContext.success();
            }
        });
    }

    private void _releaseWakeLockThread(final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                String error = FlcUtil.releaseWakeLock();
                if (error != null) {
                    callbackContext.error(error);
                    return;
                }
                callbackContext.success();
            }
        });
    }

}
