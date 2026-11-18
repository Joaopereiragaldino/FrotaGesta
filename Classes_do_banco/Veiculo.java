package Classes_do_banco;

public class Veiculo {
    private String placa;
    private int ano;
    private String statUs;
    private int quilometragem;
    private int kmManutencao;
    private String modelo;

    public Veiculo(String placa, int ano, String statUs, int quilometragem, int kmManutencao, String modelo) {
        this.placa = placa;
        this.ano = ano;
        this.statUs = statUs;
        this.quilometragem = quilometragem;
        this.kmManutencao = kmManutencao;
        this.modelo = modelo;
    }

    public String getPlaca(){
        return placa;
    }

    public void setPlaca(String placa){
        this.placa = placa;
    }

    public int getAno(){
        return ano;
    }

    public void setAno(int ano){
        this.ano = ano;
    }

    public String getStat_us(){
        return statUs;
    }

    public void setStat_us(String statUs){
        this.statUs = statUs;
    }

    public int getQuilometragem(){
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem){
        this.quilometragem = quilometragem;
    }

    public int getKm_manutencao(){
        return kmManutencao;
    }

    public void setKm_manutencao(int kmManutencao){
        this.kmManutencao = kmManutencao;
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }
}