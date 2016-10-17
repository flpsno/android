package br.com.impacta.t_025_http;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Button btn_ws;

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
        btn_ws = (Button) findViewById(R.id.btn_ws);
    }

    private void inicializarAcao() {
        btn_ws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Sincronismo().execute();
            }
        });
    }

    private class Sincronismo extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {


            Gson gson = new Gson();
            //
            Transmissao_Env env = new Transmissao_Env();
            env.setContatos(gerarContatos(1000));
            //
//            String resposta = ToolBox.comunicacao(
//                    Constantes.WEB_WS,
//                    gson.toJson(env)
//            );

            Transmissao_Rec rec = gson.fromJson(
                    ToolBox.comunicacao(
                            Constantes.WEB_WS,
                            gson.toJson(env)
                    ),
                    Transmissao_Rec.class
            );

            Log.d("HTTP", "");

            int indice = 10;


            return null;
        }
    }

    private ArrayList<Contato> gerarContatos(int quantidade) {
        ArrayList<Contato> contatos = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            contatos.add(
                    new Contato(
                            i,
                            "Nome - " + String.valueOf(i)
                    )
            );
        }
        //
        return contatos;
    }


}
