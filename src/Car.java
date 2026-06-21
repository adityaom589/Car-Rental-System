import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() { return carId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getBasePricePerDay() { return basePricePerDay; }
    public boolean isAvailable() { return isAvailable; }

    public void rent() { isAvailable = false; }
    public void returnCar() { isAvailable = true; }

    public double calculatePrice(int days) {
        return basePricePerDay * days;
    }

    public static List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Car car = new Car(
                    rs.getString("car_id"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getDouble("base_price_per_day")
                );
                if (!rs.getBoolean("is_available")) car.rent();
                cars.add(car);
            }
        }
        return cars;
    }

    public void insertIntoDB() throws SQLException {
        String sql = "INSERT IGNORE INTO cars VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, carId);
            ps.setString(2, brand);
            ps.setString(3, model);
            ps.setDouble(4, basePricePerDay);
            ps.setBoolean(5, true);
            ps.executeUpdate();
        }
    }

    public void updateAvailability(boolean available) throws SQLException {
        String sql = "UPDATE cars SET is_available = ? WHERE car_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, available);
            ps.setString(2, carId);
            ps.executeUpdate();
        }
    }
}