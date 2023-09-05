package br.com.dbc.walletlife.modelos;

import br.com.dbc.walletlife.enumerators.TipoDespesaEReceita;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "despesa")
public class Despesa extends AbstractMovimentoDinheiro<TipoDespesaEReceita> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despesa")
    private Integer idDespesa;

    @Column(name = "data_de_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuarioFK;

    public Despesa(TipoDespesaEReceita tipoDespesa, double valor, String descricao, LocalDate dataPagamento, Usuario usuarioFK) {
        super(tipoDespesa, valor, descricao);
        this.dataPagamento = dataPagamento;
        this.usuarioFK = usuarioFK;

    }

    public Despesa() {

    }

    public Integer getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Integer idDespesa) {
        this.idDespesa = idDespesa;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Usuario getUsuarioFK() {
        return usuarioFK;
    }

    public void setUsuarioFK(Usuario usuarioFK) {
        this.usuarioFK = usuarioFK;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                    "idDespesa= " + idDespesa + ", " +
                    "dataPagamento= " + dataPagamento + ", " +
                    "usuarioFK= " + usuarioFK.getIdUsuario() +
                '}';
    }
}
