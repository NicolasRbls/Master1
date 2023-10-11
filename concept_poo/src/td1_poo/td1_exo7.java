package td1_poo;

public class td1_exo7 {
    public static void main(String[] args) {
        double celsius = 25; // exemple de température en Celsius
        double fahrenheit = 77; // exemple de température en Fahrenheit

        System.out.println(celsius + "°C est égal à " + celsiusToFahrenheit(celsius) + "°F");
        System.out.println(fahrenheit + "°F est égal à " + fahrenheitToCelsius(fahrenheit) + "°C");
    }

    /**
     * Convertit une température de Celsius à Fahrenheit.
     * @param celsius - Température en Celsius.
     * @return Température en Fahrenheit.
     */
    public static double celsiusToFahrenheit(double celsius) {
        return (9.0 / 5.0) * celsius + 32;
    }

    /**
     * Convertit une température de Fahrenheit à Celsius.
     * @param fahrenheit - Température en Fahrenheit.
     * @return Température en Celsius.
     */
        
    public static double fahrenheitToCelsius(double fahrenheit) {
            return (5.0 / 9.0) * (fahrenheit - 32);
        }
}
