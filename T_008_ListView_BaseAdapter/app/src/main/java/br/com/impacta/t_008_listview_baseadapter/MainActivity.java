package br.com.impacta.t_008_listview_baseadapter;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private ListView lv_clientes;
    private HugoAdapter adapter_clientes;
    private ArrayList<HMAux> clientes;

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
        lv_clientes = (ListView)
                findViewById(R.id.telainicial_lv_clientes);
        //
        gerarClientes(100);
        //
        adapter_clientes = new HugoAdapter(
                context,
                R.layout.celula,
                clientes
        );
        //
        lv_clientes.setAdapter(adapter_clientes);
    }

    private void inicializarAcao() {
        lv_clientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter_clientes.setIdselecionado(id);
            }
        });
    }

    private void gerarClientes(int quantidade) {
        clientes = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            HMAux item = new HMAux();
            //
            item.put(HMAux.ID, String.valueOf(i));
            item.put(HMAux.TEXTO_01, "Nome - " + String.valueOf(i));
            //
            clientes.add(item);
        }
    }


}
