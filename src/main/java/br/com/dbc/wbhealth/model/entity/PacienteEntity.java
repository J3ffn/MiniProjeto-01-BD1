package br.com.dbc.wbhealth.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "PACIENTE")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PACIENTE_SEQ")
    @SequenceGenerator(name = "PACIENTE_SEQ", sequenceName = "seq_paciente", allocationSize = 1)
    @Column(name = "id_paciente")
    private Integer idPaciente;

    @ManyToOne
    @JoinColumn(name = "id_hospital", referencedColumnName = "id_hospital")
    private HospitalEntity hospitalEntity;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private PessoaEntity pessoa;

    @OneToMany(
            mappedBy = "pacienteEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<AtendimentoEntity> atendimentos;

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public HospitalEntity getHospitalEntity() {
        return hospitalEntity;
    }

    public void setHospitalEntity(HospitalEntity hospitalEntity) {
        this.hospitalEntity = hospitalEntity;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public Set<AtendimentoEntity> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(Set<AtendimentoEntity> atendimentos) {
        this.atendimentos = atendimentos;
    }
}
