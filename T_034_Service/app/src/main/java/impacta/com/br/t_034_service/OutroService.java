package impacta.com.br.t_034_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by nalmir on 05/11/2016.
 */
public class OutroService extends Service{

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {


            int index = 0;

            while(index < 25){
                Thread.sleep(1000);
                //
                Log.d("PROCESSO", String.valueOf(++index));
            }


        } catch (Exception e){

        }




        return 1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
