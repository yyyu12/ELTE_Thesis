package hu.elte.inf.backend.service.impl;

import hu.elte.inf.backend.dao.ArtworkMapper;
import hu.elte.inf.backend.sqlEntity.Artwork;
import hu.elte.inf.backend.service.ArtworkService;
import hu.elte.inf.backend.common.exceptionEnd.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 控制类和数据库的桥梁
// 接收控制器层的调用，处理业务逻辑
// 调用数据访问层与数据库交互，并返回结果给控制器层

@org.springframework.stereotype.Service
public class ArtworkServiceImpl implements ArtworkService {
    @Autowired
    private ArtworkMapper artworkMapper;

    @Override
    public Artwork getArtworkById(Long id){
        return artworkMapper.getArtworkById(id);
    }

    @Override
    public List<Artwork> getAllArtworks(){

        return artworkMapper.findAllArtworks();
    }

    @Override
    public List<Artwork> getArtworksByArtistId(Long artist_id) {
        return artworkMapper.findArtworksByArtistId(artist_id);
    }

    @Override
    public List<Artwork> getArtworksByUserId(Long userId) {
        return artworkMapper.findArtworksByUserId(userId);
    }

    @Override
    public boolean addArtwork(Artwork artwork) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", artwork.getTitle());
        params.put("artistId", artwork.getArtist_id());

        List<Artwork> existingArtworks = artworkMapper.findArtworkByTitleAndArtistId(params);

        if (existingArtworks.isEmpty()) {
            int rowsAffected = artworkMapper.insertArtwork(artwork);
            return rowsAffected > 0;  // 插入成功则返回true
        }

        return false;  // 已存在，未执行插入
    }


    @Override
    public boolean deleteArtworkById(Long id) {
        int rowsDeleted = artworkMapper.deleteArtwork(id);
        return rowsDeleted > 0;  // 如果删除了至少一行，返回true
    }

    @Override
    public boolean updateArtwork(Artwork artwork) {
        int rowsUpdated = artworkMapper.updateArtwork(artwork);
        return rowsUpdated > 0;
    }

    @Override
    public boolean purchaseArtwork(Long user_id, Long artwork_id) {
        Artwork artwork = artworkMapper.getArtworkById(artwork_id);
        if (artwork != null && artwork.getUser_id() == null) {
            artworkMapper.purchaseArtwork(user_id, artwork_id);
            return true;
        }
        return false;
    }

    @Override
    public Artwork getRandomArtworkByPriceRange(double minPrice, double maxPrice) {
        return artworkMapper.findRandomArtworkByPriceRange(minPrice, maxPrice);
    }
}
