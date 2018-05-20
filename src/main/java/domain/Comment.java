package domain;

import java.util.Date;

public class Comment {
    private User user;
    private String body;
    private Date dateCreated;

    public Comment(User user, String body, Date dateCreated) {
        this.user = user;
        this.body = body;
        this.dateCreated = dateCreated;
    }
    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
