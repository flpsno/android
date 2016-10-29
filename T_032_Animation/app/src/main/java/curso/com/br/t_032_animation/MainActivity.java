package curso.com.br.t_032_animation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    private Context context;
    //
    private Button btn_alpha;
    private Button btn_girar;
    //
    private ImageView iv_foto;
    //
    private Button btn_escala;
    private Button btn_tudo;
    private Button btn_trans;

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
        btn_alpha = (Button) findViewById(R.id.btn_alpha);
        btn_girar = (Button) findViewById(R.id.btn_girar);
        //
        iv_foto = (ImageView) findViewById(R.id.iv_foto);
        //
        btn_escala = (Button) findViewById(R.id.btn_escala);
        btn_tudo = (Button) findViewById(R.id.btn_tudo);
        btn_trans = (Button) findViewById(R.id.btn_trans);
    }

    private void inicializarAcao() {
        btn_alpha.setOnClickListener(botaoAnimacao);
        btn_girar.setOnClickListener(botaoAnimacao);
        btn_escala.setOnClickListener(botaoAnimacao);
        btn_tudo.setOnClickListener(botaoAnimacao);
        btn_trans.setOnClickListener(botaoAnimacao);
    }


    private View.OnClickListener botaoAnimacao = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            desabilitarHabilitarBotoes(false);

            switch (view.getId()){
                case R.id.btn_alpha:
                    Animation mAlpha =
                            AnimationUtils.loadAnimation(
                                    context,
                                    R.anim.alpha
                    );
                    //
                    mAlpha.setAnimationListener(MainActivity.this);
                    iv_foto.requestLayout();
                    iv_foto.setAnimation(mAlpha);
                    mAlpha.start();
                    break;
                case R.id.btn_girar:
                    Animation mGirar =
                            AnimationUtils.loadAnimation(
                                    context,
                                    R.anim.girar
                            );
                    //
                    mGirar.setAnimationListener(MainActivity.this);
                    iv_foto.requestLayout();
                    iv_foto.setAnimation(mGirar);
                    mGirar.start();
                    break;
               case R.id.btn_escala:
                   Animation mEscala =
                           AnimationUtils.loadAnimation(
                                   context,
                                   R.anim.escala
                           );
                   //
                   mEscala.setAnimationListener(MainActivity.this);
                   iv_foto.requestLayout();
                   iv_foto.setAnimation(mEscala);
                   mEscala.start();
                    break;
                case R.id.btn_tudo:
                    Animation mTudo =
                            AnimationUtils.loadAnimation(
                                    context,
                                    R.anim.tudo
                            );
                    //
                    mTudo.setAnimationListener(MainActivity.this);
                    iv_foto.requestLayout();
                    iv_foto.setAnimation(mTudo);
                    mTudo.start();
                    break;
                case R.id.btn_trans:
                    Animation mTrans =
                            AnimationUtils.loadAnimation(
                                    context,
                                    R.anim.trans
                            );
                    //
                    mTrans.setAnimationListener(MainActivity.this);
                    iv_foto.requestLayout();
                    iv_foto.setAnimation(mTrans);
                    mTrans.start();
                    break;
                default:
                    desabilitarHabilitarBotoes(true);
                    break;


            }



        }
    };

    private void desabilitarHabilitarBotoes(boolean status) {
        btn_alpha.setEnabled(status);
        btn_girar.setEnabled(status);
        btn_escala.setEnabled(status);
        btn_tudo.setEnabled(status);
        btn_trans.setEnabled(status);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        desabilitarHabilitarBotoes(true);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_frame) {

            Intent mIntent = new Intent(context, Frame.class);
            startActivity(mIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
