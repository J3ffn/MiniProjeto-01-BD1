package br.com.dbc.walletlife.modelos;

import br.com.dbc.walletlife.enumerators.TipoDespesaEReceita;

import javax.persistence.*;

@Entity
@Table(name = "receita")
public class Receita extends AbstractMovimentoDinheiro<TipoDespesaEReceita> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receita")
    private Integer idReceita;

    @Column(name = "banco", length = 100, nullable = false)
    private String banco;

    @Column(name = "empresa", length = 100, nullable = false)
    private String empresa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuarioFK;

    public Receita() {
    }

    public Receita(TipoDespesaEReceita tipoDespesaEReceita, double valor, String descricao, String banco, String empresa, Usuario usuarioFK) {
        super(tipoDespesaEReceita, valor, descricao);
        this.banco = banco;
        this.empresa = empresa;
        this.usuarioFK = usuarioFK;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuarioFK() {
        return usuarioFK;
    }

    public void setUsuarioFK(Usuario usuarioFK) {
        this.usuarioFK = usuarioFK;
    }

    @Override
    public String toString() {
        return String.format("""
                %s
                Banco: %s
                Empresa: %s""", super.toString(), getBanco(), getEmpresa());
    }
}
