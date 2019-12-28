package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book hp = new Book("Harry Potter", 250);
        Book gm = new Book("Gentleman in Moscow", 350);
        Book hd = new Book("Heart Of A Dog", 300);
        Book cc = new Book("Clean code", 320);

        Book[] books = new Book[4];

        books[0] = hp;
        books[1] = gm;
        books[2] = hd;
        books[3] = cc;

        for (int index = 0; index < books.length; index++) {
            Book pr = books[index];
            System.out.println(pr.getName() + " - " + pr.getPages());
        }
        System.out.println();

        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;

        for (int index = 0; index < books.length; index++) {
            Book pr = books[index];
            System.out.println(pr.getName() + " - " + pr.getPages());
        }
        System.out.println();

        System.out.println("Shown book 'Clean code':");
        for (int index = 0; index < books.length; index++) {
            Book pr = books[index];
            if (pr.getName().equals("Clean code")) {
                System.out.println(pr.getName() + " - " + pr.getPages());
            }
        }
    }
}