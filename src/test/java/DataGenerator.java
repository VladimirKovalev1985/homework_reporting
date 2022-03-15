import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class Generator {
    private Generator() {
    }

    private static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {
        String[] Cities = new String[]{"Санкт-Петербург", "Омск", "Новосибирск", "Уфа", "Москва"};
        int city = (int) Math.floor(Math.random() * Cities.length);
        return Cities[city];
    }

    public static String generateName(String locale) {
        return faker.name().fullName();
    }

    public static String generatePhone(String locale) {
        return faker.phoneNumber().phoneNumber();
    }

}
