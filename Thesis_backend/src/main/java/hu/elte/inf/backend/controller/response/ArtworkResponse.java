package hu.elte.inf.backend.controller.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArtworkResponse {
    private Long id;
    private Long artist_id; // 对应艺术家的ID，与artists表中的id关联
    private String title;  // 艺术品的标题
    private String description; // 艺术品的描述
    private BigDecimal price;   // 艺术品的价格，使用BigDecimal以处理财务计算中的精度问题
    private String image_url;    // 艺术品图片的URL
    private String type;        // 艺术品的类型
}
