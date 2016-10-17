package br.com.impacta.t_026_download;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private ImageView iv_foto;
    private Button btn_download;

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
        iv_foto = (ImageView) findViewById(R.id.iv_foto);
        btn_download = (Button) findViewById(R.id.btn_downaload);
    }

    private void inicializarAcao() {
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DWImagem().execute();
            }
        });
    }

    private class DWImagem extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Download");
            progressDialog.setMessage("Fazendo Download. Aguarde...");
            progressDialog.setCancelable(false);
            //
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                ToolBox.GDownload(
                        Constantes.WS_IM,
                        Constantes.WS_LOCAL
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //
            iv_foto.setImageBitmap(
                    BitmapFactory.decodeFile(Constantes.WS_LOCAL)
            );
            //
            progressDialog.dismiss();
        }
    }

}
