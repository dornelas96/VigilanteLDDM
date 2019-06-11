package com.example.vigilante;

public class Incidente {

    private String titulo;
    private String descricao;
    private String endereco;
    private String dataHora;
    private String foto;
    private String detalhes;

    public Incidente(String titulo, String descricao, String endereco,  String dataHora, String foto, String detalhes) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.dataHora = dataHora;
        this.foto = foto;
        this.detalhes = detalhes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
