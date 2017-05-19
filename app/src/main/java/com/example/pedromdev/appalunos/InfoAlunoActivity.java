package com.example.pedromdev.appalunos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.pedromdev.appalunos.helper.InfoHelper;
import com.example.pedromdev.appalunos.modelo.bean.Aluno;
import com.example.pedromdev.appalunos.modelo.dao.AlunoDAO;

public class InfoAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_aluno);

        Long matricula = getIntent().getLongExtra("matricula_aluno", 0L);
        AlunoDAO dao = new AlunoDAO(this);
        InfoHelper helper = new InfoHelper(this);

        Aluno aluno = dao.recuperar(matricula);
        helper.setDadosDoAluno(aluno);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_voltar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_voltar:
                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
