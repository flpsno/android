package br.com.impacta.t_008_listview;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private ListView lv_produtos;
    private ArrayList<HMAux> produtos;
    private SimpleAdapter adapter_produtos;

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
        lv_produtos = (ListView) findViewById(R.id.telainicial_lv_produtos);
        //
        gerarProdutos(100);
        //
        String[] De = {HMAux.TEXTO_01, HMAux.TEXTO_02};
        int[] Para = {R.id.celula_tv_nome, R.id.celula_tv_descricao_resumida};
        adapter_produtos = new SimpleAdapter(
                context,
                produtos,
                R.layout.celula,
                De,
                Para
        );
        //
        lv_produtos.setAdapter(adapter_produtos);
    }

    private void inicializarAcao() {
        lv_produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAux item = (HMAux) parent.getItemAtPosition(position);
                //
                Toast.makeText(
                        context,
                        item.get(HMAux.ID),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    private void gerarProdutos(int quantidade) {
        produtos = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            HMAux item = new HMAux();
            item.put(HMAux.ID, String.valueOf(i));
            item.put(HMAux.TEXTO_01, "Nome - " + String.valueOf(i));
            item.put(HMAux.TEXTO_02, "Descricao Resumida - " + String.valueOf(i));
            //
            produtos.add(item);
        }
    }

}
