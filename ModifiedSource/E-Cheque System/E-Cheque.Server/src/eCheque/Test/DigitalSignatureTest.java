package eCheque.Test;

import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.*;

import eCheque.Digitalsigneture;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by denholms on 2015-07-07.
 */
public class DigitalSignatureTest {
    PrivateKey priv;
    PublicKey pub;
    Digitalsigneture tester;
    SecureRandom SecRandom;
    String normalMessage = "This is a message. I am also a message; we are messages. How many messages could message" +
            "if a message could message the messaging system - messing with the message making messages";

    @Before
    public void initialize(){
        try {
            tester = new Digitalsigneture();
            SecRandom= new SecureRandom();
            KeyPairGenerator KeyGenerator= KeyPairGenerator.getInstance("RSA");
            KeyGenerator.initialize(1024,SecRandom);
            KeyPair pair = KeyGenerator.generateKeyPair();
            priv = pair.getPrivate();
            pub = pair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void emptyMessageShouldReturnValidSignature_1_1_4_1() {
        String message = "";
        byte[] sig;
        try {
            sig = tester.signeture(message, priv);
            assert(sig instanceof byte[]);
            assert(sig.length > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void nullMessageShouldThrowIllegalArgumentException_1_1_4_1() {
        String message = null;
        byte[] sig = null;
        try {
            sig = tester.signeture(message,priv);
        } catch (Exception e) {
            assertNull(sig);
            assert(e instanceof IllegalArgumentException);
            return;
        }
    }

    // Base case
    @Test
    public void signatureShouldSignRegularLengthMessage_1_1_4_2(){
        byte[] sig;

        try {
            sig = tester.signeture(normalMessage, priv);
            assert(sig instanceof byte[]);
            assert(sig.length > 0);
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void signatureShouldSignLongMessage_1_1_4_2(){
        byte[] sig;
        String message = "";

        for (int i = 0; i<=208; i++){
            message += "This is a long message. ";
        }

        try {
            sig = tester.signeture(message, priv);
            assert(sig instanceof byte[]);
            assert(sig.length > 0);
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void signatureShouldSignShortMessage_1_1_4_2(){
        byte[] sig;
        String message = "Short";

        try {
            sig = tester.signeture(message, priv);
            assert(sig instanceof byte[]);
            assert(sig.length > 0);
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void nullPrivateKeyShouldThrowIllegalArgumentException_1_1_4_3(){
        byte[] sig = null;
        PrivateKey nullPrivKey = null;

        try{
            sig = tester.signeture(normalMessage, nullPrivKey);
        } catch (Exception e){
            assertNull(sig);
            assert(e instanceof IllegalArgumentException);
            return;
        }
    }

    @Test
    public void shortPrivateKeyShouldReturnValidSig_1_1_4_4(){
        byte[] sig;
        PrivateKey shortPriv;
        Digitalsigneture test;

        try {
            test = new Digitalsigneture();
            KeyPairGenerator KeyGenerator= KeyPairGenerator.getInstance("RSA");
            KeyGenerator.initialize(512,SecRandom);
            KeyPair pair = KeyGenerator.generateKeyPair();
            shortPriv = pair.getPrivate();
            assertNotNull(shortPriv);
            assert(shortPriv.getEncoded().length > 0);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }

        try{
            sig = test.signeture(normalMessage, shortPriv);
            assert(sig instanceof byte[]);
            assert(sig.length > 0);
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void longPrivateKeyShouldReturnValidSig_1_1_4_4(){
        byte[] sig;
        PrivateKey longPriv = null;
        Digitalsigneture test = new Digitalsigneture();

        try {
            test = new Digitalsigneture();
            KeyPairGenerator KeyGenerator= KeyPairGenerator.getInstance("RSA");
            KeyGenerator.initialize(4096,SecRandom);
            KeyPair pair = KeyGenerator.generateKeyPair();
            longPriv = pair.getPrivate();
            assertNotNull(longPriv);
            assert(longPriv.getEncoded().length > 0);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }

        try{
            sig = test.signeture(normalMessage,longPriv);
            assert(sig instanceof byte[]);
            assert(sig.length > 0);
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void nullSignedMessageShouldThrowIllegalArgumentException_1_1_4_5(){
        byte[] sig = null;

        try {
            assertFalse(tester.verifySignature(sig, normalMessage, pub));
        } catch (Exception e){
            assert(e instanceof IllegalArgumentException);
            return;
        }
    }

    @Test
    public void emptySignedMessageShouldReturnFalse_1_1_4_5(){
        byte[] sig = new byte[0];

        try{
            assertFalse(tester.verifySignature(sig, normalMessage, pub));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void nullPublicKeyShouldThrowIllegalArgumentException_1_1_4_6() {
        byte[] sig;
        PublicKey nullPubKey = null;
        try{
            sig = tester.signeture(normalMessage, priv);
            assertFalse(tester.verifySignature(sig, normalMessage, nullPubKey));
        } catch (Exception e){
            assert(e instanceof IllegalArgumentException);
            return;
        }
    }

    //Includes comparison to base case
    @Test
    public void incorrectPublicKeyShouldReturnFalse_1_1_4_6(){
        byte[] sig;
        byte[] falseSig;

        try{
            sig = tester.signeture(normalMessage, priv);
            falseSig = tester.signeture("message", priv);
            assertFalse(tester.verifySignature(falseSig, normalMessage, pub));
            assertTrue(tester.verifySignature(sig, normalMessage, pub));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void shortSignedMessageShouldReturnTrue_1_1_4_7(){
        byte[] sig;
        String shortMessage = "short";

        try{
            sig = tester.signeture(shortMessage, priv);
            assertTrue(tester.verifySignature(sig, shortMessage, pub));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @Test
    public void longSignedMessageShouldReturnTrue_1_1_4_7(){
        byte[] sig;
        String message = "";

        for (int i = 0; i<=208; i++){
            message += "This is a long message. ";
        }

        try{
            sig = tester.signeture(message, priv);
            assertTrue(tester.verifySignature(sig, message, pub));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
}
