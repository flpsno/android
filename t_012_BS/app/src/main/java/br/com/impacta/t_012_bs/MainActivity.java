package br.com.impacta.t_012_bs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Button btn_fazer_broadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        btn_fazer_broadCast = (Button) findViewById(R.id.btn_fazer_broadcast);
    }

    private void inicializarAcao() {
        btn_fazer_broadCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.setAction("ABACAXI");
                mIntent.addCategory(Intent.CATEGORY_DEFAULT);
                mIntent.putExtra("valor", 10);
                //
                sendBroadcast(mIntent);
            }
        });
    }

}
