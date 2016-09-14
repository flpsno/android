package br.com.impacta.t_005_checkbox_radiobutton_togglebutton;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private CheckBox cb_android;
    private CheckBox cb_ios;
    //
    private RadioGroup rg;
    private RadioButton rb_m;
    private RadioButton rb_f;

    private ToggleButton tb_tomada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
        //
        simular_VindoDB();
        simular_PegarDB();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        cb_android = (CheckBox) findViewById(R.id.cb_android);
        cb_ios = (CheckBox) findViewById(R.id.cb_ios);
        //
        rg = (RadioGroup) findViewById(R.id.rg);
        rb_m = (RadioButton) findViewById(R.id.rb_m);
        rb_f = (RadioButton) findViewById(R.id.rb_f);
        //
        tb_tomada = (ToggleButton) findViewById(R.id.tomada);
    }

    private void inicializarAcao() {

    }

    private void simular_VindoDB() {
        int android = 0;
        int ios = 1;
        //
        String sexo = "m";
        //
        boolean tomada = true;
        //
        if (android == 1) {
            cb_android.setChecked(true);
        } else {
            cb_android.setChecked(false);
        }
        //
        if (ios == 1) {
            cb_ios.setChecked(true);
        } else {
            cb_ios.setChecked(false);
        }
        //
        if (sexo.equalsIgnoreCase("m")) {
            rb_m.setChecked(true);
        }
        if (sexo.equalsIgnoreCase("f")) {
            rb_f.setChecked(true);
        }
        //
        tb_tomada.setChecked(tomada);
    }

    private void simular_PegarDB() {

        int android = -1;
        int ios = -1;
        //
        String sexo = "";
        //
        boolean tomada = false;
        //
        if (cb_android.isChecked()){
            android = 1;
        } else {
            android = 0;
        }

        ios = cb_ios.isChecked() ? 1 : 0;

        switch (rg.getCheckedRadioButtonId()){
            case R.id.rb_m:
                 sexo = "m";
                break;
            case R.id.rb_f:
                sexo = "f";
                break;
            default:
                sexo = "I";
                break;
        }

        tomada = tb_tomada.isChecked();
    }


}
