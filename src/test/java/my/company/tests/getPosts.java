package my.company.tests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Assert;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


public class getPosts {
    private static HttpURLConnection connection;

    @Step("Отправка GET запроса на сайт {targetUrl}")
    public void getPostList(String targetUrl, int connectionTimeOut, int readTimeOut) {

        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try {
            URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(connectionTimeOut);
            connection.setReadTimeout(readTimeOut);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status > 299) {
                System.out.println("fail");
                Assert.assertTrue("Статус код от сервера не является валидным",status >299);
            }
            else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
                parsePost(responseContent.toString());
            }
        } catch (MalformedURLException e) {
            Assert.assertNull(e);
        } catch (IOException e) {
            Assert.assertNull(e);
        } finally {
            connection.disconnect();
        }

    }

    @Attachment(value = "Вложение", type = "application/json", fileExtension = ".txt")
    @Step("Парсинг полученного ответа")
    private String parsePost(String responseBody){
        Assert.assertNotNull(responseBody);
        JSONArray albums = new JSONArray(responseBody);
        for (int i =0; i < albums.length(); i++){
            JSONObject album = albums.getJSONObject(i);
            int id = album.getInt("id");
            int userId = album.getInt("userId");
            String title = album.getString("title");
            String body = album.getString("body");
            System.out.println("Id:"+ id + "\nTitle:" + title + "\nUserId:" + userId + "\nBody:" + body);
            System.out.println("--------------------------------------------");
        }
        System.out.println(albums.toString());
        return albums.toString();

    }

}
