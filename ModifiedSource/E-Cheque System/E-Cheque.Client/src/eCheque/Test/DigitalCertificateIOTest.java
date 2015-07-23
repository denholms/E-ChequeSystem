package eCheque.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import eCheque.DigitalCertificateIO;
import org.junit.Test;
import eCheque.DigitalCertificate;

import java.io.IOException;
import java.security.PublicKey;

/**
 * Created by maximell on 2015-07-15.
 */
public class DigitalCertificateIOTest {

    @Test
    public void TC1132DigitalCertificateIOsaveDCFailure() {
        DigitalCertificateIO testDigitalCertificateIO = new DigitalCertificateIO();

        DigitalCertificate testDigitalCertificate = new DigitalCertificate();

        try {
            testDigitalCertificateIO.SaveDC(testDigitalCertificate, "~/This/Is/Not/A/Path/");
            fail("Should get IOException");
        } catch (IOException e) {}
    }

    @Test
    public void TC1133DigitalCertificateIOSaveAndRetrieval() {
        DigitalCertificateIO testDigitalCertificateIO = new DigitalCertificateIO();

        DigitalCertificate testDigitalCertificate = new DigitalCertificate();

        testDigitalCertificate.setIssuer("Maxim Ellison");

        try {
            testDigitalCertificateIO.SaveDC(testDigitalCertificate, "TestCertificate");
        } catch (IOException e) {
            fail("IOException thrown, save was unsuccessful.");
        }

        DigitalCertificate retrievedDigitalCertificate = new DigitalCertificate();

        try {
            retrievedDigitalCertificate = testDigitalCertificateIO.readDigitalCertificate("TestCertificate");
        } catch (IOException e) {
            fail("Retrieval of DigitalCertificate failed with an IOException.");
        } catch (ClassNotFoundException e){
            fail("Retrieval of DigitalCertificate failed with a ClassNotFoundException.");
        }

        assertEquals("The retrieved Digital Certificate and the original Digital Certificate should have the same data.",
                testDigitalCertificate.getIssuer(), retrievedDigitalCertificate.getIssuer());
    }

    @Test
    public void TC1134DigitalCertificateIORetrievalFailure() {
        DigitalCertificateIO testDigitalCertificateIO = new DigitalCertificateIO();

        try {
            DigitalCertificate retrievedDigitalCertificate = testDigitalCertificateIO.readDigitalCertificate("NotADigitalCertificate");
            fail("Should have seen an IOException");
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            fail("Should have seen an IOException");
        }
    }
}
