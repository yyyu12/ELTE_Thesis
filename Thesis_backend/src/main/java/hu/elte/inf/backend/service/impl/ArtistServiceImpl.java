package hu.elte.inf.backend.service.impl;

import hu.elte.inf.backend.dao.ArtistMapper;
import hu.elte.inf.backend.sqlEntity.Artist;
import hu.elte.inf.backend.service.ArtistService;
import hu.elte.inf.backend.common.exceptionEnd.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistMapper artistMapper;

    @Override
    public List<Artist> getAllArtists() {
        return artistMapper.findAllArtists();
    }

    // 插入艺术家
    @Override
    public void insertArtist(Artist artist) {
        Artist existingArtist = artistMapper.getArtistByName(artist.getName());
        if (existingArtist != null) {
            throw new ArtistExistException("The Artist already exists.");
        }

        // 如果不存在，继续插入艺术家
        artistMapper.insertArtist(artist);
    }

    // 删除艺术家
    @Override
    public void deleteArtist(Long id) {
        Artist artist = artistMapper.getArtistById(id);
        if (artist == null) {
            throw new ArtistNotFoundException("Artist with id " + id + " does not exist.");
        }

        artistMapper.deleteArtist(id);
    }

    // 更新艺术家信息
    @Override
    public Artist updateArtist(Artist artist)
    {
        artistMapper.updateArtist(artist);
        return artistMapper.getArtistByName(artist.getName());
    }
}
