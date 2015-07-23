package eCheque.Test;

import eCheque.DigitalCertificate;
import eCheque.DigitalCertificateIO;
import org.junit.Test;

import java.security.PublicKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by maximell on 2015-07-15.
 */
public class DigitalCertificateTest {

    @Test
    public void TC11211setHolderNameGSReturnSame() {
        String testString = "This is a test string";

        DigitalCertificate testDigitalCertificate = new DigitalCertificate();

        testDigitalCertificate.setHolderName(testString);

        String retrievedString = testDigitalCertificate.getHolderName();

        assertEquals("The test and retrieved string must be equal.", testString, retrievedString);
    }

    @Test
    public void TC11212setSubjectNameGSReturnSame() {
        String testString = "This is a test string";

        DigitalCertificate testDigitalCertificate = new DigitalCertificate();

        testDigitalCertificate.setSubject(testString);

        String retrievedString = testDigitalCertificate.getSubject();

        assertEquals("The test and retrieved string must be equal.", testString, retrievedString);
    }

    @Test
    public void TC11213setIssuerGSReturnSame() {
        String testString = "This is a test string";

        DigitalCertificate testDigitalCertificate = new DigitalCertificate();

        testDigitalCertificate.setIssuer(testString);

        String retrievedString = testDigitalCertificate.getIssuer();

        assertEquals("The test and retrieved string must be equal.", testString, retrievedString);
    }

    @Test
    public void TC11214setSerialNumberGSReturnSame() {
        String testString = "This is a test string";

        DigitalCertificate testDigitalCertificate = new DigitalCertificate();

        testDigitalCertificate.setSerialNumber(testString);

        String retrievedString = testDigitalCertificate.getSerialNumber();

        assertEquals("The test and retrieved string must be the same", testString, retrievedString);
    }

    @Test
    public void TC11215setValidFromGSReturnSame() {
        String testString = "This is a test string";

        DigitalCertificate testDigitalCertificate = new DigitalCertificate();

        testDigitalCertificate.setValidFrom(testString);

        String retrievedString = testDigitalCertificate.getValidFrom();

        assertEquals("The test and retrieved string must be equal.", testString, retrievedString);
    }

    @Test
    public void TC11216setValidToGSReturnSame() {
        String testString = "This is a test string";

        DigitalCertificate testDigitalCertificate = new DigitalCertificate();

        testDigitalCertificate.setValidTo(testString);

        String retrievedString = testDigitalCertificate.getValidTo();

        assertEquals("The test and retrieved string must be equal.", testString, retrievedString);
    }

    @Test
    public void TC11217setValidFromGSReturnSame() {

        PublicKey testPublicKey = new PublicKey() {
            @Override
            public String getAlgorithm() {
                return null;
            }

            @Override
            public String getFormat() {
                return "This is the test public key.";
            }

            @Override
            public byte[] getEncoded() {
                return new byte[0];
            }
        };

        DigitalCertificate testDigitalCertificate = new DigitalCertificate();

        testDigitalCertificate.setPublicKey(testPublicKey);

        PublicKey retrievedKey = testDigitalCertificate.getpublicKey();

        assertEquals("The test and retrieved PublicKey should be equal", testPublicKey.getFormat(), retrievedKey.getFormat());
    }

    @Test
    public void TC11218SetIssuerSignatureGSReturnSame() {
        byte[] testArray = "Test String".getBytes();

        DigitalCertificate testDigitalCertificate = new DigitalCertificate();

        testDigitalCertificate.setIssuerSignature(testArray);

        byte [] retrievedArray = testDigitalCertificate.getIssuerSignature();

        assertEquals("The test and retrieved byte array should be the same.", testArray, retrievedArray);
    }

    @Test
    public void TC1131DigitalCertificateObjectNotNull() {
        DigitalCertificateIO testDigitalCertificateIO = new DigitalCertificateIO();

        assertNotNull("The object should be not Null.", testDigitalCertificateIO);
    }
}
