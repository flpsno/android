package br.com.impacta.t_004_botao;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private Material sImpacta;
    private Material sHugo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        sImpacta = new Material();
        sImpacta.setOnClickListener(new ITeste() {
            @Override
            public String fazerAlgo(String texto) {
                return null;
            }
        });
        //
        sHugo =new Material();
        sHugo.setOnClickListener(new ITeste() {
            @Override
            public String fazerAlgo(String texto) {
                return null;
            }
        });
        //
        Hugo sHugo = new Hugo();
        HugoMock sHugoM = new HugoMock();
        exTeste(sHugoM);

    }

    private void exTeste(ITeste ma){
        ma.fazerAlgo("Hugo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
