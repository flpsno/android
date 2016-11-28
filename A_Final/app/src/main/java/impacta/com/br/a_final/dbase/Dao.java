package impacta.com.br.a_final.dbase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nalmir on 26/11/2016.
 */
public class Dao {
    private Context context;
    protected SQLiteDatabase db;

    public Dao(Context context) {
        this.context = context;
    }

    public void abrirBanco(){
        SQLiteHelper sqLiteHelper_var = new SQLiteHelper(
                context,
                Constantes.BANCO,
                null,
                Constantes.VERSAO
        );

        this.db = sqLiteHelper_var.getWritableDatabase();
    }

    public void fecharBanco(){
        if(this.db != null){
            this.db.close();
        }
    }
}
