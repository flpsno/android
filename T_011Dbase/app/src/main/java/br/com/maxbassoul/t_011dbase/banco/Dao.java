package br.com.maxbassoul.t_011dbase.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nalmir on 17/09/2016.
 */
public class Dao {

    private Context context;
    protected SQLiteDatabase db;

    public Dao(Context context) {
        this.context = context;
    }

    public void abrirBanco(){
        ConexaoBD conexaoBD = new ConexaoBD(context);
        //
        this.db = conexaoBD.getWritableDatabase();
    }

    public void fecharBanco(){
        if (db != null){
            db.close();
        }
    }
}
