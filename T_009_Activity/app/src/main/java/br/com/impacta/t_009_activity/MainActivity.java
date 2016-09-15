package br.com.impacta.t_009_activity;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private EditText et_valor;
    private Button btn_mostrar_valor;
    private Button btn_mudar_valor;

    private String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel(savedInstanceState);
        inicializarAcao();
        //
        Log.d("TAGGG", "passei pelo onCreate");
    }

    private void inicializarVariavel(Bundle savedInstanceState) {
        context = getBaseContext();
        //
        et_valor = (EditText) findViewById(R.id.et_valor);
        btn_mostrar_valor = (Button) findViewById(R.id.btn_mostrar_valor);
        btn_mudar_valor = (Button) findViewById(R.id.btn_mudar_valor);
        //
        if (savedInstanceState == null){
            valor = "Sem Valor";
        } else {
            valor = savedInstanceState.getString(Constantes.VAR_VAR);
        }
    }

    private void inicializarAcao() {
        btn_mostrar_valor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        context,
                        valor,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        //
        btn_mudar_valor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor = et_valor.getText().toString();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //
        Log.d("TAGGG", "passei pelo onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        Log.d("TAGGG", "passei pelo onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //
        Log.d("TAGGG", "passei pelo onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //
        //
        Log.d("TAGGG", "passei pelo onStop");
    }

    @Override
    protected void onDestroy() {
        Log.d("TAGGG", "passei pelo onDestroy");
        //
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(Constantes.VAR_VAR, valor);
        //
        Log.d("TAGGG", "passei pelo onSaveInstanceState");
        //
        super.onSaveInstanceState(outState);
    }
}
