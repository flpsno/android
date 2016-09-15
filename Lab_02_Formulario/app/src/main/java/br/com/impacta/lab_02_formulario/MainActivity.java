package br.com.impacta.lab_02_formulario;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private EditText et_nome;
    private EditText et_idade;
    //
    private RadioGroup rg;
    private RadioButton rb_m;
    private RadioButton rb_f;
    //
    private ImageView iv_foto;
    //
    private Button btn_obter_dados;

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
        et_idade = (EditText) findViewById(R.id.et_idade);
        //
        rg = (RadioGroup) findViewById(R.id.rg);
        rb_m = (RadioButton) findViewById(R.id.rb_m);
        rb_f = (RadioButton) findViewById(R.id.rb_f);
        //
        iv_foto = (ImageView) findViewById(R.id.iv_foto);
        //
        btn_obter_dados = (Button) findViewById(R.id.btn_obter_dados);
        //
        iv_foto.setImageBitmap(null);
    }

    private void inicializarAcao() {
        btn_obter_dados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validacao()) {
                    exibirDados();
                } else {
                    exibirErro();
                }
            }
        });
        //
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId){
                    case R.id.rb_m:
                        iv_foto.setImageResource(R.drawable.man);
                        break;
                    case R.id.rb_f:
                        iv_foto.setImageResource(R.drawable.woman);
                        break;
                    default:
                        iv_foto.setImageResource(R.mipmap.ic_launcher);
                        break;
                }
            }
        });
    }

    private boolean validacao() {
        String nome = et_nome.getText().toString().trim();
        int idade = conversaoInteiros(et_idade.getText().toString());
        //
        if (nome.length() == 0){
            mensagemErro = "Erro: Nome é Obrigatorio!!!";
            //
            return false;
        }
        //
        if (idade == -1){
            mensagemErro = "Erro: Numero Invalildo!!!";
            //
            return false;
        }
        //
        if (rg.getCheckedRadioButtonId() == -1){
            mensagemErro = "Erro: Sexo é Obrigatório!!!";
            //
            return false;
        }
        //
        return true;
    }

    private int conversaoInteiros(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception e) {
            return -1;
        }
    }

    private void exibirDados() {
        StringBuilder resultado = new StringBuilder();
        //
        resultado.append("Nome: ")
                .append(et_nome.getText().toString().trim())
                .append("\nIdade:   ")
                .append(et_idade.getText().toString().trim())
                .append("\nSexo:    ")
                .append(
                        rg.getCheckedRadioButtonId() == R.id.rb_m ? "M" : "F"
                );
        //
        exibirMensagem(resultado.toString());
    }

    private void exibirErro() {
        exibirMensagem(mensagemErro);
    }

    private void exibirMensagem(String resultado) {
        Toast.makeText(
                context,
                resultado.toString(),
                Toast.LENGTH_SHORT
        ).show();
    }

}
