package mertbsk.springbootecommerce.config;

import mertbsk.springbootecommerce.entities.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	@Value("${allowed.origins}")
	private String[] allowedOrigins;
	private final EntityManager entityManager;

	public MyDataRestConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	//Spring Data Rest gereksinimini kullanmaya devam edip, 3. kullanıcının verilerimize salt okunur erişiminin olmasını sağlamak için HTTP PUT, POST ve DELETE
	//Metotlarına erişimini engellemek amacıyla configureRepositoryRestConfiguration fonksiyonunu ezdik.
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

		HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

		// Product'ı  salt okunur yapar
		disableHttpMethods(Product.class,config,unsupportedActions);

		// ProductCategory'yi  salt okunur yapar
		disableHttpMethods(ProductCategory.class,config,unsupportedActions);

		// Country'yi  salt okunur yapar
		disableHttpMethods(Country.class,config,unsupportedActions);

		// State'i  salt okunur yapar
		disableHttpMethods(State.class,config,unsupportedActions);
		
		// Order'ı salt okunur yapar
		disableHttpMethods(Order.class, config, unsupportedActions);

		// Id'leri ortaya çıkarmak için iç metot çağırılır
		exposeIds(config);
		
		// CORS Mapping yap
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins);
	}
	
	@SuppressWarnings("rawtypes")
	private void disableHttpMethods (Class theClass, @NotNull RepositoryRestConfiguration config , HttpMethod[] unsupportedActions){
		config.getExposureConfiguration()
				.forDomainType(theClass)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));
	}
	

	// Bu metot çağrıların içine entity id'leri parametre olarak ekler
	@SuppressWarnings("rawtypes")
	private void exposeIds(RepositoryRestConfiguration config) {

		// Entity manager'dan tüm entity'ler birer key-value çifti olarak getirilir
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

		// Entity'ler için bir liste oluşturulur
		List<Class> entityClasses = new ArrayList<>();

		// Entity key-value çiftleri olurturulan listeye aktarılır
		for(EntityType tempEntityType : entities){
			entityClasses.add(tempEntityType.getJavaType());
		}

		// Oluşturulan listeyi bir diziye çevirir, standart kullanım şeklidir
		Class[] domainTypes = entityClasses.toArray(new Class[0]);

		// Projede var olan Entity/Domain tiplerinin tümünün id'lerini açığa çıkarır
		config.exposeIdsFor(domainTypes);

	}
}
