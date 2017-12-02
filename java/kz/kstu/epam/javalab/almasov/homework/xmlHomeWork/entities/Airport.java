package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities;

import java.util.List;
import java.util.Objects;

public class Airport {

    private List<Plane> planeList;

    public Airport() {

    }

    public Airport(List<Plane> planeList) {
        this.planeList = planeList;
    }

    public List<Plane> getPlaneList() {
        return planeList;
    }

    public void setPlaneList(List<Plane> planeList) {
        this.planeList = planeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport = (Airport) o;
        return Objects.equals(planeList, airport.planeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planeList);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "planeList=" + planeList +
                '}';
    }
}
