package com.br.ng.forum.services;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;

import com.br.ng.forum.models.Post;
import com.br.ng.forum.models.User;
import com.br.ng.forum.repositories.PostRepository;
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
    private PostRepository postRepository;

    public void instantiateTestDatabase() throws ParseException {
        User user1 = new User("User 1","user1@mail.com", bCrypt.encode("qwer1234"), "USER", Collections.emptyList(), null);
        user1.setCreatedAt(OffsetDateTime.now());

        User user2 = new User("User 2","user2@mail.com", bCrypt.encode("qwer1234"), "USER", Collections.emptyList(), null);
        user2.setCreatedAt(OffsetDateTime.now());

        User user3 = new User("User 3","user3@mail.com", bCrypt.encode("qwer1234"), "USER", Collections.emptyList(), null);
        user3.setCreatedAt(OffsetDateTime.now());

        userRepository.saveAll(
            Arrays.asList(
                user1,
                user2,
                user3
            )
        );

        Post post1 = new Post("Programação para iniciantes", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user1, null, Collections.emptyList());
        post1.setCreatedAt(OffsetDateTime.now());
        
        Post post2 = new Post("Discussão para saber qual a melhor linguagem de programação.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user2, null, Collections.emptyList());
        post2.setCreatedAt(OffsetDateTime.now());
        
        Post post3 = new Post("Microservices vs Monolitos", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user3, null, Collections.emptyList());
        post3.setCreatedAt(OffsetDateTime.now());

        Post post4 = new Post("Programação para iniciantes", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user1, null, Collections.emptyList());
        post4.setCreatedAt(OffsetDateTime.now());
        
        Post post5 = new Post("Discussão para saber qual a melhor linguagem de programação.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user2, null, Collections.emptyList());
        post5.setCreatedAt(OffsetDateTime.now());
        
        Post post6 = new Post("Microservices vs Monolitos", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user3, null, Collections.emptyList());
        post6.setCreatedAt(OffsetDateTime.now());


        postRepository.saveAll(
            Arrays.asList(
                post1,
                post2,
                post3,
                post4,
                post5,
                post6
            )
        );

        Post reply1 = new Post(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user1, post2, Collections.emptyList());
        reply1.setCreatedAt(OffsetDateTime.now());
        
        Post reply2 = new Post(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user1, post5, Collections.emptyList());
        reply2.setCreatedAt(OffsetDateTime.now());
        
        Post reply3 = new Post(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user2, post1, Collections.emptyList());
        reply3.setCreatedAt(OffsetDateTime.now());
        
        Post reply4 = new Post(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user2, post3, Collections.emptyList());
        reply4.setCreatedAt(OffsetDateTime.now());
        
        Post reply5 = new Post(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user3, post1, Collections.emptyList());
        reply5.setCreatedAt(OffsetDateTime.now());
        
        Post reply6 = new Post(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user3, post4, Collections.emptyList());
        reply6.setCreatedAt(OffsetDateTime.now());

        Post reply7 = new Post(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user3, post6, Collections.emptyList());
        reply7.setCreatedAt(OffsetDateTime.now());
        
        Post reply8 = new Post(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user3, post1, Collections.emptyList());
        reply8.setCreatedAt(OffsetDateTime.now());

        Post reply9 = new Post(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user3, post5, Collections.emptyList());
        reply9.setCreatedAt(OffsetDateTime.now());
        
        Post reply10 = new Post(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor magna massa, quis consequat orci facilisis nec. Curabitur egestas, velit ut molestie tempor, tellus est euismod leo, vel laoreet justo.", user3, post1, Collections.emptyList());
        reply10.setCreatedAt(OffsetDateTime.now());

        postRepository.saveAll(
            Arrays.asList(
                reply1,
                reply2,
                reply3,
                reply4,
                reply5,
                reply6,
                reply7,
                reply8,
                reply9,
                reply10
            )
        );

        
    }
}
