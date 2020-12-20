public abstract class Person {
    private String firstName;
    private String lastName;
    private String birthDay;

    public Person(String firstName, String lastName, String birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getBirthDay(){return birthDay;}
}
