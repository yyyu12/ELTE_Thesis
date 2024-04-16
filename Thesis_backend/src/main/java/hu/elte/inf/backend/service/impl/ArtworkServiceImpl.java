package hu.elte.inf.backend.service.impl;

import hu.elte.inf.backend.dao.ArtworkMapper;
import hu.elte.inf.backend.sqlEntity.Artwork;
import hu.elte.inf.backend.service.ArtworkService;
import hu.elte.inf.backend.common.exceptionEnd.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// 控制类和数据库的桥梁
// 接收控制器层的调用，处理业务逻辑
// 调用数据访问层与数据库交互，并返回结果给控制器层

@org.springframework.stereotype.Service
public class ArtworkServiceImpl implements ArtworkService {
    @Autowired
    private ArtworkMapper artworkMapper;


    @Override
    public List<Artwork> getAllArtworks(){
        return artworkMapper.findAllArtwork();
    }

    @Override
    public void insertArtwork(Artwork artwork) {
//        // 检查是否存在相同标题和艺术家ID的艺术品
//        Artwork existingArtwork = artworkMapper.findArtworkByTitleAndArtistId(artwork.getTitle(), artwork.getArtist().getId());
//        if (existingArtwork != null) {
//            throw new ArtworkAlreadyExistsException("An artwork with the title '" + artwork.getTitle() +
//                    "' and artist ID '" + artwork.getArtist().getId() + "' already exists.");
//        }

        // 尝试插入艺术品，如果没有插入成功，抛出异常
        int rowsInserted = artworkMapper.insertArtwork(artwork);
        if (rowsInserted == 0) {
            throw new IllegalStateException("Failed to insert the artwork.");
        }
    }

    @Override
    public void deleteArtwork(Long id) {
        Artwork artwork = artworkMapper.getArtworkById(id);
        if (artwork == null) {
            throw new ArtworkNotFoundException("No artwork found with ID " + id);
        }

        int rowsDeleted = artworkMapper.deleteArtwork(id);
        if (rowsDeleted == 0) {
            throw new IllegalStateException("No artwork was deleted, unexpected error.");
        }
    }

    @Override
    public Artwork updateArtwork(Artwork artwork) {
        artworkMapper.updateArtwork(artwork);
        return artworkMapper.getArtworkById(artwork.getId());
    }
}
