package com.prologandroid.projetocrudfuncionarios.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prologandroid.projetocrudfuncionarios.R;
import com.prologandroid.projetocrudfuncionarios.model.Funcionario;
import com.prologandroid.projetocrudfuncionarios.recyclerview.adapter.listener.OnItemClickListener;

import java.util.List;

public class ListaFuncionarioAdapter extends RecyclerView.Adapter<ListaFuncionarioAdapter.FuncionarioViewHolder> {

    private final List<Funcionario> funcionarios;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public ListaFuncionarioAdapter(List<Funcionario> funcionarios, Context context) {
        this.funcionarios = funcionarios;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ListaFuncionarioAdapter.FuncionarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_funcionario, parent, false);
        return new FuncionarioViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(ListaFuncionarioAdapter.FuncionarioViewHolder holder, int position) {
        Funcionario funcionario = funcionarios.get(position);
        holder.vincula(funcionario);
    }

    @Override
    public int getItemCount() {
        return funcionarios.size();
    }

    public void adiciona(Funcionario funcionario) {
        funcionarios.add(funcionario);
        notifyDataSetChanged();
    }

    public void edita(int posicao, Funcionario funcionario) {
        funcionarios.set(posicao, funcionario);
        notifyDataSetChanged();
    }

    public void remove(int posicao) {
        funcionarios.remove(posicao);
        notifyItemRemoved(posicao);
    }

    class FuncionarioViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;
        private final TextView dataNascimento;
        private final TextView telefone;
        private final TextView email;
        private Funcionario funcionario;

        public FuncionarioViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.item_funcionario_nome);
            dataNascimento = itemView.findViewById(R.id.item_funcionario_dataNascimento);
            telefone = itemView.findViewById(R.id.item_funcionario_telefone);
            email = itemView.findViewById(R.id.item_funcionario_email);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(funcionario, getAdapterPosition());
                }
            });
        }

        public void vincula(Funcionario funcionario) {
            this.funcionario = funcionario;
            preencheCampo(funcionario);
        }

        private void preencheCampo(Funcionario funcionario) {
            nome.setText(funcionario.getNome());
            dataNascimento.setText(funcionario.getDataNascimento());
            telefone.setText(funcionario.getTelefone());
            email.setText(funcionario.getEmail());
        }
    }
}


//    public static void remove(int posicao) {
//        funcionarios.remove(posicao);
//        notifyItemRemoved(posicao);
//    }

//
//        public void remove(Funcionario posicao) {
//        funcionarios.remove(posicao);
//        notifyItemRemoved(posicao);
//    }
//
//    private void notifyItemRemoved(Funcionario posicao) {
//    }
//
//    public void confirmaRemocao(final MenuItem item) {
//        new AlertDialog
//                .Builder(context)
//                .setTitle("Removendo funcionário")
//                .setMessage("Deseja remover o funcionário permanentemente?")
//                .setPositiveButton("Sim", (dialogInterface, i) -> {
//                    AdapterView.AdapterContextMenuInfo menuInfo =
//                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//                    ListaFuncionarioAdapter adapter = null;
//                    Funcionario funcionarioEscolhido = adapter.getItem(menuInfo.position);
//                    remove(funcionarioEscolhido);
//                })
//                .setNegativeButton("Não", null)
//                .show();
//    }
//
//    private Funcionario getItem(int position) {
//        return null;
//    }
//
//    //