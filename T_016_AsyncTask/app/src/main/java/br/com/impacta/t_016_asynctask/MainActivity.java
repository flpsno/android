package br.com.impacta.t_016_asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView tv_contador;
    private Button btn_contador;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        tv_contador = (TextView)
                findViewById(R.id.tv_contador);
        //
        btn_contador = (Button)
                findViewById(R.id.btn_contador);
    }

    private void inicializarAcao() {
        btn_contador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Sincronismo().execute();
            }
        });
    }

    private class Sincronismo extends AsyncTask<Integer, Integer, Void> {

        // Acessa Tela
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //
            //btn_contador.setEnabled(false);
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Sincronismo");
            progressDialog.setMessage("Iniciando o sincronismo dos dados.");
            progressDialog.setIcon(R.mipmap.ic_launcher);
            //
            progressDialog.show();
        }

        // Nao Acessa Tela
        @Override
        protected Void doInBackground(Integer... params) {

            try {

                int indice = 0;

                while (indice <= 25) {
                    indice++;
                    //
                    Thread.sleep(500);
                    //
                    publishProgress(indice);
                }


            } catch (Exception e) {

            }

            return null;
        }

        // Acessa Tela
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //
            int valor = values[0];
            //
            tv_contador.setText(String.valueOf(valor));
            //
            progressDialog.setMessage("Processandor atividade: " + String.valueOf(valor));
        }

        // Acessa Tela
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //
            //btn_contador.setEnabled(true);
            progressDialog.dismiss();
        }
    }
}
