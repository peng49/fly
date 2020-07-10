package fly.frontend.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Post {
    private int id;
    private Column column;
    private User author;
    private String title;
    private String originalContent;
    private String content;
    private int viewCount;
    private int replyCount;
    private double heat;
    private int status;
    private Timestamp publishAt;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private int essence;
    private int top;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public double getHeat() {
        return heat;
    }

    public void setHeat(double heat) {
        this.heat = heat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(Timestamp publishAt) {
        this.publishAt = publishAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public int getEssence() {
        return essence;
    }

    public void setEssence(int essence) {
        this.essence = essence;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", column=" + column +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewCount=" + viewCount +
                ", replyCount=" + replyCount +
                ", status=" + status +
                ", publishAt=" + publishAt +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", essence=" + essence +
                ", top=" + top +
                '}';
    }
}
