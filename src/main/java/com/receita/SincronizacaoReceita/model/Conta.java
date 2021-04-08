package com.receita.SincronizacaoReceita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conta")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "agencia")
	private String agencia;

	@Column(name = "conta")
	private String conta;

	@Column(name = "saldo")
	private double saldo;
	
	@Column(name = "status")
	private String status;

	public Conta() {
	}

	public Conta(String agencia, String conta, double saldo, String status) {
		//this.id = id;
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = saldo;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setName(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", agencia=" + agencia + 
				", conta=" + conta + ", saldo=" + saldo + ", status=" + status + "]";
	}

}