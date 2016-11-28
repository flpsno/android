package impacta.com.br.t_037_sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Context context;
    //
    private TextView tv_x;
    private TextView tv_y;
    private TextView tv_z;

    private SensorManager sensorManager;

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
        tv_x = (TextView) findViewById(R.id.tv_x);
        tv_y = (TextView) findViewById(R.id.tv_y);
        tv_z = (TextView) findViewById(R.id.tv_z);
        //
        sensorManager = (SensorManager)
                context.getSystemService(SENSOR_SERVICE);
    }

    private void inicializarAcao() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        //
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float [] valores = sensorEvent.values;
        //
        tv_x.setText(String.valueOf(valores[0]));
        tv_y.setText(String.valueOf(valores[1]));
        tv_z.setText(String.valueOf(valores[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
