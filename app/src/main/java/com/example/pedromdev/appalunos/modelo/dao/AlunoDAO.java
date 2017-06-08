package com.example.pedromdev.appalunos.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pedromdev.appalunos.modelo.bean.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedromdev on 18/05/17.
 */

public class AlunoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 5;

    private static final String TABELA = "Aluno";

    private static final String DATABASE = "MPAlunos";

    private static final String TAG = "CADASTRO_ALUNO";

    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE " + TABELA + " ("
                + "matricula INTEGER PRIMARY KEY, "
                + "nome TEXT,"
                + "curso TEXT,"
                + "disciplina TEXT,"
                + "telefone TEXT,"
                + "endereco TEXT,"
                + "site TEXT,"
                + "email TEXT,"
                + "foto TEXT,"
                + "av1 REAL,"
                + "av2 REAL,"
                + "av3 REAL"
                + ")";

        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void cadastrar(Aluno aluno) {
        ContentValues values = getContentValues(aluno);
        getWritableDatabase().insert(TABELA, null, values);
        Log.i(TAG, "Aluno cadastrado: " + aluno.getNome());
    }

    public void atualizar(Aluno aluno) {
        ContentValues values = getContentValues(aluno, true);
        String[] args = {aluno.getMatricula().toString()};
        getWritableDatabase().update(TABELA, values, "matricula=?", args);
        Log.i(TAG, "Aluno atualizado: " + aluno.getNome());
    }

    public List<Aluno> listar() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "select * from " + TABELA + " order by nome";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {
                Aluno aluno = pegarDadosDoCursor(cursor);
                lista.add(aluno);
            }
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
        }
        return lista;
    }

    public Aluno recuperar(Long matricula) {
        Aluno aluno = null;
        String sql = "select * from " + TABELA + " where matricula=?";
        String[] args = {matricula.toString()};
        Cursor cursor = getReadableDatabase().rawQuery(sql, args);

        try {
            if (!cursor.moveToFirst()) {
                return null;
            }
            aluno = pegarDadosDoCursor(cursor);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
        }
        return aluno;
    }

    public void deletar(Aluno aluno) {
        String[] args = {aluno.getMatricula().toString()};

        getWritableDatabase().delete(TABELA, "matricula=?", args);

        Log.i(TAG, "Aluno deletado: " + aluno.getNome());
    }

    private Aluno pegarDadosDoCursor(Cursor cursor) {
        Aluno aluno = new Aluno();

        aluno.setMatricula(cursor.getLong(cursor.getColumnIndex("matricula")));
        aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        aluno.setCurso(cursor.getString(cursor.getColumnIndex("curso")));
        aluno.setDisciplina(cursor.getString(cursor.getColumnIndex("disciplina")));
        aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
        aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
        aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
        aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        aluno.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
        aluno.setAv1(cursor.getDouble(cursor.getColumnIndex("av1")));
        aluno.setAv2(cursor.getDouble(cursor.getColumnIndex("av2")));
        aluno.setAv3(cursor.getDouble(cursor.getColumnIndex("av3")));

        return aluno;
    }

    private ContentValues getContentValues(Aluno aluno) {
        return getContentValues(aluno, false);
    }

    private ContentValues getContentValues(Aluno aluno, Boolean semMatricula) {
        ContentValues values = new ContentValues();

        if (!semMatricula) {
            values.put("matricula", aluno.getMatricula());
        }
        values.put("nome", aluno.getNome());
        values.put("curso", aluno.getCurso());
        values.put("disciplina", aluno.getDisciplina());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("email", aluno.getEmail());
        values.put("foto", aluno.getFoto());
        values.put("av1", aluno.getAv1());
        values.put("av2", aluno.getAv2());
        values.put("av3", aluno.getAv3());
        return values;
    }
}
