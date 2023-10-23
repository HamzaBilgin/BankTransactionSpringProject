package com.bank.springbank.model;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Customer")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer extends BaseEntity{
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid2")
//	@Getter
//	@Setter
//	@Column(name = "customerUuid",unique = true)
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
//	private UUID customerUuid;
//	
	@Id
	@Column(unique = true,length = 11, nullable = false)
	@NotNull(message = "customerId field is required")
	@Getter
	@Setter
	private Long customerId;
	

	@Getter
	@Setter
	private Integer bankAmount;


	
}
