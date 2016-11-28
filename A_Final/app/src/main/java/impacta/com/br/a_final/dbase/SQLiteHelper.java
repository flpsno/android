package impacta.com.br.a_final.dbase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nalmir on 26/11/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE if not exists [pacientes] (\n" +
                "  [idpaciente] BIGINT NOT NULL, \n" +
                "  [nome] TEXT NOT NULL, \n" +
                "  CONSTRAINT [] PRIMARY KEY ([idpaciente]));");

        String [] comandos = sb.toString().split(";");

        for (int i = 0; i < comandos.length; i++) {
            sqLiteDatabase.execSQL(comandos[i].toLowerCase());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE IF EXISTS [pacientes];");

        String [] comandos = sb.toString().split(";");

        for (int ii = 0; ii < comandos.length; ii++) {
            sqLiteDatabase.execSQL(comandos[ii].toLowerCase());
        }

        onCreate(sqLiteDatabase);
    }
}
