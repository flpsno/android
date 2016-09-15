package br.com.impacta.t_010_multitelas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int PROCESSO_MULTIPLICAR_5 = 10;

    private Context context;
    //
    private Button btn_cs;
    private Button btn_cr;
    private Button btn_ct;

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
        btn_cs = (Button) findViewById(R.id.telainicial_btn_cs);
        btn_cr = (Button) findViewById(R.id.telainicial_btn_cr);
        btn_ct = (Button) findViewById(R.id.telainicial_btn_ct);
    }

    private void inicializarAcao() {
        btn_cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, SegundaTela.class);
                mIntent.putExtra(Constantes.PARAMETRO_TIPO, 1);
                mIntent.putExtra(Constantes.PARAMETRO_VALOR, 200);
                //
                startActivity(mIntent);
                //
                finish();
                //
                Toast.makeText(
                        context,
                        "O noís véi aqui!!!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        //
        btn_cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, SegundaTela.class);
                mIntent.putExtra(Constantes.PARAMETRO_TIPO, 2);
                mIntent.putExtra(Constantes.PARAMETRO_VALOR, 500);
                //
                startActivityForResult(mIntent, PROCESSO_MULTIPLICAR_5);
            }
        });
        //
        btn_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_CALL);
                mIntent.setData(Uri.parse("tel:" + "8888888"));
                //
                startActivity(mIntent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PROCESSO_MULTIPLICAR_5:
                processarResultado(resultCode, data);
                break;
            default:
                break;
        }
    }

    private void processarResultado(int resultCode, Intent data) {
        String resultado = "";

        if (resultCode == RESULT_OK) {
            int resultado_numerico = data.getIntExtra(Constantes.PARAMETRO_RESULTADO, 0);
            //
            resultado = "Valor do Calculo: " + String.valueOf(resultado_numerico);
        } else {
            resultado = "Cancelado!!!";
        }
        //
        Toast.makeText(
                context,
                resultado,
                Toast.LENGTH_SHORT
        ).show();
    }
}
