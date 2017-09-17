public class Account {

    private double balance;
    private int withdrawals;
    private final double FEE_SURCHARGE = 1.50;//$1.50 fee

    public Account(double initialBal, int iniwithdrawals) {
        withdrawals = iniwithdrawals;
        balance = initialBal;
    }

    public double getBalance() {
        return balance;
    }

    public void DEPOSIT(double AMOUNT) {
        balance += AMOUNT;
    }



    public void WITHDRAW(double AMOUNT) throws InsufficentFundsExcpetion{
        if ((AMOUNT % 20) != 0) {
            throw new InsufficentFundsExcpetion( "Withdrawals must be in $20 increments!  " );
        }
        if (AMOUNT > balance) {
            throw new InsufficentFundsExcpetion( "WITHDRAWAL $AMOUNT EXCEEDS ACCOUNT BALANCE!!!" );
        }
        if (AMOUNT >= (balance + FEE_SURCHARGE)) {
            throw new InsufficentFundsExcpetion( "Insufficient Funds!" );
        } else if (AMOUNT <= (balance + FEE_SURCHARGE)) {
            withdrawals++;
            if (withdrawals < 4) {
                balance = balance - AMOUNT;
            } else if (withdrawals >= 4){
                balance = balance - AMOUNT - FEE_SURCHARGE;
            }


        } else {
            System.err.println( "Error with Account, Please see teller." );
        }
    }

}
