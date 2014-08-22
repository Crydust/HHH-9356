package be.crydust;

import java.math.BigDecimal;
import java.math.MathContext;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DollarsToCentsConverter implements AttributeConverter<BigDecimal, Long> {

    @Override
    public Long convertToDatabaseColumn(BigDecimal dollars) {
        if (dollars == null) {
            return null;
        }
        Long cents = dollars.multiply(BigDecimal.valueOf(100L), MathContext.DECIMAL128).longValue();
        System.out.printf("*** convertToDatabaseColumn %s dollar = %s cents ***%n", dollars, cents);
        return cents;
    }

    @Override
    public BigDecimal convertToEntityAttribute(Long cents) {
        if (cents == null) {
            return null;
        }
        BigDecimal dollars = BigDecimal.valueOf(cents).divide(BigDecimal.valueOf(100L), MathContext.DECIMAL128);
        System.out.printf("*** convertToEntityAttribute %s cents = %s dollars ***%n", cents, dollars);
        return dollars;
    }

}
