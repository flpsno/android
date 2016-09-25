package br.com.impacta.t_013_alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Nalmir on 24/09/2016.
 */
public class AlarmeAcao extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(
                context,
                "Executado a Acao do Alarme!!!",
                Toast.LENGTH_SHORT
        ).show();
        //
        Log.d("ALARME", "Executou o Alarme!!!");
    }
}
