package fly.frontend.event;


import fly.frontend.entity.PostComment;

public class CommentEvent {
    private PostComment postComment;

    public CommentEvent(PostComment postComment) {
        this.postComment = postComment;
    }

    public PostComment getPostComment() {
        return postComment;
    }

    public void setPostComment(PostComment postComment) {
        this.postComment = postComment;
    }

    @Override
    public String toString() {
        return "CommentEvent{" +
                "postComment=" + postComment +
                '}';
    }
}
