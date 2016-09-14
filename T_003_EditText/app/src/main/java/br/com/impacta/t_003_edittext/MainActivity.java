package br.com.impacta.t_003_edittext;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Context context;

    private EditText et_nome;
    private Button btn_mvd;

    // Variavel Auxiliar
    private String mensagemErro;

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
        et_nome = (EditText) findViewById(R.id.et_nome);
        btn_mvd = (Button) findViewById(R.id.btn_mvd);
    }

    private void inicializarAcao() {
        btn_mvd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(processoValidacao()){
                    exibirDadosDigitados();
                } else {
                    exibirErro();
                }
            }
        });

    }

    private boolean processoValidacao() {
        String nome_digitado = et_nome.getText().toString().trim();
        //
        if(nome_digitado.length() == 0){
            mensagemErro = "Erro: Nome é Obrigatorio!!!";
            //
            return false;
        }
        //
        return true;
    }

    private void exibirDadosDigitados() {
        String nome_digitado = et_nome.getText().toString();
        //
        exibirMensagem(nome_digitado);
    }

    private void exibirErro() {
        exibirMensagem(mensagemErro);
    }

    private void exibirMensagem(String nome_digitado) {
        Toast.makeText(
                context,
                nome_digitado,
                Toast.LENGTH_SHORT
        ).show();
    }

}
