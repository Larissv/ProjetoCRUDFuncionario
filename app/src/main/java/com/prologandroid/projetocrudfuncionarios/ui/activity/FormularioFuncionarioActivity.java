package com.prologandroid.projetocrudfuncionarios.ui.activity;

import static com.prologandroid.projetocrudfuncionarios.ui.activity.FuncionarioActivityConstantes.CHAVE_FUNCIONARIO;
import static com.prologandroid.projetocrudfuncionarios.ui.activity.FuncionarioActivityConstantes.CHAVE_POSICAO;
import static com.prologandroid.projetocrudfuncionarios.ui.activity.FuncionarioActivityConstantes.POSICAO_INVALIDA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.prologandroid.projetocrudfuncionarios.R;
import com.prologandroid.projetocrudfuncionarios.dao.dao.FuncionarioDAO;
import com.prologandroid.projetocrudfuncionarios.model.Funcionario;

public class FormularioFuncionarioActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_CADASTRA = "Cadastra funcionário";
    public static final String TITULO_APPBAR_EDITA = "Edita funcionário";
    private int posicaoRecebida = POSICAO_INVALIDA;
    private TextView nome;
    private TextView dataNascimento;
    private TextView telefone;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_funcionario);

        setTitle(TITULO_APPBAR_CADASTRA);
        inicializaCampos();

        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra(CHAVE_FUNCIONARIO)){
            setTitle(TITULO_APPBAR_EDITA);
            Funcionario funcionarioRecebido = (Funcionario) dadosRecebidos
                    .getSerializableExtra(CHAVE_FUNCIONARIO);
            posicaoRecebida = dadosRecebidos.getIntExtra(CHAVE_FUNCIONARIO, POSICAO_INVALIDA);
            preencheCampos(funcionarioRecebido);
        }
    }

    private void preencheCampos(Funcionario funcionarioRecebido) {
        nome.setText(funcionarioRecebido.getNome());
        dataNascimento.setText(funcionarioRecebido.getDataNascimento());
        telefone.setText(funcionarioRecebido.getTelefone());
        email.setText(funcionarioRecebido.getEmail());
    }

    private void inicializaCampos() {
        nome = findViewById(R.id.formulario_funcionario_nome);
        dataNascimento = findViewById(R.id.formulario_funcionario_dataNascimento);
        telefone = findViewById(R.id.formulario_funcionario_telefone);
        email = findViewById(R.id.formulario_funcionario_email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_funcionario_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(validaMenuSalvaFuncionario(item)){
            Funcionario funcionarioCriado = criaFuncionario();
            retornaFuncionario(funcionarioCriado);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaFuncionario(Funcionario funcionario) {
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CHAVE_FUNCIONARIO, funcionario);
        resultadoInsercao.putExtra(CHAVE_POSICAO, posicaoRecebida);
        setResult(Activity.RESULT_OK,resultadoInsercao);
    }

    @NonNull
    private Funcionario criaFuncionario() {
        return new Funcionario(nome.getText().toString(),
                dataNascimento.getText().toString(),
                telefone.getText().toString(),
                email.getText().toString());
    }

    private boolean validaMenuSalvaFuncionario(MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_funcionario_ic_salva;
    }
}
