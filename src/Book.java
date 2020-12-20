import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Book extends Publication implements Savable{
    private String yearOfPublication;

    public Book(String name, List<String> authors, Integer numberOfaPage, String publisher, String yearOfPublication) {
        super(name, authors, numberOfaPage, publisher);
        this.yearOfPublication = yearOfPublication;
    }

    public Book(List<String> data){
        super(data.get(0), Arrays.asList(data.get(1).split(",")),Integer.parseInt(data.get(2)),data.get(3));
        this.yearOfPublication = data.get(4);
        this.setAvailable(Boolean.valueOf(data.get(5)));
    }

    public String getYearOfPublication() { return yearOfPublication; }

    @Override
    public HashMap<Integer, String> getInfo() {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(0,getName());
        map.put(1,getAuthorsToSave());
        map.put(2,String.valueOf(getCountOfPages()));
        map.put(3,getPublisher());
        map.put(4,String.valueOf(getYearOfPublication()));
        map.put(5,Boolean.toString(isAvailable()));
        return map;
    }

    @Override
    public String toString(){
        return this.getName() + ", " + this.getAuthors() + this.yearOfPublication + ", " + this.getPublisher() + ", " + this.getCountOfPages() + System.lineSeparator();
    }
}
