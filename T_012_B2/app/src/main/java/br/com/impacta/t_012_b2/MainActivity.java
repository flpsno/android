package br.com.impacta.t_012_b2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView tv_valor;

    private int indexador = 1;

    private HugoReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        inicializarVariavel();
        inicializarAcao();
        //
        receiver = new HugoReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ABACAXI");
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        //
        // Comeco a ouvir a sinalização do evento
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        // Paro de ouvir a sinalizacao do evento
        unregisterReceiver(receiver);
        //
        super.onDestroy();
    }

    private void inicializarVariavel() {
        tv_valor = (TextView) findViewById(R.id.tv_valor);
    }

    private void inicializarAcao() {

    }


    private class HugoReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            int valor = intent.getIntExtra("valor", 0);
            //
            valor = valor * indexador++;
            //
            tv_valor.setText(String.valueOf(valor));
            //
            Toast.makeText(
                    context,
                    "Resposta - B2",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }






}
