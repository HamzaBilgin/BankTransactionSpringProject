package com.bank.springbank.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Transactions")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
	@Getter
	@Setter
	@Column(name = "transactionUuid", unique = true)
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID transactionUuid;

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid2")
//	@Column(unique = true,length = 11, nullable = false)
//	@Getter
//	@Setter
//	private Long id;

	@Getter
	@Setter
	private LocalTime date;

	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private Transaction_Type transactionType = Transaction_Type.MONEYTRANSFER;

	@Getter
	@Setter
	private double amount;

	@Getter
	@Setter
	private Long sourceAccountId;

	@Getter
	@Setter
	private Long targetAccountId;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Pattern(regexp = "VALID_PATTERN_REGEX")
	@Getter
	@Setter
	private Status_Type status;
}
