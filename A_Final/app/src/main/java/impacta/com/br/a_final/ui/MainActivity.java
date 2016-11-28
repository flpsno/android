package impacta.com.br.a_final.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import impacta.com.br.a_final.R;
import impacta.com.br.a_final.dao.PacienteDao;
import impacta.com.br.a_final.dbase.Constantes;
import impacta.com.br.a_final.model.Paciente;
import impacta.com.br.a_final.service.Sincronismo;
import impacta.com.br.impacta_lib.HMAux;
import impacta.com.br.impacta_lib.ToolBox;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private ListView lv_pacientes;
    PacienteDao pacienteDao;

    private EscutarNovosDados receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
        //
        receiver = new EscutarNovosDados();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constantes.MSG_SINCRONISMO);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        //
        registerReceiver(receiver, filter);
        //
        ativarServico();
    }

    private void ativarServico() {
        Intent mIntent = new Intent(context, Sincronismo.class);
        //
        startService(mIntent);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        //
        super.onDestroy();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        pacienteDao = new PacienteDao(context);
        //
        lv_pacientes = (ListView) findViewById(R.id.lv_pacientes);
        //
        montarLista();
    }

    private void inicializarAcao() {
        lv_pacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAux item = (HMAux)
                        parent.getItemAtPosition(position);
                //
                Toast.makeText(
                        context,
                        item.get(HMAux.ID) + " - " + item.get(HMAux.TEXTO_01),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    private void montarLista() {
        lv_pacientes.setAdapter(
                new ArrayAdapter<>(
                        context,
                        android.R.layout.simple_list_item_1,
                        pacienteDao.obterListaPacientes()
                )
        );
    }

    private class EscutarNovosDados extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            montarLista();
        }
    }

}
