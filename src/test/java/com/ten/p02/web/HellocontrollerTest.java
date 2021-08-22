package com.ten.p02.web;

import org.junit.jupiter.api.Test; // JUnit5
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class) //스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@WebMvcTest(controllers = HelloController.class)
public class HellocontrollerTest {

    @Autowired //Spring이 관리하는 빈(bean)을 주입 받는다.
    private MockMvc mvc;  //웹 API를 테스트할 때 사용한다.  스프링 MVC 테스트의 시작점이다.

    @Test
    public void hello_to_return() throws Exception{

        String hello = "hello2";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));

    }
    
    @Test
    public void helloDto_to_return() throws Exception {
        String name = "hello";
        int amount = 293;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
                ).andExpect(status().isOk()).andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
