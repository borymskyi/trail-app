package com.borymskyi.trail.pojo;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */
public class TrailRequest {

    String title;

    public TrailRequest() {
    }

    public TrailRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean validateTitle(String titleTrail) {
        if (titleTrail != null && !titleTrail.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
