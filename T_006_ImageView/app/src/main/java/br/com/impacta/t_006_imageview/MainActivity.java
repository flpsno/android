package br.com.impacta.t_006_imageview;

import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private ImageView iv_foto_projeto;
    //
    private Button btn_cartao;
    //
    private ImageView iv_foto_cartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        iv_foto_projeto = (ImageView) findViewById(R.id.iv_foto_projeto);
        //
        btn_cartao = (Button) findViewById(R.id.btn_cartao);
        //
        iv_foto_cartao = (ImageView) findViewById(R.id.iv_foto_cartao);
        //
        iv_foto_projeto.setImageResource(R.drawable.fear);
        iv_foto_cartao.setImageBitmap(null);
    }

    private void inicializarAcao() {
        btn_cartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_foto_cartao.setImageBitmap(
                        BitmapFactory.decodeFile("/sdcard/DBase/tt.jpg")
                );

            }
        });
    }

}
