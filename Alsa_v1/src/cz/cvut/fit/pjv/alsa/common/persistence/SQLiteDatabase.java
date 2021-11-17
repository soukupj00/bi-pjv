package cz.cvut.fit.pjv.alsa.common.persistence;

import cz.cvut.fit.pjv.alsa.common.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SQLiteDatabase implements Database{

    private final Connection connection;

    private final ProductMapper productMapper;

    private PreparedStatement findAllPreparedStatement;
    private PreparedStatement findByIdPreparedStatement;
    private PreparedStatement insertPreparedStatement;
    private PreparedStatement updatePreparedStatement;
    private PreparedStatement deletePreparedStatement;

    public SQLiteDatabase(Connection connection, ProductMapper productMapper) {
        this.connection = connection;
        this.productMapper = productMapper;
    }

    public void init() {
        try {
            findAllPreparedStatement = connection.prepareStatement("select * from product");
            findByIdPreparedStatement = connection.prepareStatement("select * from product where id = ?");
            insertPreparedStatement = connection.prepareStatement("isert into product values (?, ?, ?, ?)");
            updatePreparedStatement = connection.prepareStatement(
                    "update product set name = ?, price = ?, count = ? where id = ?");
            deletePreparedStatement = connection.prepareStatement("delete from product where id = ?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //lazy initialization
    //private PreparedStatement findAllPreparedStatement() {
    //    if (findAllPreparedStatement == null) {
    //        findAllPreparedStatement = connection.prepareStatement("select * from product");
    //    }
    //    return findAllPreparedStatement;
    //}

    @Override
    public List<Product> findProducts() {
        try {
            ResultSet resultSet = findAllPreparedStatement.executeQuery();
            return productMapper.toProducts(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        try {
            findByIdPreparedStatement.setInt(1, id);
            ResultSet resultSet = findByIdPreparedStatement.executeQuery();

            if (!resultSet.next())
                return Optional.empty();

            return Optional.of(productMapper.toProduct(resultSet));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void insertProduct(Product product) {
        try {
            insertPreparedStatement.setInt(1, product.id());
            insertPreparedStatement.setString(2, product.name());
            insertPreparedStatement.setDouble(3, product.price());
            insertPreparedStatement.setInt(4, product.count());
            insertPreparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void updateProduct(Product product) {
        try {
            updatePreparedStatement.setString(1, product.name());
            updatePreparedStatement.setDouble(2, product.price());
            updatePreparedStatement.setInt(3, product.count());
            updatePreparedStatement.setInt(4, product.id());
            updatePreparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeProduct(Product product) {
        try {
            deletePreparedStatement.setInt(1, product.id());
            deletePreparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
