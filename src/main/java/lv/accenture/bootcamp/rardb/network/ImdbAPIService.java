package lv.accenture.bootcamp.rardb.network;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
    public class ImdbAPIService {

        @Value("${api.request}")
        private String requestUrl;

        public ImdbMovieData getImdbMovie() {
            try {
                Path path = Paths.get("./api_key.txt");
                List<String> fileData = Files.readAllLines(path);
//                System.out.println("fileData = " + fileData);
                String listString = String.join("\n ", fileData);

                URL url = new URL(requestUrl + "apikey=" + listString + "&t=iron");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(3000);
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                String jsonResponse = sb.toString();

                bufferedReader.close();

                Gson gson = new Gson();
                ImdbMovieData imdbMovieData = gson.fromJson(jsonResponse, ImdbMovieData.class);

                return imdbMovieData;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

