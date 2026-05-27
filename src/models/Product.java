package models;

public class Product {
    private String name;
    private String category;
    private double price;
    private int stock;
    private double rating;
    private int reviewsCount;

    public Product(String name, String category, double price, int stock, double rating, int reviewsCount) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.rating = rating;
        this.reviewsCount = reviewsCount;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public double getRating() { return rating; }
    public int getReviewsCount() { return reviewsCount; }
}
