package lv.accenture.bootcamp.rardb.network;

import java.io.Serializable;

public class ImdbListData implements Serializable {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;


    // Getter Methods

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getPoster() {
        return Poster;
    }

    // Setter Methods

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }
}
