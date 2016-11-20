package impacta.com.br.t_036_bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;

/**
 * Created by nalmir on 19/11/2016.
 */
public class TelaConexao extends AppCompatActivity {

    private Context context;
    //
    private BluetoothAdapter mBluetoothAdapter;
    //
    private ListView lv_dispositivos;
    //
    private String endereco_mac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaconexao);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //
        lv_dispositivos = (ListView)
                findViewById(R.id.telaconexao_lv_dispositivos);
        //
        // Set Imagine um ArrayList
        Set<BluetoothDevice> dispositivosPareados =
                mBluetoothAdapter.getBondedDevices();
        //
        ArrayAdapter<String> btAdp = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1
        );
        //
        if (dispositivosPareados.size() > 0){
            for(BluetoothDevice dAux : dispositivosPareados){
                String nomeBT = dAux.getName();
                String macBT = dAux.getAddress();
                //
                btAdp.add(nomeBT + "\n" + macBT);
            }
        }
        //
        lv_dispositivos.setAdapter(btAdp);
    }

    private void inicializarAcao() {
        lv_dispositivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dispositivo = (String) parent.getItemAtPosition(position);
                //
                retornaMac(dispositivo.substring(dispositivo.length() - 17));
            }
        });
    }

    private void retornaMac(String endereco_mac) {
        Intent mIntent = new Intent();
        mIntent.putExtra(Constantes.ENDERECO_MAC, endereco_mac);
        //
        setResult(RESULT_OK, mIntent);
        //
        finish();
    }
}
