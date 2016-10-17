package br.com.impacta.t_024_json;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Button btn_gravar_json;
    private Button btn_ler_json;
    //
    private Button btn_gravar_gson;
    private Button btn_ler_gson;

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
        btn_gravar_json = (Button) findViewById(R.id.btn_gravar_json);
        btn_ler_json = (Button) findViewById(R.id.btn_ler_json);
        //
        btn_gravar_gson = (Button) findViewById(R.id.btn_gravar_gson);
        btn_ler_gson = (Button) findViewById(R.id.btn_ler_gson);
    }

    private void inicializarAcao() {
        btn_gravar_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    JSONArray jsonArray = new JSONArray(gerarContatos_JSONObject(10));
                    JSONObject transmissao = new JSONObject();
                    transmissao.put("contatos", jsonArray);
                    //
                    String resultado = transmissao.toString();
                    //
                    int indice = 10;
                    //
                    Log.d("JSON", resultado);

                } catch (Exception e) {
                }
            }
        });
        //
        btn_ler_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder servidor = new StringBuilder();
                servidor.append("{\"contatos\":[{\"nome\":\"Nome - 1\",\"idcontato\":1,\"telefone\":\"Telefone - 1\"},{\"nome\":\"Nome - 2\",\"idcontato\":2,\"telefone\":\"Telefone - 2\"},{\"nome\":\"Nome - 3\",\"idcontato\":3,\"telefone\":\"Telefone - 3\"},{\"nome\":\"Nome - 4\",\"idcontato\":4,\"telefone\":\"Telefone - 4\"},{\"nome\":\"Nome - 5\",\"idcontato\":5,\"telefone\":\"Telefone - 5\"},{\"nome\":\"Nome - 6\",\"idcontato\":6,\"telefone\":\"Telefone - 6\"},{\"nome\":\"Nome - 7\",\"idcontato\":7,\"telefone\":\"Telefone - 7\"},{\"nome\":\"Nome - 8\",\"idcontato\":8,\"telefone\":\"Telefone - 8\"},{\"nome\":\"Nome - 9\",\"idcontato\":9,\"telefone\":\"Telefone - 9\"},{\"nome\":\"Nome - 10\",\"idcontato\":10,\"telefone\":\"Telefone - 10\"}]}");
                //
                try {

                    JSONObject jsonObject =
                            new JSONObject(servidor.toString());
                    //
                    JSONArray jsonArray = jsonObject.getJSONArray("contatos");
                    //
                    ArrayList<Contato> contatos = new ArrayList<Contato>();
                    //
                    for (int i = 0; i < jsonArray.length(); i++) {
                        contatos.add(new Contato(jsonArray.getJSONObject(i)));
                    }

                    Toast.makeText(
                            context,
                            "Quantidade de Contatos: " + String.valueOf(contatos.size()),
                            Toast.LENGTH_SHORT
                    ).show();

                } catch (Exception e) {
                }

            }
        });
        //
        btn_gravar_gson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                //
                Transmissao_Env env = new Transmissao_Env();
                env.setContatos(gerarContatos_Classe(10));
                //
                String resultado = gson.toJson(env);
                //
                int indice = 10;
                //
                Log.d("GSON", resultado);
            }
        });
        //
        btn_ler_gson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                //
                StringBuilder servidor = new StringBuilder();
                servidor.append("{\"contatos\":[{\"telefone\":\"Telefone - 1\",\"nome\":\"Nome - 1\",\"idcontato\":1},{\"telefone\":\"Telefone - 2\",\"nome\":\"Nome - 2\",\"idcontato\":2},{\"telefone\":\"Telefone - 3\",\"nome\":\"Nome - 3\",\"idcontato\":3},{\"telefone\":\"Telefone - 4\",\"nome\":\"Nome - 4\",\"idcontato\":4},{\"telefone\":\"Telefone - 5\",\"nome\":\"Nome - 5\",\"idcontato\":5},{\"telefone\":\"Telefone - 6\",\"nome\":\"Nome - 6\",\"idcontato\":6},{\"telefone\":\"Telefone - 7\",\"nome\":\"Nome - 7\",\"idcontato\":7},{\"telefone\":\"Telefone - 8\",\"nome\":\"Nome - 8\",\"idcontato\":8},{\"telefone\":\"Telefone - 9\",\"nome\":\"Nome - 9\",\"idcontato\":9},{\"telefone\":\"Telefone - 10\",\"nome\":\"Nome - 10\",\"idcontato\":10}]}");

                //
                Transmissao_Rec rec = gson.fromJson(
                        servidor.toString(),
                        Transmissao_Rec.class
                );
                //
                Log.d("GSON", String.valueOf(rec.getContatos().size()));
            }
        });

    }


    private ArrayList<Contato> gerarContatos_Classe(int quantidade) {
        ArrayList<Contato> contatos = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            contatos.add(
                    new Contato(
                            i,
                            "Nome - " + String.valueOf(i),
                            "Telefone - " + String.valueOf(i)
                    )
            );
        }
        //
        return contatos;
    }

    private ArrayList<JSONObject> gerarContatos_JSONObject(int quantidade) {
        ArrayList<JSONObject> contatos = new ArrayList<>();
        //
        for (int i = 1; i <= quantidade; i++) {
            contatos.add(
                    (new Contato(
                            i,
                            "Nome - " + String.valueOf(i),
                            "Telefone - " + String.valueOf(i)
                    )).toJSONObject()
            );
        }
        //
        return contatos;
    }


}
