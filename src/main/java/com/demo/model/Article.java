package com.demo.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.demo.model.Author;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private Date articleDate;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccessCategory> accessCategory = new ArrayList<>();

	private String description;
	private Date freeViewExpiry;
	private String publicUrl;
    private String imagePath; 

	public Article() {
		super();
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", articleDate=" + articleDate + ", authors=" + authors
				+ ", accessCategory=" + accessCategory + ", description=" + description + ", freeViewExpiry="
				+ freeViewExpiry + ", publicUrl=" + publicUrl + ", imagePath=" + imagePath + "]";
	}

	public Article(Long id, String title, Date articleDate, List<Author> authors, List<AccessCategory> accessCategory,
			String description, Date freeViewExpiry, String publicUrl, String imagePath) {
		super();
		this.id = id;
		this.title = title;
		this.articleDate = articleDate;
		this.authors = authors;
		this.accessCategory = accessCategory;
		this.description = description;
		this.freeViewExpiry = freeViewExpiry;
		this.publicUrl = publicUrl;
		this.imagePath = imagePath;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getArticleDate() {
		return articleDate;
	}

	public void setArticleDate(Date articleDate) {
		this.articleDate = articleDate;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFreeViewExpiry() {
		return freeViewExpiry;
	}

	public void setFreeViewExpiry(Date freeViewExpiry) {
		this.freeViewExpiry = freeViewExpiry;
	}

	public String getPublicUrl() {
		return publicUrl;
	}

	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}

	//Author
	public List<Author> getAuthors() {
		return authors;
	}
	
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public void addAuthor(Author author) {
		authors.add(author);
		author.setArticle(this);
	}

	public void removeAuthor(Author author) {
		authors.remove(author);
		author.setArticle(null);
	}
	
	//AccessCategory
	public List<AccessCategory> getAccessCategory() {
		return accessCategory;
	}
	
	public void setAccessCategory(List<AccessCategory> accessCategories) {
		this.accessCategory = accessCategories;
	}
	public void addAccessCategory(AccessCategory accessCategories) {
		accessCategory.add(accessCategories);
		accessCategories.setArticle(this);
	}
	
	public void removeAccessCategory(AccessCategory accessCategories) {
		accessCategory.remove(accessCategories);
		accessCategories.setArticle(null);
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
}
