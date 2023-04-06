package com.ssafy.project.common.util.init;


import com.ssafy.project.common.db.entity.base.EmotionEnum;
import com.ssafy.project.common.db.entity.base.GenreEnum;
import com.ssafy.project.common.db.entity.common.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    public final InitService initService;

    @PostConstruct
    public void init(){
        initService.scriptInit();
        initService.participantInit();
        initService.videoInit();
        initService.boardInit();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        public final EntityManager em;

        public void scriptInit() {
            Script script1 =  createScript(EmotionEnum.기쁨, GenreEnum.드라마, "더 글로리", "김은숙", "문동은", "화이팅 박연진! 브라보! 멋지다, 연진아!", 0L);
            em.persist(script1);

            Script script3 = createScript(EmotionEnum.두려움, GenreEnum.드라마, "펜트 하우스", "김순옥", "김소연", "기다릴 시간이 없었어. 내 예감이 맞았어. 당신 와이프, 민설아와 관계있어. 당신 와이프가 오늘 누굴 만났는지 알아? 민설아를 키운 보육원 원장을 만났어. 그것도 구치소에서. 민형식 원장. 알아보니 조상원 의원의 신복이었대. 보육원 아이들 골수 팔이까지 한 흉악범을 당신 와이프가 왜 면회까지 하겠어. 틀림없이 민설아와 관련이 있는거야. 민설아가 죽는 걸 목격했든 아님 민설아와 원래 아는 사람이든. 민설아, 고아잖아. 혹시 당신 와이프가 민설아 친부모를 알고 있는 거 아니야? 1%의 의심점이라도 있다면 확인해봐야 될 거 아니야. 당신 와이프 뭔가 있어. 내 말 명심해. 절대 그냥 넘어갈 일 아니야.", 0L);
            em.persist(script3);

            Script script4 = createScript(EmotionEnum.중립, GenreEnum.영화, "명랑", "김한민", "최민식", "아직도 살고자 하는 자가 있다니, 통탄을 금치 못할 일이다. 우리는 죽음을 피할 수 없다. 정녕 싸움을 피하는 것이 우리가 사는 길이냐? 육지라고 무사할듯 싶으냐? 똑똑히 봐라 나는 바다에서 죽고자 이곳을 불태운다. 더 이상 살 곳도 물러설 곳도 없다. 목숨을 기대지마라. 살고자 하면 필히 죽을 것이고 또한 죽고자 하면 살 것이니 병법에 이르기를 한 사람이 길목을 잘 지키면은 천 명의 적도 떨게 할수있다 하였다. 바로 지금 우리가 처한 형국을 두고 하는 말 아니더냐?", 0L);
            em.persist(script4);

            Script script5 = createScript(EmotionEnum.화남, GenreEnum.드라마, "더 글로리","김은숙", "하도영", "아내의 불륜 상대가 내 딸 학교에 가서 친부 행세를 했어. 그래서 화가 나, 받아쳐봐. 내가 아나 모르나 궁금하지? 예솔이가 내 딸인지 아닌지? 어 나 알아, 대꾸해 봐. 내가 어떤 마음으로 참고 있는데!!! 이 순간에도 넌 네가 얼마나 안전한지 그것만 궁금해? 예솔이가 그 지경인데! 네가 지금 날 떠봐?", 0L);
            em.persist(script5);

            Script script6 = createScript(EmotionEnum.슬픔, GenreEnum.영화, "쇼생크 탈출", "프랭크 다라본트", "앤디", "Remember Red, hope is good thing, maybe the best of things, and no good thing ever dies", 0L);
            em.persist(script6);

            Script script7 = createScript(EmotionEnum.기쁨, GenreEnum.영화, "포레스트 검프", "에릭 로스", "톰 행크스", "Mama always said ... Life is like a box of chocolates. You never know what you're gonna get!", 0L);
            em.persist(script7);

            Script script8 = createScript(EmotionEnum.기쁨, GenreEnum.드라마, "오징어 게임", "황동혁", "오영수", "우린 깐부잖아 깐부 사이에는, 네 거 내 거가 없는거야", 0L);
            em.persist(script8);

            Script script9 = createScript(EmotionEnum.두려움, GenreEnum.드라마, "오징어 게임", "황동혁", "오영수", "제발 그만해! 이러다가는 다 죽어! 다 죽는 단 말이야!!!", 0L);
            em.persist(script9);

            Script script10 = createScript(EmotionEnum.화남, GenreEnum.드라마, "오징어 게임", "황동혁", "이정재", "사람이 죽었다고요! 내 말 안들려? 이러면 안 되는 거잖아! 우리끼리 죽이면 안 되는 거잖아", 0L);
            em.persist(script10);

            Script script11 = createScript(EmotionEnum.놀람, GenreEnum.드라마, "대장금", "김영현", "조정은", "예? 저는 제 입에서 고기를 씹을 때 홍시 맛이 났는데, 어찌 홍시라 생각했느냐 하시면, 그냥 홍시 맛이 나서 홍시라 생각한거 뿐인데...", 0L);
            em.persist(script11);

            Script script12 = createScript(EmotionEnum.역겨움, GenreEnum.드라마, "더 글로리", "김은숙", "하도영", "이거 상상했던 것보다 훨씬 더 개새끼네", 0L);
            em.persist(script12);
        }

        public void participantInit() {
            Participant participant1 =  createParticipant(1L);
            em.persist(participant1);

            Participant participant2 =  createParticipant(1L);
            em.persist(participant2);

            Participant participant3 =  createParticipant(1L);
            em.persist(participant3);

            Participant participant4 =  createParticipant(1L);
            em.persist(participant4);

            Participant participant5 =  createParticipant(1L);
            em.persist(participant5);

            Participant participant6 =  createParticipant(1L);
            em.persist(participant6);

            Participant participant7 =  createParticipant(2L);
            em.persist(participant7);

            Participant participant8 =  createParticipant(1L);
            em.persist(participant8);

            Participant participant9 =  createParticipant(1L);
            em.persist(participant9);

            Participant participant10 =  createParticipant(1L);
            em.persist(participant10);

            Participant participant11 =  createParticipant(1L);
            em.persist(participant11);

            Participant participant12 =  createParticipant(1L);
            em.persist(participant12);

            Participant participant13 =  createParticipant(1L);
            em.persist(participant13);
        }

        public void videoInit() {
            Video video = createVideo("더 글로리","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/OhjUvBRAIQE/0.jpg", "강렬하게 연기할 것",1L, 1L);
            em.persist(video);
            Video video1 = createVideo("펜트 하우스","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/ZLSDGoG1GAQ/0.jpg", "강렬하게 연기할 것",1L, 2L);
            em.persist(video1);
            Video video2 = createVideo("명랑","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/JLd2a4KKU60/0.jpg", "강렬하게 연기할 것",1L, 3L);
            em.persist(video2);
            Video video3 = createVideo("더 글로리","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/aAgk_aYV2yg/0.jpg", "강렬하게 연기할 것",1L, 4L);
            em.persist(video3);
            Video video4 = createVideo("쇼생크 탈출","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/7yMxYwKpz6E/0.jpg", "강렬하게 연기할 것",1L, 5L);
            em.persist(video4);
            Video video5 = createVideo("포레스트 검프","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/fGpvI4bdafA/0.jpg", "강렬하게 연기할 것",1L, 6L);
            em.persist(video5);
            Video video6 = createVideo("오징어 게임","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/NDTJMLp43JA/0.jpg", "강렬하게 연기할 것",1L, 7L);
            em.persist(video6);
            Video video7 = createVideo("오징어 게임","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/NDTJMLp43JA/0.jpg", "강렬하게 연기할 것",1L, 8L);
            em.persist(video7);
            Video video8 = createVideo("오징어 게임","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/NDTJMLp43JA/0.jpg", "강렬하게 연기할 것",1L, 9L);
            em.persist(video8);
            Video video9 = createVideo("대장금","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/NDTJMLp43JA/0.jpg", "강렬하게 연기할 것",1L, 10L);
            em.persist(video9);
            Video video10 = createVideo("더 글로리","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/uservideo.mp4","https://step-up-bucket.s3.ap-northeast-2.amazonaws.com/fileName.jpg", "https://img.youtube.com/vi/NDTJMLp43JA/0.jpg", "강렬하게 연기할 것",1L, 11L);
            em.persist(video10);
        }

        public void boardInit() {
            Board board = createBoard("연기 평가해주시면 감사하겠습니다!", "연기 코멘트 부탁드립니다", 0L,  1L, 1L);
            em.persist(board);
            Board board1 = createBoard("연기 시작한지 얼마 안된 연기 병아리 입니다. 잘 부탁드립니다!!", "펜트 하우스", 0L,  1L, 2L);
            em.persist(board1);
            Board board2 = createBoard("연기 평가해주시면 감사하겠습니다!", "명량", 0L,  1L, 3L);
            em.persist(board2);
            Board board3 = createBoard("날카롭게 지적해주셔도 됩니다. 평가 부탁드립니다.", "날카로운 평가 부탁드립니다", 0L,  1L, 4L);
            em.persist(board3);
            Board board4 = createBoard("연기 앤디역 연기 평가해주세요!", "쇼생크 탈출 - 앤디", 0L,  1L, 5L);
            em.persist(board4);
            Board board5 = createBoard("연기 평가 부탁드립니다!", "포레스트 검프 - 톰 행크스", 0L,  1L, 6L);
            em.persist(board5);
            Board board6 = createBoard("오영수역 연기 코멘트해주세요~!", "오징어게임 - 오영수 1", 0L,  1L, 7L);
            em.persist(board6);
            Board board7 = createBoard("오영수역 다른 대사를 연기해보았습니다. 연기 코멘트해주세요~!", "오징어게임 - 오영수 2", 0L,  1L, 8L);
            em.persist(board7);
            Board board8 = createBoard("연기 평가 남겨주세요!", "오징어 게임 - 이정재", 0L,  1L, 9L);
            em.persist(board8);
            Board board9 = createBoard("연기 평가해주시면 감사하겠습니다!", "연기 코멘트 부탁드립니다.", 0L,  1L, 10L);
            em.persist(board9);
            Board board10 = createBoard("요즘 핫한 더 글로리 연기에 도전합니다. 연기 평가해주시면 감사하겠습니다!", "더 글로리", 0L,  1L, 11L);
            em.persist(board10);
        }

        public Script createScript(EmotionEnum emotion, GenreEnum genre, String title, String author, String actor, String content, Long viewCnt){
            Script script = new Script();
            script.setEmotion(emotion);
            script.setGenre(genre);
            script.setTitle(title);
            script.setAuthor(author);
            script.setActor(actor);
            script.setViewCnt(viewCnt);
            script.setContent(content);
            return script;
        }
        public Participant createParticipant(Long boardId){
            Participant participant = new Participant();
            participant.setParticipateDate(LocalDateTime.now());
            participant.setScript(Script.builder().id(boardId).build());
            return participant;
        }

        public Video createVideo(String title, String videoUrl, String graphUrl, String thumbnailUrl, String analysis, Long userId, Long participantId){
            Video video = new Video();
            video.setTitle(title);
            video.setVideoUrl(videoUrl);
            video.setGraphUrl(graphUrl);
            video.setThumbnailUrl(thumbnailUrl);
            video.setAnalysis(analysis);
            video.setUser(User.builder().id(userId).build());
            video.setParticipant(Participant.builder().id(participantId).build());
            return video;
        }
        public Board createBoard(String content, String title, Long viewCnt, Long userId, Long videoId){
            Board board = new Board();
            board.setContent(content);
            board.setTitle(title);
            board.setViewCnt(viewCnt);
            board.setUser(User.builder().id(userId).build());
            board.setVideo(Video.builder().id(videoId).build());
            return board;
        }
    }
}
