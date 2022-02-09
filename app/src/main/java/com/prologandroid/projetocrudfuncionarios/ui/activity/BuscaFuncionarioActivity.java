package com.prologandroid.projetocrudfuncionarios.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.prologandroid.projetocrudfuncionarios.R;

public class BuscaFuncionarioActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_MOSTRA = "Mostrando dados do funcionário";
    private EditText nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_funcionario);
        
        setTitle(TITULO_APPBAR_MOSTRA);
        inicializaCampos();
        configuraBotaoBuscarFuncionario();

    }

    private void inicializaCampos() {
            nome = findViewById(R.id.formulario_funcionario_nome);
    }

    private void configuraBotaoBuscarFuncionario() {
        final EditText nome = findViewById(R.id.campo_busca_funcionario_nome);
        Button botaoBuscaFuncionario = findViewById(R.id.botao_busca_funcionario);
        botaoBuscaFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // aqui utilizar o retrofit
            }
        });
    }
}