package br.com.dbc.wbhealth.model.dto.atendimento;

import java.time.LocalDate;


public class AtendimentoInputDTO {

    private Integer idHospital;

    private Integer idPaciente;

    private Integer idMedico;

    private LocalDate dataAtendimento;

    private String laudo;

    private String tipoDeAtendimento;

    private Double valorDoAtendimento;

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public String getTipoDeAtendimento() {
        return tipoDeAtendimento;
    }

    public void setTipoDeAtendimento(String tipoDeAtendimento) {
        this.tipoDeAtendimento = tipoDeAtendimento;
    }

    public Double getValorDoAtendimento() {
        return valorDoAtendimento;
    }

    public void setValorDoAtendimento(Double valorDoAtendimento) {
        this.valorDoAtendimento = valorDoAtendimento;
    }
}
