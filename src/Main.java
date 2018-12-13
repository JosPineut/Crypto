import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
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

        byte[] cleartext = ("Shrek is een Amerikaanse komische computeranimatiefilm, geproduceerd door de firma DreamWorks. De film is gebaseerd op het gelijknamige boek van William Steig. De regie was in handen van Andrew Adamson en Vicky Jenson. De stemmen worden ").getBytes();
        KeyPairGenerator keygenpair;
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

            // Encrypt the cleartext
            byte[] ciphertext = aesCipher.doFinal(cleartext);

            System.out.println("Geencrypteerde tekst: ");
            System.out.println(new String(ciphertext));

            // Initialize the same cipher for decryption
            aesCipher.init(Cipher.DECRYPT_MODE, aesKey);

            // Decrypt the ciphertext
            byte[] cleartext1 = aesCipher.doFinal(ciphertext);

            System.out.println("gedecrypteerde tekst: "+new String(cleartext1));


            Cipher RSAcypher;
            RSAcypher = Cipher.getInstance("RSA");
            keygenpair = KeyPairGenerator.getInstance("RSA");
            keygenpair.initialize(4096);


            pair=keygenpair.generateKeyPair();

            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();

            RSAcypher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] cleartext2 = RSAcypher.doFinal(cleartext);
            System.out.println("Geencrypteerde tekst: ");
            System.out.println(new String(cleartext2));

            RSAcypher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] cleartext3 = RSAcypher.doFinal(cleartext2);
            System.out.println("Gedecrypteerde tekst: ");
            System.out.println(new String(cleartext3));

            //SIGNATURE THING

            //creeer signature via private keys
            Signature rsa = Signature.getInstance("SHA256withRSA");

            PrivateKey priv = pair.getPrivate();
            rsa.initSign(priv);

            /* Update and sign the data */
            rsa.update(person2Bytes);
            byte[] sig = rsa.sign();

            PublicKey pub = pair.getPublic();
            rsa.initVerify(pub);

            /* Update and verify the data */
            rsa.update(person2Bytes);
            boolean verifies = rsa.verify(sig);
            System.out.println("signature verifies: " + verifies);


        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }










































    }
}
