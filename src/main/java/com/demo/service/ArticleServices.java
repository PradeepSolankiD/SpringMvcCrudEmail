package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ArticleDao;
import com.demo.model.Article;

@Service
public class ArticleServices {

    @Autowired
    private ArticleDao articleDao;

    @Transactional(readOnly = true)
    public List<Article> getAllArticles() {
        return articleDao.getAllArticle();
    }

    @Transactional(readOnly = true)
    public Article getById(Long id) {
        return articleDao.getArticleById(id);
    }

    @Transactional
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Transactional
    public void addArticle(Article article) {
        articleDao.addArticle(article);
    }

    @Transactional
    public void deleteArticle(Long id) {
        articleDao.deleteArticle(id);
    }
}