package com.iotstar.onlinetest.services.question;

import com.iotstar.onlinetest.DTOs.requests.QuestionImageRequest;
import com.iotstar.onlinetest.DTOs.requests.QuestionRequest;
import com.iotstar.onlinetest.DTOs.responses.QuestionResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Question;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.QuestionDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.services.answer.AnswerService;
import com.iotstar.onlinetest.services.topic.TopicServiceImp;
import com.iotstar.onlinetest.services.user.UserServiceImp;
import com.iotstar.onlinetest.statval.EQuestion;
import com.iotstar.onlinetest.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImp extends QuestionPaging implements QuestionService{


    @Autowired
    private ModelMapper mapper;
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private TopicServiceImp topicServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;

    private Question question;

    public Question getQuestionReturnQuestion(Long questionId){
        return questionDAO.findById(questionId).orElseThrow(()->
                new ResourceNotFoundException(EQuestion.QUESTION_NOTFOUND.getDes(questionId)));
    }
    public List<Question> getQuestionsByTopicReturnList(Long topicId){
        return topicServiceImp.getQuestionsInTopic(topicId);
    }

    @Override
    public QuestionResponse getQuestionById(Long id) {
        question = getQuestionReturnQuestion(id);
        return mapper.map(question, QuestionResponse.class);
    }

    @Override
    public List<QuestionResponse> getQuestionByTopicId(Long id) {
        List<Question> questions = getQuestionsByTopicReturnList(id);

        List<QuestionResponse> questionResponses = new ArrayList<>();
        for (Question i: questions){
            if (i.getStatus()!= 0)
                questionResponses.add(mapper.map(i, QuestionResponse.class));
        }
        return questionResponses;
    }

    @Override
    public List<QuestionResponse>  getQuestionByUserId(Long id) {
        List<Question> questions = userServiceImp.getQuestionsInUser(id);

        List<QuestionResponse> questionResponses = new ArrayList<>();
        for (Question i: questions){
            questionResponses.add(mapper.map(i, QuestionResponse.class));
        }
        return questionResponses;
    }


    @Override
    public Question findById(Long id){
        question = getQuestionReturnQuestion(id);
        return question;
    }

//    private String getUrlImage(MultipartFile fileInput, Long id){
//
//        return fileUtils.upload(fileInput, AppConstant.IMG_NAME_QUESTION+id);
//    }

    @Override
    @Transactional
    public Question updateImg(QuestionImageRequest questionImageRequest){
        question = findById(question.getQuestionId());
        question.setImage(fileUtils.upload(questionImageRequest.getImage(),
                EQuestion.IMG_NAME_QUESTION.getDes(questionImageRequest.getQuestionId())));
        return questionDAO.save(question);
    }

    @Override
    @Transactional
    public Question createQuestion(QuestionRequest questionRequest, Long userId) {
        //Get User
        User user = userServiceImp.getUserReturnUser(userId);

        question = new Question();
        question.setStatus(1);
        question.setUser(user);
        question.setTopic(topicServiceImp.getTopicReturnTopic(questionRequest.getTopicId()));
        question.setQuestion(questionRequest.getQuestion());
        question = questionDAO.save(question);

        question.setImage(fileUtils.upload(questionRequest.getImage(),
                EQuestion.IMG_NAME_QUESTION.getDes(question.getQuestionId())));

        question = questionDAO.save(question);
        answerService.createAnswers(questionRequest.getAnswers(), question);
        return question;
    }

    @Override
    @Transactional
    public void deleteQuestion(Long questionId) {
        question = getQuestionReturnQuestion(questionId);
        question.setStatus(0);
        questionDAO.save(question);
    }
}
