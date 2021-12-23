package my.company.tests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class postJson {

        @Step("Отправка POST запроса на сайт {targetUrl}")
    public void postJson(String targetUrl, JSONObject jsonDataToSend, int connectionTimeOut, int readTimeOut) {
        String urlAdress = targetUrl;
        HttpURLConnection connection;
        URL url;
        OutputStream os = null;
        InputStreamReader is = null;
        BufferedReader bfr = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            url = new URL(urlAdress);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.addRequestProperty("User-Agent", "Mozilla/5.0");
            connection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");

            connection.setConnectTimeout(connectionTimeOut);
            connection.setReadTimeout(readTimeOut);

            connection.connect();

            try {
                os = connection.getOutputStream();
                os.write(jsonDataToSend.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            int status = connection.getResponseCode();
            if (status > 299) {
                System.out.println(status);
                Assert.assertFalse("Статус код("+ status+") от сервера не является валидным", status > 299);
            } else {
                System.out.println(status);
                String line;
                is = new InputStreamReader(connection.getInputStream());
                bfr = new BufferedReader(is);
                while ((line = bfr.readLine()) != null) {
                    stringBuilder.append(line);
                }
                is.close();
                System.out.println(stringBuilder.toString());
                parsePost(stringBuilder);
            }

        } catch (IOException e) {
            Assert.assertNull("",e);
        }
    }


    @Attachment(value = "Вложение", type = "application/json", fileExtension = ".txt")
    @Step("Чтение полученного ответа")
    private String parsePost(StringBuilder responseBody) {
        Assert.assertNotNull(responseBody);{
        }
        return responseBody.toString();

    }
}


//
//        String urlAdress = targetUrl;
//        URL url;
//        HttpURLConnection connection;
//        OutputStream os = null;
//        InputStreamReader is = null;
//        BufferedReader bfr = null;
//        StringBuilder stringBuilder = new StringBuilder();
//
//        JSONObject json = new JSONObject();
//        json.put("grant_type",12);
//        json.put("client_id",1);
//        json.put("client_secret",45);
//        json.put("username",24);
//
//        try {
//            url = new URL(urlAdress);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//
//            connection.addRequestProperty("User-Agent", "Mozilla/5.0");
//            connection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
//
//            connection.setConnectTimeout(connectionTimeOut);
//            connection.setReadTimeout(readTimeOut);
//            connection.connect();
//
//            try {
//                os = connection.getOutputStream();
//                os.write(json.toString().getBytes());
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//            if (connection.getResponseCode() >= 299){
//                String line;
//                is = new InputStreamReader(connection.getInputStream());
//                bfr = new BufferedReader(is);
//                while ((line =bfr.readLine()) != null){
//                    stringBuilder.append(line);
//                }
//            }
//            else {
//                Assert.assertTrue(connection.getResponseCode() >= 299);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            bfr.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return stringBuilder.toString();
//    }
//
//}


//    String urlAdress = "http://jsonplaceholder.typicode.com/posts";
//    URL url;
//    HttpURLConnection httpURLConnection;
//    OutputStream os = null;
//    InputStreamReader is = null;
//    BufferedReader bfr = null;
//    StringBuilder stringBuilder = new StringBuilder();
//
//    Map<String, String> postargs = new HashMap<>();
//        postargs.put("user", "Max");
//                postargs.put("pass", "123");
//                postargs.put("id","123");
//
//                byte[] out = postargs.toString().getBytes();
//
//
//                try {
//                url = new URL(urlAdress);
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setDoInput(true);
//
//                httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
//                httpURLConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//                httpURLConnection.setConnectTimeout(5000);
//                httpURLConnection.setReadTimeout(5000);
//
//                httpURLConnection.connect();
//
//                try {
//                os = httpURLConnection.getOutputStream();
//                os.write(out);
//                } catch (IOException e){
//                e.printStackTrace();
//                }
////            if (httpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()){
//                String line;
//                is = new InputStreamReader(httpURLConnection.getInputStream());
//                bfr = new BufferedReader(is);
//                while ((line =bfr.readLine()) != null){
//                stringBuilder.append(line);
//
//                }
////            }
//                System.out.println(httpURLConnection.getResponseCode());
//                System.out.println(stringBuilder);
//
//                }catch (IOException e){
//                e.printStackTrace();
//                } finally {
//                try {
//                is.close();
//                } catch (IOException e) {
//                e.printStackTrace();
//                }
//                }
//                try {
//                bfr.close();
//                } catch (IOException e) {
//                e.printStackTrace();
//                }
//                try {
//                os.close();
//                } catch (IOException e) {
//                e.printStackTrace();
//                }
//                }