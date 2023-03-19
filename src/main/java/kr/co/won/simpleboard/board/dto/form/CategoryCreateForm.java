package kr.co.won.simpleboard.board.dto.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateForm {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    // if sub category, board category parent code
    private String parentCode;

}
