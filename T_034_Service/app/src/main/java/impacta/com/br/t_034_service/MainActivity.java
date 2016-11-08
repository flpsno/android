package impacta.com.br.t_034_service;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Button btn_ativiar;
    private Button btn_desativar;
    private Button btn_servico;

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
        btn_ativiar = (Button) findViewById(R.id.btn_ativar);
        btn_desativar = (Button) findViewById(R.id.btn_desativar);
        btn_servico = (Button) findViewById(R.id.btn_servico);
    }

    private void inicializarAcao() {
        btn_ativiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(context, AtivacaoService.class);
                startService(mIntent);
            }
        });
        //
        btn_desativar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(context, AtivacaoService.class);
                //
                stopService(mIntent);
            }
        });
        //
        btn_servico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(context, OutroServiceNew.class);
                //
                startService(mIntent);
            }
        });
    }

}
