package com.example.vaichover.model;

import java.util.Objects;

public class cidadeModel {

    private String nome;
    private String clima;
    private String climaDescricao;
    private double temperaturaAtual;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private Integer humidade;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getClimaDescricao() {
        return climaDescricao;
    }

    public void setClimaDescricao(String climaDescricao) {
        this.climaDescricao = climaDescricao;
    }

    public double getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public void setTemperaturaAtual(double temperaturaAtual) {
        this.temperaturaAtual = temperaturaAtual;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public Integer getHumidade() {
        return humidade;
    }

    public void setHumidade(Integer humidade) {
        this.humidade = humidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        cidadeModel that = (cidadeModel) o;
        return Double.compare(that.getTemperaturaAtual(), getTemperaturaAtual()) == 0 &&
                Double.compare(that.getTemperaturaMaxima(), getTemperaturaMaxima()) == 0 &&
                Double.compare(that.getTemperaturaMinima(), getTemperaturaMinima()) == 0 &&
                getNome().equals(that.getNome()) &&
                getClima().equals(that.getClima()) &&
                getClimaDescricao().equals(that.getClimaDescricao()) &&
                getHumidade().equals(that.getHumidade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getClima(), getClimaDescricao(), getTemperaturaAtual(), getTemperaturaMaxima(), getTemperaturaMinima(), getHumidade());
    }

    @Override
    public String toString() {
        return "cidadeModel{" +
                "nome='" + nome + '\'' +
                ", clima='" + clima + '\'' +
                ", climaDescricao='" + climaDescricao + '\'' +
                ", temperaturaAtual=" + temperaturaAtual +
                ", temperaturaMaxima=" + temperaturaMaxima +
                ", temperaturaMinima=" + temperaturaMinima +
                ", humidade=" + humidade +
                '}';
    }
}
