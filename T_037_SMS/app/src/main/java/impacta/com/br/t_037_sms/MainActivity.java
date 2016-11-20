package impacta.com.br.t_037_sms;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Button btn_sms_simples;
    private Button btn_sms_intercept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        btn_sms_simples =
                (Button) findViewById(R.id.btn_sms_simples);
        btn_sms_intercept =
                (Button) findViewById(R.id.btn_sms_intercept);
        //
        btn_sms_simples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sm = SmsManager.getDefault();
                //
                sm.sendTextMessage(
                        "11992323232",
                        null,
                        "Ok . Fá",
                        null,
                        null
                );
            }
        });
        //
        btn_sms_intercept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sm = SmsManager.getDefault();
                //
                sm.sendTextMessage(
                        "11992323232",
                        null,
                        "APN:Ok . Fá",
                        null,
                        null
                );

            }
        });
    }

}
