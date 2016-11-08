package impacta.com.br.t_035_camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private static final int PROCESSO_TIRAR_FOTO = 10;

    private Context context;
    //
    private ImageView iv_foto;
    private Button btn_tirar_foto;
    //
    private String localPath;
    private File localFile;

    private int altura;
    private int largura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);
        //
        inicializarVariavel(savedInstanceState);
        inicializarAcao();
    }

    private void inicializarVariavel(Bundle savedInstanceState) {
        context = getBaseContext();
        //
        iv_foto = (ImageView) findViewById(R.id.iv_foto);
        //
        btn_tirar_foto = (Button) findViewById(R.id.btn_tf);
        //
        if (savedInstanceState != null){
            altura = savedInstanceState.getInt(Constantes.ALTURA);
            largura = savedInstanceState.getInt(Constantes.LARGURA);
            //
            localPath = savedInstanceState.getString(Constantes.PATH);
            localFile = (File) savedInstanceState.getSerializable(Constantes.FILE);
        } else {
            localPath = "/sdcard/CC_CACHE/foto.jpg";
            localFile = new File(localPath);
        }
    }

    private void inicializarAcao() {
        btn_tirar_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                largura = iv_foto.getWidth();
                altura = iv_foto.getHeight();
                //
                Intent mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(localFile));
                //
                startActivityForResult(mIntent, PROCESSO_TIRAR_FOTO);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(Constantes.ALTURA, altura);
        outState.putInt(Constantes.LARGURA, largura);
        //
        outState.putString(Constantes.PATH, localPath);
        outState.putSerializable(Constantes.FILE, localFile);
        //
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case PROCESSO_TIRAR_FOTO:
                setPic(localPath, iv_foto);
                break;
            default:
                break;
        }

    }

    private void setPic(String localPath, ImageView iv_foto) {
        int targetW = largura;
        int targetH = altura;
        //
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(localPath, options);
        //
        int photow = options.outWidth;
        int photoh = options.outHeight;
        //
        int scaleFactor = Math.min(photow/targetW, photoh/targetH);
        //
        options.inSampleSize = scaleFactor;
        options.inJustDecodeBounds = false;
        //
        Bitmap bm = BitmapFactory.decodeFile(localPath, options);
        //
        iv_foto.setImageBitmap(bm);
    }
}
