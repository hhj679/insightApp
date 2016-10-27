package com.thx.insight.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Table(name = "users")
@Entity
public class User {

	@Id
	@Column(name = "id")
    private Long id;
	@Column(name = "login")
    private String login;
	@Column(name = "git_id")
    private Long git_id;
	@Column(name = "avatar_url")
    private String avatar_url;
	@Column(name = "gravatar_id")
    private String gravatar_id;
	@Column(name = "url")
    private String url;
	@Column(name = "html_url")
    private String html_url;
	@Column(name = "followers_url")
    private String followers_url;
	@Column(name = "following_url")
    private String following_url;
	@Column(name = "gists_url")
    private String gists_url;
	@Column(name = "starred_url")
    private String starred_url;
	@Column(name = "subscriptions_url")
    private String subscriptions_url;
	@Column(name = "organizations_url")
    private String organizations_url;
	@Column(name = "repos_url")
    private String repos_url;
	@Column(name = "events_url")
    private String events_url;
	@Column(name = "received_events_url")
    private String received_events_url;
	@Column(name = "type")
    private String type;
	@Column(name = "site_admin")
    private Integer site_admin;
	@Column(name = "name")
    private String name;
	@Column(name = "company")
    private String company;
	@Column(name = "blog")
    private String blog;
	@Column(name = "location")
    private String location;
	@Column(name = "email")
    private String email;
	@Column(name = "hireable")
    private Integer hireable;
	@Column(name = "bio")
    private String bio;
	@Column(name = "public_repos")
    private Integer public_repos;
	@Column(name = "public_gists")
    private Integer public_gists;
	@Column(name = "followers")
    private Integer followers;
	@Column(name = "following")
    private Integer following;
	@Column(name = "created_at")
    private Timestamp created_at;
	@Column(name = "updated_at")
    private Timestamp updated_at;
}
