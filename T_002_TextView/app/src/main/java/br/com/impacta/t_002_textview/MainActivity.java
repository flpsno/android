package br.com.impacta.t_002_textview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView hugo_tv_android;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        hugo_tv_android = (TextView) findViewById(R.id.tv_android);
        //
        hugo_tv_android.setTextColor(
                getResources().getColor(R.color.vermelho_paulista)
        );
    }

}
