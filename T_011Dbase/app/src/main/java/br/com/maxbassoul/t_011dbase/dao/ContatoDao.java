package br.com.maxbassoul.t_011dbase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.io.CharArrayReader;
import java.util.ArrayList;

import br.com.maxbassoul.t_011dbase.banco.Dao;
import br.com.maxbassoul.t_011dbase.banco.HMAux;
import br.com.maxbassoul.t_011dbase.model.Contato;

/**
 * Created by nalmir on 17/09/2016.
 */
public class ContatoDao extends Dao {

    private static final String TABELA = "contatos";
    private static final String IDCONTATO = "idcontato";
    private static final String NOME = "nome";
    private static final String TELEFONE = "telefone";
    private static final String IDADE = "idade";

    public ContatoDao(Context context) {
        super(context);
    }

    public void incluirContato(Contato contato) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        cv.put(IDCONTATO, contato.getIdcontato());
        cv.put(NOME, contato.getNome());
        cv.put(TELEFONE, contato.getTelefone());
        cv.put(IDADE, contato.getIdade());
        //
        db.insert(
                TABELA,
                null,
                cv
        );
        //
        fecharBanco();
    }

    public void atualizarContato(Contato contato) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        String filtro = IDCONTATO + " = ? ";
        String[] parametros = {
                String.valueOf(contato.getIdcontato())
        };
        //cv.put(IDCONTATO, contato.getIdcontato());
        cv.put(NOME, contato.getNome());
        cv.put(TELEFONE, contato.getTelefone());
        cv.put(IDADE, contato.getIdade());
        //
        db.update(
                TABELA,
                cv,
                filtro,
                parametros
        );
        //
        fecharBanco();
    }

    public void apagarContato(long idcontato) {
        abrirBanco();
        //
        String filtro = IDCONTATO + " = ? ";
        String[] parametros = {
                String.valueOf(idcontato)
        };
        //
        db.delete(
                TABELA,
                filtro,
                parametros
        );
        //
        fecharBanco();
    }

    public Contato obterContatoByID(long idcontato) {
        Contato cAux = null;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String[] parametros = {
                    String.valueOf(idcontato)};
            StringBuilder SQlcomando = new StringBuilder();
            SQlcomando
                    .append("select * from ")
                    .append(TABELA)
                    .append(" where ")
                    .append(IDCONTATO)
                    .append(" = ?");
            cursor = db.rawQuery(SQlcomando.toString(), parametros);
            //
            while (cursor.moveToNext()) {
                cAux = new Contato();
                //
                cAux.setIdcontato(cursor.getLong(cursor.getColumnIndex(IDCONTATO)));
                cAux.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                cAux.setTelefone(cursor.getString(cursor.getColumnIndex(TELEFONE)));
                cAux.setIdade(cursor.getInt(cursor.getColumnIndex(IDADE)));
            }

            cursor.close();
            cursor = null;
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return cAux;
    }

    public ArrayList<Contato> obterContatos() {
        ArrayList<Contato> contatos = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder SQlcomando = new StringBuilder();
            SQlcomando
                    .append("select ")
                    .append(IDCONTATO)
                    .append(",")
                    .append(NOME)
                    .append(" from ")
                    .append(TABELA)
                    .append(" order by ")
                    .append(NOME);
            cursor = db.rawQuery(SQlcomando.toString(), null);
            //
            while (cursor.moveToNext()) {
                Contato cAux = new Contato();
                //
                cAux.setIdcontato(cursor.getLong(cursor.getColumnIndex(IDCONTATO)));
                cAux.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                //
                contatos.add(cAux);
            }

            cursor.close();
            cursor = null;
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return contatos;
    }

    public ArrayList<HMAux> obterContatos_hm() {
        ArrayList<HMAux> contatos = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder SQlcomando = new StringBuilder();
            SQlcomando
                    .append("select ")
                    .append(IDCONTATO)
                    .append(",")
                    .append(NOME)
                    .append(" from ")
                    .append(TABELA)
                    .append(" order by ")
                    .append(NOME);
            cursor = db.rawQuery(SQlcomando.toString(), null);
            //
            while (cursor.moveToNext()) {
                HMAux cAux = new HMAux();
                //
                cAux.put(HMAux.ID, String.valueOf(
                        cursor.getLong(
                                cursor.getColumnIndex(IDCONTATO)
                        )
                ));
                //
                cAux.put(HMAux.TEXTO_01, String.valueOf(
                        cursor.getString(
                                cursor.getColumnIndex(NOME)
                        )
                ));
                //
                contatos.add(cAux);
            }

            cursor.close();
            cursor = null;
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return contatos;
    }


    public long proximoID() {
        long idProximo = 0;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder SQlcomando = new StringBuilder();
            SQlcomando
                    .append("select max(")
                    .append(IDCONTATO)
                    .append(") + 1 as id")
                    .append(" from ")
                    .append(TABELA);
            cursor = db.rawQuery(SQlcomando.toString(), null);
            //
            while (cursor.moveToNext()) {
                idProximo = cursor.getLong(cursor.getColumnIndex("id"));
            }

            if (idProximo == 0) {
                idProximo = 1;
            }

            cursor.close();
            cursor = null;
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return idProximo;
    }


}
