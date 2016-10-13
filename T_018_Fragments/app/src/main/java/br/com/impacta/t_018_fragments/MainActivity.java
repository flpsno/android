package br.com.impacta.t_018_fragments;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private F01 f01;
    private F02 f02;

    private FragmentManager fm;

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
        fm = getSupportFragmentManager();
        //
        f01 = (F01) fm.findFragmentById(R.id.telainicial_f01);
        f02 = (F02) fm.findFragmentById(R.id.telainicial_f02);
    }

    private void inicializarAcao() {
        f01.setOnMudarTextoF1(new F01.IF01() {
            @Override
            public void mudarTextoF1(String texto) {
                f02.mudarTexto("Android 7");
            }
        });
    }

//    public void mudarTextoF1(String texto){
//
//    }


}
