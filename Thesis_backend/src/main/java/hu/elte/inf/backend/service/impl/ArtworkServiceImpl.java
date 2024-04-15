package hu.elte.inf.backend.service.impl;

import hu.elte.inf.backend.dao.ArtworkMapper;
import hu.elte.inf.backend.sqlEntity.Artwork;
import hu.elte.inf.backend.service.ArtworkService;
import hu.elte.inf.backend.common.exceptionEnd.*;
import org.springframework.beans.factory.annotation.Autowired;

// 控制类和数据库的桥梁
// 接收控制器层的调用，处理业务逻辑
// 调用数据访问层与数据库交互，并返回结果给控制器层

@org.springframework.stereotype.Service
public class ArtworkServiceImpl implements ArtworkService {
    @Autowired
    private ArtworkMapper artworkMapper;

    @Override
    public void insertArtwork(Artwork artwork) {
        artworkMapper.insertArtwork(artwork);
    }

    @Override
    public void deleteArtwork(Long id) {
        artworkMapper.deleteArtwork(id);
    }

    @Override
    public Artwork updateArtwork(Artwork artwork) {
        artworkMapper.updateArtwork(artwork);
        return artworkMapper.getArtworkById(artwork.getId());
    }
}
