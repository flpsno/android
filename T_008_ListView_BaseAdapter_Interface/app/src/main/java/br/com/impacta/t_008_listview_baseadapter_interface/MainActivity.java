package br.com.impacta.t_008_listview_baseadapter_interface;

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
    private Adatper_Clientes adatper_clientes;

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
        lv_clientes = (ListView) findViewById(R.id.lv_clientes);
        //
        adatper_clientes = new Adatper_Clientes(
                context,
                R.layout.celula,
                gerarClientes(30)
        );
        //
        adatper_clientes.setOnClienteListener(new Adatper_Clientes.IADTC() {
            @Override
            public void chamarCliente(String texto) {
                Toast.makeText(
                        context,
                        "Cliente: " + texto,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        //
        lv_clientes.setAdapter(adatper_clientes);
    }

    private ArrayList<HMAux> gerarClientes(int quantidade) {
        ArrayList<HMAux> dAuxs = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            HMAux item = new HMAux();
            item.put(HMAux.ID, String.valueOf(i));
            item.put(HMAux.TEXTO_01, "Nome - " + String.valueOf(i));
            //
            dAuxs.add(item);
        }
        //
        return dAuxs;
    }

    private void inicializarAcao() {
        lv_clientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAux item = (HMAux) parent.getItemAtPosition(position);
                //
                Toast.makeText(
                        context,
                        item.get(HMAux.TEXTO_01),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

}
