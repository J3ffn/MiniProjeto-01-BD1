package br.com.dbc.wbhealth.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "HOSPITAL")
public class HospitalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOSPITAL_SEQ")
    @SequenceGenerator(name = "HOSPITAL_SEQ", sequenceName = "seq_hospital", allocationSize = 1)
    @Column(name = "id_hospital")
    private Integer idHospital;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "hospitalEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AtendimentoEntity> atendimentos;

    @OneToMany(mappedBy = "hospitalEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PacienteEntity> pacientes;

    @OneToMany(mappedBy = "hospitalEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicoEntity> medicos;

}
