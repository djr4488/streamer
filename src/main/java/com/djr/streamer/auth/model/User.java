package com.djr.streamer.auth.model;

import javax.persistence.*;

/**
 * Created by IMac on 7/31/2014.
 */
@Entity
@Table(name = "users")
public class User {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;


}
