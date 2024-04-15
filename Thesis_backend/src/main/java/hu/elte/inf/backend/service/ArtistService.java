package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.Artist;

public interface ArtistService {
    void insertArtist(Artist artist);
    void deleteArtist(Long id);
    Artist updateArtist(Artist artist);
}
