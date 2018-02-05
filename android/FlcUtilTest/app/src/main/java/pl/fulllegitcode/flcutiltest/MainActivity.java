package pl.fulllegitcode.flcutiltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.Executors;

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
        plugin.setThreadPool(Executors.newCachedThreadPool());
        plugin.acquireWakeLock();
    }
}
