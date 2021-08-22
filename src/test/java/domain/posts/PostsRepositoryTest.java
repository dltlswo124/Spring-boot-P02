package domain.posts;

import com.sun.corba.se.impl.ior.OldObjectKeyTemplateBase;
import com.ten.p02.Application;
import com.ten.p02.domain.posts.Posts;
import com.ten.p02.domain.posts.PostsRepository;
import com.ten.p02.web.HelloController;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class) //스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@SpringBootTest(classes = Application.class)
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void clanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void boar_save_search() {
        String title = "테스트 게시글";
        String content = "본문내용";

        postsRepository.save(Posts.builder().title(title)
                .content(content)
                .author("ausinjae@naver.com")
                .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);


    }

    @Test
    public void BaseTimeEntity_save(){
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>create date=" + posts.getCreateDate()+", modifiedDate = " + posts.getModifiedDate());


        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
