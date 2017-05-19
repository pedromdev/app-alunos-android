package com.example.pedromdev.appalunos.helper;

import android.widget.EditText;

import com.example.pedromdev.appalunos.FormDadosAlunoActivity;
import com.example.pedromdev.appalunos.R;
import com.example.pedromdev.appalunos.modelo.bean.Aluno;

/**
 * Created by pedromdev on 18/05/17.
 */

public class FormularioHelper {

    private EditText matricula;

    private EditText nome;

    private EditText curso;

    private EditText disciplina;

    private EditText telefone;

    private EditText av1;

    private EditText av2;

    private EditText av3;

    public FormularioHelper(FormDadosAlunoActivity activity) {
        matricula = (EditText) activity.findViewById(R.id.edtMat);
        nome = (EditText) activity.findViewById(R.id.edtNome);
        curso = (EditText) activity.findViewById(R.id.edtCurso);
        disciplina = (EditText) activity.findViewById(R.id.edtDisc);
        telefone = (EditText) activity.findViewById(R.id.edtFone);
        av1 = (EditText) activity.findViewById(R.id.edtAV1);
        av2 = (EditText) activity.findViewById(R.id.edtAV2);
        av3 = (EditText) activity.findViewById(R.id.edtAV3);
    }

    public Aluno getAluno() {
        Aluno aluno = new Aluno();

        aluno.setMatricula(Long.valueOf(matricula.getText().toString()));
        aluno.setNome(nome.getText().toString());
        aluno.setCurso(curso.getText().toString());
        aluno.setDisciplina(disciplina.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setAv1(Double.valueOf(av1.getText().toString()));
        aluno.setAv2(Double.valueOf(av2.getText().toString()));
        aluno.setAv3(Double.valueOf(av3.getText().toString()));

        return aluno;
    }

    public void setDadosDoAluno(Aluno aluno) {
        matricula.setText(aluno.getMatricula().toString());
        nome.setText(aluno.getNome());
        curso.setText(aluno.getCurso());
        disciplina.setText(aluno.getDisciplina());
        telefone.setText(aluno.getTelefone());
        av1.setText(aluno.getAv1().toString());
        av2.setText(aluno.getAv2().toString());
        av3.setText(aluno.getAv3().toString());

        matricula.setFocusable(false);
    }
}
