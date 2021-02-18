package com.br.ng.forum.domains.question.topic.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.br.ng.forum.config.exceptions.ObjectNotFoundException;
import com.br.ng.forum.domains.question.topic.domain.Topic;
import com.br.ng.forum.domains.question.topic.persistence.TopicPersistenceService;
import com.br.ng.forum.domains.question.topic.web.viewmodel.TopicVM;
import com.br.ng.forum.domains.user.persistence.UserPersistenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicPersistenceService topicPersistenceService;

    @Autowired
    private UserPersistenceService userPersistenceService;

    // public Topic save(Topic topic){
    // User user = new User();
    // user.setId(loginService.authenticated().getId());
    // topic.setUser(user);
    // topic.setCreatedAt(OffsetDateTime.now());
    // return topicRepository.save(topic);
    // }

    // public Topic update(Topic topic){
    // topic.setCreatedAt(findById(topic.getId()).getCreatedAt());
    // User user = new User();
    // user.setId(loginService.authenticated().getId());
    // topic.setUser(user);
    // return topicRepository.save(topic);
    // }

    // public Topic findById(UUID id){
    // return topicRepository.findById(id).orElseThrow(() -> new
    // ObjectNotFoundException("Tópico não encontrado"));
    // }

    @Override
    public Page<Topic> pageable(Integer page, Integer linesPerPage) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage,
        Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Topic> list = topicPersistenceService.findAll(pageRequest);
        return list;
    }

    @Override
    @Transactional
    public Topic save(TopicVM vm) {
        Topic topic = new Topic();

        if (vm.isUpdate()) {
            Optional<Topic> optionalTopic = this.topicPersistenceService.findByHash(vm.getHash(), Topic.class);
            if (optionalTopic.isPresent()) {
                topic = optionalTopic.get();
            }
        }

        vm.fill(topic, userPersistenceService);

        this.topicPersistenceService.save(topic);
        return topic;
    }

    @Override
    public Optional<TopicVM> getEnabledForEditing(UUID hash) {
        Optional<Topic> optionalTopic = this.topicPersistenceService.findByHash(hash, Topic.class);

        if(!optionalTopic.isPresent()){
            throw new ObjectNotFoundException("Tópico não encontrado");
        }

        return Optional.of(TopicVM.from(optionalTopic.get()));
    }

    @Override
    public void removeLogicallyByHash(UUID hash) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<TopicVM> getAllAsList(UUID hash) {
        return topicPersistenceService.findByUserHash(hash).stream()
            .map(topic -> TopicVM.from(topic)).collect(Collectors.toList());
    }

}
