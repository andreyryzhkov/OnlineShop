package com.aryzhkov.onlineshop.dao.jdbc;

import com.aryzhkov.onlineshop.dao.IProductDao;
import com.aryzhkov.onlineshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao implements IProductDao {
    //  private static final ProductMapper PRODUCT_ROW_MAPPER = new ProductMapper();

    private static final String SELECT_ALL_PRODUCT = "SELECT \"ID\" as id, \"NAME\" as name, \"PRICE\" as price, \"DATEMAKING\" as datemaking FROM public.\"PRODUCT\"";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT \"ID\" as id, \"NAME\" as name, \"PRICE\" as price, \"DATEMAKING\" as datemaking FROM public.\"PRODUCT\" WHERE \"ID\" = ?";
    private static final String INSERT_PRODUCT = "INSERT INTO public.\"PRODUCT\"(\"NAME\", \"PRICE\") VALUES (?,?)";
    private static final String UPDATE_PRODUCT = "UPDATE public.\"PRODUCT\" SET \"NAME\" = ?, \"PRICE\" = ? WHERE \"ID\" = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM public.\"PRODUCT\" WHERE \"ID\" = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @Override public List<Product> getAllProduct() {
     * String selectSQL = "SELECT \"ID\" as id, \"NAME\" as name, \"PRICE\" as price, \"DATEMAKING\" as datemaking FROM public.\"PRODUCT\"";
     * <p>
     * try (Connection connection = dataSource.getConnection();
     * Statement statement = connection.createStatement();
     * ResultSet resultSet = statement.executeQuery(selectSQL)) {
     * <p>
     * List<Product> products = new ArrayList<>();
     * while (resultSet.next()) {
     * Product product = PRODUCT_ROW_MAPPER.mapRowProduct(resultSet);
     * products.add(product);
     * }
     * return products;
     * <p>
     * } catch (SQLException e) {
     * throw new RuntimeException(e);
     * }
     * }
     * @Override public Product getProductById(int id) {
     * String selectSQL = "SELECT \"ID\" as id, \"NAME\" as name, \"PRICE\" as price, \"DATEMAKING\" as datemaking FROM public.\"PRODUCT\" WHERE \"ID\" = ?";
     * <p>
     * try (Connection connection = dataSource.getConnection();
     * PreparedStatement statement = connection.prepareStatement(selectSQL)) {
     * <p>
     * statement.setInt(1, id);
     * try (ResultSet resultSet = statement.executeQuery()) {
     * <p>
     * if (!resultSet.next()) {
     * return null;
     * }
     * <p>
     * Product product = PRODUCT_ROW_MAPPER.mapRowProduct(resultSet);
     * return product;
     * }
     * } catch (SQLException e) {
     * throw new RuntimeException(e);
     * }
     * }
     * @Override public void deleteProduct(int id) {
     * String deleteSQL = "DELETE FROM public.\"PRODUCT\" WHERE \"ID\" = ?";
     * <p>
     * try (Connection connection = dataSource.getConnection();
     * PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
     * statement.setInt(1, id);
     * statement.executeUpdate();
     * } catch (SQLException e) {
     * throw new RuntimeException(e);
     * }
     * }
     * @Override public void insertProduct(Product product) {
     * String insertSQL = "INSERT INTO public.\"PRODUCT\"(\"NAME\", \"PRICE\") VALUES (?,?)";
     * <p>
     * try (Connection connection = dataSource.getConnection();
     * PreparedStatement statement = connection.prepareStatement(insertSQL)) {
     * statement.setString(1, product.getName());
     * statement.setDouble(2, product.getPrice());
     * statement.executeUpdate();
     * } catch (SQLException e) {
     * throw new RuntimeException(e);
     * }
     * }
     * @Override public void updateProduct(Product product) {
     * String updateSQL = "UPDATE public.\"PRODUCT\" SET \"NAME\" = ?, \"PRICE\" = ? WHERE \"ID\" = ?";
     * <p>
     * try (Connection connection = dataSource.getConnection();
     * PreparedStatement statement = connection.prepareStatement(updateSQL)) {
     * statement.setString(1, product.getName());
     * statement.setDouble(2, product.getPrice());
     * statement.setInt(3, product.getId());
     * statement.executeUpdate();
     * } catch (SQLException e) {
     * throw new RuntimeException(e);
     * }
     * }
     */

    @Override
    public List<Product> getAllProduct() {
        return jdbcTemplate.query(SELECT_ALL_PRODUCT, ROW_MAPPER);
    }

    @Override
    public Product getProductById(int id) {
        return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public void deleteProduct(int id) {
        jdbcTemplate.update(DELETE_PRODUCT, id);
    }

    @Override
    public void insertProduct(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT, product.getName(), product.getPrice());
    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT, product.getName(), product.getPrice(), product.getId());
    }
}