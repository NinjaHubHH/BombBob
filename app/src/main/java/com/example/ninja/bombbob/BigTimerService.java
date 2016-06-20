package com.example.ninja.bombbob;

        import android.app.Service;
        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.os.CountDownTimer;
        import android.os.IBinder;
        import android.util.Log;

        import android.app.Service;
        import android.content.Intent;
        import android.os.CountDownTimer;
        import android.os.IBinder;
        import android.util.Log;

public class BigTimerService extends Service {

    private final static String TAG = "BroadcastService";
    public static int bombtime = 120000;
    public static int bombtick = 1000;
    public static long millis;
    private boolean enterCountdown = false;
    private boolean playerRunning = false;

    MediaPlayer mp;

    public static final String COUNTDOWN_BR = "your_package_name.countdown_br";
    Intent bi = new Intent(COUNTDOWN_BR);

    static CountDownTimer cdt = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Starting timer...");

            cdt = new CountDownTimer(bombtime, bombtick) {
                @Override
                public void onTick(long millisUntilFinished) {
                    millis = millisUntilFinished;
                    Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
                    bi.putExtra("countdown", millisUntilFinished);
                    sendBroadcast(bi);
                    if (millis < 15000 && millis != 0 && enterCountdown == false){
                        enterCountdown = true;
                        mp = MediaPlayer.create(BigTimerService.this, R.raw.alarm);
                        mp.start();
                        playerRunning = true;
                    }
                }

                @Override
                public void onFinish() {
                    Log.i(TAG, "Timer finished");
                    Intent dialogIntent = new Intent(BigTimerService.this, LoseScreen.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
            };

            cdt.start();
        }

    @Override
    public void onDestroy() {
        cdt.cancel();
        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
       if(playerRunning){ mp.stop();}
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


}

