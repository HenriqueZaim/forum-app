package com.br.ng.forum.common;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;

import com.br.ng.forum.domains.question.answer.domain.Answer;
import com.br.ng.forum.domains.question.answer.persistence.AnswerRepository;
import com.br.ng.forum.domains.question.topic.domain.Topic;
import com.br.ng.forum.domains.question.topic.persistence.TopicRepository;
import com.br.ng.forum.domains.user.domain.User;
import com.br.ng.forum.domains.user.persistence.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {
    
    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public void instantiateTestDatabase() throws ParseException {
        User user1 = new User("User 1","user1@gmail.com", bCrypt.encode("qwer1234"), "USER", Collections.emptyList(), Collections.emptyList(), null);
        user1.setName("User 1");

        User user2 = new User("User 2","user2@gmail.com", bCrypt.encode("qwer1234"), "USER", Collections.emptyList(), Collections.emptyList(), null);
        user2.setName("User 2");

        userRepository.saveAll(
            Arrays.asList(
                user1,
                user2
            )
        );

        Topic topic1 = new Topic("eaeae", null, user1, Collections.emptyList());
        topic1.setText("aeaea");
        Topic topic2 = new Topic("afagaga", null, user2, Collections.emptyList());
        topic2.setText("gagaga");

        topicRepository.saveAll(
            Arrays.asList(
                topic1,
                topic2
            )
        );

        Answer answer1 = new Answer(user1, topic1, null, Collections.emptyList());
        answer1.setText("qwermqwme");

        Answer answer2 = new Answer(user2, topic1, null, Collections.emptyList());
        answer2.setText("akakakaka");

        Answer answer3 = new Answer(user2, topic1, answer1, Collections.emptyList());
        answer3.setText("kekekekeke");

        answerRepository.saveAll(
            Arrays.asList(
                answer1,
                answer2,
                answer3
            )
        ); 

        // Topic topic1 = new Topic("title", "enqewj kjqnwekj rnqwe rq", 0L, 1L, null, user1, Collections.emptyList());
        // topicRepository.save(topic1);

        // Answer answer1 = new Answer("qnwekjrnqwer", 0L, user2, topic1, null, Collections.emptyList());
        // answerRepository.save(answer1);

        
    }
}
