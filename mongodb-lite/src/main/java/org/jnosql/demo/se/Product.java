package org.jnosql.demo.se;


import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import net.datafaker.Faker;
import org.eclipse.jnosql.databases.mongodb.mapping.ObjectIdConverter;
import jakarta.nosql.Convert;

import java.util.Objects;

@Entity
public class Product {


    @Id
    @Convert(ObjectIdConverter.class)
    private String id;

    @Column
    private String name;

    @Column
    private String material;
    @Column
    private String category;

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCategory() {
        return category;
    }

    void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", material='" + material + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public static Product of(Faker faker){
        Product product = new Product();
        product.setName(faker.commerce().productName());
        product.setMaterial(faker.commerce().material());
        product.setCategory(faker.commerce().department());
        return product;
    }
}
