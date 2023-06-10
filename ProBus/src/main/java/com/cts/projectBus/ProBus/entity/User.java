package com.cts.projectBus.ProBus.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String userId;
	private String userName;
	private String password;
	private int noOfTimesBooking;
	@Id
	private String email;
	@OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
	private List<Transaction> transactions;

}
