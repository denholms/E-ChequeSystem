package eCheque.Test;

/**
 * Created by bolinkd on 13/07/15.
 */

import eCheque.EChequeRegisteration;
import org.junit.*;

public class ClientEChequeRegistrationTest {

    public ClientEChequeRegistrationTest(){

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("\nSETUP CLASS RUNNING -- Nothing to do though");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("\nTEARDOWN CLASS RUNNING -- Nothing to do though");
    }

    @Before
    public void setUp() {
        System.out.println("\nSETUP IS RUNNING -- Nothing to do though");
    }

    @After
    public void tearDown() {
        System.out.println("TEARDOWN IS RUNNING -- Nothing to do though");
    }

    @Test
    public void testAssessors(){
        System.out.println("testAssessors");

        String expBankName = "Test Bank";
        String expBankAddress = "123 Fake Street";
        String expAccountNumber = "1921681100";
        String expClientName = "Billy Johnson";
        String expEWalletLocation = "/home/user/Documents/Wallet";
        int expUserNameHash = 16;
        int expPasswordHash = 82;

        EChequeRegisteration instance = new EChequeRegisteration();
        instance.setAccountNumber("1921681100");
        instance.setBankAddress("123 Fake Street");
        instance.setBankName("Test Bank");
        instance.setClientName("Billy Johnson");
        instance.setEWalletLoaction("/home/user/Documents/Wallet");
        instance.setUsername(16);
        instance.setPasword(82);


        Assert.assertEquals("Bank Name"       ,expBankName        ,instance.getBankName());
        Assert.assertEquals("Bank Address"    ,expBankAddress     ,instance.getBankAddress());
        Assert.assertEquals("Account Number"  ,expAccountNumber   ,instance.getAccountNumber());
        Assert.assertEquals("Client Name"     ,expClientName      ,instance.getClientName());
        Assert.assertEquals("Wallet Location" ,expEWalletLocation ,instance.getEWalletLoaction());
        Assert.assertEquals("Username Hash"   ,expUserNameHash    ,instance.getUsername());
        Assert.assertEquals("Password Hash"   ,expPasswordHash    ,instance.getPasword());




    }

}
