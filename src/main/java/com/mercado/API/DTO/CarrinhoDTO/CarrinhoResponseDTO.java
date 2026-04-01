package com.mercado.API.DTO.CarrinhoDTO;


import java.math.BigDecimal;

public class CarrinhoResponseDTO {
    private Long carrinhoId;
    private Long produtoId;
    private String nomeProduto;
    private String descricaoProduto;
    private BigDecimal preco;
    private Integer quantidade;
    private String imagemUrl;

    public Long getCarrinhoId() { return carrinhoId; }
    public void setCarrinhoId(Long carrinhoId) { this.carrinhoId = carrinhoId; }
    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }
    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }
    public String getDescricaoProduto() { return descricaoProduto; }
    public void setDescricaoProduto(String d) { this.descricaoProduto = d; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
}