package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.AccessCategory;
import com.demo.model.Article;
import com.demo.model.Author;
import com.demo.service.ArticleServices;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/demo")
public class ArticleController {

    @Autowired
    ArticleServices articleServices;

    @Autowired
    ServletContext servletContext;

    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(ArticleController.class);

    @GetMapping("/add")
    public String showAddArticleForm(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("author", new Author());
        model.addAttribute("accessCategory", new AccessCategory());
        return "AddArticle";
    }

    @PostMapping("/add")
    public String addArticle(@ModelAttribute("article") Article article,
                             @RequestParam("authorNames") List<String> authorNames,
                             @RequestParam("accessCategoryNames") List<String> accessCategoryNames,
                             @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        List<Author> authors = new ArrayList<>();
        for (String authorName : authorNames) {
            Author author = new Author();
            author.setName(authorName);
            author.setArticle(article); // Set the association
            authors.add(author);
        }
        article.setAuthors(authors);

        List<AccessCategory> accessCategories = new ArrayList<>();
        for (String accessCategoryName : accessCategoryNames) {
            AccessCategory accessCategory = new AccessCategory();
            accessCategory.setName(accessCategoryName);
            accessCategory.setArticle(article); // Set the association
            accessCategories.add(accessCategory);
        }
        article.setAccessCategory(accessCategories);
        
        // Save the image and set the relative path
        String imagePath = saveImage(imageFile);
        if (imagePath != null) {
            article.setImagePath(imagePath);
        }

        articleServices.addArticle(article);
        return "redirect:/demo/report";
    }

    @GetMapping("/report")
    public String showArticleReport(Model model) {
        List<Article> articles = articleServices.getAllArticles();
        model.addAttribute("articles", articles);
        logger.info("article report list{}", articles);
        return "ArticleReport";
    }

    @GetMapping("/edit/{id}")
    public String showEditArticleForm(@PathVariable Long id, Model model) {
        Article article = articleServices.getById(id);
        if (article != null) {
            model.addAttribute("article", article);
            return "EditArticle";  // Ensure this matches the name of your JSP
        } else {
            return "redirect:/demo/report";  // Handle the case where the article is not found
        }
    }

    @PostMapping("/edit/{id}")
    public String updateArticle(@PathVariable Long id,
                                @ModelAttribute("article") Article article,
                                @RequestParam("authorNames") List<String> authorNames,
                                @RequestParam("accessCategoryNames") List<String> accessCategoryNames,
                                @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Article existingArticle = articleServices.getById(id);
        if (existingArticle != null) {
            // Update other fields
            existingArticle.setTitle(article.getTitle());
            existingArticle.setArticleDate(article.getArticleDate());
            existingArticle.setDescription(article.getDescription());
            existingArticle.setFreeViewExpiry(article.getFreeViewExpiry());
            existingArticle.setPublicUrl(article.getPublicUrl());

            // Remove old authors
            List<Author> currentAuthors = new ArrayList<>(existingArticle.getAuthors());
            for (Author author : currentAuthors) {
                existingArticle.removeAuthor(author);
            }

            // Add new authors
            for (String authorName : authorNames) {
                Author author = new Author();
                author.setName(authorName);
                existingArticle.addAuthor(author);
            }

            // Remove old accessCategory
            List<AccessCategory> currentAccessCategory = new ArrayList<>(existingArticle.getAccessCategory());
            for (AccessCategory accessCategory : currentAccessCategory) {
                existingArticle.removeAccessCategory(accessCategory);
            }

            // Add new accessCategory
            for (String accessCategoryName : accessCategoryNames) {
                AccessCategory accessCategory = new AccessCategory();
                accessCategory.setName(accessCategoryName);
                existingArticle.addAccessCategory(accessCategory);
            }

            // Save the image and set the path
            if (!imageFile.isEmpty()) {
                String imagePath = saveImage(imageFile);
                if (imagePath != null) {
                    existingArticle.setImagePath(imagePath); // Set the new image path
                }
            }

            articleServices.updateArticle(existingArticle);
        }
        return "redirect:/demo/report";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleServices.deleteArticle(id);
        return "redirect:/demo/report"; // Changed redirect URL to /demo/report
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        String realPath = servletContext.getRealPath("/WEB-INF/resources/uploads/images/");
        File uploadDir = new File(realPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create the directory if it doesn't exist
        }
        String fileName = imageFile.getOriginalFilename();
        File uploadFile = new File(uploadDir, fileName);
        imageFile.transferTo(uploadFile);
        // Store relative path instead of absolute path
        return "resources/uploads/images/" + fileName;
    }

	/*
	 * // Image saving method private String saveImage(MultipartFile imageFile)
	 * throws IOException { String realPath =
	 * servletContext.getRealPath("/WEB-INF/resources/uploads/images/"); File
	 * uploadDir = new File(realPath); if (!uploadDir.exists()) {
	 * uploadDir.mkdirs(); // Create the directory if it doesn't exist } String
	 * fileName = imageFile.getOriginalFilename(); File uploadFile = new
	 * File(uploadDir, fileName); imageFile.transferTo(uploadFile); return
	 * uploadFile.getAbsolutePath(); }
	 */
}
