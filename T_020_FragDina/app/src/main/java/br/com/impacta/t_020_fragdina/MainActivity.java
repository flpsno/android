package br.com.impacta.t_020_fragdina;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button btn_f01;
    private Button btn_f02;

    private F01 f01;
    private F02 f02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        btn_f01 = (Button)
                findViewById(R.id.telainicial_btn_f01);
        btn_f02 = (Button)
                findViewById(R.id.telainicial_btn_f02);
        //
        mudarFrag(2);
   }

    private void inicializarAcao() {
        btn_f01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mudarFrag(1);
            }
        });
        //
        btn_f02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mudarFrag(2);
            }
        });

    }

    private void mudarFrag(int indice) {
        FragmentManager fm = getSupportFragmentManager();
        String tag = "";
        //
        switch (indice){
            case 1:
                tag = "f01";
                //
                if (f01 == null){
                    f01 = new F01();
                }
                //
                if (fm.findFragmentByTag(tag)== null){
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.telainicial_ll, f01, tag);
                    ft.commit();
                }

                break;
            case 2:
                tag = "f02";
                //
                if (f02 == null){
                    f02 = new F02();
                }
                //
                if (fm.findFragmentByTag(tag)== null){
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.telainicial_ll, f02, tag);
                    ft.commit();
                }

                break;
            default:
                break;
        }
    }

}
