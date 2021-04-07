package com.receita.SincronizacaoReceita.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.receita.SincronizacaoReceita.model.Conta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long>{
}
