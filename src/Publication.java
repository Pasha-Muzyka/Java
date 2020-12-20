import java.util.List;

public abstract class Publication {
    private String name;
    private List<String> authors;
    private Integer countOfPages;
    private String publisher;
    private boolean isAvailable;

    public Publication(String name, List<String> authors, Integer numberOfaPage, String publisher) {
        this.name = name;
        this.authors = authors;
        this.countOfPages = numberOfaPage;
        this.publisher = publisher;
        isAvailable = true;
    }

    public String getName() {
        return name;
    }

    public Integer getCountOfPages() {
        return countOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthors() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String author : authors) {
            stringBuilder.append(author).append(", ");
        }

        return stringBuilder.toString();
    }

    public String getAuthorsToSave() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String author : authors) {
            stringBuilder.append(author).append(",");
        }

        return stringBuilder.toString();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean bool) {
        isAvailable = bool;
    }
}
