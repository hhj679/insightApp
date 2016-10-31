package com.thx.insight.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thx.insight.service.StarrdService;

@RestController
@RequestMapping(value="/starred")
public class StarrdController {
	
	private static Logger LOG = Logger.getLogger(StarrdController.class);
	
	@Autowired
	private StarrdService starService;

    @RequestMapping(value="/{repo}", method=RequestMethod.GET)
    public List<?> index(@PathVariable String repo) {
    	repo = repo.replace("ppp;;;", "/");
    	LOG.info("query starred trends: " +  repo);
        return starService.getRepoStarsTimeLine(repo);
    }

}