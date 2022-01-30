package com.prologandroid.projetocrudfuncionarios.dao.dao;

import com.prologandroid.projetocrudfuncionarios.model.Funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FuncionarioDAO {

    private final static ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public List<Funcionario> todosFuncionarios() {
        return (List<Funcionario>) funcionarios.clone();
    }

    public void cadastraFuncionario(Funcionario... funcionarios) {
        FuncionarioDAO.funcionarios.addAll(Arrays.asList(funcionarios));
    }

    public void editaFuncionario(int posicao, Funcionario funcionario) {
        funcionarios.set(posicao, funcionario);
    }

    public void removeFuncionario(int posicao) {
        funcionarios.remove(posicao);
    }

}
