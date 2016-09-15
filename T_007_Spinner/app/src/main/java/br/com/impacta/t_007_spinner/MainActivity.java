package br.com.impacta.t_007_spinner;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Spinner spinner;
    private ArrayList<String> nomes;
    private ArrayAdapter<String> adapter_nomes;

    // Produto
    private Spinner spinner_produtos;
    private ArrayList<Produto> produtos;
    private ArrayAdapter<Produto> adapter_produtos;

    private Button btn_obter_valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVaraivel();
        inicializarAcao();
        //
        spinner_produtos.setSelection(descobriPosition(spinner_produtos, 25L));
    }

    private int descobriPosition(Spinner spAux, long id) {
        int indice = 0;
        //
        for (int i = 0; i < spAux.getCount(); i++) {
            Produto pAux = (Produto) spAux.getItemAtPosition(i);
            //
            if (pAux.getIdproduto() == id){
                indice = i;
                //
                break;
            }
        }
        //
        return indice;
    }

    private void inicializarVaraivel() {
        context = getBaseContext();
        //
        spinner = (Spinner) findViewById(R.id.sp_nomes);
        //
        adapter_nomes = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_item,
                gerarNomes(100)
        );
        adapter_nomes.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        //
        spinner.setAdapter(adapter_nomes);
        //
        // Produto
        //
        spinner_produtos = (Spinner) findViewById(R.id.sp_produtos);
        //
        adapter_produtos = new ArrayAdapter<Produto>(
                context,
                android.R.layout.simple_spinner_item,
                gerarProdutos(100)
        );
        adapter_produtos.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        //
        spinner_produtos.setAdapter(adapter_produtos);
        //
        btn_obter_valor = (Button) findViewById(R.id.btn_obter_valor);
    }

    private ArrayList<String> gerarNomes(int quantiadade) {
        ArrayList<String> nAuxs = new ArrayList<>();
        //
        for (int i = 1; i <= quantiadade; i++) {
            nAuxs.add("Nome - " + String.valueOf(i));
        }
        //
        return nAuxs;
    }

    private ArrayList<Produto> gerarProdutos(int quantidade) {
        ArrayList<Produto> pAuxs = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            Produto pAux = new Produto();
            pAux.setIdproduto(i);
            pAux.setNome("Nome - " + String.valueOf(i));
            pAux.setPreco(0.5 * i);
            //
            pAuxs.add(pAux);
        }
        //
        return pAuxs;
    }

    private void inicializarAcao() {
        btn_obter_valor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto p1 = (Produto) spinner_produtos.getSelectedItem();
                //
                Toast.makeText(
                        context,
                        "ID - " + String.valueOf(p1.getIdproduto()),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

}
