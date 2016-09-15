package br.com.impacta.lab_05_somar_telas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Nalmir on 10/09/2016.
 */
public class SegundaTela extends AppCompatActivity {

    private Context context;
    //
    private TextView tv_parametros;
    //
    private Button btn_retornar_a_soma;

    private int x;
    private int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundatela);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        tv_parametros = (TextView)
                findViewById(R.id.segundatela_tv_valores);
        btn_retornar_a_soma = (Button)
                findViewById(R.id.segundatela_btn_retornar_a_soma);
        //
        recuperarValores();
        //
        tv_parametros.setText("X(" + String.valueOf(x) + ") + Y(" + String.valueOf(y) + ")");
    }

    private void recuperarValores() {
        x = getIntent().getIntExtra(Constantes.PARAMETRO_X, -1);
        y = getIntent().getIntExtra(Constantes.PARAMETRO_Y, -1);
    }

    private void inicializarAcao() {
        btn_retornar_a_soma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.putExtra(Constantes.PARAMETRO_SOMA, (x+y));
                //
                setResult(RESULT_OK, mIntent);
                //
                finish();
            }
        });
    }
}
