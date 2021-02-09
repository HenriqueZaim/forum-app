package com.br.ng.forum.services;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;

import com.br.ng.forum.models.Answer;
import com.br.ng.forum.models.Topic;
import com.br.ng.forum.models.User;
import com.br.ng.forum.repositories.AnswerRepository;
import com.br.ng.forum.repositories.TopicRepository;
import com.br.ng.forum.repositories.UserRepository;

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
        User user1 = new User("user1@gmail.com", bCrypt.encode("qwer1234"), "USER", Collections.emptyList(), Collections.emptyList());
        user1.setName("User 1");

        User user2 = new User("user2@gmail.com", bCrypt.encode("qwer1234"), "USER", Collections.emptyList(), Collections.emptyList());
        user2.setName("User 2");

        userRepository.saveAll(
            Arrays.asList(
                user1,
                user2
            )
        );

        Topic topic1 = new Topic("enqewj kjqnwekj rnqwe rq", 0L, 1L, user1, Collections.emptyList());
        topic1.setName("Tópico 1");

        topicRepository.save(topic1);

        Answer answer1 = new Answer("qnwekjrnqwer", 0L, user2, topic1, null, Collections.emptyList());

        answerRepository.save(answer1);

        
    }
}
