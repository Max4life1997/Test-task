package testsCases;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.junit4.DisplayName;
import methods.getPosts;
import methods.postJson;
import org.json.JSONObject;
import org.junit.Test;


public class testCaseTest {

    getPosts get = new getPosts();
    postJson post = new postJson();

    @Issue("1")
    @DisplayName("Отправка GET запроса")
    @Description("Отправка GET запроса на сайт")
    @Test
    public void getPost(){
        String targetUrl = "http://jsonplaceholder.typicode.com/posts";
        int connectionTimeOut = 5000;
        int connectionReadTime = 5000;

        get.getPostList(targetUrl,connectionTimeOut,connectionReadTime);
    }


    @Issue("2")
    @DisplayName("Отправка POST запроса")
    @Description("Тестирование модуля на отправку POST запроса на сайт {targetUrl}")
    @Test
    public void postPosts(){
        String targetUrl = "http://jsonplaceholder.typicode.com/posts";
        int connectionTimeOut = 5000;
        int connectionReadTime = 5000;
        JSONObject jsonDataToSend = new JSONObject();
        jsonDataToSend.put("title","Max");
        jsonDataToSend.put("body","Body of the Text");

        post.postJson(targetUrl,jsonDataToSend,connectionTimeOut,connectionReadTime);
    }

}
