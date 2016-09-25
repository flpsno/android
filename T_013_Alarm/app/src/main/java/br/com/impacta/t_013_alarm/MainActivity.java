package br.com.impacta.t_013_alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Button btn_au;
    private Button btn_ar;
    private Button btn_ac;
    //
    private PendingIntent pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        btn_au = (Button) findViewById(R.id.btn_au);
        btn_ar = (Button) findViewById(R.id.btn_ar);
        btn_ac = (Button) findViewById(R.id.btn_ac);
    }

    private void inicializarAcao() {
        btn_au.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cAux = Calendar.getInstance();
                //
                cAux.set(
                        Calendar.SECOND,
                        cAux.get(Calendar.SECOND) + 5
                );
                //
                Intent mIntent = new Intent(context, AlarmeAcao.class);
                //
                pi = PendingIntent.getBroadcast(
                        context,
                        0,
                        mIntent,
                        0
                );
                //
                AlarmManager am =
                        (AlarmManager) context.getSystemService(ALARM_SERVICE);
                //
                am.set(
                        AlarmManager.RTC_WAKEUP,
                        cAux.getTimeInMillis(),
                        pi
                );


            }
        });
        //
        btn_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, AlarmeAcao.class);
                //
                pi = PendingIntent.getBroadcast(
                        context,
                        0,
                        mIntent,
                        0
                );
                //
                AlarmManager am =
                        (AlarmManager) context.getSystemService(ALARM_SERVICE);
                //
                am.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + ( 5 * 1000 ),
                        ( 5 * 1000 ),
                        pi
                );

            }
        });
        //
        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, AlarmeAcao.class);
                //
                pi = PendingIntent.getBroadcast(
                        context,
                        0,
                        mIntent,
                        0
                );
                //
                AlarmManager am =
                        (AlarmManager) context.getSystemService(ALARM_SERVICE);
                //
                am.cancel(pi);
            }
        });
    }

}
