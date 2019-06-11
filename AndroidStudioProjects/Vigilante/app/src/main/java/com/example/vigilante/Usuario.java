package com.example.vigilante;

public class Usuario {


    private String nome;
    private String senha;
    private String dataNasc;
    private String email;
    private String nIncidentes;

    public Usuario() {
        this.nome = "";
        this.senha = "";
        this.dataNasc = "";
        this.email = "";
        this.nIncidentes = "";
    }

    public Usuario(String nome, String senha, String dataNasc,  String email, String nIncidentes) {
        this.nome = nome;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.email = email;
        this.nIncidentes = nIncidentes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getnIncidentes() {
        return nIncidentes;
    }

    public void setnIncidentes(String nIncidentes) {
        this.nIncidentes = nIncidentes;
    }



}
