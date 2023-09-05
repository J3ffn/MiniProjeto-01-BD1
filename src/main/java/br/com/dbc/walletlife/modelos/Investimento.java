package br.com.dbc.walletlife.modelos;

import java.time.LocalDate;

import javax.persistence.*;

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

	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioFK;

	public Investimento() {}

	public Investimento(String corretora, String tipo, Double valor, LocalDate dataInicio, String descricao, Usuario idFK) {
		this.corretora = corretora;
		this.tipo = tipo;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.descricao = descricao;
		this.usuarioFK = idFK;
	}

	// getters e setters
	public Integer getId() {
		return id;
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

	public Usuario getUsuarioFK() {
		return usuarioFK;
	}

	@Override
	public String toString() {
		return "Investimento{" +
					"id= " + id +
					"corretora= '" + corretora + '\'' + ", " +
					"tipo= '" + tipo + '\'' + ", " +
					"valor= " + valor + ", " +
					"dataInicio= " + dataInicio + ", " +
					"descricao= '" + descricao + '\'' + ", " +
					"usuarioFK= " + usuarioFK.getIdUsuario() +
				'}';
	}
}
