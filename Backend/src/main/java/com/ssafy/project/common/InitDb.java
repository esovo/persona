package com.ssafy.project.common;


import com.ssafy.project.common.db.entity.base.EmotionEnum;
import com.ssafy.project.common.db.entity.base.GenreEnum;
import com.ssafy.project.common.db.entity.common.Script;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    public final InitService initService;

    @PostConstruct
    public void init(){
        initService.scriptInit();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        public final EntityManager em;


        public void scriptInit() {
            Script script1 =  createScript(EmotionEnum.기쁨, GenreEnum.드라마, "더 글로리", "김은숙", "문동은", "화이팅 박연진", 1L);
            em.persist(script1);
        }

        public Script createScript(EmotionEnum emotion, GenreEnum genre, String title, String author, String actor, String content, Long viewCnt){
            Script script = new Script();
            script.setEmotion(emotion);
            script.setGenre(genre);
            script.setTitle(title);
            script.setAuthor(author);
            script.setActor(actor);
            script.setViewCnt(viewCnt);

            return script;
        }
    }
}
