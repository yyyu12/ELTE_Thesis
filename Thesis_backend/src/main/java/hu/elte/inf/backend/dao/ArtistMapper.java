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
     * Get All the Artists
     * @return the list of Artists
     */
    List<Artist> findAllArtists();

    Artist getArtistById(Long id);
    Artist getArtistByName(String name);
    void insertArtist(Artist artist);

    void updateArtist(Artist artist);

    void deleteArtist(Long id);
}
