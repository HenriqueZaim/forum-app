package com.br.ng.forum.domains.question.answer.web.viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.ng.forum.common.CRUDViewModel;
import com.br.ng.forum.config.exceptions.ObjectNotFoundException;
import com.br.ng.forum.domains.question.answer.domain.Answer;
import com.br.ng.forum.domains.question.answer.persistence.AnswerPersistenceService;
import com.br.ng.forum.domains.question.topic.domain.Topic;
import com.br.ng.forum.domains.question.topic.persistence.TopicPersistenceService;
import com.br.ng.forum.domains.question.topic.web.viewmodel.TopicVM;
import com.br.ng.forum.domains.user.domain.User;
import com.br.ng.forum.domains.user.persistence.UserPersistenceService;
import com.br.ng.forum.domains.user.web.viewmodel.UserVM;
import com.devskiller.friendly_id.FriendlyId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerVM extends CRUDViewModel{

    @NotNull(message = "Adicione um texto apra sua resposta")
    @NotBlank(message = "Campo Texto não pode ser vazio")
    @Min(value = 3, message = "Campo Texto deve possui mais que 3 caracteres")
    private String text;

    private Long upvotes = 0L;
    private Long visualizations = 0L; 

    @NotNull(message = "Esta resposta precisa de um dono")
    @NotBlank(message = "Esta resposta precisa de um dono")
    private String userFriendlyHash;
    private UserVM userVM;

    @NotNull(message = "Esta resposta precisa de uma questão")
    @NotBlank(message = "Esta resposta precisa de uma questão")
    private String topicFriendlyHash;
    private TopicVM topicVM;
    
    private String parentAnswerFriendlyHash;

    private List<AnswerVM> answers = new ArrayList<>();

    public void fill(Answer answer, 
    AnswerPersistenceService answerPersistenceService, 
    TopicPersistenceService topicPersistenceService, 
    UserPersistenceService userPersistenceService){

        answer.setText(text);
        
        if(null != parentAnswerFriendlyHash){
            Optional<Answer> parentAnswer = answerPersistenceService.findByHash(
                FriendlyId.toUuid(parentAnswerFriendlyHash), Answer.class);

            if(parentAnswer.isPresent())
                answer.setParentAnswer(parentAnswer.get());
        }

        Optional<User> user = userPersistenceService.findByHash(
            FriendlyId.toUuid(userFriendlyHash), User.class);
        if(!user.isPresent())
            throw new ObjectNotFoundException("Usuário não encontrado");
        answer.setUser(user.get());

        Optional<Topic> topic = topicPersistenceService.findByHash(
            FriendlyId.toUuid(topicFriendlyHash), Topic.class);
        if(!topic.isPresent())
            throw new ObjectNotFoundException("Tópico não encontrado");
        answer.setTopic(topic.get());
    }

    public static AnswerVM from(Answer answer){
        AnswerVM answerVM = new AnswerVM();
        answerVM.setText(answer.getText());
        answerVM.setUpvotes(answer.getUpvotes());
        answerVM.setVisualizations(answer.getVisualizations());
        answerVM.setFriendlyHash(answer.getFriendlyHash());

        answerVM.setUserVM(UserVM.from(answer.getUser()));
        answerVM.setTopicVM(TopicVM.from(answer.getTopic()));

        if(null != answer.getParentAnswer())
            answerVM.setParentAnswerFriendlyHash(answer.getParentAnswer().getFriendlyHash());
        
        if(null != answer.getAnswers())
            answerVM.setAnswers(answer.getAnswers()
                                .stream()
                                    .map(childAnswer -> AnswerVM.from(childAnswer))
                                    .collect(Collectors.toList()));

        return answerVM;
    }
}
