package com.danceschool.danceschool;;

public class Student {

    private PersonalData personalData;
    private Level level;

    public static class Builder {

        private PersonalData personalData;
        private Level level;

        public Builder() {
        }

        Builder(PersonalData personalData, Level level) {
            this.personalData = personalData;
            this.level = level;
        }

        public Builder personalData(PersonalData personalData){
            this.personalData = personalData;
            return Builder.this;
        }

        public Builder level(Level level){
            this.level = level;
            return Builder.this;
        }

        public Student build() {
            if(this.personalData == null){
                throw new NullPointerException("The property \"personalData\" is null. "
                        + "Please set the value by \"personalData()\". "
                        + "The property \"personalData\" is required.");
            }

            return new Student(this);
        }
    }

    private Student(Builder builder) {
        this.personalData = builder.personalData;
        this.level = builder.level;
    }

    public void doSomething() {
        // do something
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (personalData != null ? !personalData.equals(student.personalData) : student.personalData != null)
            return false;
        return level == student.level;
    }

    @Override
    public int hashCode() {
        int result = personalData != null ? personalData.hashCode() : 0;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "personalData=" + personalData +
                ", level=" + level +
                '}';
    }
}
