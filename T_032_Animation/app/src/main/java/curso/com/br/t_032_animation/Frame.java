package curso.com.br.t_032_animation;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Nalmir on 29/10/2016.
 */
public class Frame extends AppCompatActivity {

    private Context context;
    //
    private ImageView iv_coruja;
    //
    private AnimationDrawable mCoruja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        //
        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        //
        iv_coruja = (ImageView) findViewById(R.id.iv_coruja);
        //
        mCoruja = (AnimationDrawable) iv_coruja.getBackground();
    }

    private void inicializarAcao() {
        iv_coruja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCoruja.isRunning()){
                    mCoruja.stop();
                } else {
                    mCoruja.start();
                }
            }
        });
    }
}
