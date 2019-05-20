package com.SBI;

public class myThread extends Thread {
    private Bank bankObj;
    private int start;
    private int end;
    myThread(Bank bankObj, int start, int end){
        this.bankObj = bankObj;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run(){
        try{
            for(int i=start; i<end; i++){
                Transaction t = bankObj.transactionList.get(i);
                int account1 = t.getAccount1();
                int account2 = t.getAccount2();
                bankObj.process(account1, account2);
            }
        }catch(Exception e){
            System.out.println("Exception Caught : " + e);
        }
    }
}
