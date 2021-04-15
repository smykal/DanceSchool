package com.danceschool.danceschool.data;

public class PersonalData {
    private String name;
    private String surname;
    private Address address;

    private PersonalData(String name, String surname, Address address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(Address address) { this.address = address; }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Address getAddress() { return address; }


    @Override
    public String toString() {
        return "PersonalData{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalData that = (PersonalData) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return surname != null ? surname.equals(that.surname) : that.surname == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    public static final class PersonalDataBuilder {
        private String name;
        private String surname;
        private Address address;

        public PersonalDataBuilder() {
        }

        public PersonalDataBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonalDataBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonalDataBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public PersonalData build() {
            return new PersonalData(name, surname, address);
        }
    }
}
