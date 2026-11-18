package Classes_do_banco;

import java.time.LocalDateTime;

public class Manutencao {
    private int idManu;
    private String statUs;
    private LocalDateTime dataGerada;
    private String descricao;
    private String placaVeiculo;

    public Manutencao(int idManu, String statUs, LocalDateTime dataGerada, String descricao, String placaVeiculo) {
        this.idManu = idManu;
        this.statUs = statUs;
        this.dataGerada = dataGerada;
        this.descricao = descricao;
        this.placaVeiculo = placaVeiculo;
    }

    public int getId_manu(){
        return idManu;
    }

    public void setId_manu(int idManu){
        this.idManu = idManu;
    }

    public String getStat_us(){
        return statUs;
    }

    public void setStat_us(String statUs){
        this.statUs = statUs;
    }

    public LocalDateTime getData_gerada(){
        return dataGerada;
    }

    public void setData_gerada(LocalDateTime dataGerada){
        this.dataGerada = dataGerada;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getPlaca_veiculo(){
        return placaVeiculo;
    }

    public void setPlaca_veiculo(String placaVeiculo){
        this.placaVeiculo = placaVeiculo;
    }
}