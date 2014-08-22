package be.crydust;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Amount implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "cents")
    @Convert(converter = DollarsToCentsConverter.class)
    private BigDecimal dollars;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getDollars() {
        return dollars;
    }

    public void setDollars(BigDecimal dollars) {
        this.dollars = dollars;
    }

    @Override
    public String toString() {
        return "Amount{" + "dollars=" + dollars + '}';
    }

}
