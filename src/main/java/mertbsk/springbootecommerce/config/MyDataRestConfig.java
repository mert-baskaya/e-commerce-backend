package mertbsk.springbootecommerce.config;

import mertbsk.springbootecommerce.entities.Product;
import mertbsk.springbootecommerce.entities.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	//Spring Data Rest gereksinimini kullanmaya devam edip, 3. kullanıcının verilerimize salt okunur erişiminin olmasını sağlamak için HTTP PUT, POST ve DELETE
	//Metotlarına erişimini engellemek amacıyla configureRepositoryRestConfiguration fonksiyonunu ezdik.
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

		HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

		// Product'ın PUT, POST ve DELETE HTTP metotlarını devre dışı bırakır
		config.getExposureConfiguration()
				.forDomainType(Product.class)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));

		// ProductCategory'nin PUT, POST ve DELETE HTTP metotlarını devre dışı bırakır
		config.getExposureConfiguration()
				.forDomainType(ProductCategory.class)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));

	}
}
