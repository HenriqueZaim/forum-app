package com.br.ng.forum.domains.question.topic.web.viewmodel;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import com.br.ng.forum.common.CRUDViewModel;
import com.br.ng.forum.domains.question.topic.domain.Topic;
import com.br.ng.forum.domains.user.domain.User;
import com.br.ng.forum.domains.user.persistence.UserPersistenceService;
import com.devskiller.friendly_id.FriendlyId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicVM extends CRUDViewModel{


    @NotBlank(message = "Campo Título não pode ser vazio")
    private String title;

    @NotBlank(message = "Campo Texto não pode ser vazio")
    private String text;

    private String userName;
    private String userImage;

    private Long upvotes = 0L;
    private Long visualizations = 0L; 

    private String userFriendlyHash;

    public void fill(Topic topic, UserPersistenceService userPersistenceService){
        topic.setTitle(title);
        topic.setText(text);
        topic.setUpvotes(upvotes);
        topic.setVisualizations(visualizations);

        if(null != userFriendlyHash){
            Optional<User> optionalUser = userPersistenceService.findByHash(FriendlyId.toUuid(userFriendlyHash), User.class);
            
            if(optionalUser.isPresent())
                topic.setUser(optionalUser.get());
        }

    }
    
    public static TopicVM from(Topic topic){
        TopicVM topicVM = new TopicVM();
        topicVM.setTitle(topic.getTitle());
        topicVM.setText(topic.getText());
        topicVM.setFriendlyHash(FriendlyId.toFriendlyId(topic.getHash()));
        topicVM.setUpvotes(topic.getUpvotes());
        topicVM.setVisualizations(topic.getVisualizations());

        topicVM.setCreatedAt(topic.getCreatedAt());
        topicVM.setUpdatedAt(topic.getUpdatedAt());
        topicVM.setDeletedAt(topic.getDeletedAt());

        if(null != topic.getUser()){
            topicVM.setUserName(topic.getUser().getName());
            topicVM.setUserImage(topic.getUser().getImage());
            topicVM.setUserFriendlyHash(FriendlyId.toFriendlyId(topic.getUser().getHash()));
        }

        return topicVM;
    }
}
