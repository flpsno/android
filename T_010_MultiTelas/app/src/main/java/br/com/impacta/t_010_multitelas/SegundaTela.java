package br.com.impacta.t_010_multitelas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Nalmir on 10/09/2016.
 */
public class SegundaTela extends AppCompatActivity {

    private Context context;
    //
    private TextView tv_parametros;
    private Button btn_multiplicar_5;

    private int tipo;
    private int valor;

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
                findViewById(R.id.segundatela_tv_parametros);
        //
        btn_multiplicar_5 = (Button)
                findViewById(R.id.segundatela_btn_multiplicar_5);
        //
        recuperarParametros();
        //
        if (tipo == 1) {
            btn_multiplicar_5.setEnabled(false);
        } else {
            btn_multiplicar_5.setEnabled(true);
        }
        //
        tv_parametros.setText("Valor : " + String.valueOf(valor));
    }

    private void recuperarParametros() {
        tipo = getIntent().getIntExtra(Constantes.PARAMETRO_TIPO, 1);
        valor = getIntent().getIntExtra(Constantes.PARAMETRO_VALOR, 10);
    }

    private void inicializarAcao() {
        btn_multiplicar_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultado = valor * 5;
                //
                Intent mIntent = new Intent();
                mIntent.putExtra(Constantes.PARAMETRO_RESULTADO, resultado);
                //
                setResult(RESULT_OK, mIntent);
                //
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (tipo == 1) {
            chamarPrimeiraTela();
        } else {
            super.onBackPressed();
        }
    }

    private void chamarPrimeiraTela() {
        Intent mIntent = new Intent(context, MainActivity.class);
        //
        startActivity(mIntent);
        //
        finish();
    }
}
