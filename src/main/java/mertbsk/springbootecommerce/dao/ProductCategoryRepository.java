package mertbsk.springbootecommerce.dao;

import mertbsk.springbootecommerce.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//Api'de /api/productCategories linkini istemiyorum, bu sebepten ötürü @RepositoryRestResource ek açıklamasını kullanarak /api/product-category linkini kullanıyorum.
@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}