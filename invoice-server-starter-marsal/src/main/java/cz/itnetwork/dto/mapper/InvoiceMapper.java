package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    // DTO → Entity
    // Buyer and seller are set manually in service, so ignore them here
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    InvoiceEntity toEntity(InvoiceDTO source);

    // Entity → DTO
    @Mapping(target = "buyer", source = "buyer")
    @Mapping(target = "seller", source = "seller")
    InvoiceDTO toDTO(InvoiceEntity source);

    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    void updateInvoiceEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);

}