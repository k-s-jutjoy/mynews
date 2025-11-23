package com.jutjoy.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jutjoy.domain.entity.News;

import javassist.compiler.ast.ASTList;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
	public ASTList findByTitleLike(String title);

	public List<News> findAllByOrderById();
}