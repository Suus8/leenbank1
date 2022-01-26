package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetails {
    //region ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Sector sector;
    //endregion

    //region CONSTRUCTOR
    public CompanyDetails(String name, Sector sector) {
        this.name = name;
        this.sector = sector;
    }
    //endregion

    //region METHODS

    //endregion

    //region Helper Methods

    //endregion

    //region GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public String toString() {
        return "CompanyDetails{" +
                "name='" + name + '\'' +
                ", sector=" + sector +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDetails that = (CompanyDetails) o;
        return Objects.equals(name, that.name) && sector == that.sector;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sector);
    }
    //endregion

}
