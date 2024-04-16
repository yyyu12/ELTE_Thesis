package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.Artist;
import hu.elte.inf.backend.sqlEntity.Artwork;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArtworkMapper {
    List<Artwork> getArtworkByArtist(Long artistId);
    List<Artwork> findAllArtwork();
    Artwork getArtworkById(Long id);
    void insertArtwork(Artwork artwork);
    void updateArtwork(Artwork artwork);
    int deleteArtwork(Long id);
}
