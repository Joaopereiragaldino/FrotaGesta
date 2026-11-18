package Classes_do_banco;

import java.time.LocalDateTime;

public class Abastecimento {
    private int idAbastecimento;
    private String placaVeiculo;
    private LocalDateTime dataHora;
    private double valorTotal;

    public Abastecimento(int idAbastecimento, String placaVeiculo, LocalDateTime dataHora, double valorTotal) {
        this.idAbastecimento = idAbastecimento;
        this.placaVeiculo = placaVeiculo;
        this.dataHora = dataHora;
        this.valorTotal = valorTotal;
    }

    public int getId_abastecimento() { 
        return idAbastecimento;
    }

    public String getPlaca_veiculo() { 
        return placaVeiculo;
    }

    public LocalDateTime getData_hora() { 
        return dataHora;
    }
    
    public double getValor_total() { 
        return valorTotal;
    }
}