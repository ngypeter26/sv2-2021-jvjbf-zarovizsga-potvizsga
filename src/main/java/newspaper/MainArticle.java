package newspaper;

import java.util.List;

public class MainArticle extends Article{

    public MainArticle(String author, Header header, List<String> paragraphs) {
        super(author, header, paragraphs);
    }

    @Override
    public int getImportance() {
        return super.getHeader().getLevel()+super.getParagraphs().size();
    }
}
