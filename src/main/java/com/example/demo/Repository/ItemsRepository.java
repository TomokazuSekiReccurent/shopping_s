package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Items;

@Repository
public interface ItemsRepository  extends JpaRepository<Items, Integer> {
	
	List<Items>findByCode(Integer code);
//検索機能
	List<Items>findByNameContains(@Param("namePrefix")String str);

}

