package kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.dao.impl;

import com.sun.org.apache.regexp.internal.RE;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.dao.AbstractDAO;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Airport;
import kz.kstu.epam.javalab.almasov.homework.xmlHomeWork.entities.Plane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaneDAO extends AbstractDAO<Plane> {

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS plane(id INT,model VARCHAR(20)," +
            "origin VARCHAR(20),plane_type VARCHAR(20), crew_seats_number INT, carrying_capacity INT," +
            "passengers_number INT, length DOUBLE,width DOUBLE, height DOUBLE, price DOUBLE, currency VARCHAR(20))";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS plane";
    private static final String INSERT_PLANE = "INSERT INTO plane VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_PLANE_BY_ID = "SELECT id, model, origin, plane_type, crew_seats_number, " +
            "carrying_capacity, passengers_number, length, width, height, price, currency FROM plane WHERE id = ?";
    private static final String SELECT_ALL_PLANES = "SELECT id, model, origin, plane_type, crew_seats_number, " +
            "carrying_capacity, passengers_number, length, width, height, price, currency FROM plane";
    private static final String UPDATE_PLANE_BY_ID = "UPDATE plane SET model=?, origin=?, plane_type=?, " +
            "crew_seats_number=?, carrying_capacity=?, passengers_number=?, length=?, width=?, height=?, price=?, " +
            "currency=? WHERE id=?";
    private static final String DELETE_PLANE = "DELETE FROM plane WHERE id = ?";
    private static final String DELETE_ALL_PLANES = "DELETE FROM plane";

    public PlaneDAO() {
    }

    public PlaneDAO(Connection connection) {
        super(connection);
    }

    public void createTable(Connection connection) throws SQLException {

        try (Statement statement = getConnection().createStatement()) {
            statement.execute(CREATE_TABLE_QUERY);
        }

    }

    public void dropTable(Connection connection) throws SQLException {

        try (Statement statement = connection.createStatement()){
            statement.executeQuery(DROP_TABLE);
        }

    }

    @Override
    public void add(Plane plane) throws SQLException {

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_PLANE)) {
            preparedStatement.setInt(1, plane.getNumber());
            preparedStatement.setString(2, plane.getModel());
            preparedStatement.setString(3, plane.getOrigin());
            preparedStatement.setString(4, plane.getChars().getType());
            preparedStatement.setInt(5, plane.getChars().getCrewSeatsNumber());
            preparedStatement.setInt(6, plane.getChars().getCarryingCapacity());
            preparedStatement.setInt(7, plane.getChars().getPassengersNumber());
            preparedStatement.setDouble(8, plane.getParameters().getLenght());
            preparedStatement.setDouble(9, plane.getParameters().getWidth());
            preparedStatement.setDouble(10, plane.getParameters().getHeight());
            preparedStatement.setDouble(11, plane.getCost().getPrice());
            preparedStatement.setString(12, plane.getCost().getCurrency());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Plane> getAll() throws SQLException {

        List<Plane> planeList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {

            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_PLANES)) {

                while (resultSet.next()) {
                    Plane plane = new Plane();
                    plane.setNumber(resultSet.getInt("id"));
                    plane.setModel(resultSet.getString("model"));
                    plane.setOrigin(resultSet.getString("origin"));
                    plane.getChars().setType(resultSet.getString("plane_type"));
                    plane.getChars().setCrewSeatsNumber(resultSet.getInt("crew_seats_number"));
                    plane.getChars().setCarryingCapacity(resultSet.getInt("carrying_capacity"));
                    plane.getChars().setPassengersNumber(resultSet.getInt("passengers_number"));
                    plane.getParameters().setLenght(resultSet.getDouble("length"));
                    plane.getParameters().setWidth(resultSet.getDouble("width"));
                    plane.getParameters().setHeight(resultSet.getDouble("height"));
                    plane.getCost().setPrice(resultSet.getDouble("price"));
                    plane.getCost().setCurrency(resultSet.getString("currency"));

                    planeList.add(plane);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return planeList;
    }

    @Override
    public Plane getEntityById(int id) throws SQLException {

        Plane plane = null;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_PLANE_BY_ID)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                plane.setNumber(resultSet.getInt("id"));
                plane.setModel(resultSet.getString("model"));
                plane.setOrigin(resultSet.getString("origin"));
                plane.getChars().setType(resultSet.getString("plane_type"));
                plane.getChars().setCrewSeatsNumber(resultSet.getInt("crew_seats_number"));
                plane.getChars().setCarryingCapacity(resultSet.getInt("carrying_capacity"));
                plane.getChars().setPassengersNumber(resultSet.getInt("passengers_number"));
                plane.getParameters().setLenght(resultSet.getDouble("length"));
                plane.getParameters().setWidth(resultSet.getDouble("width"));
                plane.getParameters().setHeight(resultSet.getDouble("height"));
                plane.getCost().setPrice(resultSet.getDouble("price"));
                plane.getCost().setCurrency(resultSet.getString("currency"));

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return plane;
    }

    @Override
    public void update(Plane plane) throws SQLException {

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_PLANE_BY_ID)) {
            preparedStatement.setString(1, plane.getModel());
            preparedStatement.setString(2, plane.getOrigin());
            preparedStatement.setString(3, plane.getChars().getType());
            preparedStatement.setInt(4, plane.getChars().getCrewSeatsNumber());
            preparedStatement.setInt(5, plane.getChars().getCarryingCapacity());
            preparedStatement.setInt(6, plane.getChars().getPassengersNumber());
            preparedStatement.setDouble(7, plane.getParameters().getLenght());
            preparedStatement.setDouble(8, plane.getParameters().getWidth());
            preparedStatement.setDouble(9, plane.getParameters().getHeight());
            preparedStatement.setDouble(10, plane.getCost().getPrice());
            preparedStatement.setString(11, plane.getCost().getCurrency());
            preparedStatement.setInt(12, plane.getNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_PLANE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeAll() throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_ALL_PLANES)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
