package com.example.ProductManagement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String kod;
    private String name;
    private int ucret;
    private Date tarih;
    private int miktar;
    private int toplamDeger;
    public int getMiktar() {
		return miktar;
	}

	public void setMiktar(int miktar) {
		this.miktar = miktar;
	}

	public int  getUcret() {
		return ucret;
	}

	public void setUcret(int ucret) {
		this.ucret = ucret;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}


	
	public User() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKod() {
		return kod;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getToplamDeger() {
		return toplamDeger;
	}

	public void setToplamDeger(int toplamDeger) {
		this.toplamDeger = toplamDeger;
	}

	
	
}
