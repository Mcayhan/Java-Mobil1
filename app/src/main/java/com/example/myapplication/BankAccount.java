package com.example.myapplication;

public class BankAccount {
    private int bakiye;

    public BankAccount(int bakiye) {
        this.bakiye = bakiye;
    }

    public void paraYatir(int x){
        if (x>0){
            bakiye+=x;
            System.out.println(x + " TL hesabınıza yatırıldı. Güncel bakiye: "+ bakiye);
        }else{
            System.out.println("Geçersiz miktar girdiniz!");
        }
    }

    public void paraCek(int y){
        if (y<=0){
            System.out.println("Geçersiz miktar girdiniz!");
        }else if(y>bakiye){
            System.out.println("Yetersiz bakiye! Hesabınızda " + bakiye + " TL var");
        }else{
            bakiye -= y; //bakiyeden parayı düştük
            System.out.println(y + " TL hesabınızdan çekildi. Güncel bakiye: " + bakiye);

        }
    }

    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }

}
