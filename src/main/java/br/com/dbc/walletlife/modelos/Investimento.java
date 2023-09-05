package br.com.dbc.walletlife.modelos;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVESTIMENTO")
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INVESTIMENTO")
    private Integer id;

    @Column(name = "CORRETORA")
    private String corretora;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "DATA_INICIAL")
    private LocalDate dataInicio;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "ID_USUARIO")
    private Integer idFK;
    
    // getters e setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorretora() {
		return corretora;
	}

	public void setCorretora(String corretora) {
		this.corretora = corretora;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdFK() {
		return idFK;
	}

	public void setIdFK(Integer idFK) {
		this.idFK = idFK;
	}
    
}
