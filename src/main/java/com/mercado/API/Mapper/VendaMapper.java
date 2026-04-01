package com.mercado.API.Mapper;

import com.mercado.API.DTO.VendaDTO.VendaItemDTO;
import com.mercado.API.DTO.VendaDTO.VendaResponseDTO;
import com.mercado.API.Model.Venda;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class VendaMapper {


    public VendaResponseDTO toDTO(Venda entity) {
        VendaResponseDTO dto = new VendaResponseDTO();
        dto.setId(entity.getId());
        dto.setUsuarioId(entity.getUsuario().getId());
        dto.setNomeUsuario(entity.getUsuario().getNome());
        dto.setTotal(entity.getTotal());
        dto.setDataVenda(entity.getDataVenda());
        dto.setStatusVenda(entity.getStatusVenda());

        // Mapeia os itens da venda
        if (entity.getItens() != null) {
            dto.setItens(entity.getItens().stream().map(item -> {
                VendaItemDTO itemDTO = new VendaItemDTO();
                itemDTO.setProdutoId(item.getProduto().getId());
                itemDTO.setNomeProduto(item.getProduto().getNome());
                itemDTO.setQuantidade(item.getQuantidade());
                itemDTO.setPrecoUnit(item.getPrecoUnit());
                return itemDTO;
            }).collect(Collectors.toList()));
        }

        return dto;
    }
}
