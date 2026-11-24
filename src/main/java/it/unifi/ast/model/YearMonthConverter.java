
package it.unifi.ast.model;

@jakarta.persistence.Converter
public class YearMonthConverter implements
        jakarta.persistence.AttributeConverter<
                java.time.YearMonth, java.lang.String> {

    @Override
    public java.lang.String convertToDatabaseColumn(
            java.time.YearMonth month) {

        return month == null ? null : month.toString();
    }

    @Override
    public java.time.YearMonth convertToEntityAttribute(
            java.lang.String value) {

        return value == null ? null : java.time.YearMonth.parse(value);
    }
}
