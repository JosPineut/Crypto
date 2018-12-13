public class Main {

    public static void main(String[] args) {

        Person person1 = new Person("Brian Mariman","Warehem","0474654872");

        Person person1fake = new Person("Brion Mariman","Warehem","0474654872");

        byte[] person1bytes = person1.getBytes();
        byte[] person1fakebytes = person1fake.getBytes();
    }
}
