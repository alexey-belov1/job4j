package ru.job4j.inheritance;

public class Programmer extends Engineer {
    private String programmingLanguage;  //Знание языков программирования

    public Programmer(String name, String surname, String education, String birthday, boolean knowledgeOffice, String firmsType, String programmingLanguage) {
        super(name, surname, education, birthday, knowledgeOffice, firmsType);
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return this.programmingLanguage;
    }
}
