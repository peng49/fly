package fly.frontend.enums;

public enum PostStatus {
    DRAFT(0, "草稿"), PUBLISHED(1, "发布"), DELETE(2, "删除");

    private int status;

    private String label;

    PostStatus(int status, String label) {
        this.status = status;
        this.label = label;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
