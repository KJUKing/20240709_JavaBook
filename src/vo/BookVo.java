package vo;

import lombok.Data;

@Data
public class BookVo {

    private int no;
    private String issn;
    private String title;
    private String content;
    private String pub_date;
}
