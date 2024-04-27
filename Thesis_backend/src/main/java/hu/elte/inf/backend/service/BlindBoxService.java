package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.BlindBox;
import hu.elte.inf.backend.sqlEntity.BlindBoxDetail;
import java.util.List;

public interface BlindBoxService {

    List<BlindBoxDetail> getAllBlindBoxDetails();
    List<BlindBoxDetail> getAllBlindBoxDetailsByUserId(Long user_id);
    List<BlindBoxDetail> getAllBlindBoxDetailsByBlindBoxId(Long blind_box_id);
    boolean addBlindBox(BlindBox blindBox);
    List<BlindBox> getBlindBoxesByUserId(Long user_id);
    BlindBox getBlindBoxByBlindBoxId(Long blind_box_id);
}
