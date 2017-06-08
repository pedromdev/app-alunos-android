package com.example.pedromdev.appalunos.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pedromdev.appalunos.InfoAlunoActivity;
import com.example.pedromdev.appalunos.R;
import com.example.pedromdev.appalunos.modelo.bean.Aluno;

/**
 * Created by pedromdev on 18/05/17.
 */

public class InfoHelper {

    private ImageView foto;

    private TextView matricula;

    private TextView nome;

    private TextView curso;

    private TextView disciplina;

    private TextView telefone;

    private TextView endereco;

    private TextView site;

    private TextView email;

    private TextView av1;

    private TextView av2;

    private TextView av3;

    private TextView media;

    private TextView situacao;

    private static final String TAG = "INFO_HELPER";

    public InfoHelper(InfoAlunoActivity activity) {
        foto = (ImageView) activity.findViewById(R.id.foto);
        matricula = (TextView) activity.findViewById(R.id.R_matricula);
        nome = (TextView) activity.findViewById(R.id.R_nome);
        curso = (TextView) activity.findViewById(R.id.R_curso);
        disciplina = (TextView) activity.findViewById(R.id.R_disciplina);
        telefone = (TextView) activity.findViewById(R.id.R_telefone);
        endereco = (TextView) activity.findViewById(R.id.R_endereco);
        site = (TextView) activity.findViewById(R.id.R_site);
        email = (TextView) activity.findViewById(R.id.R_email);
        av1 = (TextView) activity.findViewById(R.id.R_av1);
        av2 = (TextView) activity.findViewById(R.id.R_av2);
        av3 = (TextView) activity.findViewById(R.id.R_av3);
        media = (TextView) activity.findViewById(R.id.R_media);
        situacao = (TextView) activity.findViewById(R.id.R_situacao);
        foto = (ImageView) activity.findViewById(R.id.foto);
    }

    public void setDadosDoAluno(Aluno aluno) {
        matricula.setText(aluno.getMatricula().toString());
        nome.setText(aluno.getNome());
        curso.setText(aluno.getCurso());
        disciplina.setText(aluno.getDisciplina());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        email.setText(aluno.getEmail());
        av1.setText(aluno.getAv1().toString());
        av2.setText(aluno.getAv2().toString());
        av3.setText(aluno.getAv3().toString());
        media.setText(aluno.getMedia().toString());
        situacao.setText(aluno.aprovado() ? "Aprovado" : "Reprovado");

        if (aluno.getFoto() != null) {
            carregaFoto(aluno);
        }
    }

    public void carregaFoto(Aluno aluno) {
        Log.i(TAG, "Carregando foto: " + aluno.getFoto());
        Bitmap imagemFoto = BitmapFactory.decodeFile(aluno.getFoto());
        Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagemFoto, 60, 60, true);
        foto.setImageBitmap(imagemReduzida);
    }
}
