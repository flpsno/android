package br.com.impacta.lab_03_herois;

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
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private ListView lv_herois;

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
        lv_herois = (ListView) findViewById(R.id.telainicial_lv_herois);
        //
        String[] De = {HMAux.TEXTO_01, HMAux.TEXTO_02};
        int[] Para = {R.id.celula_iv_foto, R.id.celula_tv_nome};
        lv_herois.setAdapter(
                new SimpleAdapter(
                        context,
                        gerarHerois(),
                        R.layout.celula,
                        De,
                        Para
                )
        );
    }

    int fotos[] = {
            R.drawable.avenger01,
            R.drawable.avenger02,
            R.drawable.avenger03,
            R.drawable.avenger04,
            R.drawable.avenger05,
            R.drawable.avenger06,
            R.drawable.avenger07,
            R.drawable.avenger08,
            R.drawable.avenger09,
            R.drawable.avenger10,
            R.drawable.avenger11
    };

    String nomes [] = {
            "Gaviao Arqueiro",
            "War Machine",
            "Thor",
            "Nick Fury",
            "Loky",
            "Homem de Ferro",
            "Hulk",
            "Homem Formiga",
            "Capitao America",
            "Viuva Negra",
            "Agente Qualquer"
    };

    String poderes [] = {
            "Nada",
            "Amigo Rico",
            "Bom Moço",
            "Cego de Um Olho",
            "Trolador",
            "Rico",
            "Paciencia",
            "Pequeno",
            "Bom Moço",
            "Bonita Jogado",
            "Desculpa"
    };


    private ArrayList<HMAux> gerarHerois() {
        ArrayList<HMAux> dados = new ArrayList<>();
        //
        for (int i = 0; i < fotos.length; i++) {
            HMAux item = new HMAux();
            //
            item.put(HMAux.ID, String.valueOf(i+1));
            item.put(HMAux.TEXTO_01, String.valueOf(fotos[i]));
            item.put(HMAux.TEXTO_02, nomes[i]);
            item.put(HMAux.TEXTO_03, poderes[i]);
            //
            dados.add(item);
        }
        //
        return dados;
    }

    private void inicializarAcao() {
        lv_herois.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAux item = (HMAux) parent.getItemAtPosition(position);
                //
                Toast.makeText(
                        context,
                        item.get(HMAux.TEXTO_03) + " - " + item.get(HMAux.ID),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

}
