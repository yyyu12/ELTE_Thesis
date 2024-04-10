package hu.elte.inf.backend.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


// 接收和校验来自前端的数据
@Data
@Schema (description = "A insert example")
public class InsertRequest {

    @NotBlank(message = "id not empty")
    private String id;

    @NotBlank(message = "name not empty")
    private String name;
}
