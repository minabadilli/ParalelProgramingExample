package com.mycompany.paralelprogramingexample;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ParalelProgramingExample {
    
    // Birinci thread kullanıcı bilgilerini alıp person.txt dosyasına kaydedecek.
    static class FirstThread implements Runnable {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Kullanıcı adını girin:");
            String username = scanner.nextLine();

            System.out.print("E-posta adresini girin:");
            String email = scanner.nextLine();

            System.out.print("Şifreyi girin:");
            String password = scanner.nextLine();

            // Birinci thread'ı dosyaya yazma işlemi gerçekleştirecek.
            try {
                FileWriter fileWriter = new FileWriter("c:\\io\\turgutozaluniversitesi\\person.txt");
                fileWriter.write("Kullanıcı Adı: " + username + "\n");
                fileWriter.write("E-posta: " + email + "\n");
                fileWriter.write("Şifre: " + password + "\n");
                fileWriter.close();
                System.out.println("BİLGİLER PERSON.TXT DOSYASINA KAYDEDİLDİ.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // İkinci thread gizli bilgileri alıp secret.txt dosyasına kaydedecek.
    static class SecondThread implements Runnable {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Gizli bilgiyi girin:");
            String secretInformation = scanner.nextLine();

            // İkinci thread'ı dosyaya yazma işlemi gerçekleştirecek.
            try {
                FileWriter fileWriter = new FileWriter("c:\\io\\turgutozaluniversitesi\\secret.txt");
                fileWriter.write("Gizli Bilgi: " + secretInformation + "\n");
                fileWriter.close();
                System.out.println("GİZLİ BİLGİ SECRET.TXT DOSYASINA KAYDEDİLDİ.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        
        // Step-1) İki farklı veriyi sırayla almak için iki thread oluşturuldu.
        Thread firstThread = new Thread(new FirstThread());
        Thread secondThread = new Thread(new SecondThread());
        
        // Step-2) Birinci thread kullanıcı bilgilerini aldı ve person.txt adlı dosyaya kaydetti.
        firstThread.start();
        try {
            firstThread.join(); // Birinci thread'in bitmesini bekledi.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step-3) İkinci thread gizli bilgiyi aldı ve secret.txt adlı dosyaya kaydetti.
        secondThread.start();
    }
}













