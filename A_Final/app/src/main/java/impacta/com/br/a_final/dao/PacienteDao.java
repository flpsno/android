package impacta.com.br.a_final.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import impacta.com.br.a_final.dbase.Dao;
import impacta.com.br.a_final.model.Paciente;
import impacta.com.br.impacta_lib.HMAux;

/**
 * Created by nalmir on 26/11/2016.
 */
public class PacienteDao extends Dao {

    private static final String TABELA = "pacientes";
    private static final String IDPACIENTE = "idpaciente";
    private static final String NOME = "nome";

    public PacienteDao(Context context) {
        super(context);
    }

    public ArrayList<HMAux> obterListaPacientes(){
        ArrayList<HMAux> dados = new ArrayList<>();
        //
        abrirBanco();
        //
        try{
            StringBuilder sbQuery = new StringBuilder();
            sbQuery.append(" select idpaciente, nome from pacientes order by nome ");

            Cursor cursor = db.rawQuery(sbQuery.toString(), null);

            while(cursor.moveToNext()){
                HMAux hmAux = new HMAux();
                hmAux.put(HMAux.ID, String.valueOf(
                        cursor.getLong(
                                cursor.getColumnIndex(IDPACIENTE)
                        )));
                //
                hmAux.put(HMAux.TEXTO_01, cursor.getString(
                                cursor.getColumnIndex(NOME)
                        ));
                //
                dados.add(hmAux);
            }

            cursor.close();
            cursor =null;

        } catch (Exception e){
        }
        //
        fecharBanco();
        //
        return dados;
    }

    public void inserirListaPacientes(ArrayList<Paciente> pacientes){
        abrirBanco();
        //
        db.beginTransaction();
        //
        try {
            db.delete(TABELA, null, null);

            ContentValues cv = new ContentValues();

            for (Paciente pAux : pacientes){
                cv.clear();
                //
                cv.put(IDPACIENTE, pAux.getIdpaciente());
                cv.put(NOME, pAux.getNome());
                //
                db.insert(TABELA, null, cv);
            }

            db.setTransactionSuccessful();

        } catch (Exception e){
        } finally {
            db.endTransaction();
        }
        //
        fecharBanco();
    }

}
