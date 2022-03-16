package com.e.pood.dao.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.e.pood.ApplicationDatabaseNames.USER;

@Entity(name = "User")
@Table(name = USER)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;

	@Column(name = "user_name")
	private String username;

	@Column(name = "user_password")
	private String password;

}
