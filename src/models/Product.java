package models;

public class Product {
    private String name;
    private String category;
    private double price;
    private int stock, id;
    private double rating;
    private int reviewsCount;

    public Product(int id, String name, String category, double price, int stock, double rating, int reviewsCount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.rating = rating;
        this.reviewsCount = reviewsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return this.name.equals(p.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public double getRating() { return rating; }
    public int getReviewsCount() { return reviewsCount; }
}
