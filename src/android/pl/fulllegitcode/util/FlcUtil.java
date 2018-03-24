package pl.fulllegitcode.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.util.Log;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Locale;

import static android.content.Context.WIFI_SERVICE;

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

  public static byte[] decodeImage(byte[] bytes) {
    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    ByteBuffer buffer = ByteBuffer.allocate(bitmap.getByteCount());
    bitmap.copyPixelsToBuffer(buffer);
    return buffer.array();
  }

  public static String getIp(Context context) {
    try {
      WifiManager manager = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
      if (manager != null) {
        int ip = manager.getConnectionInfo().getIpAddress();
        if (ip != 0) {
          return String.format(Locale.ENGLISH, "%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
        }
      }
    } catch (Exception e) {
      Log.e("FlcUtil", String.format(Locale.ENGLISH, "get ip. message=%s", e.getMessage()));
    }
    return "192.168.43.1";
  }

}
