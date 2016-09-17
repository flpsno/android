package br.com.maxbassoul.t_011dbase.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nalmir on 17/09/2016.
 */
public class ConexaoBD extends SQLiteOpenHelper {
    public ConexaoBD(Context context) {
        super(context, Constantes.DATABASE, null, Constantes.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sb = new StringBuilder();
        //
        sb.append("CREATE TABLE if not exists [contatos] (\n" +
                "  [idcontato] BIGINT NOT NULL, \n" +
                "  [nome] TEXT NOT NULL, \n" +
                "  [telefone] text NOT NULL, \n" +
                "  [idade] INT NOT NULL, \n" +
                "  CONSTRAINT [] PRIMARY KEY ([idcontato]));");
        //
        String [] comandos = sb.toString().split(";");
        //
        for (int i = 0; i < comandos.length; i++) {
            db.execSQL(comandos[i].toLowerCase());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sb = new StringBuilder();
        //
        sb.append("DROP TABLE IF EXISTS [contatos];");
        //
        String [] comandos = sb.toString().split(";");
        //
        for (int i = 0; i < comandos.length; i++) {
            db.execSQL(comandos[i].toLowerCase());
        }
        //
        onCreate(db);
    }
}
