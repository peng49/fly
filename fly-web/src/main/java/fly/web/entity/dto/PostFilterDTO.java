package fly.web.entity.dto;

import lombok.Data;

@Data
public class PostFilterDTO {
    public final static int DEFAULT_PAGE_SIZE = 10;

    private int page = 1;

    private int pageSize  = DEFAULT_PAGE_SIZE;

    private String list;

    private String orderBy;
}
