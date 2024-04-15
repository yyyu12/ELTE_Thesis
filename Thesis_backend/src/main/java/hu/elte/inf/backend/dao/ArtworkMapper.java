package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.Artwork;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArtworkMapper {
    Artwork getArtworkById(Long id);
    void insertArtwork(Artwork artwork);
    void updateArtwork(Artwork artwork);
    void deleteArtwork(Long id);
}
