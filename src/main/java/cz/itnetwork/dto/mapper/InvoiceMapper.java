package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface InvoiceMapper {
    /**
     * vrati Entitu dle DTO (source)
     * @param source DTO
     * @return Entita
     */
    InvoiceEntity toEntity(InvoiceDTO source);
    /**
     * vrati DTO dle Entity (source)
     * @param source Entity
     * @return DTO
     */
    InvoiceDTO toDTO(InvoiceEntity source);

    /**
     * do Entity (target) nakopiruje DTO (source) bez id
     * @param source data v DTO
     * @param target entita
     * @return entita
     */

    @Mapping(target = "id",ignore = true)
    InvoiceEntity updateEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);
}
