package br.com.impacta.t_014_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
    private Button btn_fn;
    private Button btn_cn;

    private int idNotificacao = 10;

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
        btn_fn = (Button) findViewById(R.id.btn_fn);
        btn_cn = (Button) findViewById(R.id.btn_cn);
    }

    private void inicializarAcao() {
        btn_fn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (Build.VERSION.SDK_INT) {
                    case Build.VERSION_CODES.JELLY_BEAN_MR1:
                    case Build.VERSION_CODES.JELLY_BEAN:
                        notificacao_JB_MR1("Sincronismo", "Sincronismo Realizado com Sucesso!!!");
                        break;
                    default:
                        notificacao_JB("Sincronismo", "Sincronismo Realizado com Sucesso!!!");
                        break;
                }


//                Intent mIntent = new Intent(
//                        context,
//                        MainActivity.class
//                );
//                //
//                PendingIntent pi = PendingIntent.getActivity(
//                        context,
//                        0,
//                        mIntent,
//                        0
//                );
//                //
//                NotificationManager nm = (NotificationManager)
//                        context.getSystemService(NOTIFICATION_SERVICE);
//                //
//                Notification.Builder notificacao =
//                        new Notification.Builder(context);
//                //
//                notificacao.setContentIntent(pi)
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setAutoCancel(true)
//                        .setContentTitle("Sincronismo")
//                        .setContentText("Sincronismo realizado com Sucesso!!!");
//                //
//                notificacao.setDefaults(
//                        Notification.DEFAULT_SOUND |
//                                Notification.DEFAULT_VIBRATE);
//                //
//                int versao = Build.VERSION.SDK_INT;
//                //
////                switch (versao){
////                    case Build.VERSION_CODES.JELLY_BEAN_MR1:
////                        break;
////                    default:
////                        break;
////                }
//
//                if (versao >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                    nm.notify(
//                            idNotificacao,
//                            notificacao.build()
//                    );
//                } else {
//                    nm.notify(
//                            idNotificacao,
//                            notificacao.getNotification()
//                    );
//                }


            }
        });
        //
        btn_cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager nm = (NotificationManager)
                        context.getSystemService(NOTIFICATION_SERVICE);
                //
                nm.cancel(idNotificacao);
            }
        });
    }

    private void notificacao_JB_MR1(String titulo, String mensagem) {
        Intent mIntent = new Intent(
                context,
                MainActivity.class
        );
        //
        PendingIntent pi = PendingIntent.getActivity(
                context,
                0,
                mIntent,
                0
        );
        //
        NotificationManager nm = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);
        //
        Notification.Builder notificacao =
                new Notification.Builder(context);
        //
        notificacao.setContentIntent(pi)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle(titulo)
                .setContentText(mensagem);
        //
        notificacao.setDefaults(
                Notification.DEFAULT_SOUND |
                        Notification.DEFAULT_VIBRATE);
        //
        nm.notify(
                idNotificacao,
                notificacao.build()
        );
    }

    private void notificacao_JB(String titulo, String mensagem) {
        Intent mIntent = new Intent(
                context,
                MainActivity.class
        );
        //
        PendingIntent pi = PendingIntent.getActivity(
                context,
                0,
                mIntent,
                0
        );
        //
        NotificationManager nm = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);
        //
        Notification.Builder notificacao =
                new Notification.Builder(context);
        //
        notificacao.setContentIntent(pi)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle(titulo)
                .setContentText(mensagem);
        //
        notificacao.setDefaults(
                Notification.DEFAULT_SOUND |
                        Notification.DEFAULT_VIBRATE);
        //
        nm.notify(
                idNotificacao,
                notificacao.getNotification()
        );
    }


}
