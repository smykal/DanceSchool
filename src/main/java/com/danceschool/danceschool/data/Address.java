package com.danceschool.danceschool.data;

public class Address {

    private String city;
    private String postalCode;
    private String street;
    private String blockNumber;
    private String apartmentNumber;

    public static class Builder {

        private String city;
        private String postalCode;
        private String street;
        private String blockNumber;
        private String apartmentNumber;

        public Builder() {
        }

        Builder(String city, String postalCode, String street, String blockNumber, String apartmentNumber) {
            this.city = city;
            this.postalCode = postalCode;
            this.street = street;
            this.blockNumber = blockNumber;
            this.apartmentNumber = apartmentNumber;
        }

        public Builder city(String city){
            this.city = city;
            return Builder.this;
        }

        public Builder postalCode(String postalCode){
            this.postalCode = postalCode;
            return Builder.this;
        }

        public Builder street(String street){
            this.street = street;
            return Builder.this;
        }

        public Builder blockNumber(String blockNumber){
            this.blockNumber = blockNumber;
            return Builder.this;
        }

        public Builder apartmentNumber(String apartmentNumber){
            this.apartmentNumber = apartmentNumber;
            return Builder.this;
        }

        public Address build() {
            if(this.city == null){
                throw new NullPointerException("The property \"city\" is null. "
                        + "Please set the value by \"city()\". "
                        + "The properties \"city\", \"postalCode\", \"street\" and \"blockNumber\" are required.");
            }
            if(this.postalCode == null){
                throw new NullPointerException("The property \"postalCode\" is null. "
                        + "Please set the value by \"postalCode()\". "
                        + "The properties \"city\", \"postalCode\", \"street\" and \"blockNumber\" are required.");
            }
            if(this.street == null){
                throw new NullPointerException("The property \"street\" is null. "
                        + "Please set the value by \"street()\". "
                        + "The properties \"city\", \"postalCode\", \"street\" and \"blockNumber\" are required.");
            }
            if(this.blockNumber == null){
                throw new NullPointerException("The property \"blockNumber\" is null. "
                        + "Please set the value by \"blockNumber()\". "
                        + "The properties \"city\", \"postalCode\", \"street\" and \"blockNumber\" are required.");
            }

            return new Address(this);
        }
    }

    private Address(Builder builder) {
        this.city = builder.city;
        this.postalCode = builder.postalCode;
        this.street = builder.street;
        this.blockNumber = builder.blockNumber;
        this.apartmentNumber = builder.apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    @Override
    public String toString() {
        if (apartmentNumber == null) {
            return "Address{" +
                    "city='" + city + '\'' +
                    ", postalCode='" + postalCode + '\'' +
                    ", street='" + street + '\'' +
                    ", blockNumber='" + blockNumber + '\'' +
                    '}';
        } else
            return "Address{" +
                    "city='" + city + '\'' +
                    ", postalCode='" + postalCode + '\'' +
                    ", street='" + street + '\'' +
                    ", blockNumber='" + blockNumber + '\'' +
                    ", apartmentNumber='" + apartmentNumber + '\'' +
                    '}';
    }

}