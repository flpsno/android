package br.com.maxbassoul.t_030_cdt;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private DTEditText dt_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        dt_data = (DTEditText) findViewById(R.id.dt_data);
        dt_data.setTitulo("Selecione uma data");
        dt_data.setText("01-02-2006");
    }


}
