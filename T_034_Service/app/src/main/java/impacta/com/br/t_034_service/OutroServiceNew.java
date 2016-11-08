package impacta.com.br.t_034_service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by nalmir on 05/11/2016.
 */
public class OutroServiceNew extends IntentService {

    public OutroServiceNew() {
        super("OutroServiceNew");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {


            int index = 0;

            while(index < 25){
                Thread.sleep(1000);
                //
                Log.d("PROCESSO", String.valueOf(++index));
            }


        } catch (Exception e){

        }

    }
}
