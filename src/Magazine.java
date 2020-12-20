import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Magazine extends Publication implements Savable{
    private String dateOfPublication;
    private String type;
    private Integer numberOfArticle;

    public Magazine(String name, List<String> authors, Integer numberOfaPage, String publisher, String dateOfPublication, Integer numberOfArticle, String type) {
        super(name, authors, numberOfaPage, publisher);
        this.dateOfPublication = dateOfPublication;
        this.numberOfArticle = numberOfArticle;
        this.type = type;
    }

    public Magazine(List<String> data){
        super(data.get(0), Arrays.asList(data.get(1).split(",")),Integer.parseInt(data.get(4)),data.get(3));
        dateOfPublication = data.get(2);
        numberOfArticle = Integer.parseInt(data.get(5));
        type = data.get(6);
        this.setAvailable(Boolean.valueOf(data.get(7)));
    }

    public Integer getNumberOfArticle() {
        return numberOfArticle;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    @Override
    public String toString(){
        return this.getName() + ", " + this.getAuthors() + this.dateOfPublication + ", " + this.getPublisher() + ", " + this.getCountOfPages() + ", " + this.numberOfArticle + ", " + this.type + System.lineSeparator();
    }

    @Override
    public HashMap<Integer, String> getInfo() {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(0,getName());
        map.put(1,getAuthorsToSave());
        map.put(2,getDateOfPublication());
        map.put(3,getPublisher());
        map.put(4,String.valueOf(getCountOfPages()));
        map.put(5,String.valueOf(numberOfArticle));
        map.put(6,type);
        map.put(7,Boolean.toString(isAvailable()));
        return map;
    }
}
