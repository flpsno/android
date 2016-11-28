package impacta.com.br.a_final.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;

import com.google.gson.Gson;

import impacta.com.br.a_final.R;
import impacta.com.br.a_final.dao.PacienteDao;
import impacta.com.br.a_final.dbase.Constantes;
import impacta.com.br.a_final.model.Transmissao_Env;
import impacta.com.br.a_final.model.Transmissao_Rec;
import impacta.com.br.impacta_lib.ToolBox;

/**
 * Created by nalmir on 26/11/2016.
 */
public class Sincronismo extends IntentService {

    private String mensagem;

    public Sincronismo() {
        super("Sincronismo");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        try {
            Gson gson = new Gson();

            Transmissao_Env env = new Transmissao_Env();
            env.setUser("Hugo");
            env.setPwd(ToolBox.md5("teste"));
            env.setPacientes(1000);
            //
            String resultado =
                    ToolBox.comunicacao(
                            Constantes.WS_SERVICE,
                            gson.toJson(env)
                    );

            String par[] = resultado.split("#WSTAG#");

            switch (par.length) {
                case 2:
                    if (par[0].equals("0")) {
                        Transmissao_Rec rec =
                                gson.fromJson(
                                        par[1],
                                        Transmissao_Rec.class
                                );
                        //
                        PacienteDao pacienteDao = new PacienteDao(getApplicationContext());
                        //
                        pacienteDao.inserirListaPacientes(rec.getPacientes());
                        //
                        sendBroard();
                        //
                        mensagem = "Sincronismo Realizado com Sucesso!!!";
                    } else {
                        mensagem = "Erro: " + par[1];
                    }
                    break;
                default:
                    mensagem = "Erro: " + par[0];
                    break;
            }

        } catch (Exception e) {
            mensagem = "Erro: " + e.toString();
        } finally {
            notificacao();
        }

    }

    private void sendBroard() {
        Intent mIntent = new Intent();
        mIntent.setAction(Constantes.MSG_SINCRONISMO);
        mIntent.addCategory(Intent.CATEGORY_DEFAULT);
        //
        sendBroadcast(mIntent);
    }

    private void notificacao() {
        NotificationManager nm =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //
        Notification.Builder notificacao =
                new Notification.Builder(getApplicationContext());
        //
        notificacao
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle("Sincromismo")
                .setContentText(mensagem);
        //
        notificacao.setDefaults(
                Notification.DEFAULT_SOUND |
                        Notification.DEFAULT_VIBRATE);
        //
        int VERSAO = Build.VERSION.SDK_INT;
        //
        if (VERSAO >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            nm.notify(10, notificacao.build());
        } else {
            nm.notify(10, notificacao.getNotification());
        }
    }
}
