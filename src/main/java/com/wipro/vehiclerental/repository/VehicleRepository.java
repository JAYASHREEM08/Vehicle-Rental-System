package com.wipro.vehiclerental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wipro.vehiclerental.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    // WHERE
    @Query(value = "SELECT * FROM vehicle WHERE rent_per_day > 1000",
           nativeQuery = true)
    List<Vehicle> findVehiclesAbove1000();

    // LIKE
    @Query(value = "SELECT * FROM vehicle WHERE vehicle_number LIKE :number",
           nativeQuery = true)
    List<Vehicle> findVehiclesByNumber(String number);

    // ORDER BY
    @Query(value = "SELECT * FROM vehicle ORDER BY rent_per_day DESC",
           nativeQuery = true)
    List<Vehicle> findVehiclesOrderByRent();

    // GROUP BY
    @Query(value = "SELECT vehicle_type, COUNT(*) " +
                   "FROM vehicle GROUP BY vehicle_type",
           nativeQuery = true)
    List<Object[]> countVehiclesByType();

    // HAVING
    @Query(value = "SELECT vehicle_type, COUNT(*) " +
                   "FROM vehicle " +
                   "GROUP BY vehicle_type " +
                   "HAVING COUNT(*) > 1",
           nativeQuery = true)
    List<Object[]> findVehicleTypesWithMoreThanOne();

    // AVG
    @Query(value = "SELECT AVG(rent_per_day) FROM vehicle",
           nativeQuery = true)
    Double findAverageRent();

    // MAX
    @Query(value = "SELECT * FROM vehicle " +
                   "WHERE rent_per_day = " +
                   "(SELECT MAX(rent_per_day) FROM vehicle)",
           nativeQuery = true)
    List<Vehicle> findVehicleWithHighestRent();

    // Subquery - AVG
    @Query(value = "SELECT * FROM vehicle " +
                   "WHERE rent_per_day > " +
                   "(SELECT AVG(rent_per_day) FROM vehicle)",
           nativeQuery = true)
    List<Vehicle> findVehiclesAboveAverage();

    // ANY
    @Query(value = "SELECT * FROM vehicle " +
                   "WHERE rent_per_day > ANY " +
                   "(SELECT rent_per_day FROM vehicle " +
                   "WHERE vehicle_type = 'Bike')",
           nativeQuery = true)
    List<Vehicle> findVehiclesGreaterThanAnyBike();

    // ALL
    @Query(value = "SELECT * FROM vehicle " +
                   "WHERE rent_per_day > ALL " +
                   "(SELECT rent_per_day FROM vehicle " +
                   "WHERE vehicle_type = 'Bike')",
           nativeQuery = true)
    List<Vehicle> findVehiclesGreaterThanAllBikes();

    // EXISTS
    @Query(value = "SELECT * FROM vehicle v " +
                   "WHERE EXISTS " +
                   "(SELECT 1 FROM rental r " +
                   "WHERE r.vehicle_id = v.vehicle_id)",
           nativeQuery = true)
    List<Vehicle> findRentedVehicles();

    // NOT EXISTS
    @Query(value = "SELECT * FROM vehicle v " +
                   "WHERE NOT EXISTS " +
                   "(SELECT 1 FROM rental r " +
                   "WHERE r.vehicle_id = v.vehicle_id)",
           nativeQuery = true)
    List<Vehicle> findNeverRentedVehicles();
}