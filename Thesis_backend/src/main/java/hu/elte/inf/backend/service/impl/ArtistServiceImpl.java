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
    public Artist getArtistById(Long id){
        return artistMapper.getArtistById(id);
    }

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

        int rowsInserted = artistMapper.insertArtist(artist);
        if (rowsInserted == 0) {
            throw new IllegalStateException("Failed to insert artist.");
        }
    }


    // 删除艺术家
    @Override
    public void deleteArtist(Long id) {
        Artist artist = artistMapper.getArtistById(id);
        if (artist == null) {
            throw new ArtistNotFoundException("Artist with id " + id + " does not exist.");
        }

        int rowsDeleted = artistMapper.deleteArtist(id);
        if (rowsDeleted == 0) {
            throw new IllegalStateException("No artist was deleted.");
        }
    }


    // 更新艺术家信息
    @Override
    public Artist updateArtist(Artist artist) {
        int rowsUpdated = artistMapper.updateArtist(artist);
        if (rowsUpdated == 0) {
            throw new ArtistNotFoundException("No artist found with ID " + artist.getId() + ", or no new data provided for update.");
        }
        return artistMapper.getArtistById(artist.getId());
    }
}
