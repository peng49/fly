package fly.frontend.entity;

import java.sql.Date;

public class PostComment {
    private int id;
    private User user;
    private Post post;
    private String content;
    private Date commentTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "PostComment{" +
                "id=" + id +
                ", user=" + user +
                ", post=" + post +
                ", content='" + content + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }
}
