package servicios;

public record ExchangeCurrency(

        String time_last_update_utc,
        String time_next_update_utc,
        String base_code,
        String target_code,
        double conversion_rate
) {
}
