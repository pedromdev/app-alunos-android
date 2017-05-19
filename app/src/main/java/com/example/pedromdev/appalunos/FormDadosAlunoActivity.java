package com.example.pedromdev.appalunos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pedromdev.appalunos.helper.FormularioHelper;
import com.example.pedromdev.appalunos.modelo.bean.Aluno;
import com.example.pedromdev.appalunos.modelo.dao.AlunoDAO;
import com.example.pedromdev.appalunos.validator.AlunoValidator;

/**
 * Created by pedromdev on 17/05/17.
 */

public class FormDadosAlunoActivity extends AppCompatActivity {

    private Button btnSalvar;

    private FormularioHelper helper;

    private AlunoDAO dao;

    private Aluno alunoSelecionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_dados_aluno);

        helper = new FormularioHelper(this);
        dao = new AlunoDAO(this);
        btnSalvar = (Button) findViewById(R.id.btnCadastrar);

        Long matricula = getIntent().getLongExtra("matricula_aluno", 0L);

        if (matricula > 0) {
            alunoSelecionado = dao.recuperar(matricula);
            helper.setDadosDoAluno(alunoSelecionado);
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlunoValidator validator = new AlunoValidator(FormDadosAlunoActivity.this);

                if (!validator.isValid()) {
                    String str = "Alguns campos estao invalidos:";
                    for (String mensagem : validator.getMessages()) {
                        str += "\n - " + mensagem;
                    }
                    Toast.makeText(FormDadosAlunoActivity.this, str, Toast.LENGTH_LONG).show();
                    return;
                }

                Aluno aluno = helper.getAluno();

                if (alunoSelecionado == null) {
                    dao.cadastrar(aluno);
                } else {
                    dao.atualizar(aluno);
                }
                dao.close();

                Toast.makeText(
                    FormDadosAlunoActivity.this,
                    "Aluno cadastrado com sucesso",
                    Toast.LENGTH_SHORT
                ).show();

                finish();
            }
        });
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
