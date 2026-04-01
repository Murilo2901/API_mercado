package com.mercado.API.DTO.UsuarioDTO;

public class LoginResponseDTO {
    private boolean sucesso;
    private String mensagem;
    private Long id;
    private String nome;
    private String usuario;

    public LoginResponseDTO(boolean sucesso, String mensagem, Long id, String nome, String usuario) {
        this.sucesso = sucesso; this.mensagem = mensagem;
        this.id = id; this.nome = nome; this.usuario = usuario;
    }
    public LoginResponseDTO(boolean sucesso, String mensagem) {
        this.sucesso = sucesso; this.mensagem = mensagem;
    }

    public boolean isSucesso() { return sucesso; }
    public String getMensagem() { return mensagem; }
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getUsuario() { return usuario; }
}