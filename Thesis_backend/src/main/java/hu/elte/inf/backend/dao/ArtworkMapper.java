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
    int insertArtwork(Artwork artwork);
    int updateArtwork(Artwork artwork);
    int deleteArtwork(Long id);
}
