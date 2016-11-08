package impacta.com.br.t_034_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by nalmir on 05/11/2016.
 */
public class AtivacaoService extends Service {

    private MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        //
        player = MediaPlayer.create(this, R.raw.ci);
        player.setLooping(true);
        player.setVolume(50f, 50f);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();

        return 1;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        //
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
