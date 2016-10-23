package br.com.maxbassoul.t_031_alerta_customizado;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button btn_alerta_customizado;
    //
    private String nome = "";
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        btn_alerta_customizado = (Button)
                findViewById(R.id.btn_mostrar_alerta);
    }

    private void inicializarAcao() {
        btn_alerta_customizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montar_alerta_customizado();
            }
        });

    }

    private void montar_alerta_customizado() {
        LayoutInflater mInflater =
                LayoutInflater.from(getBaseContext());
        //
        final View textEntryView = mInflater.inflate(R.layout.alerta_customizado, null);
        //
        final EditText et_nome = (EditText) textEntryView.findViewById(R.id.alerta_customizado_nome);
        final EditText et_email = (EditText) textEntryView.findViewById(R.id.alerta_customizado_email);
        //
        et_nome.setText(nome);
        et_email.setText(email);
        //
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setIcon(R.mipmap.ic_launcher);
        alerta.setTitle("Digite os Dados de Envio:");
        alerta.setView(textEntryView);
        //
        alerta.setNegativeButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nome = et_nome.getText().toString();
                email = et_email.getText().toString();
                //
                StringBuilder sb = new StringBuilder();
                sb.append("Nome:        ")
                        .append(nome)
                        .append("\nEmail:     ")
                        .append(email);
                //
                Toast.makeText(
                        getBaseContext(),
                        sb.toString(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        //
        alerta.show();


    }

}
