package deadlock;

import java.util.Random;

/**
 * 多人同时转账，依然很危险
 */
public class MultiTransferMoney {
    public static final int NUM_ACCOUNTS = 500;
    public static final int NUM_MONEY = 1000;
    public static final int NUM_ITERATIONS = 1000000;
    public static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        Random random = new Random();
        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new TransferMoney.Account(NUM_MONEY);
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = random.nextInt(NUM_ACCOUNTS);
                    int toAcct = random.nextInt(NUM_ACCOUNTS);
                    int amount = random.nextInt(NUM_MONEY);
                    TransferMoney.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }

    }
}
