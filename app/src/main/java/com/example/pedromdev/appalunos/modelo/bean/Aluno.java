package com.example.pedromdev.appalunos.modelo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pedromdev on 18/05/17.
 */

public class Aluno implements Serializable {

    private Long matricula;

    private String nome;

    private String curso;

    private String disciplina;

    private String telefone;

    private String endereco;

    private String site;

    private String email;

    private String foto;

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

    public String getEndereco() {
        return endereco;
    }

    public Aluno setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public String getSite() {
        return site;
    }

    public Aluno setSite(String site) {
        this.site = site;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Aluno setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFoto() {
        return foto;
    }

    public Aluno setFoto(String foto) {
        this.foto = foto;
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
