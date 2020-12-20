import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Library {
    private final String name = "NameOfLibrary";
    private final String address = "Address of Library";
    private List<Book> books;
    private List<User> users;
    private List<Author> authors;
    private List<Magazine> magazines;
    private String bookFilePath = "";

    public Library(){
        //load();
        books = new ArrayList<>();
        users = new ArrayList<>();
        magazines = new ArrayList<>();
        authors = new ArrayList<>();
    };

    public void addAuthorToRegister(Author author){ authors.add(author); }

    public void addBookToRegister(Book book) { books.add(book); }

    public void addUserToRegister(User user) { users.add(user); }

    public void addMagazineToRegister(Magazine magazine) { magazines.add(magazine); }

    public String getAllPublications() {
        return "BOOKS"+System.lineSeparator()+getBooks() + "MAGAZINES"+System.lineSeparator()+getMagazine();
    }

    public String getBooks() {
        String newStr = "";

        for(Book b : books){
            newStr += b.toString();
        }
        return newStr;
    }

    public String getMagazine() {
        String newStr = "";

        for(Magazine m : magazines){
            newStr += m.toString();
        }
        return newStr;
    }

    public void deleteBook(String name){
        int index = isBookInRegister(name);

        if(index == -1){
            System.out.println("The book isn`t in library"+System.lineSeparator());
            return;
        }

        if(!books.get(index).isAvailable()){
            System.out.println("The book isn`t available"+System.lineSeparator());
            return;
        }

        books.remove(index);
    }

    public void deleteMagazine(String name){
        int index = isMagazineInRegister(name);

        if(index == -1){
            System.out.println("The magazine isn`t in library"+System.lineSeparator());
            return;
        }

        if(!magazines.get(index).isAvailable()){
            System.out.println("The magazine isn`t available"+System.lineSeparator());
            return;
        }

        magazines.remove(index);
    }

    public String giveOutBook(String name) {
        List<Publication> temp = new ArrayList<>();
        temp.addAll(books);
        temp.addAll(magazines);
        return temp.stream().
                filter(i -> i.getName().contains(name)).
                collect(Collector.of(
                        StringBuilder::new,
                        (b ,s) -> b.append(s.toString()).append(System.lineSeparator()),
                        (b1, b2) -> b1.append(b2).append(" , "),
                        StringBuilder::toString
                ));
    }

    public String getListOfPublicationsWithWord(String word){
        String publications = "BOOKS"+System.lineSeparator();
        for(Book b : books){
            if(b.getName().contains(word)){
                publications += b.toString() + System.lineSeparator();
            }
        }

        publications += "MAGAZINES"+System.lineSeparator();
        for(Magazine m : magazines){
            if(m.getName().contains(word)){
                publications += m.toString() + System.lineSeparator();
            }
        }

        return publications;
    }

    public void giveBookToUser(String bookName, String firstName, String lastName){
        int index = isBookInRegister(bookName);

        if(index == -1){
            System.out.println("The book isn`t in library"+System.lineSeparator());
            return;
        }

        if(!books.get(index).isAvailable()){
            System.out.println("The book isn`t available"+System.lineSeparator());
            return;
        }

        users.get(isUserRegistered(firstName, lastName)).writePublicationToUser(books.get(index).getName());
        books.get(index).setAvailable(false);
    }

    public void giveMagazineToUser(String magazineName, String firstName, String lastName){
        int index = isMagazineInRegister(magazineName);

        if(index == -1){
            System.out.println("The book isn`t in library"+System.lineSeparator());
            return;
        }

        if(!magazines.get(index).isAvailable()){
            System.out.println("The book isn`t available"+System.lineSeparator());
            return;
        }

        users.get(isUserRegistered(firstName, lastName)).writePublicationToUser(magazines.get(index).getName());
        magazines.get(index).setAvailable(false);
    }

    public void returnBookToRegister(String bookName, String firstName, String lastName){
        int indexBook = isBookInRegister(bookName), indexUser = isUserRegistered(firstName, lastName);

        if(indexBook == -1){
            System.out.println("The book isn`t in library"+System.lineSeparator());
            return;
        }

        if(indexUser == -1){
            System.out.println("User isn't registered" + System.lineSeparator());
            return;
        }

        if(!users.get(indexUser).isUserHavePublication(bookName)){
            System.out.println("User don't have required book" + System.lineSeparator());
            return;
        }

        users.get(indexUser).deletePublicationFromUser(bookName);
        books.get(indexBook).setAvailable(true);
    }

    public void returnMagazineToRegister(String magazineName, String firstName, String lastName){
        int indexMagazine = isMagazineInRegister(magazineName), indexUser = isUserRegistered(firstName, lastName);

        if(indexMagazine == -1){
            System.out.println("The book isn`t in library"+System.lineSeparator());
            return;
        }

        if(indexUser == -1){
            System.out.println("User isn't registered" + System.lineSeparator());
            return;
        }

        if(!users.get(indexUser).isUserHavePublication(magazineName)){
            System.out.println("User don't have required book" + System.lineSeparator());
            return;
        }

        users.get(indexUser).deletePublicationFromUser(magazineName);
        magazines.get(indexMagazine).setAvailable(true);
    }

    public void save(){
        Convertor<Book> bookConvertor = new Convertor<>();
        String str = "";
        str = bookConvertor.getString(str, books);
        FileWriter.writeStringToFile(str, ".\\outputFiles\\books.csv");

        Convertor<Magazine> magazineConvertor = new Convertor<>();
        str = "";
        str = magazineConvertor.getString(str, magazines);
        FileWriter.writeStringToFile(str, ".\\outputFiles\\magazines.csv");

        Convertor<User> userConvertor = new Convertor<>();
        str = "";
        str = userConvertor.getString(str, users);
        FileWriter.writeStringToFile(str, ".\\outputFiles\\users.csv");

        Convertor<Author> authorConvertor = new Convertor<>();
        str = "";
        str = authorConvertor.getString(str, authors);
        FileWriter.writeStringToFile(str, ".\\outputFiles\\authors.csv");
    }

    public void load(){
        String table = FileReader.readFileToString(".\\outputFiles\\books.csv");
        if(!table.isEmpty()) {
            for (List<String> s : Convertor.createList(table)) {
                books.add(new Book(s));
            }
        }

        table = FileReader.readFileToString(".\\outputFiles\\magazines.csv");
        if(!table.isEmpty()) {
            for (List<String> s : Convertor.createList(table)) {
                magazines.add(new Magazine(s));
            }
        }

        table = FileReader.readFileToString(".\\outputFiles\\users.csv");
        if(!table.isEmpty()) {
            for (List<String> s : Convertor.createList(table)) {
                users.add(new User(s));
            }
        }

        table = FileReader.readFileToString(".\\outputFiles\\authors.csv");
        if(!table.isEmpty()) {
            for (List<String> s : Convertor.createList(table)) {
                authors.add(new Author(s));
            }
        }
    }

    public int isAuthorRegistered(String name, String lastname){
        for(int i = 0; i < authors.size(); i++){
            if(authors.get(i).getFirstName().equals(name)&&authors.get(i).getLastName().equals(lastname)){
                return i;
            }
        }
        return -1;
    }

    public int isUserRegistered(String name, String lastname){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getFirstName().equals(name)&&users.get(i).getLastName().equals(lastname)){
                return i;
            }
        }
        return -1;
    }

    private int isBookInRegister(String name){
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getName().equals(name)) return i;
        }
        return -1;
    }

    private int isMagazineInRegister(String name){
        for(int i = 0; i < magazines.size(); i++){
            if(magazines.get(i).getName().equals(name)) return i;
        }
        return -1;
    }
}
