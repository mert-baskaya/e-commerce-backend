package mertbsk.springbootecommerce.dao;

import mertbsk.springbootecommerce.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//Api'de /api/productCategories linkini istemiyorum, bu sebepten ötürü @RepositoryRestResource ek açıklamasını kullanarak /api/product-category linkini kullanıyorum.
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}