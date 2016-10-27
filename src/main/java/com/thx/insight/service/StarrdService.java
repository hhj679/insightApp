package com.thx.insight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thx.insight.dao.BaseDao;
import com.thx.insight.dao.StarrdDao;
import com.thx.insight.entity.Starrd;

@Service
@Transactional
public class StarrdService extends BaseService<Starrd, Long> {
	
	@Autowired
    private StarrdDao starrdDao;

	@Override
	protected BaseDao<Starrd, Long> getEntityDao() {
		// TODO Auto-generated method stub
		return starrdDao;
	}
	
	public List<?> getRepoStarsTimeLine(String repoFullName) {
		List<?> starrds = starrdDao.getRepoStarsTimeLine(repoFullName);
		for(int i=1; i<starrds.size(); i++) {
			Object[] nMonthStarrd = (Object[]) starrds.get(i);
			Object[] lMonthStarrd = (Object[]) starrds.get(i-1);
			nMonthStarrd[2] = (Long)lMonthStarrd[2] + (Long)nMonthStarrd[2];
		}
		return starrds;
	}
}
