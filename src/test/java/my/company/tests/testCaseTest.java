package my.company.tests;

import io.qameta.allure.Description;
import org.junit.Test;


public class testCaseTest {

    @Description("Отправка GET запроса на сайт}")
    @Test
    public void getPost(){
        getPostsTest get = new getPostsTest();
        get.getPostList("http://jsonplaceholder.typicode.com/posts",5000,5000);
    }



}
