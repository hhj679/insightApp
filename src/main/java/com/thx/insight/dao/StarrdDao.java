package com.thx.insight.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thx.insight.entity.Starrd;

@Repository
public interface StarrdDao extends BaseDao<Starrd, Long>{
	
	@Query("select s.repoFullName, DATE_FORMAT(s.starredAt, '%Y-%m'), count(s.userLogin)" +
			"from Starrd s where s.repoFullName=:repoFullName group by DATE_FORMAT(s.starredAt, '%Y-%m'), s.repoFullName order by DATE_FORMAT(s.starredAt, '%Y-%m')")
	List<?> getRepoStarsTimeLine(@Param("repoFullName") String repoFullName);
	
//	@Query("select r.itemId from TopicRelation r where r.topicId in :topicIds")
//	List<Long> getItemIdsByTopicIds(@Param("topicIds") Collection<Long> topicIds);
//	
//	@Query("select r.itemId from TopicRelation r where r.topicId in :topicIds and r.type=:type")
//	List<Long> getItemIdsByTopicIds(@Param("topicIds") Collection<Long> topicIds, 
//			@Param("type") String type);
}
