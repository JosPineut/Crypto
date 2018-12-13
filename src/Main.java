import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {

        Person person1 = new Person("Brian Mariman","Warehem","0474654872");
        Person person2 = new Person("Sleepy Kazan","Tokyo","047505698");
        Person person1fake = new Person("Brion Mariman","Warehem","0474654872");

        byte[] person1bytes = person1.getBytes();
        byte[] person2Bytes = person2.getBytes();
        byte[] person1fakebytes = person1fake.getBytes();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");

            sha.update(person1bytes);
            //sha.update(person2Bytes);

            sha.update(person1fakebytes);

            System.out.println(new String(person1bytes));
            //System.out.println(new String(person2Bytes));
            System.out.println("andere:");
            System.out.println(new String(person1fakebytes));


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }



    }
}
