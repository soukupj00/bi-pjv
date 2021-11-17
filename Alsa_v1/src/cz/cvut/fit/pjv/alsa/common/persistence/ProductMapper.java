package cz.cvut.fit.pjv.alsa.common.persistence;

import cz.cvut.fit.pjv.alsa.common.entity.Product;
import cz.cvut.fit.pjv.alsa.common.entity.Television;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public Product toProduct(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        int count = resultSet.getInt("count");

        return new Television(id, name, price, count);
    }

    public List<Product> toProducts(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(toProduct(resultSet));
        }

        return products;
    }
}
