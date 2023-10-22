package com.bank.springbank.model;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
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
public class Customer {
	
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
	
	@Column(length = 200, nullable = false)
	@Getter
	@Setter
	private String customerName;
	
	@Column(length = 2500, nullable = false)
	@Getter
	@Setter
	private String customerLastName;
	
	
	@Getter
	@Setter
	private LocalTime customerRegisterDate;

	@Getter
	@Setter
	private Integer customerBankAmount;


	
}
