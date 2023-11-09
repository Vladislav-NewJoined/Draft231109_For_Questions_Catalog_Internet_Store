import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
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


        Products prod1 = new Products("Nokia 3310", 12345, 4, 0);
        Products prod2 = new Products("Samsung Galaxy S100", 30000, 7, 0);
        Products prod3 = new Products("IPhone 20", 392049, 1, 0);
        Products prod4 = new Products("Google Pixel 10a", 30000, 0, 0);
        List<Products> catalog = new ArrayList<>();
        catalog.add(prod1);
        catalog.add(prod2);
        catalog.add(prod3);
        catalog.add(prod4);

        System.out.println("\nРЕАЛИЗУЕМ НАВИГАЦИОННОЕ МЕНЮ, СОСОЯЩЕЕ ИЗ РАЗДЕЛОВ:");
        System.out.println("ПОЛНЫЙ КАТАЛОГ:");
        catalog.stream().map((product -> "Наименование: " + product.getName() + ", " + "Цена: " +
                        product.getPrice() + ", " + "Кол-во ед. в наличии на складе: " + product.getQtyInStock() + "."))
                .forEach(System.out::println);
        System.out.println();

        System.out.println("КОРЗИНА: \n(Выбрано: Samsung Galaxy S100 - 2 шт.)");
        catalog.stream().filter(element
                        -> Objects.equals(element.getName(), "Samsung Galaxy S100"))
                .map((product -> "Наименование: " + product.getName() + ", " + "Цена: " +
                        product.getPrice() /*+ ", " + "Кол-во ед. в наличии на складе: " + product.getQtyInStock() */+ ", " + "Кол-во заказано: " +
                        (product.getQtyOrdered()+2) + "."))
                .forEach(System.out::println);
        System.out.println();

        System.out.println("ОФОРМЛЕНИЕ ЗАКАЗА:");
        catalog.stream().filter(element
                        -> Objects.equals(element.getName(), "Samsung Galaxy S100"))
                .map((product -> "Наименование: " + product.getName() + ", " + "Цена: " +
                        product.getPrice() /*+ ", " + "Кол-во ед. в наличии на складе: " + product.getQtyInStock() */+ ", " + "Кол-во заказано: " +
                        (product.getQtyOrdered()+2) + ", " +  "Итого сумма к оплате: " +
                        product.getPrice()*2 + " руб."))
                .forEach(System.out::println);
        System.out.println();

        System.out.println("НАЗАД:");

    }
}

class Products {
    private String name;
    private int price;
    private int qtyInStock;
    private int qtyOrdered;


    public Products(String name, int price, int qtyInStock, int qtyOrdered) {
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
        return "Products{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", qtyInStock=" + qtyInStock +
                ", qtyOrdered=" + qtyOrdered +
                '}';
    }
}
