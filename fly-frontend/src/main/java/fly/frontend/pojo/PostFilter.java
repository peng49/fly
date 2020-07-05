package fly.frontend.pojo;

public class PostFilter {
    public final static int DEFAULT_PAGE_SIZE = 10;

    private int page = 1;
    private int pageSize  = DEFAULT_PAGE_SIZE;

    private String list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PostFilter{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
