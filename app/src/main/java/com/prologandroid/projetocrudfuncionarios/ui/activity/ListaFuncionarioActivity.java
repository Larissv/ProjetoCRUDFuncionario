package com.prologandroid.projetocrudfuncionarios.ui.activity;

import static com.prologandroid.projetocrudfuncionarios.ui.activity.FuncionarioActivityConstantes.CHAVE_FUNCIONARIO;
import static com.prologandroid.projetocrudfuncionarios.ui.activity.FuncionarioActivityConstantes.CHAVE_POSICAO;
import static com.prologandroid.projetocrudfuncionarios.ui.activity.FuncionarioActivityConstantes.CODIGO_REQUISICAO_CADASTRA_FUNCIONARIO;
import static com.prologandroid.projetocrudfuncionarios.ui.activity.FuncionarioActivityConstantes.CODIGO_REQUISICAO_EDITA_FUNCIONARIO;
import static com.prologandroid.projetocrudfuncionarios.ui.activity.FuncionarioActivityConstantes.POSICAO_INVALIDA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.prologandroid.projetocrudfuncionarios.R;
import com.prologandroid.projetocrudfuncionarios.dao.dao.FuncionarioDAO;
import com.prologandroid.projetocrudfuncionarios.model.Funcionario;
import com.prologandroid.projetocrudfuncionarios.recyclerview.adapter.ListaFuncionarioAdapter;
import com.prologandroid.projetocrudfuncionarios.recyclerview.adapter.listener.OnItemClickListener;

import java.util.List;

public class ListaFuncionarioActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Funcionários";
    private ListaFuncionarioAdapter listaFuncionarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_funcionarios);

        setTitle(TITULO_APPBAR);

        List<Funcionario> todosFuncionarios = pegaTodosFuncionarios();
        configuraRecyclerView(todosFuncionarios);
        configuraBotaoAdicionaFuncionario();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.menu_lista_funcionarios, menu);
    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//
//        int itemId = item.getItemId();
//        if (itemId == R.id.menu_lista_funcionario_menu_remover) {
//            listaFuncionarioAdapter.confirmaRemocao(item);
//        }
//
//        return super.onContextItemSelected(item);
//    }
//
//    private void configuraBotaoAdicionaFuncionario() {
//        FloatingActionButton botaoCadastraFuncionario = findViewById(
//                R.id.lista_funcionarios_cadastra_funcionario);
//        botaoCadastraFuncionario.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                vaiParaFormularioFuncionarioActivityInsere();
//            }
//        });
//    }

    private void configuraBotaoAdicionaFuncionario() {
        TextView botaoAdicionaFuncionario = findViewById(R.id.lista_funcionarios_cadastra_funcionario);
        botaoAdicionaFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaFormularioFuncionarioActivityInsere();
            }
        });
    }

    private void vaiParaFormularioFuncionarioActivityInsere() {
        Intent iniciaFormularioFuncionario =
                new Intent(ListaFuncionarioActivity.this,
                        FormularioFuncionarioActivity.class);
        startActivityForResult(iniciaFormularioFuncionario,
                CODIGO_REQUISICAO_CADASTRA_FUNCIONARIO);
    }

    private List<Funcionario> pegaTodosFuncionarios() {
        FuncionarioDAO dao = new FuncionarioDAO();
        return dao.todosFuncionarios();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (validaResultadoAdicionaFuncionario(requestCode, data)) {

            if (resultadoOk(resultCode)) {
                Funcionario funcionarioRecebido = (Funcionario) data.getSerializableExtra(CHAVE_FUNCIONARIO);
                cadastra(funcionarioRecebido);
            }
        }

        if (validaResultadoAlteraFuncionario(requestCode, data)) {
            if (resultadoOk(resultCode)) {
                Funcionario funcionarioRecebido = (Funcionario) data.getSerializableExtra(CHAVE_FUNCIONARIO);
                int posicaoRecebida = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
                if (validaPosicao(posicaoRecebida)) {
                    edita(funcionarioRecebido, posicaoRecebida);
                }
            }
        }
    }

    private void edita(Funcionario funcionario, int posicao) {
        new FuncionarioDAO().editaFuncionario(posicao, funcionario);
        listaFuncionarioAdapter.edita(posicao, funcionario);
    }


    private boolean validaPosicao(int posicaoRecebida) {
        return posicaoRecebida > POSICAO_INVALIDA;
    }

    private boolean validaResultadoAlteraFuncionario(int requestCode, Intent data) {
        return validaCodigoRequisicaoEditaFuncionario(requestCode) &&
                temFuncionario(data);
    }

    private boolean validaCodigoRequisicaoEditaFuncionario(int requestCode) {
        return requestCode == CODIGO_REQUISICAO_EDITA_FUNCIONARIO;
    }

    private void cadastra(Funcionario funcionario) {
        new FuncionarioDAO().cadastraFuncionario(funcionario);
        listaFuncionarioAdapter.adiciona(funcionario);
    }

    public void remove(int funcionario) {
        new FuncionarioDAO().removeFuncionario(funcionario);
        listaFuncionarioAdapter.remove(funcionario);
    }

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

    private boolean validaResultadoAdicionaFuncionario(int requestCode, Intent data) {
        return validaCodigoRequisicaoCadastraFuncionario(requestCode) &&
                temFuncionario(data);
    }

    private boolean temFuncionario(Intent data) {
        return data != null && data.hasExtra(CHAVE_FUNCIONARIO);
    }

    private boolean resultadoOk(int resultCode) {
        return resultCode == Activity.RESULT_OK;
    }

    private boolean validaCodigoRequisicaoCadastraFuncionario(int requestCode) {
        return requestCode == CODIGO_REQUISICAO_CADASTRA_FUNCIONARIO;
    }

    private void configuraRecyclerView(List<Funcionario> todosFuncionarios) {
        RecyclerView listaFuncionarios = findViewById(R.id.lista_funcionarios_recyclerview);
        configuraAdapter(todosFuncionarios, listaFuncionarios);
    }

    private void configuraAdapter(List<Funcionario> todosFuncionarios, RecyclerView listaFuncionarios) {
        listaFuncionarioAdapter = new ListaFuncionarioAdapter(todosFuncionarios, this);
        listaFuncionarios.setAdapter(listaFuncionarioAdapter);
        listaFuncionarioAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Funcionario funcionario, int posicao) {
                vaiParaFormularioFuncionarioActivityEdita(funcionario, posicao);
            }
        });
    }

    private void vaiParaFormularioFuncionarioActivityEdita(Funcionario funcionario, int posicao) {
        Intent abreFormularioComFuncionario = new Intent(ListaFuncionarioActivity.this,
                FormularioFuncionarioActivity.class);
        abreFormularioComFuncionario.putExtra(CHAVE_FUNCIONARIO, funcionario);
        abreFormularioComFuncionario.putExtra(CHAVE_POSICAO, posicao);
        startActivityForResult(abreFormularioComFuncionario, CODIGO_REQUISICAO_EDITA_FUNCIONARIO);
    }
}
