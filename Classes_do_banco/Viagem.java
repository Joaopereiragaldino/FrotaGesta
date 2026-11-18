package Classes_do_banco;

import java.time.LocalDateTime;

public class Viagem {
    private int idViagem;
    private String idMotorista;
    private String idVeiculo;
    private int idUsuario;
    private int quilometragemInicial;
    private LocalDateTime dataHoraSaida;
    private Integer quilometragemFinal;
    private LocalDateTime dataHoraFinal;
    private String statUs;

    public Viagem(int idViagem, String idMotorista, String idVeiculo, int idUsuario, int quilometragemInicial, LocalDateTime dataHoraSaida, String statUs) {
        this.idViagem = idViagem;
        this.idMotorista = idMotorista;
        this.idVeiculo = idVeiculo;
        this.idUsuario = idUsuario;
        this.quilometragemInicial = quilometragemInicial;
        this.dataHoraSaida = dataHoraSaida;
        this.statUs = statUs;
        this.quilometragemFinal = null;
        this.dataHoraFinal = null;
    }

    public int getId_viagem(){
        return idViagem;
    }

    public void setId_viagem(int idViagem){
        this.idViagem = idViagem;
    }

    public String getId_motorista(){
        return idMotorista;
    }

    public void setId_motorista(String idMotorista){
        this.idMotorista = idMotorista;
    }

    public String getId_veiculo(){
        return idVeiculo;
    }

    public void setId_veiculo(String idVeiculo){
        this.idVeiculo = idVeiculo;
    }

    public int getId_usuario(){
        return idUsuario;
    }

    public void setId_usuario(int idUsuario){
        this.idUsuario = idUsuario;
    }

    public int getQuilometragem_inicial(){
        return quilometragemInicial;
    }

    public void setQuilometragem_inicial(int quilometragemInicial){
        this.quilometragemInicial = quilometragemInicial;
    }

    public LocalDateTime getData_hora_saida(){
        return dataHoraSaida;
    }

    public void setData_hora_saida(LocalDateTime dataHoraSaida){
        this.dataHoraSaida = dataHoraSaida;
    }

    public Integer getQuilometragem_final(){
        return quilometragemFinal;
    }

    public void setQuilometragem_final(Integer quilometragemFinal){
        this.quilometragemFinal = quilometragemFinal;
    }

    public LocalDateTime getData_hora_final(){
        return dataHoraFinal;
    }

    public void setData_hora_final(LocalDateTime dataHoraFinal){
        this.dataHoraFinal = dataHoraFinal;
    }

    public String getStat_us(){
        return statUs;
    }

    public void setStat_us(String statUs){
        this.statUs = statUs;
    }
}