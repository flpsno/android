package br.com.impacta.t_012_b1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Nalmir on 24/09/2016.
 */
public class Receiver_B1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(
                context,
                "Resposta de B1",
                Toast.LENGTH_SHORT
        ).show();
    }
}
