package eCheque.Test;


/**
 * Created by bolinkd on 13/07/15.
 */

import eCheque.RSAGenerator;
import org.junit.*;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;


public class RSAGeneratorTest {
    public RSAGeneratorTest(){

    }

    @Test
    public void TC1181testKeysMatchRSAGenerator() throws NoSuchAlgorithmException{

        RSAGenerator instance = new RSAGenerator();
        KeyPair pair = instance.GenerateRSAKeys();

        Key key = pair.getPrivate();

        if (key instanceof RSAPrivateCrtKey) {
            RSAPrivateCrtKey pvt = (RSAPrivateCrtKey) key;
            BigInteger e = pvt.getPublicExponent();
            RSAPublicKey pub = (RSAPublicKey) pair.getPublic();
            Assert.assertTrue("Keys Match",e.equals(pub.getPublicExponent()) && pvt.getModulus().equals(pub.getModulus()));
        }
    }



}
