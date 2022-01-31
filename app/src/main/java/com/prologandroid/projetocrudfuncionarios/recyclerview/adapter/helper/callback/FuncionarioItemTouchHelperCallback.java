package com.prologandroid.projetocrudfuncionarios.recyclerview.adapter.helper.callback;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.prologandroid.projetocrudfuncionarios.dao.dao.FuncionarioDAO;
import com.prologandroid.projetocrudfuncionarios.recyclerview.adapter.ListaFuncionarioAdapter;

public class FuncionarioItemTouchHelperCallback extends ItemTouchHelper.Callback{

    private final ListaFuncionarioAdapter adapter;

    public FuncionarioItemTouchHelperCallback(ListaFuncionarioAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int marcacoesDeArrastar = ItemTouchHelper.DOWN | ItemTouchHelper.UP
                | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        return makeMovementFlags(marcacoesDeArrastar, marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int posicaoDoFuncionarioDeslizado = viewHolder.getAdapterPosition();
        removeFuncionario(posicaoDoFuncionarioDeslizado);
    }

    private void removeFuncionario(int posicao) {
        new FuncionarioDAO().removeFuncionario(posicao);
        adapter.remove(posicao);
    } }


