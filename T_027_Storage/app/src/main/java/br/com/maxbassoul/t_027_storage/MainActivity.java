package br.com.maxbassoul.t_027_storage;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Button btn_gravar_em;
    private Button btn_ler_em;
    //
    private Button btn_gravar_sd;
    private Button btn_ler_sd;
    //
    private Button btn_gravar_sb;
    private Button btn_ler_sb;

    public MainActivity() {
    }

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
        btn_gravar_em = (Button)
                findViewById(R.id.btn_gravar_em);
        btn_ler_em = (Button)
                findViewById(R.id.btn_ler_em);
        //
        btn_gravar_sd = (Button)
                findViewById(R.id.btn_gravar_sd);
        btn_ler_sd = (Button)
                findViewById(R.id.btn_ler_sd);
        //
        btn_gravar_sb = (Button)
                findViewById(R.id.btn_gravar_sb);
        btn_ler_sb = (Button)
                findViewById(R.id.btn_ler_sb);
    }

    private void inicializarAcao() {
        btn_gravar_em.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String extStorage = System.getenv("EXTERNAL_STORAGE");
                //
                gravarFF("Hugo", extStorage + "/ce", "teste.txt");
            }
        });
        //
        btn_ler_em.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String extStorage = System.getenv("EXTERNAL_STORAGE");
                //
                String resultado = lerFF(extStorage + "/ce", "teste.txt");
                //
                Toast.makeText(
                        context,
                        resultado,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        //
        btn_gravar_sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File[] path = getExternalFilesDirs(null);

                String extStorage = path[1].getPath();
                //
                gravarFF("Hugo", extStorage + "/ce", "teste.txt");

            }
        });
        //
        btn_ler_sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File[] path = getExternalFilesDirs(null);

                String extStorage = path[1].getPath();
                //
                String resultado = lerFF(extStorage + "/ce", "teste.txt");
                //
                Toast.makeText(
                        context,
                        resultado,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        //hugo
        btn_gravar_sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = getFilesDir();

                String extStorage = file.getPath();
                //
                gravarFF("Hugo", extStorage + "/ce", "teste.txt");

            }
        });
        //
        btn_ler_sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = getFilesDir();

                String extStorage = file.getPath();
                //
                String resultado = lerFF(extStorage + "/ce", "teste.txt");
                //
                Toast.makeText(
                        context,
                        resultado,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });



    }

    private void gravarFF(String mensagem, String path, String fileName){

        try {
            File diretorio = new File(path);
            //
            if (!diretorio.exists()){
                diretorio.mkdir();
            }
            //
            FileWriter f;
            //
            f =new FileWriter(path + "/" + fileName, true);
            f.append(mensagem);

            f.flush();
            f.close();

        } catch (Exception e) {
            Log.d("GRAVACAO", "Erro:" + e.toString());
        }
    }

    private String lerFF(String path, String fileName){
        StringBuilder conteudo = new StringBuilder();
        //
        try{
            File diretorio = new File(path);

            if(!diretorio.exists()){
                return "";
            }

            File arquivo = new File(path + "/" + fileName);

            if (!arquivo.exists()){
                return "";
            }

            BufferedReader input =
                    new BufferedReader(new FileReader(arquivo));
            //
            String linha = null;

            while((linha=input.readLine()) != null){
                conteudo.append(linha);
            }

            input.close();

        } catch (Exception e){
            Log.d("LEITURA", "Erro:" + e.toString());
        }
        //
        return conteudo.toString();
    }


}
