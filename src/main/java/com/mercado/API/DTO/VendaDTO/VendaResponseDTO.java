package com.mercado.API.DTO.VendaDTO;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class VendaResponseDTO {
    private Long id;
    private Long usuarioId;
    private String nomeUsuario;
    private List<VendaItemDTO> itens;
    private BigDecimal total;
    private LocalDate dataVenda;
    private String statusVenda;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }
    public List<VendaItemDTO> getItens() { return itens; }
    public void setItens(List<VendaItemDTO> itens) { this.itens = itens; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public LocalDate getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDate dataVenda) { this.dataVenda = dataVenda; }
    public String getStatusVenda() { return statusVenda; }
    public void setStatusVenda(String statusVenda) { this.statusVenda = statusVenda; }
}