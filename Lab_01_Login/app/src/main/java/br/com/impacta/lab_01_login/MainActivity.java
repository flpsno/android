package br.com.impacta.lab_01_login;

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
    //
    private EditText et_nome;
    private EditText et_senha;
    //
    private Button btn_cancelar;
    private Button btn_login;

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
        et_senha = (EditText) findViewById(R.id.et_senha);
        //
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    private void inicializarAcao() {
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparTela();
            }
        });
        //
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validacao()) {
                    exibirConfirmacao();
                } else {
                    exibirErro();
                }
            }
        });
    }

    private void limparTela() {
        et_nome.setText("");
        et_senha.setText("");
        //
        et_nome.requestFocus();
    }

    private boolean validacao() {
        String nome = et_nome.getText().toString();
        String senha = et_senha.getText().toString();
        //
        if (nome.trim().length() == 0) {
            mensagemErro = "Erro: Nome e Obrigatorio!!!";
            //
            return false;
        }
        if (senha.trim().length() == 0) {
            mensagemErro = "Erro: Senha e Obrigatoria!!!";
            //
            return false;
        }
        // Regra de Negocio
        if (!nome.equalsIgnoreCase("Hugo") || !senha.equals("T123")) {
            mensagemErro = "Credenciais Invalidas!!!";
            //
            return false;
        }

        return true;
    }


    private void exibirErro() {
        exibirMensagem("Credenciais Validas");
    }


    private void exibirConfirmacao() {
        exibirMensagem(mensagemErro);
    }

    private void exibirMensagem(String msg) {
        Toast.makeText(
                context,
                mensagemErro,
                Toast.LENGTH_SHORT
        ).show();
    }


}
