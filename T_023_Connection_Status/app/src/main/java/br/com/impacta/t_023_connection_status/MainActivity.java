package br.com.impacta.t_023_connection_status;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Context context;

    private Button btn_conexao_status;

    private String sStatus = "";

    private MudancaConexao mudancaConexao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
        //
        mudancaConexao = new MudancaConexao();
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        //
        registerReceiver(mudancaConexao, filter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mudancaConexao);
        super.onDestroy();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        btn_conexao_status = (Button)
                findViewById(R.id.btn_conexao_status);
    }

    private void inicializarAcao() {
        btn_conexao_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sStatus = checkStatus();
                //
                Toast.makeText(
                        context,
                        sStatus,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    private String checkStatus() {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(CONNECTIVITY_SERVICE);
        //
        NetworkInfo mWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mMobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        //
        if (mWifi.isAvailable() && mWifi.isConnected()) {
            return "WIFI";
        }
        //
        if (mMobile.isAvailable() && mMobile.isConnected()) {
            return "OPERADORA";
        }
        //
        return "SEM CONEXAO";
    }

    private class MudancaConexao extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            sStatus = checkStatus();
            //
            Toast.makeText(
                    context,
                    sStatus,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }


}
