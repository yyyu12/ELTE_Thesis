package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.Artist;
import hu.elte.inf.backend.sqlEntity.Artwork;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArtworkMapper {
    List<Artwork> findAllArtworks();
    List<Artwork> findArtworksByArtistId(Long artistId);

    List<Artwork> findArtworkByTitleAndArtistId(Map<String, Object> params);

    // 得到特定的艺术品根据名字和艺术家id
    Artwork findArtworkByTitleAndArtistId(@Param("title") String title, @Param("artistId") Long artistId);
    Artwork getArtworkById(Long id);
    int insertArtwork(Artwork artwork);
    int updateArtwork(Artwork artwork);
    int deleteArtwork(Long id);
}
