import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class User extends Person implements Savable {
    private static int countOfUsers = 1;
    private Integer numberOfLibraryTicket;
    private List<String> publications;

    public User(String firstName, String lastName, String dataOfBirds) {
        super(firstName, lastName, dataOfBirds);
        numberOfLibraryTicket = countOfUsers;
        publications = new ArrayList();
        countOfUsers++;
    }

    public User(List<String> data){
        super(data.get(0), data.get(1), data.get(2) );
        numberOfLibraryTicket = Integer.parseInt(data.get(3));
        publications = new ArrayList(Arrays.asList(data.get(4).split(",")));
        countOfUsers++;
    }

    public void writePublicationToUser(String name){
        publications.add(name);
    }

    public void deletePublicationFromUser(String name){
        publications.remove(name);
    }

    public boolean isUserHavePublication(String name){
        return publications.contains(name);
    }

    public Integer getNumberOfLibraryTicket() { return numberOfLibraryTicket; }

    private String getListOfPublications(){
        String str = "";
        for(String s : publications){
            str += s;
        }
        return str;
    }

    @Override
    public HashMap<Integer, String> getInfo() {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(0, getFirstName());
        map.put(1, getLastName());
        map.put(2, getBirthDay());
        map.put(3, String.valueOf(numberOfLibraryTicket));
        map.put(4, getListOfPublications());
        return map;
    }
}
