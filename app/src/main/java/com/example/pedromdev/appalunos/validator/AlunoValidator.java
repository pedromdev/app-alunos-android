package com.example.pedromdev.appalunos.validator;

import android.widget.EditText;

import com.example.pedromdev.appalunos.FormDadosAlunoActivity;
import com.example.pedromdev.appalunos.R;
import com.example.pedromdev.appalunos.modelo.bean.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedromdev on 18/05/17.
 */

public class AlunoValidator {


    private String matricula;

    private String nome;

    private String curso;

    private String disciplina;

    private String telefone;

    private String av1;

    private String av2;

    private String av3;

    private List<String> messages;

    public AlunoValidator(FormDadosAlunoActivity activity) {
        matricula = ((EditText) activity.findViewById(R.id.edtMat)).getText().toString();
        nome = ((EditText) activity.findViewById(R.id.edtNome)).getText().toString();
        curso = ((EditText) activity.findViewById(R.id.edtCurso)).getText().toString();
        disciplina = ((EditText) activity.findViewById(R.id.edtDisc)).getText().toString();
        telefone = ((EditText) activity.findViewById(R.id.edtFone)).getText().toString();
        av1 = ((EditText) activity.findViewById(R.id.edtAV1)).getText().toString();
        av2 = ((EditText) activity.findViewById(R.id.edtAV2)).getText().toString();
        av3 = ((EditText) activity.findViewById(R.id.edtAV3)).getText().toString();
    }

    public Boolean isValid() {
        Boolean valid = true;
        messages = new ArrayList<>();

        try {
            if (matricula == null || matricula.length() == 0) {
                valid = false;
                messages.add("A matrícula é obrigatória");
            } else if (Long.valueOf(matricula) <= 0L) {
                valid = false;
                messages.add("A matrícula deve ser um número maior que zero");
            }
        } catch (NumberFormatException e) {
            valid = false;
            messages.add("O formato do valor da matrícula está inválido");
        }

        if (nome == null || nome.length() == 0) {
            valid = false;
            messages.add("O nome é obrigatório");
        }

        if (curso == null || curso.length() == 0) {
            valid = false;
            messages.add("O curso é obrigatório");
        }

        if (disciplina == null || disciplina.length() == 0) {
            valid = false;
            messages.add("A disciplina é obrigatória");
        }

        if (telefone == null || telefone.length() == 0) {
            valid = false;
            messages.add("O telefone é obrigatório");
        }

        try {
            if (av1 == null || av1.length() == 0) {
                valid = false;
                messages.add("A AV1 é obrigatória");
            } else if (Double.valueOf(av1) < 0 || Double.valueOf(av1) > 10) {
                valid = false;
                messages.add("A AV1 deve ser uma nota entre 0 e 10");
            }
        } catch (NumberFormatException e) {
            valid = false;
            messages.add("O formato do valor da AV1 está inválido");
        }

        try {
            if (av2 == null || av2.length() == 0) {
                valid = false;
                messages.add("A AV2 é obrigatória");
            } else if (Double.valueOf(av2) < 0 || Double.valueOf(av2) > 10) {
                valid = false;
                messages.add("A AV2 deve ser uma nota entre 0 e 10");
            }
        } catch (NumberFormatException e) {
            valid = false;
            messages.add("O formato do valor da AV2 está inválido");
        }

        try {
            if (av3 == null || av3.length() == 0) {
                valid = false;
                messages.add("A AV3 é obrigatória");
            } else if (Double.valueOf(av3) < 0 || Double.valueOf(av3) > 10) {
                valid = false;
                messages.add("A AV3 deve ser uma nota entre 0 e 10");
            }
        } catch (NumberFormatException e) {
            valid = false;
            messages.add("O formato do valor da AV3 está inválido");
        }

        return valid;
    }

    public List<String> getMessages() {
        return messages;
    }
}
