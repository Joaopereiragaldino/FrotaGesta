package Classes_do_banco;

public class Motorista {
    private String cpf;
    private String nome;
    private String tipoCnh;
    private String statUs;

    public Motorista(String cpf, String nome, String tipoCnh, String statUs) {
        this.cpf = cpf;
        this.nome = nome;
        this.tipoCnh = tipoCnh;
        this.statUs = statUs;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTipo_CNH(){
        return tipoCnh;
    }

    public void setTipo_CNH(String tipoCnh){
        this.tipoCnh = tipoCnh;
    }

    public String getStat_us(){
        return statUs;
    }

    public void setStat_us(String statUs){
        this.statUs = statUs;
    }
}