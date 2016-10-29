package br.com.impacta.curso.hlib;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by nalmir on 29/10/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Context context;
    //
    private EditText et_nome;
    private EditText et_senha;
    //
    private Button btn_cancelar;
    private Button btn_entrar;

    private int resource = R.layout.tela_login;

    public void setResource(int resource) {
        this.resource = resource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resource);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        et_nome = (EditText) findViewById(R.id.login_et_nome);
        et_senha = (EditText) findViewById(R.id.login_et_senha);
        //
        btn_cancelar = (Button) findViewById(R.id.login_btn_cancelar);
        btn_entrar = (Button) findViewById(R.id.login_btn_Entrar);
    }

    private void inicializarAcao() {
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_nome.setText("");
                et_senha.setText("");
                //
                et_nome.requestFocus();
            }
        });
        //
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamarLogin(
                        et_nome.getText().toString(),
                        et_senha.getText().toString()
                );
            }
        });
    }

    public abstract void chamarLogin(String nome, String senha);
}
