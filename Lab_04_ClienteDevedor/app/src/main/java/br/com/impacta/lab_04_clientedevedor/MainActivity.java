package br.com.impacta.lab_04_clientedevedor;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private ListView lv_clientes;

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
        lv_clientes = (ListView) findViewById(R.id.telainicial_lv_clientes);
        //
        lv_clientes.setAdapter(
                new Adapter_Clientes(
                        context,
                        R.layout.celula,
                        gerarClientes(100)
                )
        );
    }

    private void inicializarAcao() {
        lv_clientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAux item = (HMAux) parent.getItemAtPosition(position);
                //
                Toast.makeText(
                        context,
                        item.get(HMAux.TEXTO_03),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private ArrayList<HMAux> gerarClientes(int quantidade) {
        ArrayList<HMAux> dados = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            HMAux item = new HMAux();
            //
            item.put(HMAux.ID, String.valueOf(i));
            item.put(HMAux.TEXTO_01, "Nome - " + String.valueOf(i));
            item.put(HMAux.TEXTO_02, String.valueOf(i % 2));
            item.put(HMAux.TEXTO_03, (i % 2) == 0 ? "Cliente Bom" : "Sai fora CALOTEIRO!!!");
            //
            dados.add(item);
        }
        //
        return dados;
    }

}
