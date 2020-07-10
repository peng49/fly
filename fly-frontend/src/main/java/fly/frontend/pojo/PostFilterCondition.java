package fly.frontend.pojo;

public class PostFilterCondition {
    private String list = "all";

    private int authorId;

    private int columnId;

    private String orderBy = "heat desc";

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
