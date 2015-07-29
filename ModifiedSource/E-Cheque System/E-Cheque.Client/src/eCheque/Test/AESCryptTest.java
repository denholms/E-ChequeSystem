package eCheque.Test;

import eCheque.AESCrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import eCheque.EChequeRegistration;
import org.junit.Test;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;

import static org.junit.Assert.*;
/**
 * Created by denholms on 2015-07-24.
 */
public class AESCryptTest {
    AESCrypt tester = new AESCrypt();

    @Test
    public void TC1111generatesValidAESKey() throws Exception{
        SecretKey key = tester.GenerateRandomAESKey();
        assert(key.getEncoded().length > 0);
        assertEquals("RAW", key.getFormat());
        assertEquals("AES", key.getAlgorithm());
        assertEquals("class javax.crypto.spec.SecretKeySpec", key.getClass().toString());
    }

    @Test
    public void TC1112generatesAESKeyByShortPassword() {
        SecretKeySpec key = tester.inilizeAESKeyByPassword("pas");
        assert(key.getEncoded().length > 0);
        assertEquals("RAW", key.getFormat());
        assertEquals("AES", key.getAlgorithm());
        assertEquals("class javax.crypto.spec.SecretKeySpec", key.getClass().toString());
    }

    @Test
    public void TC1112generatesAESKeyByRegularPassword() {
        SecretKeySpec key = tester.inilizeAESKeyByPassword("sUp3rPw0rd");
        assert(key.getEncoded().length > 0);
        assertEquals("RAW", key.getFormat());
        assertEquals("AES", key.getAlgorithm());
        assertEquals("class javax.crypto.spec.SecretKeySpec", key.getClass().toString());
    }

    @Test
    public void TC1112generatesAESKeyByLongPassword() {
        SecretKeySpec key = tester.inilizeAESKeyByPassword("sup3rerduperlongpasswordThatm@ybeshouldn'tbesolong");
        assert(key.getEncoded().length > 0);
        assertEquals("RAW", key.getFormat());
        assertEquals("AES", key.getAlgorithm());
        assertEquals("class javax.crypto.spec.SecretKeySpec", key.getClass().toString());
    }

    @Test
    public void TC1113nullPasswordThrowsIllegalArgumentException() {
        String password = null;
        SecretKeySpec key = null;

        try {
            key = tester.inilizeAESKeyByPassword(password);
        } catch (Exception e){
            assertNull(key);
            e.printStackTrace();
            assert(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void TC1113emptyPasswordThrowsIllegalArgumentException(){
        SecretKeySpec key = null;
        try {
            key = tester.inilizeAESKeyByPassword("");
        } catch (Exception e){
            assertNull(key);
            assert (e instanceof IllegalArgumentException);
        }
    }

    @Test //Password must be 16 characters
    public void TC1114initializeCipherReturnsValidCipherUsing0Mode(){
        Cipher cipher;
        Key key = tester.inilizeAESKeyByPassword("passw0rded124567");
        try {
            cipher = tester.initializeCipher(key,0);
            assertEquals("AES", cipher.getAlgorithm());
            assertEquals(16, cipher.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC1114initializeCipherTurnsValidCipherUsing1Mode(){
        Cipher cipher;
        Key key = tester.inilizeAESKeyByPassword("passw0rded124567");
        try {
            cipher = tester.initializeCipher(key,1);
            assertEquals("AES", cipher.getAlgorithm());
            assertEquals(16, cipher.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TC1114initializeCipherTurnsValidCipherUsing2Mode(){
        Cipher cipher = null;
        Key key = tester.inilizeAESKeyByPassword("passw0rded124567");
        try {
            cipher = tester.initializeCipher(key,2);
            assertEquals("RSA", cipher.getAlgorithm());
            assertEquals(16, cipher.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
            assertNotNull(cipher);
        }
    }

    @Test
    public void TC1114initializeCipherTurnsValidCipherUsing3Mode(){
        Cipher cipher = null;
        Key key = tester.inilizeAESKeyByPassword("passw0rded124567");
        try {
            cipher = tester.initializeCipher(key,3);
            assertEquals("RSA", cipher.getAlgorithm());
            assertEquals(16, cipher.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
            assertNotNull(cipher);
        }
    }

    @Test
    public void TC1115initializeCipherThrowsIllegalArgumentExceptionOnNullKey(){
        Cipher cipher = null;
        Key key = null;
        try {
            cipher = tester.initializeCipher(key,1);
        } catch (Exception e) {
            assertNull(cipher);
            assert(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void TC1116initializeCipherThrowsInvalidKeyExceptionOn64ByteKey(){
        Cipher cipher = null;
        Key key = tester.inilizeAESKeyByPassword("passw0rded124567passw0rded12passw0rded124567passw0rded1212341234");
        try {
            cipher = tester.initializeCipher(key,0);
        } catch (Exception e) {
            assertNull(cipher);
            assert (e instanceof InvalidKeyException);
        }
    }

    @Test
      public void TC1116initializeCipherThrowsInvalidKeyExceptionOn32ByteKey(){
        Cipher cipher = null;
        Key key = tester.inilizeAESKeyByPassword("passw0rded124567passw0rded12pas4");
        try {
            cipher = tester.initializeCipher(key,0);
        } catch (Exception e) {
            assertNull(cipher);
            assert (e instanceof InvalidKeyException);
        }
    }

    @Test
    public void TC1116initializeCipherThrowsInvalidKeyExceptionOn10ByteKey(){
        Cipher cipher = null;
        Key key = tester.inilizeAESKeyByPassword("passw0rded");
        try {
            cipher = tester.initializeCipher(key,0);
        } catch (Exception e) {
            assertNull(cipher);
            assert (e instanceof InvalidKeyException);
        }
    }

    @Test
    public void TC1117cryptBaseCase(){
        Key AES128 = tester.inilizeAESKeyByPassword("password12345678");
        EChequeRegistration eChequeReg = new EChequeRegistration();
        eChequeReg.setEWalletLocation("D:\\Documents\\Programming\\SENG426\\E-ChequeSystem\\Wallet");
        try {
            Cipher cipher = tester.initializeCipher(AES128,1);
            InputStream in = new FileInputStream(eChequeReg.getEWalletLocation()+"\\Security Tools\\Private Key.key");
            OutputStream out = new FileOutputStream(eChequeReg.getEWalletLocation()+"\\Security Tools\\PrivateKey.key");
            tester.crypt(in,out,cipher);
        } catch (Exception e) {
            e.printStackTrace();
            assert(e instanceof IOException);
        }
    }

    @Test
    public void TC1117cryptThrowsIOExceptionOnNullInputStream(){
        Key AES128 = tester.inilizeAESKeyByPassword("password12345678");
        EChequeRegistration eChequeReg = new EChequeRegistration();
        eChequeReg.setEWalletLocation("D:\\Documents\\Programming\\SENG426\\E-ChequeSystem\\Wallet");
        try {
            Cipher cipher = tester.initializeCipher(AES128,1);
            InputStream in = null;
            OutputStream out = new FileOutputStream(eChequeReg.getEWalletLocation()+"\\Security Tools\\PrivateKey.key");
            tester.crypt(in,out,cipher);
        } catch (Exception e) {
            e.printStackTrace();
            assert(e instanceof IOException);
        }
    }

    @Test
    public void TC1117cryptThrowsIOExceptionOnNullOutputStream(){
        Key AES128 = tester.inilizeAESKeyByPassword("password12345678");
        EChequeRegistration eChequeReg = new EChequeRegistration();
        eChequeReg.setEWalletLocation("D:\\Documents\\Programming\\SENG426\\E-ChequeSystem\\Wallet");
        try {
            Cipher cipher = tester.initializeCipher(AES128,1);
            InputStream in = new FileInputStream(eChequeReg.getEWalletLocation()+"\\Security Tools\\Private Key.key");
            OutputStream out = null;
            tester.crypt(in,out,cipher);
        } catch (Exception e) {
            e.printStackTrace();
            assert(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void TC1117cryptThrowsIllegalArgumentExceptionOnNullCipher(){
        EChequeRegistration eChequeReg = new EChequeRegistration();
        eChequeReg.setEWalletLocation("D:\\Documents\\Programming\\SENG426\\E-ChequeSystem\\Wallet");
        try {
            Cipher cipher = null;
            InputStream in = new FileInputStream(eChequeReg.getEWalletLocation()+"\\Security Tools\\Private Key.key");
            OutputStream out = new FileOutputStream(eChequeReg.getEWalletLocation()+"\\Security Tools\\PrivateKey.key");
            tester.crypt(in,out,cipher);
        } catch (Exception e) {
            e.printStackTrace();
            assert(e instanceof IOException);
        }
    }

    @Test
    public void TC1117cryptHandlesEmptyInputStream(){
        Key AES128 = tester.inilizeAESKeyByPassword("password12345678");
        EChequeRegistration eChequeReg = new EChequeRegistration();
        eChequeReg.setEWalletLocation("D:\\Documents\\Programming\\SENG426\\E-ChequeSystem\\Wallet");
        try {
            Cipher cipher = tester.initializeCipher(AES128,1);
            InputStream in = new FileInputStream(eChequeReg.getEWalletLocation()+"\\Security Tools\\emptyPrivate Key.key");
            OutputStream out = new FileOutputStream(eChequeReg.getEWalletLocation()+"\\Security Tools\\PrivateKey.key");
            tester.crypt(in,out,cipher);
        } catch (Exception e) {
            e.printStackTrace();
            assert(e instanceof IOException);
        }
    }
}
