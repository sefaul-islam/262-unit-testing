package parking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {


    @Test
    public void zeroBalanceTest(){

        Wallet wallet = new Wallet();
        assertTrue(wallet.getBalance()==0);
    }
//    @Test
//    public void negativeAddFundTest(){
//        Wallet wallet = new Wallet();
//        wallet.addFunds();
//    }

    @Test
    public void shouldAddFundsTest(){
        Wallet wallet = new Wallet();
        wallet.addFunds(5);
        assertEquals(5,wallet.getBalance());
    }
    @Test
    public void shouldRejectNegativeFundTest(){
        Wallet wallet = new Wallet();
        assertThrows(InvalidAmountException.class, ()->{
            wallet.addFunds(-20);
        });
    }
    @Test
    public void shouldRejectAddingZeroFundTest(){
        Wallet wallet = new Wallet();
        assertThrows(InvalidAmountException.class, ()->{
            wallet.addFunds(0);
        });
    }

    @Test
    public void shouldDeductPartialFundsTest(){
        Wallet wallet = new Wallet(100);
        wallet.deductFunds(20);
        assertEquals(80,wallet.getBalance());

    }
    @Test
    public void shouldDeductFullFundsTest(){
        Wallet wallet = new Wallet(100.00);
        wallet.deductFunds(100);
        assertTrue(wallet.getBalance()==0);
    }
    @Test
    public void deductMoreThanBalance(){
        Wallet wallet = new Wallet(50);

        assertThrows(InsufficientFundsException.class,()->{
            wallet.deductFunds(100);
        });
        assertTrue(wallet.getBalance()==50);
    }
    @Test
    public void shouldRejectNegativeDeductTest(){
        Wallet wallet = new Wallet(100);
        double balance = wallet.getBalance();
        assertThrows(InvalidAmountException.class,()->{
            wallet.deductFunds(-50);
        });
        assertTrue(balance==100);
    }
    @Test
    public void shouldRejectZeroDeductTest(){
        Wallet wallet = new Wallet(100);
        double balance = wallet.getBalance();
        assertThrows(InvalidAmountException.class,()->{
            wallet.deductFunds(0);
        });
        assertTrue(balance==100);
    }

    @Test
    public void shouldPartialTransferFundsTest(){
        Wallet sender = new Wallet(500);
        double senderBalance = sender.getBalance();
        Wallet receiver = new Wallet(200);
        double receiverBalance = receiver.getBalance();
        double fundAmount = 150;
        sender.transferFunds(receiver,fundAmount);

        assertTrue(receiver.getBalance()== receiverBalance + fundAmount);
    }
    @Test
    public void shouldFullTransferFundsTest(){
        Wallet sender = new Wallet(500);
        double senderBalance = sender.getBalance();
        Wallet receiver = new Wallet(200);
        double receiverBalance = receiver.getBalance();
        double fundAmount = 500;
        sender.transferFunds(receiver,fundAmount);

        assertTrue(receiver.getBalance()== receiverBalance + fundAmount);
    }
    @Test
    public void shouldRejectGreaterBalanceTransferFundsTest(){
        Wallet sender = new Wallet(500);
        double senderBalance = sender.getBalance();
        Wallet receiver = new Wallet(200);
        double receiverBalance = receiver.getBalance();
        double fundAmount = 1000;

        assertThrows(InsufficientFundsException.class,()->{
            sender.transferFunds(receiver,fundAmount);
        });
        assertTrue(receiver.getBalance()== receiverBalance);
    }
    @Test
    public void shouldRejectNegativeBalanceTransferFundsTest(){
        Wallet sender = new Wallet(500);
        double senderBalance = sender.getBalance();
        Wallet receiver = new Wallet(200);
        double receiverBalance = receiver.getBalance();
        double fundAmount = -1000;

        assertThrows(InvalidAmountException.class,()->{
            sender.transferFunds(receiver,fundAmount);
        });
        assertTrue(receiver.getBalance()== receiverBalance);
    }
    @Test
    public void shouldRejectZeroTransferFundsTest(){
        Wallet sender = new Wallet(500);
        Wallet receiver = new Wallet(200);
        double receiverBalance = receiver.getBalance();
        double fundAmount = 0;

        assertThrows(InvalidAmountException.class,()->{
            sender.transferFunds(receiver,fundAmount);
        });
        assertTrue(receiver.getBalance()== receiverBalance);
    }

    @Test
    public void shouldCheckNullRecipient(){
        Wallet  sender = new Wallet(500);
        double sendFunds = 100;
        assertThrows(NullPointerException.class,()->{
            sender.transferFunds(null,100);
        });
//        assertEquals(500,sender.getBalance());
        //Defect 1 even after null exception, the fund gets deducted but as there is
        //no recipient the funds doesn't get rolled back
        // no atomicity of funds
    }

}
