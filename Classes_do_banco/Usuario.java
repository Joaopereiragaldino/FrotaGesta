package Classes_do_banco;

public class Usuario {
    private int idUsuario;
    private String tipo;
    private String nome;
    private String statUs;

    public Usuario(int idUsuario, String tipo, String nome, String statUs) {
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.nome = nome;
        this.statUs = statUs;
    }

    public int getId_usuario(){
        return idUsuario;
    }

    public void setId_usuario(int idUsuario){
        this.idUsuario = idUsuario;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getStat_us(){
        return statUs;
    }

    public void setStat_us(String statUs){
        this.statUs = statUs;
    }
}