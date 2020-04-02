package lv.accenture.bootcamp.rardb.network;

import java.util.ArrayList;
import java.util.List;

public class ImdbMovieList {
    public List< ImdbListData > Search = new ArrayList< ImdbListData >();
    private String totalResults;
    private String Response;


    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public void setResponse(String Response) {
        this.Response = Response;
    }
}
