package com.example.bikeibmec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class ConfirmarDadosPessoaisActivity extends BaseActivity {

    private TextInputLayout cadastroClientesConfirmacaoMatricula;
    private TextInputLayout cadastroClientesConfirmacaoNome;
    private TextInputLayout cadastroClientesConfirmacaoSobrenome;
    private TextInputLayout cadastroClientesConfirmacaoSexo;
    private TextInputLayout cadastroClientesConfirmacaoCurso;
    private TextInputLayout cadastroClientesConfirmacaoCelular;
    private TextInputLayout cadastroClientesConfirmacaoEmail;
    private TextInputLayout cadastroClientesConfirmacaoCartaoBandeira;
    private TextInputLayout cadastroClientesConfirmacaoCartaoNumero;
    private TextInputLayout cadastroClientesConfirmacaoCartaoTitular;
    private TextInputLayout cadastroClientesConfirmacaoCartaoValidade;
    private TextInputLayout cadastroClientesConfirmacaoCartaoCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_dados_pessoais);

        cadastroClientesConfirmacaoMatricula = findViewById(R.id.cadastro_clientes_confirmacao_matricula);
        cadastroClientesConfirmacaoNome = findViewById(R.id.cadastro_clientes_confirmacao_nome);
        cadastroClientesConfirmacaoSobrenome = findViewById(R.id.cadastro_clientes_confirmacao_sobrenome);
        cadastroClientesConfirmacaoSexo = findViewById(R.id.cadastro_clientes_confirmacao_sexo);
        cadastroClientesConfirmacaoCurso = findViewById(R.id.cadastro_clientes_confirmacao_curso);
        cadastroClientesConfirmacaoCelular = findViewById(R.id.cadastro_clientes_confirmacao_celular);
        cadastroClientesConfirmacaoEmail = findViewById(R.id.cadastro_clientes_confirmacao_email);
        cadastroClientesConfirmacaoCartaoBandeira = findViewById(R.id.cadastro_clientes_confirmacao_cartao_bandeira);
        cadastroClientesConfirmacaoCartaoNumero = findViewById(R.id.cadastro_clientes_confirmacao_cartao_numero);
        cadastroClientesConfirmacaoCartaoTitular = findViewById(R.id.cadastro_clientes_confirmacao_cartao_titular);
        cadastroClientesConfirmacaoCartaoValidade = findViewById(R.id.cadastro_clientes_confirmacao_cartao_validade);
        cadastroClientesConfirmacaoCartaoCv = findViewById(R.id.cadastro_clientes_confirmacao_cartao_cv);

        Intent intent = getIntent();
        ClienteModel clienteModel = intent.getExtras().getParcelable(ClienteModel.ID);

        preencheCliente(clienteModel);

        Button confirmButton = findViewById(R.id.cadastro_clientes_confirmacao_confirm);

        confirmButton.setOnClickListener(v -> {
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.putExtra(ClienteModel.ID, (Parcelable)clienteModel);
            startActivity(mainIntent);
        });

        Button cancelButton = findViewById(R.id.cadastro_clientes_confirmacao_cancel);

        cancelButton.setOnClickListener(v -> {
            Intent cadastroIntent = new Intent(this, CadastroDadosPessoaisActivity.class);
            cadastroIntent.putExtra(ClienteModel.ID, (Parcelable)clienteModel);
            startActivity(cadastroIntent);
        });

        setHomeButton();
    }

    void preencheCliente(ClienteModel clienteModel) {
        cadastroClientesConfirmacaoMatricula.getEditText().setText(clienteModel.getMatricula());
        cadastroClientesConfirmacaoNome.getEditText().setText(clienteModel.getNome());
        cadastroClientesConfirmacaoSobrenome.getEditText().setText(clienteModel.getSobrenome());
        cadastroClientesConfirmacaoSexo.getEditText().setText(clienteModel.getSexo());
        cadastroClientesConfirmacaoCurso.getEditText().setText(clienteModel.getCursos().toString());
        cadastroClientesConfirmacaoCelular.getEditText().setText(clienteModel.getCelular());
        cadastroClientesConfirmacaoEmail.getEditText().setText(clienteModel.getEmail());
        cadastroClientesConfirmacaoCartaoBandeira.getEditText().setText(clienteModel.getCartaoBandeira());
        cadastroClientesConfirmacaoCartaoNumero.getEditText().setText(clienteModel.getCartaoNumero());
        cadastroClientesConfirmacaoCartaoTitular.getEditText().setText(clienteModel.getCartaoTitular());
        cadastroClientesConfirmacaoCartaoValidade.getEditText().setText(clienteModel.getCartaoValidade());
        cadastroClientesConfirmacaoCartaoCv.getEditText().setText(clienteModel.getCartaoCv());
    }
}