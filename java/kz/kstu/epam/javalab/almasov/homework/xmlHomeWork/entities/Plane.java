package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities;

import java.util.Objects;

public class Plane implements Comparable {

    private Integer number;
    private String model;
    private String origin;
    private Chars chars = new Chars();
    private Parameters parameters = new Parameters();
    private Cost cost = new Cost();

    @Override
    public int compareTo(Object object) {
        Plane plane = null;
        if (object instanceof Plane) {
            plane = (Plane) object;
        }
        if (this.getCost().getPrice() < plane.getCost().getPrice()) {
            return -1;
        } else if (this.getCost().getPrice() > plane.getCost().getPrice()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static class Chars {

        private String type;
        private Integer crewSeatsNumber;
        private Integer carryingCapacity;
        private Integer passengersNumber;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getCrewSeatsNumber() {
            return crewSeatsNumber;
        }

        public void setCrewSeatsNumber(Integer crewSeatsNumber) {
            this.crewSeatsNumber = crewSeatsNumber;
        }

        public Integer getCarryingCapacity() {
            return carryingCapacity;
        }

        public void setCarryingCapacity(Integer carryingCapacity) {
            this.carryingCapacity = carryingCapacity;
        }

        public Integer getPassengersNumber() {
            return passengersNumber;
        }

        public void setPassengersNumber(Integer passengersNumber) {
            this.passengersNumber = passengersNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Chars)) return false;
            Chars chars = (Chars) o;
            return Objects.equals(type, chars.type) &&
                    Objects.equals(crewSeatsNumber, chars.crewSeatsNumber) &&
                    Objects.equals(carryingCapacity, chars.carryingCapacity) &&
                    Objects.equals(passengersNumber, chars.passengersNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, crewSeatsNumber, carryingCapacity, passengersNumber);
        }
    }

    public static class Parameters {
        private Double lenght;
        private Double width;
        private Double height;

        public Double getLenght() {
            return lenght;
        }

        public void setLenght(Double lenght) {
            this.lenght = lenght;
        }

        public Double getWidth() {
            return width;
        }

        public void setWidth(Double width) {
            this.width = width;
        }

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Parameters)) return false;
            Parameters that = (Parameters) o;
            return Objects.equals(lenght, that.lenght) &&
                    Objects.equals(width, that.width) &&
                    Objects.equals(height, that.height);
        }

        @Override
        public int hashCode() {
            return Objects.hash(lenght, width, height);
        }
    }

    public static class Cost {
        private Double price;
        private String currency;

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Cost)) return false;
            Cost cost = (Cost) o;
            return Objects.equals(price, cost.price) &&
                    Objects.equals(currency, cost.currency);
        }

        @Override
        public int hashCode() {
            return Objects.hash(price, currency);
        }
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Chars getChars() {
        return chars;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public Cost getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return Objects.equals(number, plane.number) &&
                Objects.equals(model, plane.model) &&
                Objects.equals(origin, plane.origin) &&
                Objects.equals(chars, plane.chars) &&
                Objects.equals(parameters, plane.parameters) &&
                Objects.equals(cost, plane.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, origin, chars, parameters, cost);
    }

    @Override
    public String toString() {
        return "Plane: " +
                "number=" + number +
                ", model=" + model +
                ", origin=" + origin +
                ", chars{" +
                "type=" + chars.getType() +
                ", crewSeatsNumber=" + chars.getCrewSeatsNumber() +
                ", carryingCapacity=" + chars.getCarryingCapacity() +
                ", passengersNumber=" + chars.getPassengersNumber() +
                "}, parameters{" +
                "length=" + parameters.getLenght() +
                ", width=" + parameters.getWidth() +
                ", height=" + parameters.getHeight() +
                "}, cost=" + cost.getPrice() + " " + cost.getCurrency();
    }
}
