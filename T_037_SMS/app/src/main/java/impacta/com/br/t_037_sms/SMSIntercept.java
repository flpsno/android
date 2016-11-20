package impacta.com.br.t_037_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by nalmir on 19/11/2016.
 */
public class SMSIntercept extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String sFinal = "";
        //
        Bundle bundle = intent.getExtras();
        //
        SmsMessage [] msgs = null;
        //
        String str = "";
        //
        if (bundle != null){
            Object [] pdus = (Object[]) bundle.get("pdus");
            //
            msgs = new SmsMessage[pdus.length];
            //
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                str += msgs[i].getMessageBody();
            }
            //
            if (str.contains("APN:")){
                Toast.makeText(
                        context,
                        str,
                        Toast.LENGTH_SHORT
                ).show();
                //
                abortBroadcast();
            }
        }







    }
}
