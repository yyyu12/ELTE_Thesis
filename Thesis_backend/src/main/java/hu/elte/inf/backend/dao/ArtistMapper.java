package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.User;
import org.apache.ibatis.annotations.Mapper;
import hu.elte.inf.backend.sqlEntity.Artist;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArtistMapper {
    /**
     * Retrieves all artists from the database.
     * @return a list of artists
     */
    List<Artist> findAllArtists();

    /**
     * Retrieves an artist by their ID.
     * @param id the artist's ID
     * @return the artist, or null if not found
     */
    Artist getArtistById(Long id);

    /**
     * Retrieves an artist by their name.
     * @param name the artist's name
     * @return the artist, or null if not found
     */
    Artist getArtistByName(String name);

    /**
     * Inserts a new artist into the database.
     * @param artist the artist to insert
     * @return the number of rows affected
     */
    int insertArtist(Artist artist);

    /**
     * Updates an existing artist's information.
     * @param artist the artist with updated information
     * @return the number of rows affected
     */
    int updateArtist(Artist artist);

    /**
     * Deletes an artist by their ID.
     * @param id the artist's ID
     * @return the number of rows affected
     */
    int deleteArtist(Long id);
}
