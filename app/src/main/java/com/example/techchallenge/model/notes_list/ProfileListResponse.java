package com.example.techchallenge.model.notes_list;

import com.example.techchallenge.model.notes_list.Invites;
import com.example.techchallenge.model.notes_list.Likes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileListResponse {
    @SerializedName("invites")
    @Expose
    private Invites invites;
    @SerializedName("likes")
    @Expose
    private Likes likes;

    public Invites getInvites() {
        return invites;
    }

    public void setInvites(Invites invites) {
        this.invites = invites;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }
}
