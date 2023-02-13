package kr.co.won.simpleboard.email.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailForm {

    private String userEmail;

    private String subject;

    private String context;
}
