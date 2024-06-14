package com.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.demo.controller.ArticleController;
import com.demo.model.Article;

@Component
@Repository
public class ArticleDao {

	private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(ArticleController.class);

	@Autowired
	HibernateTemplate hibernateTemplate;

	// add article
	@Transactional
	public void addArticle(Article article) {
		hibernateTemplate.save(article);
		// Optional logging for debugging
		logger.info("Saving article: {}", article);
		
	}

	// get all article
	public List<Article> getAllArticle() {
		List<Article> articles = hibernateTemplate.loadAll(Article.class);
		for (Article article : articles) {
			Hibernate.initialize(article.getAuthors());
			Hibernate.initialize(article.getAccessCategory());
		}
		return articles;
	}

	// get article by id
	@Transactional
	public Article getArticleById(Long id) {
		Article article = hibernateTemplate.get(Article.class, id);
		if (article != null) {
			Hibernate.initialize(article.getAuthors());
			Hibernate.initialize(article.getAccessCategory());
		}
		return article;
	}

	// update article
	@Transactional
	public void updateArticle(Article article) {
	    hibernateTemplate.merge(article);  // Use merge instead of update for better handling of entity states
	}


	// delete article
	@Transactional
	public void deleteArticle(Long id) {
		Article article = hibernateTemplate.load(Article.class, id);
		if (article != null) {
			hibernateTemplate.delete(article);
		}
	}

}