package eCheque.Test;

/**
 * Created by bolinkd on 13/07/15.
 */

import eCheque.EChequeRegistration;

import org.junit.Assert;
import org.junit.Test;

public class ClientEChequeRegistrationTest {

    EChequeRegistration instance = new EChequeRegistration();


    @Test
    public void TC1171testBankNameAccessor(){
        String expBankName = "Bankers Banking Bank";

        instance.setBankName(null);
        Assert.assertEquals("Bank Name NULL", null, instance.getBankName());

        instance.setBankName("Bankers Banking Bank");
        Assert.assertEquals("Bank Name NOT NULL", expBankName, instance.getBankName());
    }

    @Test
    public void TC1171testBankAddressAccessor(){
        String expBankAddress = "123 Fake Street";

        instance.setBankAddress(null);
        Assert.assertEquals("Bank Address NULL", null, instance.getBankAddress());

        instance.setBankAddress("123 Fake Street");
        Assert.assertEquals("Bank Address NOT NULL", expBankAddress, instance.getBankAddress());
    }

    @Test
    public void TC1171testAccountNumberAccessor(){
        String expAccountNumber = "1921681100";

        instance.setAccountNumber(null);
        Assert.assertEquals("Account Number NULL", null, instance.getAccountNumber());

        instance.setAccountNumber("1921681100");
        Assert.assertEquals("Account Number NOT NULL", expAccountNumber, instance.getAccountNumber());
    }

    @Test
    public void TC1171testClientNameAccessor(){
        String expClientName = "Billy Johnson";

        instance.setClientName(null);
        Assert.assertEquals("Client Name NULL", null, instance.getClientName());

        instance.setClientName("Billy Johnson");
        Assert.assertEquals("Client name NOT NULL", expClientName, instance.getClientName());
    }

    @Test
    public void TC1171testWalletLocationAccessor(){
        String expEWalletLocation = "/home/usr/Documents/Wallet";

        instance.setEWalletLocation(null);
        Assert.assertEquals("Wallet Location NULL", null, instance.getEWalletLocation());

        instance.setEWalletLocation("/home/usr/Documents/Wallet");
        Assert.assertEquals("Wallet Location NOT NULL", expEWalletLocation, instance.getEWalletLocation());
    }

    @Test
    public void TC1171testUsernameHashAccessor(){
        int expUsernameHash = 42;

        instance.setUsername(42);
        Assert.assertEquals("Username Hash", expUsernameHash, instance.getUsername());
    }

    @Test
    public void TC1171testPasswordHashAccessor(){
        int expPasswordHash = 42;

        instance.setPassword(42);
        Assert.assertEquals("Password Hash", expPasswordHash, instance.getPassword());
    }

}
