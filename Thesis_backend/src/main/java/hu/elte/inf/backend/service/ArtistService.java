package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.Artist;

import java.util.List;

public interface ArtistService {
    Artist getArtistById(Long id);
    List<Artist> getAllArtists();
    void insertArtist(Artist artist);
    void deleteArtist(Long id);
    Artist updateArtist(Artist artist);
}
