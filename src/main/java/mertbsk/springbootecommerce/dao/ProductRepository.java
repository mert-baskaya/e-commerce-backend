package mertbsk.springbootecommerce.dao;

import mertbsk.springbootecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

// CORS Politikasını aşmak için yazılır, CORS Politikası tarayıcılarda çalışan JavaScript uygulamaları için geçerlidir
// Adres çubuğundan yapılan çağrıları etkilemez
// CORS Politikasına göre sadece aynı kökenli uygulamalar birbirlerine HTTP istekleri gönderebilir
// Aynı kökenli uygulamalardaki ayrım {{ http protokolü + host adı + port }} şeklindedir ve bunların hepsi aynı olmalıdır
// {{ http://localhost:4200 }} != {{ http://localhost:8080 }}
// @CrossOrigin yazıp bırakırsak API'yi aleni (herkese açık) yaparız
// 09.12.2021 Düzenleme: Cross Origin düzenlemeleri application.properties dosyasına taşındı
public interface ProductRepository extends JpaRepository<Product, Long> {

	// http://localhost:8080/api/products/search/findByCategoryId?id=1
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

	// http://localhost:8080/api/products/search/findByNameContaining?name=Python
	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);

}