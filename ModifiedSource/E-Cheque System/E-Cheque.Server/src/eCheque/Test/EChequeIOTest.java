package eCheque.Test;

import eCheque.ECheque;
import eCheque.EChequeIO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public class EChequeIOTest {

    private final String _testDirectoryPath = "Tests";
    private final String _outputFilePath = Paths.get(_testDirectoryPath, "TestECheque.txt").toString();

    @Before
    public void Setup(){
        // Create test directory
        File testDirectory = new File(_testDirectoryPath);
        testDirectory.mkdir();
    }

    @After
    public void TearDown(){
        File testDirectory = new File(_testDirectoryPath);

        // Delete test directory contents
        String[]entries = testDirectory.list();
        for(String s: entries){
            File currentFile = new File(testDirectory.getPath(),s);
            currentFile.delete();
        }

        // Delete test directory
        testDirectory.delete();
    }

    // TC 1.1.6.1
    @Test
    public void TC2151TestSaveChequeSerializesEChequeToFile() throws IOException{

        // Arrange
        ECheque eCheque = new ECheque();
        eCheque.setaccountholder("Jon Doe");
        eCheque.setaccountNumber("27");
        eCheque.setamountofMony("0.99");
        eCheque.setbankname("My Bank");
        eCheque.setpayToOrderOf("Myself");
        eCheque.setcurrencytype("Dogecoin");
        eCheque.setchequeNumber("1");
        eCheque.setguaranteed(true);
        eCheque.setbanksignature(new byte[]{1,0,0,1,0,0,1});
        eCheque.setdrawersiganure(new byte[]{0, 1, 1, 0, 1, 1, 0});

        EChequeIO writer = new EChequeIO();
        File outputFile = new File(_outputFilePath);

        // Act
        writer.savecheque(eCheque, _outputFilePath);

        // Assert
        Assert.assertTrue(outputFile.exists() && outputFile.isFile());
        Assert.assertFalse(outputFile.length() == 0);
    }

    // TC 1.1.6.2
    @Test(expected = IOException.class)
    public void TC2152TestSaveChequeThrowsIOExceptionForInvalidFilePath() throws IOException{
        // Arrange
        ECheque eCheque = new ECheque();
        eCheque.setaccountholder("Jon Doe");
        eCheque.setaccountNumber("27");
        eCheque.setamountofMony("0.99");
        eCheque.setbankname("My Bank");
        eCheque.setpayToOrderOf("Myself");
        eCheque.setcurrencytype("Dogecoin");
        eCheque.setchequeNumber("1");
        eCheque.setguaranteed(true);
        eCheque.setbanksignature(new byte[]{1,0,0,1,0,0,1});
        eCheque.setdrawersiganure(new byte[]{0, 1, 1, 0, 1, 1, 0});

        EChequeIO writer = new EChequeIO();

        // Act
        writer.savecheque(eCheque, "/some/non-existent/file-path");
    }

    // TC 1.1.6.3
    @Test(expected = IOException.class)
    public void TC2153TestSaveChequeThrowsIOExceptionWhenFileAlreadyExists() throws IOException{
        // Arrange
        ECheque eCheque = new ECheque();
        eCheque.setaccountholder("Jon Doe");
        eCheque.setaccountNumber("27");
        eCheque.setamountofMony("0.99");
        eCheque.setbankname("My Bank");
        eCheque.setpayToOrderOf("Myself");
        eCheque.setcurrencytype("Dogecoin");
        eCheque.setchequeNumber("1");
        eCheque.setguaranteed(true);
        eCheque.setbanksignature(new byte[]{1,0,0,1,0,0,1});
        eCheque.setdrawersiganure(new byte[]{0, 1, 1, 0, 1, 1, 0});

        EChequeIO writer = new EChequeIO();

        File outputFile = new File(_outputFilePath);
        outputFile.createNewFile();

        // Act
        writer.savecheque(eCheque, _outputFilePath);
    }

    // TC 1.1.6.4
    @Test(expected = IllegalArgumentException.class)
    public void TC2154TestSaveChequeThrowsIllegalArgumentExceptionForNullECheque() throws IOException{
        EChequeIO eChequeIO = new EChequeIO();
        eChequeIO.savecheque(null, _outputFilePath);
    }

    // TC 1.1.6.5
    @Test
    public void TC2155TestReadChequeReturnsCorrentEChequeForValidFilePath() throws ClassNotFoundException, IOException{
        // Arrange
        ECheque eCheque = new ECheque();
        eCheque.setaccountholder("Jon Doe");
        eCheque.setaccountNumber("27");
        eCheque.setamountofMony("0.99");
        eCheque.setbankname("My Bank");
        eCheque.setpayToOrderOf("Myself");
        eCheque.setcurrencytype("Dogecoin");
        eCheque.setchequeNumber("1");
        eCheque.setguaranteed(true);
        eCheque.setbanksignature(new byte[]{1,0,0,1,0,0,1});
        eCheque.setdrawersiganure(new byte[]{0, 1, 1, 0, 1, 1, 0});

        EChequeIO eChequeIO = new EChequeIO();

        // Act
        eChequeIO.savecheque(eCheque, _outputFilePath);
        ECheque returnedECheque = eChequeIO.readcheque(_outputFilePath);

        // Assert
        Assert.assertEquals(eCheque.getaccountholder(), returnedECheque.getaccountholder());
        Assert.assertEquals(eCheque.getaccountNumber(), returnedECheque.getaccountNumber());
        Assert.assertEquals(eCheque.getMoney(), returnedECheque.getMoney());
        Assert.assertEquals(eCheque.getbankname(), returnedECheque.getbankname());
        Assert.assertEquals(eCheque.getpayToOrderOf(), returnedECheque.getpayToOrderOf());
        Assert.assertEquals(eCheque.getcurrencytype(), returnedECheque.getcurrencytype());
        Assert.assertEquals(eCheque.getchequeNumber(), returnedECheque.getchequeNumber());
        Assert.assertEquals(eCheque.getguaranteed(), returnedECheque.getguaranteed());
        Assert.assertArrayEquals(eCheque.getbanksignature(), returnedECheque.getbanksignature());
        Assert.assertArrayEquals(eCheque.getdrawersiganure(), returnedECheque.getdrawersiganure());
    }

    // TC 1.1.6.6
    @Test(expected = IOException.class)
    public void TC2156TestReadChequeThrowsIOExceptionForInvalidFilePath() throws ClassNotFoundException, IOException{
        EChequeIO eChequeIO = new EChequeIO();
        eChequeIO.readcheque("non-existent-file-path");
    }

    // TC 1.1.6.7
    @Test(expected = FileNotFoundException.class)
    public void TC2157TestReadChequeThrowsIOExcepptionWhenFilePathIsDirectory() throws ClassNotFoundException, IOException{
        EChequeIO eChequeIO = new EChequeIO();
        eChequeIO.readcheque(_testDirectoryPath);
    }

    // TC 1.1.6.8
    @Test
    public void TC2158TestReadChequeReturnsNullForNonEChequeFileType() throws ClassNotFoundException, IOException{
        // Arrange
        EChequeIO eChequeIO = new EChequeIO();
        File emptyOutputFile = new File(_testDirectoryPath, "empty.txt");
        emptyOutputFile.createNewFile();

        // Act
        ECheque returned = eChequeIO.readcheque(emptyOutputFile.getPath());

        // Assert
        Assert.assertNull(returned);
    }
}
