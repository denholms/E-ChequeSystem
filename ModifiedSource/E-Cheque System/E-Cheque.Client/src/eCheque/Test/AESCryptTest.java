package eCheque.Test;

import eCheque.AESCrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import eCheque.EChequeRegisteration;
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
    public void generatesValidAESKey_1_1_1_1() throws Exception{
        SecretKey key = tester.GenerateRandomAESKey();
        assert(key.getEncoded().length > 0);
        assertEquals("RAW", key.getFormat());
        assertEquals("AES", key.getAlgorithm());
        assertEquals("class javax.crypto.spec.SecretKeySpec", key.getClass().toString());
    }

    @Test
    public void generatesAESKeyByShortPassword_1_1_1_2() {
        SecretKeySpec key = tester.inilizeAESKeyByPassword("pas");
        assert(key.getEncoded().length > 0);
        assertEquals("RAW", key.getFormat());
        assertEquals("AES", key.getAlgorithm());
        assertEquals("class javax.crypto.spec.SecretKeySpec", key.getClass().toString());
    }

    @Test
    public void generatesAESKeyByRegularPassword_1_1_1_2() {
        SecretKeySpec key = tester.inilizeAESKeyByPassword("sUp3rPw0rd");
        assert(key.getEncoded().length > 0);
        assertEquals("RAW", key.getFormat());
        assertEquals("AES", key.getAlgorithm());
        assertEquals("class javax.crypto.spec.SecretKeySpec", key.getClass().toString());
    }

    @Test
    public void generatesAESKeyByLongPassword_1_1_1_2() {
        SecretKeySpec key = tester.inilizeAESKeyByPassword("sup3rerduperlongpasswordThatm@ybeshouldn'tbesolong");
        assert(key.getEncoded().length > 0);
        assertEquals("RAW", key.getFormat());
        assertEquals("AES", key.getAlgorithm());
        assertEquals("class javax.crypto.spec.SecretKeySpec", key.getClass().toString());
    }

    @Test
    public void nullPasswordThrowsIllegalArgumentException_1_1_1_3() {
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
    public void emptyPasswordThrowsIllegalArgumentException_1_1_1_3(){
        SecretKeySpec key = null;
        try {
            key = tester.inilizeAESKeyByPassword("");
        } catch (Exception e){
            assertNull(key);
            assert (e instanceof IllegalArgumentException);
        }
    }

    @Test //Password must be 16 characters
    public void initializeCipherReturnsValidCipherUsing0Mode_1_1_1_4(){
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
    public void initializeCipherTurnsValidCipherUsing1Mode_1_1_1_4(){
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
    public void initializeCipherTurnsValidCipherUsing2Mode_1_1_1_4(){
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
    public void initializeCipherTurnsValidCipherUsing3Mode_1_1_1_4(){
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
    public void initializeCipherThrowsIllegalArgumentExceptionOnNullKey_1_1_1_5(){
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
    public void initializeCipherThrowsInvalidKeyExceptionOn64ByteKey_1_1_1_6(){
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
      public void initializeCipherThrowsInvalidKeyExceptionOn32ByteKey_1_1_1_6(){
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
    public void initializeCipherThrowsInvalidKeyExceptionOn10ByteKey_1_1_1_6(){
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
    public void cryptBaseCase_1_1_1_7(){
        Key AES128 = tester.inilizeAESKeyByPassword("password12345678");
        EChequeRegisteration eChequeReg = new EChequeRegisteration();
        eChequeReg.setEWalletLoaction("D:\\Documents\\Programming\\SENG426\\E-ChequeSystem\\Wallet");
        try {
            Cipher cipher = tester.initializeCipher(AES128,1);
            InputStream in = new FileInputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\Private Key.key");
            OutputStream out = new FileOutputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\PrivateKey.key");
            tester.crypt(in,out,cipher);
        } catch (Exception e) {
            e.printStackTrace();
            assert(e instanceof IOException);
        }
    }

    @Test
    public void cryptThrowsIOExceptionOnNullInputStream_1_1_1_7(){
        Key AES128 = tester.inilizeAESKeyByPassword("password12345678");
        EChequeRegisteration eChequeReg = new EChequeRegisteration();
        eChequeReg.setEWalletLoaction("D:\\Documents\\Programming\\SENG426\\E-ChequeSystem\\Wallet");
        try {
            Cipher cipher = tester.initializeCipher(AES128,1);
            InputStream in = null;
            OutputStream out = new FileOutputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\PrivateKey.key");
            tester.crypt(in,out,cipher);
        } catch (Exception e) {
            e.printStackTrace();
            assert(e instanceof IOException);
        }
    }

    @Test
    public void cryptThrowsIOExceptionOnNullOutputStream_1_1_1_7(){
        Key AES128 = tester.inilizeAESKeyByPassword("password12345678");
        EChequeRegisteration eChequeReg = new EChequeRegisteration();
        eChequeReg.setEWalletLoaction("D:\\Documents\\Programming\\SENG426\\E-ChequeSystem\\Wallet");
        try {
            Cipher cipher = tester.initializeCipher(AES128,1);
            InputStream in = new FileInputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\Private Key.key");
            OutputStream out = null;
            tester.crypt(in,out,cipher);
        } catch (Exception e) {
            e.printStackTrace();
            assert(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void cryptThrowsIllegalArgumentExceptionOnNullCipher_1_1_1_7(){
        EChequeRegisteration eChequeReg = new EChequeRegisteration();
        eChequeReg.setEWalletLoaction("D:\\Documents\\Programming\\SENG426\\E-ChequeSystem\\Wallet");
        try {
            Cipher cipher = null;
            InputStream in = new FileInputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\Private Key.key");
            OutputStream out = new FileOutputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\PrivateKey.key");
            tester.crypt(in,out,cipher);
        } catch (Exception e) {
            e.printStackTrace();
            assert(e instanceof IOException);
        }
    }

    @Test
    public void cryptHandlesEmptyInputStream_1_1_1_7(){
        Key AES128 = tester.inilizeAESKeyByPassword("password12345678");
        EChequeRegisteration eChequeReg = new EChequeRegisteration();
        eChequeReg.setEWalletLoaction("D:\\Documents\\Programming\\SENG426\\E-ChequeSystem\\Wallet");
        try {
            Cipher cipher = tester.initializeCipher(AES128,1);
            InputStream in = new FileInputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\emptyPrivate Key.key");
            OutputStream out = new FileOutputStream(eChequeReg.getEWalletLoaction()+"\\Security Tools\\PrivateKey.key");
            tester.crypt(in,out,cipher);
        } catch (Exception e) {
            e.printStackTrace();
            assert(e instanceof IOException);
        }
    }
}
