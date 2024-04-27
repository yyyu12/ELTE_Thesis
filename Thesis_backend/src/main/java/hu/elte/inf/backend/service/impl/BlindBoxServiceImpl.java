package hu.elte.inf.backend.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.elte.inf.backend.dao.BlindBoxMapper;
import hu.elte.inf.backend.service.BlindBoxService;
import hu.elte.inf.backend.sqlEntity.BlindBox;
import hu.elte.inf.backend.sqlEntity.BlindBoxDetail;

@org.springframework.stereotype.Service
public class BlindBoxServiceImpl implements BlindBoxService {

    @Autowired
    private BlindBoxMapper blindBoxMapper;

    @Override
    public List<BlindBoxDetail> getAllBlindBoxDetails(){
        return blindBoxMapper.getAllBlindBoxDetails();
    }

    @Override
    public List<BlindBoxDetail> getAllBlindBoxDetailsByUserId(Long user_id){
        return blindBoxMapper.getAllBlindBoxDetailsByUserId(user_id);
    }

    @Override
    public List<BlindBoxDetail> getAllBlindBoxDetailsByBlindBoxId(Long blind_box_id){
        return blindBoxMapper.getAllBlindBoxDetailsByBlindBoxId(blind_box_id);
    }

    @Override
    public boolean addBlindBox(BlindBox blindBox){
        BlindBox existingBlindBoxes = blindBoxMapper.selectBlindBoxByUserIdAndArtworkId(blindBox.getUser_id(), blindBox.getArtwork_id());

        if(existingBlindBoxes == null){
            int rowsAffected = blindBoxMapper.insertBlindBox(blindBox);
            return rowsAffected > 0;
        }

        return false;
    }

    @Override
    public List<BlindBox> getBlindBoxesByUserId(Long user_id){
        return blindBoxMapper.selectBlindBoxesByUserId(user_id);
    }

    @Override
    public BlindBox getBlindBoxByBlindBoxId(Long blind_box_id){
        return blindBoxMapper.selectBlindBoxByBlindBoxId(blind_box_id);
    }
}
