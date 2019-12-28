package ru.job4j.inheritance;

public class Profession {
    private String name;    //Имя
    private String surname; //Фамилия
    private String education;   //Образование
    private String birthday;    //День рождения

    public Profession(String name, String surname, String education, String birthday) {
        this.name = name;
        this.surname = surname;
        this.education = education;
        this.birthday = birthday;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String education() {
        return this.education;
    }

    public String getBirthday() {
        return this.birthday;
    }
}
