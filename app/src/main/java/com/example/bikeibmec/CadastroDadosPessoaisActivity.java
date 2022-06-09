package com.example.bikeibmec;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputLayout;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CadastroDadosPessoaisActivity extends BaseActivity {

    private TextInputLayout  cadastroClientesMatricula;
    private TextInputLayout  cadastroClientesNome;
    private TextInputLayout  cadastroClientesSobrenome;
    private TextInputLayout  cadastroClientesCelular;
    private TextInputLayout  cadastroClientesEmail;
    private TextInputLayout  cadastroClientesCartaoNumero;
    private TextInputLayout  cadastroClientesCartaoTitular;
    private TextInputLayout  cadastroClientesCartaoValidade;
    private TextInputLayout  cadastroClientesCartaoCv;
    private RadioGroup       cadastroClientesSexo;
    private RadioButton      cadastroClientesSexoMasculino;
    private RadioButton      cadastroClientesSexoFeminino;
    private RadioButton      cadastroClientesCartaoBandeiraElo;
    private RadioButton      cadastroClientesCartaoBandeiraMastercard;
    private RadioButton      cadastroClientesCartaoBandeiraVisa;
    private RadioGroup       cadastroClientesCartaoBandeira;
    private MaterialCheckBox cadastroClientesCursoEngComp;
    private MaterialCheckBox cadastroClientesCursoEngCiv;
    private MaterialCheckBox cadastroClientesCursoEngProd;
    private MaterialCheckBox cadastroClientesCursoEngMec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_dados_pessoais);

        Button submitButton = findViewById(R.id.cadastro_clientes_submit);

        cadastroClientesMatricula = findViewById(R.id.cadastro_clientes_matricula);
        cadastroClientesNome = findViewById(R.id.cadastro_clientes_nome);
        cadastroClientesSobrenome = findViewById(R.id.cadastro_clientes_sobrenome);
        cadastroClientesCelular = findViewById(R.id.cadastro_clientes_celular);
        cadastroClientesEmail = findViewById(R.id.cadastro_clientes_email);
        cadastroClientesCartaoNumero = findViewById(R.id.cadastro_clientes_cartao_numero);
        cadastroClientesCartaoTitular = findViewById(R.id.cadastro_clientes_cartao_titular);
        cadastroClientesCartaoValidade = findViewById(R.id.cadastro_clientes_cartao_validade);
        cadastroClientesCartaoCv = findViewById(R.id.cadastro_clientes_cartao_cv);
        cadastroClientesSexo = findViewById(R.id.cadastro_clientes_sexo);
        cadastroClientesCartaoBandeira = findViewById(R.id.cadastro_clientes_cartao_bandeira);
        cadastroClientesCursoEngComp = findViewById(R.id.cadastro_clientes_curso_eng_comp);
        cadastroClientesCursoEngCiv = findViewById(R.id.cadastro_clientes_curso_eng_civ);
        cadastroClientesCursoEngProd = findViewById(R.id.cadastro_clientes_curso_eng_prod);
        cadastroClientesCursoEngMec = findViewById(R.id.cadastro_clientes_curso_eng_mec);
        cadastroClientesSexoMasculino = findViewById(R.id.cadastro_clientes_sexo_masculino);
        cadastroClientesSexoFeminino = findViewById(R.id.cadastro_clientes_sexo_feminino);
        cadastroClientesCartaoBandeiraElo = findViewById(R.id.cadastro_clientes_cartao_bandeira_elo);
        cadastroClientesCartaoBandeiraMastercard = findViewById(R.id.cadastro_clientes_cartao_bandeira_mastercard);
        cadastroClientesCartaoBandeiraVisa = findViewById(R.id.cadastro_clientes_cartao_bandeira_visa);

        submitButton.setOnClickListener(v -> {
            if(!validaCliente()) {
                takeAction();
                return;
            }

            Intent intent = new Intent(this, ConfirmarDadosPessoaisActivity.class);

            ClienteModel clienteModel = criaClienteModel();

            intent.putExtra(ClienteModel.ID, (Parcelable)clienteModel);

            startActivity(intent);
        });

        Intent intent = getIntent();
        Bundle intentExtras = intent.getExtras();

        if (intentExtras != null) {
            ClienteModel clienteModel = intent.getExtras().getParcelable(ClienteModel.ID);
            preencheCliente(clienteModel);
        }

        addListeners();

        setHomeButton();
    }

    void preencheCliente(@NonNull ClienteModel clienteModel) {

        cadastroClientesMatricula.getEditText()
                .setText(clienteModel.getMatricula());

        cadastroClientesNome.getEditText()
                .setText(clienteModel.getNome());

        cadastroClientesSobrenome.getEditText()
                .setText(clienteModel.getSobrenome());

        if(cadastroClientesSexoMasculino.getText().toString().equals(clienteModel.getSexo()))
            cadastroClientesSexoMasculino.setChecked(true);
        else
            cadastroClientesSexoFeminino.setChecked(true);

        for(String curso:clienteModel.getCursos()){
            if(curso.equals(cadastroClientesCursoEngComp.getText().toString()))
                cadastroClientesCursoEngComp.setChecked(true);
            else if(curso.equals(cadastroClientesCursoEngCiv.getText().toString()))
                cadastroClientesCursoEngCiv.setChecked(true);
            else if(curso.equals(cadastroClientesCursoEngProd.getText().toString()))
                cadastroClientesCursoEngProd.setChecked(true);
            else if(curso.equals(cadastroClientesCursoEngMec.getText().toString()))
                cadastroClientesCursoEngMec.setChecked(true);
        }

        cadastroClientesCelular.getEditText()
                .setText(clienteModel.getCelular());

        cadastroClientesEmail.getEditText()
                .setText(clienteModel.getEmail());

        String cartaoBandeira = clienteModel.getCartaoBandeira();
        if(cartaoBandeira.equals(cadastroClientesCartaoBandeiraElo.getText().toString()))
            cadastroClientesCartaoBandeiraElo.setChecked(true);
        if(cartaoBandeira.equals(cadastroClientesCartaoBandeiraMastercard.getText().toString()))
            cadastroClientesCartaoBandeiraMastercard.setChecked(true);
        if(cartaoBandeira.equals(cadastroClientesCartaoBandeiraVisa.getText().toString()))
            cadastroClientesCartaoBandeiraVisa.setChecked(true);

        cadastroClientesCartaoNumero.getEditText()
                .setText(clienteModel.getCartaoNumero());

        cadastroClientesCartaoTitular.getEditText()
                .setText(clienteModel.getCartaoTitular());

        cadastroClientesCartaoValidade.getEditText()
                .setText(clienteModel.getCartaoValidade());

        cadastroClientesCartaoCv.getEditText()
                .setText(clienteModel.getCartaoCv());

    }

    void addListeners() {

        cadastroClientesMatricula.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validaMatricula();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        cadastroClientesNome.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validaNome();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        cadastroClientesSobrenome.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validaSobrenome();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        cadastroClientesCelular.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validaCelular();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        cadastroClientesEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validaEmail();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        cadastroClientesCartaoNumero.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validaCartaoNumero();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        cadastroClientesCartaoTitular.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validaCartaoTitular();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        cadastroClientesCartaoValidade.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validaValidade();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        cadastroClientesCartaoCv.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validaCv();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }

    ClienteModel criaClienteModel() {

        String sexo = null;
        if(cadastroClientesSexo.getCheckedRadioButtonId() != -1) {
            MaterialRadioButton mrbSexo = findViewById(cadastroClientesSexo.getCheckedRadioButtonId());
            sexo = mrbSexo.getText().toString();
        } else {
            sexo = "";
        }

        String bandeira = null;
        if(cadastroClientesCartaoBandeira.getCheckedRadioButtonId() != -1) {
            MaterialRadioButton mrbBandeira = findViewById(cadastroClientesCartaoBandeira.getCheckedRadioButtonId());
            bandeira = mrbBandeira.getText().toString();
        } else {
            bandeira = "";
        }

        List<String> cursos = new ArrayList<>();
        if(cadastroClientesCursoEngComp.isChecked())
            cursos.add(cadastroClientesCursoEngComp.getText().toString());
        if(cadastroClientesCursoEngCiv.isChecked())
            cursos.add(cadastroClientesCursoEngCiv.getText().toString());
        if(cadastroClientesCursoEngProd.isChecked())
            cursos.add(cadastroClientesCursoEngProd.getText().toString());
        if(cadastroClientesCursoEngMec.isChecked())
            cursos.add(cadastroClientesCursoEngMec.getText().toString());

        ClienteModel clienteModel = new ClienteModel(
                cadastroClientesMatricula.getEditText() == null ? "" : String.valueOf(cadastroClientesMatricula.getEditText().getText()),
                cadastroClientesNome.getEditText() == null ? "" : String.valueOf(cadastroClientesNome.getEditText().getText()),
                cadastroClientesSobrenome.getEditText() == null ? "" : String.valueOf(cadastroClientesSobrenome.getEditText().getText()),
                sexo,
                cursos,
                cadastroClientesCelular.getEditText() == null ? "" : String.valueOf(cadastroClientesCelular.getEditText().getText()),
                cadastroClientesEmail.getEditText() == null ? "" : String.valueOf(cadastroClientesEmail.getEditText().getText()),
                bandeira,
                cadastroClientesCartaoNumero.getEditText() == null ? "" : String.valueOf(cadastroClientesCartaoNumero.getEditText().getText()),
                cadastroClientesCartaoTitular.getEditText() == null ? "" : String.valueOf(cadastroClientesCartaoTitular.getEditText().getText()),
                cadastroClientesCartaoValidade.getEditText() == null ? "" : String.valueOf(cadastroClientesCartaoValidade.getEditText().getText()),
                cadastroClientesCartaoCv.getEditText() == null ? "" : String.valueOf(cadastroClientesCartaoCv.getEditText().getText())
        );



        return clienteModel;

    }

    boolean validaCliente() {

        if(!validaMatricula())
            return false;

        if(!validaNome())
            return false;

        if(!validaSobrenome())
            return false;

        if(!validaSexo())
            return false;

        if(!validaCurso())
            return false;

        if(!validaCelular())
            return false;

        if(!validaEmail())
            return false;

        if(!validaCartaoBandeira())
            return false;

        if(!validaCartaoNumero())
            return false;

        if(!validaCartaoTitular())
            return false;

        if(!validaValidade())
            return false;

        if(!validaCv())
            return false;

        return true;
    }

    boolean validaLength(@NonNull TextInputLayout in, int min_length, int max_length) {

        String s = in.getEditText() == null ? "" : String.valueOf(in.getEditText().getText());

        if(s.length() == 0) {

            in.requestFocus();
            in.setError("Campo nao deve ser vazio.");

            return false;
        }

        if(min_length == max_length) {

            if(s.length() != min_length) {

                in.requestFocus();
                in.setError("Campo deve ter " + min_length +" caracteres.");

                return false;
            }

        } else {

            if(s.length() < min_length
                    || s.length() > max_length) {

                in.requestFocus();
                in.setError("Campo deve ter de " + min_length + " a " + max_length + " caracteres.");

                return false;
            }

        }

        in.setError(null);

        return true;
    }

    boolean validaRegex(@NonNull TextInputLayout in, String regex) {

        String s = in.getEditText() == null ? "" : String.valueOf(in.getEditText().getText());

        if( ! Pattern.compile(regex).
                matcher(s)
                .matches()
        ) {
            in.requestFocus();
            in.setError("Campo deve estar devidamente formatado.");

            return false;
        }

        in.setError(null);

        return true;
    }

    boolean validaRadioGroup(@NonNull RadioGroup radioGroup) {

        if(radioGroup.getCheckedRadioButtonId() == -1) {
            return false;
        }

        return true;
    }

    boolean validaCheckList(@NonNull List<MaterialCheckBox> checkBoxes, int min, int max) {

        int nChecked = 0;

        for(MaterialCheckBox checkBox : checkBoxes) {

            if(checkBox.isChecked()) {
                nChecked++;
            }

        }

        if(nChecked < min || nChecked > max)
            return false;

        return true;
    }

    boolean valida(@NonNull TextInputLayout in, String regex, int min_length, int max_length) {

        if(!validaLength(in, min_length, max_length))
            return false;

        if(!validaRegex(in, regex))
            return false;

        return true;
    }

    boolean validaNomeGenerico(@NonNull TextInputLayout in, int min_length, int max_length) {

        return valida(in, "^[^\\s]*(\\p{Upper}\\p{Lower}+)((\\s\\p{Lower}+)*(\\s\\p{Upper}\\p{Lower}+))*[^\\s]*$", min_length, max_length);
    }

    boolean validaMatricula() {

        boolean valid = valida(cadastroClientesMatricula,
                "^[^\\s]*\\d+[^\\s]*$", getResources().getInteger(R.integer.matricula_length_min), getResources().getInteger(R.integer.matricula_length_max));

        Log.d("Validacao", "Valida Matricula: "+valid);

        return valid;

    }

    boolean validaNome() {

        boolean valid = validaNomeGenerico(cadastroClientesNome,
                getResources().getInteger(R.integer.nome_length_min), getResources().getInteger(R.integer.nome_length_max));

        Log.d("Validacao", "Valida Nome: "+valid);

        return valid;
    }

    boolean validaSobrenome() {

        boolean valid = validaNomeGenerico(cadastroClientesSobrenome,
                getResources().getInteger(R.integer.sobrenome_length_min), getResources().getInteger(R.integer.sobrenome_length_max));

        Log.d("Validacao", "Valida Sobrenome: "+valid);

        return valid;
    }

    boolean validaSexo() {
        boolean valid = validaRadioGroup(cadastroClientesSexo);

        Log.d("Validacao", "Valida Sexo: "+valid);

        return valid;
    }

    boolean validaCurso() {
        List<MaterialCheckBox> checkBoxes = new ArrayList<MaterialCheckBox>();

        checkBoxes.add(cadastroClientesCursoEngComp);
        checkBoxes.add(cadastroClientesCursoEngCiv);
        checkBoxes.add(cadastroClientesCursoEngProd);
        checkBoxes.add(cadastroClientesCursoEngMec);

        boolean valid = validaCheckList(checkBoxes, 1, checkBoxes.size());

        Log.d("Validacao", "Valida Curso: "+valid);

        return valid;
    }

    boolean validaCelular() {

        boolean valid = valida(cadastroClientesCelular,
                "\\d+", getResources().getInteger(R.integer.celular_length_min), getResources().getInteger(R.integer.celular_length_max));

        Log.d("Validacao", "Valida Celular: "+valid);

        return valid;
    }

    boolean validaEmail() {
        boolean valid = valida(cadastroClientesEmail,
                "^[\\p{Alnum}_\\-.]+@([\\p{Alnum}_\\-]+\\.)+[\\p{Alnum}_\\-]{2,4}$",
                getResources().getInteger(R.integer.email_length_min), getResources().getInteger(R.integer.email_length_max));

        Log.d("Validacao", "Valida Email: "+valid);

        return valid;
    }

    boolean validaCartaoBandeira() {
        boolean valid = validaRadioGroup(cadastroClientesCartaoBandeira);

        Log.d("Validacao", "Valida Cartao Bandeira: "+valid);

        return valid;
    }

    boolean validaCartaoNumero() {
        boolean valid = valida(cadastroClientesCartaoNumero,"\\d+",
                getResources().getInteger(R.integer.cartao_numero_length_min), getResources().getInteger(R.integer.cartao_numero_length_max));

        Log.d("Validacao", "Valida Cartao Numero: "+valid);

        return valid;
    }

    boolean validaCartaoTitular() {
        boolean valid = validaNomeGenerico(cadastroClientesCartaoTitular,
                getResources().getInteger(R.integer.cartao_titular_length_min), getResources().getInteger(R.integer.cartao_titular_length_max));

        Log.d("Validacao", "Valida Cartao Titular: "+valid);

        return valid;
    }

    boolean validaValidade() {

        TextInputLayout in = cadastroClientesCartaoValidade;

        boolean valid = true;

        if( ! validaLength(in ,5,5) ) {

            valid = false;
        } else {

            String sDate = in.getEditText() == null ? "" : String.valueOf(in.getEditText().getText());

            valid = false;

            try {

                YearMonth.parse(sDate,
                        DateTimeFormatter.ofPattern( "MM/uu" )
                );

                valid = true;

            } catch (DateTimeParseException e) {
                e.printStackTrace();
                valid = false;
            }
        }

        if(valid) {

            in.setError(null);

        } else {

            in.requestFocus();
            in.setError("Campo deve ser valido.");

        }

        Log.d("Validacao", "Valida Cartao Validade: "+valid);

        return valid;
    }

    boolean validaCv() {
        boolean valid = valida(cadastroClientesCartaoCv,"\\d+",
                getResources().getInteger(R.integer.cartao_cv_length_min), getResources().getInteger(R.integer.cartao_cv_length_max));

        Log.d("Validacao", "Valida Cartao CV: "+valid);

        return valid;
    }

    void takeAction() {

        Toast toast = Toast.makeText(
                getApplicationContext(),
                "Entre com valores validos nos campos!",
                Toast.LENGTH_SHORT
        );

        toast.show();
    }

}