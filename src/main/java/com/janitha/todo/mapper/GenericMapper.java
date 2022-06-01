package com.janitha.todo.mapper;

import java.util.Collection;

public interface GenericMapper <DAO, DTO> {

    /**
     * Maps a DTO to a DAO
     *
     * @param dto The DTO to map to a DAO
     * @return A DAO
     */
    DAO toDomain(DTO dto);

    /**
     * Maps a DAO to a DTO
     *
     * @param dao to DAO to map to a DTO
     * @return A DTO
     */
    DTO fromDomain(DAO dao);

    /**
     * Maps a collection DAOs to DTOs
     *
     * @param daos the collections of DAOs
     * @return the list of DTOs
     */
    Collection<DTO> fromDomain(Collection<DAO> daos);

    /**
     * Maps a collection DTOs to DAOs
     *
     * @param dtos the collections of DTOs
     * @return the list of DAOs
     */
    Collection<DAO> toDomain(Collection<DTO> dtos);

}
