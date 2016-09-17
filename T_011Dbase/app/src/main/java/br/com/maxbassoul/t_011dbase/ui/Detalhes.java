package br.com.maxbassoul.t_011dbase.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.maxbassoul.t_011dbase.R;
import br.com.maxbassoul.t_011dbase.banco.Constantes;
import br.com.maxbassoul.t_011dbase.dao.ContatoDao;
import br.com.maxbassoul.t_011dbase.model.Contato;

/**
 * Created by nalmir on 17/09/2016.
 */
public class Detalhes extends AppCompatActivity {

    private Context context;
    //
    private EditText et_codigo;
    private EditText et_nome;
    private EditText et_telefone;
    private EditText et_idade;
    //
    private Button btn_excluir;
    private Button btn_salvar;

    private ContatoDao contatoDao;
    private long idAtual;

    private String mensagemErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        contatoDao = new ContatoDao(context);
        //
        et_codigo = (EditText)
                findViewById(R.id.detalhes_et_codigo);
        et_nome = (EditText)
                findViewById(R.id.detalhes_et_nome);

        et_telefone = (EditText)
                findViewById(R.id.detalhes_et_telefone);

        et_idade = (EditText)
                findViewById(R.id.detalhes_et_idade);
        //
        btn_excluir = (Button)
                findViewById(R.id.detalhes_btn_excluir);
        btn_salvar = (Button)
                findViewById(R.id.detalhes_btn_salvar);
        //
        recurperDados();
        //
        if (idAtual != -1) {
            Contato cAux = contatoDao.obterContatoByID(idAtual);
            //
            et_codigo.setText(String.valueOf(cAux.getIdcontato()));
            et_nome.setText(cAux.getNome());
            et_telefone.setText(cAux.getTelefone());
            et_idade.setText(String.valueOf(cAux.getIdade()));
            //
            btn_excluir.setVisibility(View.VISIBLE);
        } else {
            btn_excluir.setVisibility(View.GONE);
        }


    }

    private void recurperDados() {
        idAtual = getIntent().getLongExtra(Constantes.PARAMETRO_ID, 0);
    }

    private void inicializarAcao() {
        btn_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contatoDao.apagarContato(idAtual);
                //
                chamarLista();
            }
        });
        //
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validacao()) {
                    salvarContato();
                } else {
                    exibirErro();
                }
            }
        });

    }

    private boolean validacao() {
        String nome = et_nome.getText().toString().trim();
        String telefone = et_telefone.getText().toString().trim();
        //
        int idade = -1;

        try {
            idade = Integer.parseInt(et_idade.getText().toString());
        } catch (Exception e) {
            idade = -1;
        }
        //
        if (nome.length() == 0) {
            mensagemErro = "Erro: Nome é Obrigatório!!!";
            //
            return false;
        }
        //
        if (telefone.length() == 0) {
            mensagemErro = "Erro: Telefone é Obrigatório!!!";
            //
            return false;
        }
        //
        if (idade <= 10) {
            mensagemErro = "Erro: Idade Invalida!!!";
            //
            return false;
        }
        //
        return true;
    }

    private void salvarContato() {
        Contato cAux = new Contato();
        //
        cAux.setNome(et_nome.getText().toString().trim());
        cAux.setTelefone(et_telefone.getText().toString().trim());
        cAux.setIdade(Integer.parseInt(et_idade.getText().toString()));
        //
        if (idAtual != -1) {
            cAux.setIdcontato(idAtual);
            //
            contatoDao.atualizarContato(cAux);
        } else {
            idAtual = contatoDao.proximoID();

            cAux.setIdcontato(idAtual);
            //
            contatoDao.incluirContato(cAux);
            //
            et_codigo.setText(String.valueOf(cAux.getIdcontato()));
            //
            btn_excluir.setVisibility(View.VISIBLE);
        }

        exibirMensagem("Contato Salvo com Sucesso");
    }

    private void exibirErro() {
        exibirMensagem(mensagemErro);
    }

    private void exibirMensagem(String texto) {
//        Toast.makeText(
//                context,
//                texto,
//                Toast.LENGTH_SHORT
//        ).show();
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(Detalhes.this);

        alerta.setTitle("Cadastro de Contatos");
        alerta.setMessage(texto);
        //
        alerta.setPositiveButton("Ok", null);
        //
        alerta.show();

    }


    private void chamarLista() {
        Intent mIntent = new Intent(context, MainActivity.class);
        //
        startActivity(mIntent);
        //
        finish();
    }

    @Override
    public void onBackPressed() {
        caixaAlerta();
    }

    private void caixaAlerta() {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(Detalhes.this);

        alerta.setTitle("Cadastro de Contatos");
        alerta.setMessage("Deseja Realmente Sair?");
        //
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chamarLista();
            }
        });
        alerta.setNegativeButton("Não", null);
        //
        alerta.show();
    }
}
