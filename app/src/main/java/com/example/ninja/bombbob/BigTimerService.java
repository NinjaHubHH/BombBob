package com.example.ninja.bombbob;

        import android.app.Service;
        import android.content.Intent;
        import android.os.CountDownTimer;
        import android.os.IBinder;
        import android.util.Log;

public class BigTimerService extends Service {

    CountDownTimer cdt = null;

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("Starting timer...");

            cdt = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                System.out.println("Countdown seconds remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                System.out.println("Timer finished");
                Intent i = new Intent();
                i.setClass(BigTimerService.this, LoseScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        cdt.start();
    }

    @Override
    public void onDestroy() {

        cdt.cancel();
        System.out.println("Timer cancelled");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}