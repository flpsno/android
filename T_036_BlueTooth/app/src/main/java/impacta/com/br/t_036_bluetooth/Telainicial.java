package impacta.com.br.t_036_bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;


public class Telainicial extends AppCompatActivity {

    private static final int PROCESSO_HABILITAR_BT = 1;
    private static final int PROCESSO_CONEXAO_BT = 2;

    private Context context;
    //
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mDispositivo = null;
    private BluetoothSocket mBTSocket = null;
    //
    private Button btn_habilitar_BT;
    private Button btn_desabilitar_BT;
    private Button btn_conexao_BT;

    private boolean conexao_status = false;

    //UUID da serial port Bluetooth
    private UUID mUuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

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
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //
        btn_habilitar_BT = (Button) findViewById(R.id.telainicial_btn_habilitar_bt);
        btn_desabilitar_BT = (Button) findViewById(R.id.telainicial_btn_desabilitar_bt);
        btn_conexao_BT = (Button) findViewById(R.id.telainicial_btn_conexao_bt);
    }

    private void inicializarAcao() {
        btn_habilitar_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBluetoothAdapter == null) {
                    exibirMensagem("Dispositivo nao suporta BT");
                } else if (!mBluetoothAdapter.isEnabled()) {
                    chamarTela_Habiliar_BT();
                } else {
                    exibirMensagem("BT Habilitado");
                }
            }
        });
        //
        btn_desabilitar_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBluetoothAdapter.disable();
                //
                exibirMensagem("BT Desabilitado!!!");
            }
        });
        //
        btn_conexao_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (conexao_status) {
                    desabilitarConexao();
                } else {
                    chamarTelaConexaoBT();
                }
            }
        });

    }

    private void desabilitarConexao() {
        try {
            conexao_status = false;
            mBTSocket.close();
            //
            btn_conexao_BT.setText("Conexao BT (Off)");
        } catch (Exception e) {
            exibirMensagem("Erro: " + e.toString());
        }
    }

    private void chamarTelaConexaoBT() {
        Intent mIntent = new Intent(context, TelaConexao.class);
        //
        startActivityForResult(mIntent, PROCESSO_CONEXAO_BT);
    }

    private void chamarTela_Habiliar_BT() {
        Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        //
        startActivityForResult(mIntent, PROCESSO_HABILITAR_BT);
    }

    private void exibirMensagem(String mensagem) {
        Toast.makeText(
                context,
                mensagem,
                Toast.LENGTH_SHORT
        ).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PROCESSO_HABILITAR_BT:
                verificarStatus_Habilitar_BT(resultCode);
                break;
            case PROCESSO_CONEXAO_BT:
                verificarMAC(resultCode, data);
                break;
            default:
                break;
        }

    }

    private void verificarStatus_Habilitar_BT(int resultCode) {
        if (resultCode == RESULT_OK) {
            exibirMensagem("BT Habilitado!!!");
        } else {
            exibirMensagem("BT Desabilitado!!!");
        }
    }

    private void verificarMAC(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String endereco_mac = data.getStringExtra(Constantes.ENDERECO_MAC);
            //
            mDispositivo = mBluetoothAdapter.getRemoteDevice(endereco_mac);
            //
            try {
                mBTSocket = mDispositivo.createRfcommSocketToServiceRecord(mUuid);
                mBTSocket.connect();
                //
                conexao_status = true;
                //
                btn_conexao_BT.setText(
                        "Conexao BT (On - " +
                                mDispositivo.getName() +
                                " )");


            } catch (Exception e) {
                conexao_status = false;
                btn_conexao_BT.setText("Conexao BT (Off)");
                //
                exibirMensagem("Erro: " + e.toString());
            }


        } else {
            exibirMensagem("Cancelado!!!");
        }

    }
}
