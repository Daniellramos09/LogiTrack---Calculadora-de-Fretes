package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Delivery {

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String id;
    private LocalDate date;
    private String capital;
    private Double kg;
    private String name;

    public Delivery(){

    }

    public Delivery(String id, LocalDate date, String capital, Double kg, String name) {
        this.id = id;
        this.date = date;
        this.capital = capital;
        this.kg = kg;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Delivery entrega = (Delivery) o;
        return Objects.equals(id, entrega.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "id=" + id +
                ", date=" + date.format(fmt) +
                ", capital='" + capital + '\'' +
                ", kg=" + kg +
                ", name='" + name + '\'' +
                '}';
    }
}

