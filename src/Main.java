import javax.crypto.*;
import java.security.*;

public class Main {

    private static KeyPair pair;
    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    public static void main(String[] args) {

        Person person1 = new Person("Brian Mariman", "Warehem", "0474654872");
        Person person2 = new Person("Sleepy Kazan", "Tokyo", "047505698");
        Person person1fake = new Person("Brion Mariman", "Warehem", "0474654872");

        byte[] person1bytes = person1.getBytes();
        byte[] person2Bytes = person2.getBytes();
        byte[] person1fakebytes = person1fake.getBytes();

        /*
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
        }*/

        KeyGenerator keygen = null;
        try {

            keygen = KeyGenerator.getInstance("AES");

            SecretKey aesKey = keygen.generateKey();

            Cipher aesCipher;

            // Create the cipher
            aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Initialize the cipher for encryption
            aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);

            // Our cleartext
            byte[] cleartext = ("Shrek is een Amerikaanse komische computeranimatiefilm, geproduceerd door de firma DreamWorks. De film is gebaseerd op het gelijknamige boek van William Steig. De regie was in handen van Andrew Adamson en Vicky Jenson. De stemmen worden " +
                    "ingesproken door Mike Myers, Eddie Murphy, Cameron Diaz, en John Lithgow. De hoofdrolspeler is Shrek, een groene oger. In 2001 werd het eerste deel uitgebracht. De film was een groot succes en vestigde Dreamworks’ naam als producer van computeranimatiefilms. " +
                    "De film kreeg drie vervolgen plus ook nog een behoorlijk aantal spin-offs. Zo is er een kerstmis- en halloweenspecial en ook een aparte film verschenen voor de gelaarsde kat (personage dat werd geïntroduceerd tijdens de 2de film).").getBytes();

            // Encrypt the cleartext
            byte[] ciphertext = aesCipher.doFinal(cleartext);

            System.out.println("Geencrypteerde tekst: ");
            System.out.println(new String(ciphertext));

            // Initialize the same cipher for decryption
            aesCipher.init(Cipher.DECRYPT_MODE, aesKey);

            // Decrypt the ciphertext
            byte[] cleartext1 = aesCipher.doFinal(ciphertext);

            System.out.println("gedecrypteerde tekst: "+new String(cleartext1));


        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        KeyPairGenerator keygenpair;
        try {
            keygenpair = KeyPairGenerator.getInstance("RSA");
            keygenpair.initialize(4096);

            pair = keygenpair.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
