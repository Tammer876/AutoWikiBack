package com.springboot.autowiki.repository;

import com.springboot.autowiki.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long> {

    Optional<Article> findByApprovalToken(String token);
    Optional<Article> findByDenialToken(String token);


}
