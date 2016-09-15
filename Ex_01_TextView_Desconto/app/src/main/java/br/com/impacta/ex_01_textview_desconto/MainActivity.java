package br.com.impacta.ex_01_textview_desconto;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText et_desconto;
    private EditText et_preco;

    private double valor_eup = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        et_desconto = (EditText) findViewById(R.id.et_desconto);
        et_preco = (EditText) findViewById(R.id.et_preco);
        //
        et_desconto.setText("0");
        et_preco.setText(String.valueOf(valor_eup));
        //
        et_desconto.addTextChangedListener(txt_desconto);
        et_preco.addTextChangedListener(txt_preco);
    }

    private void inicializarAcao() {

    }

    private TextWatcher txt_desconto = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            // desligar preco
            et_preco.removeTextChangedListener(txt_preco);
            // exucutar os processo para obtencao do novo preco
            try {
                double desconto_digitado =
                        Double.parseDouble(et_desconto.getText().toString());
                double preco_com_desconto = (1 - desconto_digitado / 100) * valor_eup;
                //
                et_preco.setText(String.valueOf(preco_com_desconto));

            } catch (Exception e) {
                et_preco.setText(String.valueOf(valor_eup));
            }
            // religar preco
            et_preco.addTextChangedListener(txt_preco);
        }
    };

    private TextWatcher txt_preco = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            // desligar desconto
            et_desconto.removeTextChangedListener(txt_desconto);
            // exucutar os processo para obtencao do novo desconto
            try {
                double preco_digitado =
                        Double.parseDouble(et_preco.getText().toString());
                double desconto_praticado = (1-preco_digitado/valor_eup) * 100;
                //
                et_desconto.setText(String.valueOf(desconto_praticado));

            } catch (Exception e){
                et_desconto.setText("");
            }
            // religar desconto
            et_desconto.addTextChangedListener(txt_desconto);

        }
    };
}
