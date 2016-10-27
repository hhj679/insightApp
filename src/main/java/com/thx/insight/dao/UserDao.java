package com.thx.insight.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thx.insight.entity.User;

@Repository
public interface UserDao extends BaseDao<User, Long> {

//	@Query("select q.questionId from QuestionFocus q where q.uid=:uid")
//	List<Long> getFocusQuestionIdsByUid(@Param("uid") Long uid);
//	
//	@Query("select q.questionId from QuestionUninterested q where q.uid=:uid")
//	List<Long> getQuestionUninterested(@Param("uid") Long uid);
}
