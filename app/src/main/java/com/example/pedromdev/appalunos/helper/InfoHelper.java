package com.example.pedromdev.appalunos.helper;

import android.widget.TextView;

import com.example.pedromdev.appalunos.InfoAlunoActivity;
import com.example.pedromdev.appalunos.R;
import com.example.pedromdev.appalunos.modelo.bean.Aluno;

/**
 * Created by pedromdev on 18/05/17.
 */

public class InfoHelper {

    private TextView matricula;

    private TextView nome;

    private TextView curso;

    private TextView disciplina;

    private TextView telefone;

    private TextView av1;

    private TextView av2;

    private TextView av3;

    private TextView media;

    private TextView situacao;

    public InfoHelper(InfoAlunoActivity activity) {
        matricula = (TextView) activity.findViewById(R.id.R_matricula);
        nome = (TextView) activity.findViewById(R.id.R_nome);
        curso = (TextView) activity.findViewById(R.id.R_curso);
        disciplina = (TextView) activity.findViewById(R.id.R_disciplina);
        telefone = (TextView) activity.findViewById(R.id.R_telefone);
        av1 = (TextView) activity.findViewById(R.id.R_av1);
        av2 = (TextView) activity.findViewById(R.id.R_av2);
        av3 = (TextView) activity.findViewById(R.id.R_av3);
        media = (TextView) activity.findViewById(R.id.R_media);
        situacao = (TextView) activity.findViewById(R.id.R_situacao);
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
        media.setText(aluno.getMedia().toString());
        situacao.setText(aluno.aprovado() ? "Aprovado" : "Reprovado");
    }
}
