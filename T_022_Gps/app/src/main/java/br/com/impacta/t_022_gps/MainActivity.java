package br.com.impacta.t_022_gps;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Context context;
    //
    private Button btn_gps;
    private Button btn_rede;
    private Button btn_parar;
    //
    private TextView tv_latitude;
    private TextView tv_longitude;
    //
    private Button btn_lbs;
    private Button btn_verificar;

    private LocationManager lm;

    private double latitude;
    private double longitude;

    private PegaTrouxaListener trouxaListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVaraivel();
        inicializarAcao();
        //
        // Responsavel por fazer o processamento da resposta
        trouxaListener = new PegaTrouxaListener();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.location.PROVIDERS_CHANGED");
        //
        registerReceiver(trouxaListener, filter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(trouxaListener);
        super.onDestroy();
    }

    private void inicializarVaraivel() {
        context = getBaseContext();
        //
        lm = (LocationManager)
                context.getSystemService(LOCATION_SERVICE);
        //
        btn_gps = (Button) findViewById(R.id.btn_gps);
        btn_rede = (Button) findViewById(R.id.btn_rede);
        btn_parar = (Button) findViewById(R.id.btn_parar);
        //
        tv_latitude = (TextView) findViewById(R.id.tv_latitude);
        tv_longitude = (TextView) findViewById(R.id.tv_longitude);
        //
        btn_lbs = (Button) findViewById(R.id.btn_lbs);
        btn_verificar = (Button) findViewById(R.id.btn_verificar);
    }

    private void inicializarAcao() {
        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
                habilitarDesabilitar(false);
                //
                lm.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,
                        0,
                        gpsListener
                );
            }
        });
        //
        btn_rede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
                habilitarDesabilitar(false);
                //
                lm.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        0,
                        0,
                        gpsListener
                );
            }
        });
        //
        btn_parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.removeUpdates(gpsListener);
                //
                String uriGMaps = "geo:0,0?q=" +
                        String.valueOf(latitude).replace(",",".") +
                        "," +
                        String.valueOf(longitude).replace(",", ".");
                //
                Intent mIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(uriGMaps.toString())
                );
                //
                startActivity(mIntent);
                //
                habilitarDesabilitar(true);
            }
        });
        //
        btn_lbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelephonyManager tm = (TelephonyManager)
                        context.getSystemService(TELEPHONY_SERVICE);
                //
                GsmCellLocation location = (GsmCellLocation)
                        tm.getCellLocation();
                //
                StringBuilder sb = new StringBuilder();
                sb.append("CellID:      ")
                        .append(location.getCid())
                        .append("\nLac:       ")
                        .append(location.getLac());
                //
                Toast.makeText(
                        context,
                        sb.toString(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        //
        btn_verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationCHeck()) {
                    exibirOk();
                } else {
                    exibirAlerta();
                }
            }
        });
    }

    private boolean locationCHeck() {
        if (lm != null &&
                (
                        lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                                lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                )) {
            return true;
        } else {
            return false;
        }

    }

    private void exibirOk() {
        Toast.makeText(
                context,
                "Servico de Localizacao Habilitado",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void exibirAlerta() {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(MainActivity.this);
        //
        alerta.setIcon(R.mipmap.ic_launcher)
                .setTitle("Location Status")
                .setMessage("Serviço de Localização desligado." +
                        " Deseja reconfigura=lo?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mIntent = new Intent();
                mIntent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                //
                startActivity(mIntent);
            }
        });
        alerta.setNegativeButton("Nao", null);
        //
        alerta.show();
    }

    private void limparCampos() {
        tv_latitude.setText("0");
        tv_longitude.setText("0");
    }

    private void habilitarDesabilitar(boolean status) {
        btn_gps.setEnabled(status);
        btn_rede.setEnabled(status);
    }

    private LocationListener gpsListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            //
            tv_latitude.setText(String.valueOf(latitude));
            tv_longitude.setText(String.valueOf(longitude));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    private class PegaTrouxaListener extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            StringBuilder sb = new StringBuilder();

            if(locationCHeck()){
                sb.append("Sistema de Localizacao: Habilitado. Bom Usuário esse!!!");
            } else {
                sb.append("Tentativa de Violaçao de Segurando. Seu Superior esta " +
                        "sem avisado nesse momento!!!");
            }
            //
            Toast.makeText(
                    context,
                    sb.toString(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

}
