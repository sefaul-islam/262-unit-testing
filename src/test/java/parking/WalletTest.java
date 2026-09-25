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
    public void shouldRejectZeroFundTest(){
        Wallet wallet = new Wallet();
        assertThrows(InvalidAmountException.class, ()->{
            wallet.addFunds(0);
        });
    }

    @Test
    public void shouldDeductFundsTest(){
        Wallet wallet = new Wallet(100);
        wallet.deductFunds(20);
        assertEquals(80,wallet.getBalance());

    }
}
