package com.br.ng.forum.domains.question.answer.web;

import com.br.ng.forum.common.CRUDViewModel;
import com.br.ng.forum.domains.question.answer.domain.Answer;

public class AnswerVM extends CRUDViewModel{
    
    public static AnswerVM from(Answer topic){
        return new AnswerVM();
    }
}
