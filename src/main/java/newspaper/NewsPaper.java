package newspaper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;

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
//        List<Article> result = articles.stream().filter(article -> article.getAuthor().equals(authorName)).toList();
        return articles.stream().filter(article -> article.getAuthor().equals(authorName)).toList();
    }
    public List<Article> findArticleByParagraphPart(String part){
        List<Article> result = articles.stream().filter(article -> containsParagraphsPart(article.getParagraphs(),part)).toList();
        return result;
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
