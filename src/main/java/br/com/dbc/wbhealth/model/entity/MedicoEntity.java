package br.com.dbc.wbhealth.model.entity;

import br.com.dbc.wbhealth.model.Pagamento;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "MEDICO")
public class MedicoEntity implements Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICO_SEQ")
    @SequenceGenerator(name = "MEDICO_SEQ", sequenceName = "seq_medico", allocationSize = 1)
    @Column(name = "id_medico")
    private Integer idMedico;

    @Column(name = "crm")
    private String crm;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "id_hospital", referencedColumnName = "id_hospital")
    private HospitalEntity hospitalEntity;

    @OneToMany(mappedBy = "medicoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AtendimentoEntity> atendimentos;

    @Override
    public Double calcularSalarioMensal() {
        Double taxaInss = 0.14;
        return pessoa.getSalarioMensal() - pessoa.getSalarioMensal() * taxaInss;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public HospitalEntity getHospitalEntity() {
        return hospitalEntity;
    }

    public void setHospitalEntity(HospitalEntity hospitalEntity) {
        this.hospitalEntity = hospitalEntity;
    }

    public Set<AtendimentoEntity> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(Set<AtendimentoEntity> atendimentos) {
        this.atendimentos = atendimentos;
    }
}
