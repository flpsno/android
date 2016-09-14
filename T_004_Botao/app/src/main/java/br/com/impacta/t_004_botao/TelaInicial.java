package br.com.impacta.t_004_botao;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by nalmir on 20/08/2016.
 */
public class TelaInicial extends AppCompatActivity {

    private Context context;
    //
    private Button btn_01;
    private Button btn_02;
    private Button btn_03;

    private boolean status = false;

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
        btn_01 = (Button) findViewById(R.id.btn_01);
        btn_02 = (Button) findViewById(R.id.btn_02);
        btn_03 = (Button) findViewById(R.id.btn_03);
    }

    private void inicializarAcao() {
        btn_01.setOnClickListener(hugoListener);
        btn_02.setOnClickListener(hugoListener);
        btn_03.setOnClickListener(hugoListener);
    }

    private View.OnClickListener hugoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String resultado = "";


            switch (v.getId()){
                case R.id.btn_01:
                    resultado = "Botao 01";
                    break;
                case R.id.btn_02:
                    if (status){
                        btn_01.setOnClickListener(hugoListener);
                    } else {
                        btn_01.setOnClickListener(null);
                    }
                    //
                    status = !status;
                    //
                    resultado = "Botao 02";
                    break;
                case R.id.btn_03:
                    resultado = "Botao 03";
                    break;

            }

            Toast.makeText(
                    context,
                    "Chamei " + resultado ,
                    Toast.LENGTH_SHORT
            ).show();
        }
    };
}
