package eCheque.Test;
import eCheque.ECheque;
import org.junit.Assert;
import org.junit.Test;

public class EChequeTest {

    // TC 1.1.5.1
    @Test
    public void TC1151TestAccountHolderAccessors(){
        ECheque echeque = new ECheque();
        String accountHolder = "John Smith";

        echeque.setaccountholder(accountHolder);
        Assert.assertEquals(accountHolder, echeque.getaccountholder());

        echeque.setaccountholder(null);
        Assert.assertEquals(null, echeque.getaccountholder());
    }

    // TC 1.1.5.1
    @Test
    public void TC1151TestAccountNumberAccessors(){
        ECheque echeque = new ECheque();
        String accountNumber = "42";

        echeque.setaccountNumber(accountNumber);
        Assert.assertEquals(accountNumber, echeque.getaccountNumber());

        echeque.setaccountNumber(null);
        Assert.assertEquals(null, echeque.getaccountNumber());
    }

    // TC 1.1.5.1
    @Test
    public void TC1151TestBankNameAccessors(){
        ECheque eCheque = new ECheque();
        String bankName = "I'm a bank!";

        eCheque.setbankname(bankName);
        Assert.assertEquals(bankName, eCheque.getbankname());

        eCheque.setbankname(null);
        Assert.assertEquals(null, eCheque.getbankname());
    }

    // TC 1.1.5.1
    @Test
    public void TC1151TestPayToOrderOfAccessors(){
        ECheque eCheque = new ECheque();
        String payToOrderOf = "My Bank Account";

        eCheque.setpayToOrderOf(payToOrderOf);
        Assert.assertEquals(payToOrderOf, eCheque.getpayToOrderOf());

        eCheque.setpayToOrderOf(null);
        Assert.assertEquals(null, eCheque.getpayToOrderOf());
    }

    // TC 1.1.5.1
    @Test
    public void TC1151TestAmountOfMoneyAccessors(){
        ECheque eCheque = new ECheque();
        String amount = "1.00";

        eCheque.setamountofMony(amount);
        Assert.assertEquals(amount, eCheque.getMoney());

        eCheque.setamountofMony(null);
        Assert.assertEquals(null, eCheque.getMoney());
    }

    // TC 1.1.5.1
    @Test
    public void TC1151TestCurrencyTypeAccessors(){
        ECheque eCheque = new ECheque();
        String currencyType = "Bitcoin";

        eCheque.setcurrencytype(currencyType);
        Assert.assertEquals(currencyType, eCheque.getcurrencytype());

        eCheque.setcurrencytype(null);
        Assert.assertEquals(null, eCheque.getcurrencytype());
    }

    // TC 1.1.5.1
    @Test
    public void TC1151TestChequeNumberAccessors(){
        ECheque eCheque = new ECheque();
        String number = "ABC-123";

        eCheque.setchequeNumber(number);
        Assert.assertEquals(number, eCheque.getchequeNumber());

        eCheque.setchequeNumber(null);
        Assert.assertEquals(null, eCheque.getchequeNumber());
    }

    // TC 1.1.5.1
    @Test
    public void TC1151TestGuaranteedAccessors(){
        ECheque eCheque = new ECheque();

        eCheque.setguaranteed(true);
        Assert.assertEquals(true, eCheque.getguaranteed());

        eCheque.setguaranteed(false);
        Assert.assertEquals(false, eCheque.getguaranteed());
    }

    // TC 1.1.5.1
    @Test
    public void TC1151TestEarnDayAccessors(){
        ECheque eCheque = new ECheque();
        String earnDay = "Monday";

        eCheque.setearnday(earnDay);
        Assert.assertEquals(earnDay, eCheque.getearnday());

        eCheque.setearnday(null);
        Assert.assertNull(eCheque.getearnday());
    }

    // TC 1.1.5.1
    @Test
    public void TC1151TestBankSignatureAccessors(){
        ECheque eCheque = new ECheque();
        byte[] signature = {1, 0, 0, 1, 0, 0 ,1};

        eCheque.setbanksignature(signature);
        Assert.assertArrayEquals(signature, eCheque.getbanksignature());

        eCheque.setbanksignature(null);
        Assert.assertArrayEquals(null, eCheque.getbanksignature());
    }

    // TC 1.1.5.1
    @Test
    public void TC1151TestDrawerSignatureAccessors(){
        ECheque eCheque = new ECheque();
        byte[] signature = {1, 0, 0, 1, 0, 0 ,1};

        eCheque.setdrawersiganure(signature);
        Assert.assertArrayEquals(signature, eCheque.getdrawersiganure());

        eCheque.setdrawersiganure(null);
        Assert.assertArrayEquals(null, eCheque.getdrawersiganure());
    }
}
