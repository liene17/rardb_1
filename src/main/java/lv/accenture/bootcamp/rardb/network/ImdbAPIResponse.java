package lv.accenture.bootcamp.rardb.network;

public class ImdbAPIResponse {

    private String status;

    private ImdbMovieData results;

    public ImdbMovieData getResults() {

        return results;
    }
}
