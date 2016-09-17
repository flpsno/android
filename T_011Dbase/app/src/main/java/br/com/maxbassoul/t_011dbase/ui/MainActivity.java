package br.com.maxbassoul.t_011dbase.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.com.maxbassoul.t_011dbase.R;
import br.com.maxbassoul.t_011dbase.banco.Constantes;
import br.com.maxbassoul.t_011dbase.banco.HMAux;
import br.com.maxbassoul.t_011dbase.dao.ContatoDao;
import br.com.maxbassoul.t_011dbase.model.Contato;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private ListView lv_contatos;

    private ContatoDao contatoDao;

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
        contatoDao = new ContatoDao(context);
        //
        lv_contatos = (ListView)
                findViewById(R.id.telainicial_lv_contatos);
        //
        carregarDadosTela();
    }

    private void carregarDadosTela() {
        String[] De = { HMAux.TEXTO_01 };
        int[] Para = { R.id.celula_tv_nome };

        lv_contatos.setAdapter(
                new SimpleAdapter(
                        context,
                        contatoDao.obterContatos_hm(),
                        R.layout.celula,
                        De,
                        Para
                )
        );
    }

    private void inicializarAcao() {
        lv_contatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAux item =
                        (HMAux) parent.getItemAtPosition(position);
                //
                chamarDetalhes(Long.parseLong(item.get(HMAux.ID)));
            }
        });

    }

    private void chamarDetalhes(long id) {
        Intent mIntent = new Intent(context, Detalhes.class);
        mIntent.putExtra(Constantes.PARAMETRO_ID, id);
        //
        startActivity(mIntent);
        //
        finish();
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
        if (id == R.id.action_incluir_contato) {

            chamarDetalhes(-1L);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
