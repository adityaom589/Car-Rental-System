import java.sql.*;
import java.util.List;

class CarRentalSystem {

    public void addCar(Car car) throws SQLException {
        car.insertIntoDB();
    }

    public List<Car> getAvailableCars() throws SQLException {
        return Car.getAllCars();
    }

    public void rentCar(Car car, Customer customer, int days) throws SQLException {
        customer.saveToDB();
        car.updateAvailability(false);

        double total = car.calculatePrice(days);
        String sql = "INSERT INTO rentals (car_id, customer_id, rental_days, total_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, car.getCarId());
            ps.setString(2, customer.getCustomerId());
            ps.setInt(3, days);
            ps.setDouble(4, total);
            ps.executeUpdate();
        }
        System.out.println("✅ Car rented successfully! Total price: ₹" + total);
    }

    public void returnCar(Car car) throws SQLException {
        car.updateAvailability(true);
        System.out.println("✅ Car returned successfully!");
    }
}