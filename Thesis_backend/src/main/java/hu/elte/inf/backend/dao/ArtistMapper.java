package hu.elte.inf.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import hu.elte.inf.backend.sqlEntity.Artist;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArtistMapper {
    Artist getArtistById(Long id);
    Artist getArtistByName(String name);
    void insertArtist(Artist artist);

    void updateArtist(Artist artist);

    void deleteArtist(Long id);
}
