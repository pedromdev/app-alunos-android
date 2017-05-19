package com.example.pedromdev.appalunos.modelo.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pedromdev on 18/05/17.
 */

public class Aluno {

    private Long matricula;

    private String nome;

    private String curso;

    private String disciplina;

    private String telefone;

    private Double av1;

    private Double av2;

    private Double av3;

    public Long getMatricula() {
        return matricula;
    }

    public Aluno setMatricula(Long matricula) {
        this.matricula = matricula;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Aluno setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCurso() {
        return curso;
    }

    public Aluno setCurso(String curso) {
        this.curso = curso;
        return this;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public Aluno setDisciplina(String disciplina) {
        this.disciplina = disciplina;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Aluno setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public Double getAv1() {
        return av1;
    }

    public Aluno setAv1(Double av1) {
        this.av1 = av1;
        return this;
    }

    public Double getAv2() {
        return av2;
    }

    public Aluno setAv2(Double av2) {
        this.av2 = av2;
        return this;
    }

    public Double getAv3() {
        return av3;
    }

    public Aluno setAv3(Double av3) {
        this.av3 = av3;
        return this;
    }

    public Double getMedia() {
        List<Double> notas = new ArrayList<>();

        notas.add(av1);
        notas.add(av2);
        notas.add(av3);

        Collections.sort(notas, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        });

        return (notas.get(1) + notas.get(2)) / 2.0;
    }

    public boolean aprovado() {
        return getMedia() >= 6;
    }

    @Override
    public String toString() {
        return this.nome + " - " + (aprovado() ? "Aprovado" : "Reprovado");
    }
}
