package com.example.pedromdev.appalunos.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pedromdev.appalunos.FormDadosAlunoActivity;
import com.example.pedromdev.appalunos.R;
import com.example.pedromdev.appalunos.modelo.bean.Aluno;

/**
 * Created by pedromdev on 18/05/17.
 */

public class FormularioHelper {

    private ImageView foto;

    private EditText matricula;

    private EditText nome;

    private EditText curso;

    private EditText disciplina;

    private EditText telefone;

    private EditText endereco;

    private EditText site;

    private EditText email;

    private EditText av1;

    private EditText av2;

    private EditText av3;

    private Aluno aluno;

    private static final String TAG = "FORMULARIO_HELPER";

    public FormularioHelper(FormDadosAlunoActivity activity) {
        foto = (ImageView) activity.findViewById(R.id.form_foto);
        matricula = (EditText) activity.findViewById(R.id.edtMat);
        nome = (EditText) activity.findViewById(R.id.edtNome);
        curso = (EditText) activity.findViewById(R.id.edtCurso);
        disciplina = (EditText) activity.findViewById(R.id.edtDisc);
        telefone = (EditText) activity.findViewById(R.id.edtFone);
        endereco = (EditText) activity.findViewById(R.id.edtEndereco);
        site = (EditText) activity.findViewById(R.id.edtSite);
        email = (EditText) activity.findViewById(R.id.edtEmail);
        av1 = (EditText) activity.findViewById(R.id.edtAV1);
        av2 = (EditText) activity.findViewById(R.id.edtAV2);
        av3 = (EditText) activity.findViewById(R.id.edtAV3);

        aluno = new Aluno();
    }

    public Aluno getAluno() {
        aluno.setMatricula(Long.valueOf(matricula.getText().toString()));
        aluno.setNome(nome.getText().toString());
        aluno.setCurso(curso.getText().toString());
        aluno.setDisciplina(disciplina.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setAv1(Double.valueOf(av1.getText().toString()));
        aluno.setAv2(Double.valueOf(av2.getText().toString()));
        aluno.setAv3(Double.valueOf(av3.getText().toString()));

        return aluno;
    }

    public void carregaFoto(String localFoto) {
        Log.i(TAG, "Carregando foto: " + localFoto);
        Bitmap imagemFoto = BitmapFactory.decodeFile(localFoto);
        Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagemFoto, 60, 60, true);
        aluno.setFoto(localFoto);
        foto.setImageBitmap(imagemReduzida);
    }

    public void setDadosDoAluno(Aluno aluno) {
        this.aluno = aluno;
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

        if (aluno.getFoto() != null) {
            carregaFoto(aluno.getFoto());
        }

        matricula.setFocusable(false);
    }

    public ImageView getFoto() {
        return foto;
    }
}
