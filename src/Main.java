import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {

        Person person1 = new Person("Brian Mariman","Warehem","0474654872");
        Person person2 = new Person("Sleepy Kazan","Tokyo","047505698");
        Person person1fake = new Person("Brion Mariman","Warehem","0474654872");

        byte[] person1bytes = person1.getBytes();
        byte[] personBytes = person2.getBytes();
        byte[] person1fakebytes = person1fake.getBytes();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
