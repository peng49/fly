package fly.frontend.pojo;

public class PostFilter {
    public final static int DEFAULT_PAGE_SIZE = 10;

    private int page = 1;
    private int pageSize  = DEFAULT_PAGE_SIZE;

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

    @Override
    public String toString() {
        return "PostFilter{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
