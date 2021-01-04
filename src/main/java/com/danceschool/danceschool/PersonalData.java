package com.danceschool.danceschool;

public class PersonalData {
    private PersonalDataName name;
    private PersonalDataSurname surname;
    private Sex sex;
    private AdvanceLevel advanceLevel;

    public PersonalData(PersonalDataName name, PersonalDataSurname surname, Sex sex, AdvanceLevel advanceLevel) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.advanceLevel = advanceLevel;
    }

    public PersonalDataName getName() {
        return name;
    }

    public PersonalDataSurname getSurname() {
        return surname;
    }

    public Sex getSex() {
        return sex;
    }

    public AdvanceLevel getAdvanceLevel() {
        return advanceLevel;
    }
}

