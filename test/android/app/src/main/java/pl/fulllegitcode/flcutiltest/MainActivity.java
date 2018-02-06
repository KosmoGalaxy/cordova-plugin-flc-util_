package pl.fulllegitcode.flcutiltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.fulllegitcode.util.FlcUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _start();
    }

    private void _start() {
        FlcUtil.acquireWakeLock(this);
        FlcUtil.releaseWakeLock();
    }

}
