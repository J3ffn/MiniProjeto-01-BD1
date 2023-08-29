package br.com.dbc.walletlife.modelos;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass

public abstract class AbstractMovimentoDinheiro<T> {

    @Column(name = "TIPO_DE_MOVIMENTO", length = 10)
    @Enumerated(EnumType.STRING)
    private T tipo;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "DESCRICAO")
    private String descricao;

    public AbstractMovimentoDinheiro() {}

    public AbstractMovimentoDinheiro(T tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }

    public T getTipo() {
        return this.tipo;
    }

    public void setTipo(T tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDecricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("""
                Tipo: %s
                Valor: %5.2f
                Descrição: %s""", getTipo(), getValor(), getDescricao());
    }
}
