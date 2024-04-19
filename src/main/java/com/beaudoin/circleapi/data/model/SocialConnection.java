package com.beaudoin.circleapi.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SOCIAL_CONNECTION_TABLE")
public class SocialConnection {

    public SocialConnection() {}


    @Id
    @GeneratedValue
    @Column(name = "SOCIAL_CONNECTION_ID")
    private long socialConnectionId;

    @Column(name = "USER_ID")
    private long userID;

    @ManyToOne
    @JoinColumn(name = "FRIEND_USER_ID")
    private User friendUser;

    public long getSocialConnectionId() {
        return socialConnectionId;
    }

    public void setSocialConnectionId(long socialConnectionId) {
        this.socialConnectionId = socialConnectionId;
    }

    public User getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(User friendUser) {
        this.friendUser = friendUser;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
