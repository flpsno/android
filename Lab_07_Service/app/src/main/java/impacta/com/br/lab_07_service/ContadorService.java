package impacta.com.br.lab_07_service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by nalmir on 05/11/2016.
 */
public class ContadorService extends IntentService {

    private int valor;

    public ContadorService() {
        super("ContadorService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try{

            recuperarParametros(intent);

            int indice = 0;

            while(indice < valor){

                Thread.sleep(1000);
                //
                sinalizadorStatus(Constantes.TIPO_ATUALIZAR, ++indice);
            }

        } catch (Exception e){
        } finally {
            sinalizadorStatus(Constantes.TIPO_FIM_PROCESSO, -1);
        }
    }

    private void recuperarParametros(Intent intent) {
        valor = intent.getIntExtra(Constantes.PARAMETRO, 0);
    }

    private void sinalizadorStatus(int tipo, int indice) {
        Intent mIntent = new Intent();
        mIntent.setAction(Constantes.EVENTO);
        mIntent.addCategory(Intent.CATEGORY_DEFAULT);
        //
        Bundle bundle = new Bundle();
        bundle.putInt(Constantes.TIPO, tipo);
        bundle.putInt(Constantes.VALOR, indice);
        bundle.putInt(Constantes.VALOR_REPETICAO, valor);
        //
        mIntent.putExtras(bundle);
        //
        sendBroadcast(mIntent);
    }
}
