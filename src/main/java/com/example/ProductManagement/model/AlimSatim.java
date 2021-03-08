package com.example.ProductManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.ProductManagement.controller.ProductController;
@Entity
public class AlimSatim {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String miktar;
	private String testOrder;
	public int toplam=0;
	public String getMiktar() {
		return miktar;
	}
	public void setMiktar(String miktar) {
		this.miktar = miktar;
	}
	public String getTestOrder() {
		return testOrder;
	}
	public void setTestOrder(String testOrder) {
		this.testOrder = testOrder;
	}

	
}
