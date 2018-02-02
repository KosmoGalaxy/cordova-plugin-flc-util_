package pl.fulllegitcode.flcutiltest;

import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import pl.fulllegitcode.util.FlcUtilPlugin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _start();
    }

    private void _start() {
        FlcUtilPlugin plugin = new FlcUtilPlugin();
        plugin.setContext(this);
        plugin.acquireWakeLock(PowerManager.PARTIAL_WAKE_LOCK);
    }
}
