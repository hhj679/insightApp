package com.thx.insight.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Table(name = "project_stars_stastics")
@Entity
public class Starrd extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "repo_full_name")
	private String repoFullName;
	
	@Column(name = "user_login")
	private String userLogin;
	
	@Column(name = "starred_at")
	private Timestamp starredAt;
}
