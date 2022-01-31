package com.prologandroid.projetocrudfuncionarios.model;

import java.io.Serializable;

public class Funcionario implements Serializable {

    private String nome;
    private String dataNascimento;
    private String telefone;
    private String email;

    public Funcionario(String nome, String dataNascimento, String telefone, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
}
