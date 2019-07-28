package com.javawiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer {
 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prooduct_id_seq")
	@SequenceGenerator(name = "prooduct_id_seq", sequenceName = "PRODUCT_ID_SEQ", allocationSize = 100)
    private Long id;
    
    @Column(name = "FIRSTNAME")
    private String firstName;
    
    @Column(name = "LASTNAME")
    private String lastName;
    
    public Customer(String firstName, String lastName) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    }
}