class Rental {
    private Car car;
    private Customer customer;
    private int rentalDays;
    private double totalPrice;

    public Rental(Car car, Customer customer, int rentalDays) {
        this.car = car;
        this.customer = customer;
        this.rentalDays = rentalDays;
        this.totalPrice = car.calculatePrice(rentalDays);
    }

    public Car getCar() { return car; }
    public Customer getCustomer() { return customer; }
    public int getRentalDays() { return rentalDays; }
    public double getTotalPrice() { return totalPrice; }
}