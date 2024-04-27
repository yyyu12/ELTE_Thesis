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
    public List<BlindBoxDetail> getAllBlindBoxDetailsByUserId(Long user_id){
        return blindBoxMapper.getAllBlindBoxDetailsByUserId(user_id);
    }

    @Override
    public boolean addBlindBox(BlindBox blindBox){
        int rowsAffected = blindBoxMapper.insertBlindBox(blindBox);
        return rowsAffected > 0;
    }

    @Override
    public List<BlindBox> getBlindBoxesByUserId(Long user_id){
        return blindBoxMapper.selectBlindBoxesByUserId(user_id);
    }
}
