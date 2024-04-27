package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.BlindBox;
import hu.elte.inf.backend.sqlEntity.BlindBoxDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BlindBoxMapper {
    /**
     * Get all blind box details by user id
     * @param user_id
     * @return
     */
    List<BlindBoxDetail> getAllBlindBoxDetailsByUserId(@Param("user_id") Long user_id);

    /*
     * Get all blind box details
     */
    List<BlindBoxDetail> getAllBlindBoxDetails();

    /**
     * Get all blind box details by blind box id
     * @param blind_box_id
     * @return
     */
    List<BlindBoxDetail> getAllBlindBoxDetailsByBlindBoxId(@Param("blind_box_id") Long blind_box_id);

    /**
     * Insert blind box
     * @param blindBox
     */
    int insertBlindBox(BlindBox blindBox);

    /**
     * Select blind box by user id and artwork id
     * @param user_id
     * @param artwork_id
     * @return
     */
    BlindBox selectBlindBoxByUserIdAndArtworkId(@Param("user_id") Long user_id, @Param("artwork_id") Long artwork_id);

    /**
     * Select blind box by blind box id
     * @param blind_box_id
     * @return
     */
    BlindBox selectBlindBoxByBlindBoxId(Long blind_box_id);

    /**
     * Get all blind boxes by user id
     * @param user_id
     * @return
     */
    List<BlindBox> selectBlindBoxesByUserId(Long user_id);
}
