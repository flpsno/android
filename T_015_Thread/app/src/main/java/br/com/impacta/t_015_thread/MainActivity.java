package br.com.impacta.t_015_thread;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView tv_contador;
    private Button btn_acionar_contador;
    private Button btn_acionar;

    private int indice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        btn_acionar_contador = (Button)
                findViewById(R.id.btn_contador);
        btn_acionar = (Button)
                findViewById(R.id.btn_acionar);
        //
        tv_contador = (TextView)
                findViewById(R.id.tv_contador);
    }

    private void inicializarAcao() {
        btn_acionar_contador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int indice = 0;
//                //
//                while(indice <= 25){
//                    try {
//                        Thread.sleep(500);
//                        //
//                        tv_contador.setText(String.valueOf(++indice));
//                        //
//
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
                btn_acionar_contador.setEnabled(false);
                //
                processoSeparado();
            }
        });
        //
        btn_acionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getBaseContext(),
                        "mensagem tela ok",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    private void processoSeparado() {
        Thread thread = new Thread() {

            @Override
            public void run() {

                indice = 0;
                //
                while (indice <= 25) {
                    try {
                        Thread.sleep(500);
                        //
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_contador.setText(String.valueOf(++indice));
                            }
                        });


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btn_acionar_contador.setEnabled(true);
                    }
                });


            }
        };
        //
        thread.start();
    }

}
