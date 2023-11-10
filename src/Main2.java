import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Урок 20. Задание с использованием Stream Api.
                    Реализовать навигационное меню состоящее из:
                    Каталог
                    Корзина
                    Оформление заказа
                    Назад

                Решение:\s""");


        Products3 nokia = new Products3("Nokia 3310", 12345, 4, 0);
        Products3 samsungGalaxy = new Products3("Samsung Galaxy S100", 30000, 7, 0);
        Products3 iphone = new Products3("IPhone 20", 392049, 1, 0);
        Products3 googlePixel = new Products3("Google Pixel 10a", 30000, 0, 0);
        List<Products3> catalog = Arrays.asList(nokia, samsungGalaxy, iphone, googlePixel);

        System.out.println("\nРЕАЛИЗУЕМ НАВИГАЦИОННОЕ МЕНЮ, СОСТОЯЩЕЕ ИЗ РАЗДЕЛОВ:");
        System.out.println("ПОЛНЫЙ КАТАЛОГ:");
        catalog.forEach(p -> System.out.printf("Наименование: %s, Цена: %d, Кол-во на складе: %d.%n",
                p.getName(), p.getPrice(), p.getQtyInStock())
        );
        System.out.println();

        String name = "Samsung Galaxy S100";
        int count = 2;

        System.out.printf("КОРЗИНА: \n(Выбрано:  %s- %d шт.)%n", name, count);

// нашли продукт
        Products3 selected = catalog.stream()
                .filter(product -> Objects.equals(product.getName(), name))
                .filter(product -> product.getQtyInStock() >= count)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Товар не найден"));
// изменили состояние при заказе
        selected.setQtyOrdered(count);
// распечатали содержимое корзины
        catalog
                .stream()
                .filter(product -> product.getQtyOrdered() > 0)
                .forEach(p -> System.out.printf("Наименование: %s, Цена: %d, Кол-во заказа: %d.%n",
                        p.getName(), p.getPrice(), p.getQtyOrdered()
                ));
        System.out.println();

        System.out.println("ОФОРМЛЕНИЕ ЗАКАЗА:");
        catalog.stream()
                .filter(product -> Objects.equals(product.getName(), name)) // product.getQtyOrdered() > 0
                .forEach(p -> System.out.printf("Наименование: %s, Цена: %d, Кол-во заказа: %d, Сумма к оплате: %d руб.%n",
                        p.getName(), p.getPrice(), p.getQtyOrdered(), p.getPrice() * p.getQtyOrdered()
                ));
        System.out.println();
// изменили состояние после оформления заказа, т.е. покупки
        selected.sell();

        System.out.println("НАЗАД:");
        catalog.forEach(p -> System.out.printf("Наименование: %s, Цена: %d, Кол-во на складе: %d.%n",
                p.getName(), p.getPrice(), p.getQtyInStock())
        );
    }
}

class Products3 {
    private String name;
    private int price;
    private int qtyInStock;
    private int qtyOrdered;


    public Products3(String name, int price, int qtyInStock, int qtyOrdered) {
        this.setName(name);
        this.setPrice(price);
        this.setQtyInStock(qtyInStock);
        this.setQtyOrdered(qtyOrdered);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQtyInStock() {
        return qtyInStock;
    }

    public void setQtyInStock(int qtyInStock) {
        this.qtyInStock = qtyInStock;
    }

    public int getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(int qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    @Override
    public String toString() {
        return "Products3{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", qtyInStock=" + qtyInStock +
                ", qtyOrdered=" + qtyOrdered +
                '}';
    }

    // class Products3
    public void sell() {
        this.qtyInStock -= this.qtyOrdered;
        this.qtyOrdered = 0;
    }
}

/*public */class Shop {
    private List<Products> stock;

    public Shop(List<Products> products) {
        this.stock = new ArrayList<>(products);
    }

    // Каталог
    public void inventory() {
        // вывести каталог товаров
    }

    // Корзина
    public void putToCart(String name, int count) {
        // добавить в "корзину", распечатать её содержимое
    }

    // Оформить заказ
    public void completeOrder() {
        // изменить состояние склада
    }
// ...
}
