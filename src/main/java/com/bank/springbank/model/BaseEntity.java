package com.bank.springbank.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BaseEntity {

	@Column(name = "VERSION")
	@Version
	@Setter
	@Getter
	private int version;
	
	@Id
	@Column(unique = true,length = 11, nullable = false)
	@Getter
	@Setter
	private Long id;
	
	@Column(length = 200, nullable = false)
	@Getter
	@Setter
	private String name;
	
	@Column(length = 200, nullable = false)
	@Getter
	@Setter
	private String lastName;
	
	@Column(length = 200, nullable = false)
	@Getter
	@Setter
	private Integer password;
	
	@Getter
	@Setter
	private Integer newPassword;
	
	@Getter
	@Setter
	private LocalTime createdDate;
	
	@Getter
	@Setter
	private LocalTime updatedDate;
}
