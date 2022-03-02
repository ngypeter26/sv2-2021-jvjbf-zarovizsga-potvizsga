package newspaper;

import java.util.*;

public class NewsPaper {
    private String name;
    private TreeSet<Article> articles = new TreeSet<>();
    public NewsPaper(String name) {
        this.name = name;
    }

    public void addArticle(Article article){
        articles.add(article);
    }

    public List<Article> findArticlesByAuthor(String authorName){
        return articles.stream().filter(article -> article.getAuthor().equals(authorName)).toList();
    }
    public List<Article> findArticleByParagraphPart(String part){
        return articles.stream().filter(article -> containsParagraphsPart(article.getParagraphs(),part)).toList();
    }
        private boolean containsParagraphsPart(List<String> paragraphs,String part){
            return  paragraphs.stream().filter(p -> p.contains(part)).toList().size()>0;
        }

    public String getName() {
        return name;
    }


    public TreeSet<Article> getArticles() {
        return articles;
    }
}
