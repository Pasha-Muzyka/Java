import java.util.HashMap;
import java.util.List;

public class Author extends Person implements Savable{

    public Author(String firstName, String lastName, String birthDay) {
        super(firstName, lastName, birthDay);
    }

    public Author(List<String> data){
        super(data.get(0), data.get(1), data.get(2));
    }

    @Override
    public HashMap<Integer, String> getInfo() {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(0, getFirstName());
        map.put(1, getLastName());
        map.put(2, getBirthDay());
        return map;
    }
}
