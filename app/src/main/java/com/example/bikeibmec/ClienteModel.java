package com.example.bikeibmec;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class ClienteModel implements Serializable, Parcelable {

    public static final String ID = "CLIENTE_MODEL";

    private String matricula;
    private String nome;
    private String sobrenome;
    private String sexo;
    private List<String> cursos;
    private String celular;
    private String email;
    private String cartaoBandeira;
    private String cartaoNumero;
    private String cartaoTitular;
    private String cartaoValidade;
    private String cartaoCv;

    public ClienteModel(
            String matricula,
            String nome,
            String sobrenome,
            String sexo,
            List<String> cursos,
            String celular,
            String email,
            String cartaoBandeira,
            String cartaoNumero,
            String cartaoTitular,
            String cartaoValidade,
            String cartaoCv
    ) {
        this.matricula = matricula;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.cursos = cursos;
        this.celular = celular;
        this.email = email;
        this.cartaoBandeira = cartaoBandeira;
        this.cartaoNumero = cartaoNumero;
        this.cartaoTitular = cartaoTitular;
        this.cartaoValidade = cartaoValidade;
        this.cartaoCv = cartaoCv;
    }

    protected ClienteModel(Parcel in) {
        matricula = in.readString();
        nome = in.readString();
        sobrenome = in.readString();
        sexo = in.readString();
        cursos = in.readArrayList(String.class.getClassLoader());
        celular = in.readString();
        email = in.readString();
        cartaoBandeira = in.readString();
        cartaoNumero = in.readString();
        cartaoTitular = in.readString();
        cartaoValidade = in.readString();
        cartaoCv = in.readString();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<String> getCursos() {
        return cursos;
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCartaoBandeira() {
        return cartaoBandeira;
    }

    public void setCartaoBandeira(String cartaoBandeira) {
        this.cartaoBandeira = cartaoBandeira;
    }

    public String getCartaoNumero() {
        return cartaoNumero;
    }

    public void setCartaoNumero(String cartaoNumero) {
        this.cartaoNumero = cartaoNumero;
    }

    public String getCartaoTitular() {
        return cartaoTitular;
    }

    public void setCartaoTitular(String cartaoTitular) {
        this.cartaoTitular = cartaoTitular;
    }

    public String getCartaoValidade() {
        return cartaoValidade;
    }

    public void setCartaoValidade(String cartaoValidade) {
        this.cartaoValidade = cartaoValidade;
    }

    public String getCartaoCv() {
        return cartaoCv;
    }

    public void setCartaoCv(String cartaoCv) {
        this.cartaoCv = cartaoCv;
    }

    public static final Creator<ClienteModel> CREATOR = new Creator<ClienteModel>() {
        @Override
        public ClienteModel createFromParcel(Parcel in) {
            return new ClienteModel(in);
        }

        @Override
        public ClienteModel[] newArray(int size) {
            return new ClienteModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(matricula);
        parcel.writeString(nome);
        parcel.writeString(sobrenome);
        parcel.writeString(sexo);
        parcel.writeList(cursos);
        parcel.writeString(celular);
        parcel.writeString(email);
        parcel.writeString(cartaoBandeira);
        parcel.writeString(cartaoNumero);
        parcel.writeString(cartaoTitular);
        parcel.writeString(cartaoValidade);
        parcel.writeString(cartaoCv);
    }
}
