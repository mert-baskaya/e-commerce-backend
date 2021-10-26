package mertbsk.springbootecommerce.dao;

import mertbsk.springbootecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

// CORS Politikasını aşmak için yazılır, CORS Politikası tarayıcılarda çalışan JavaScript uygulamaları için geçerlidir
// Adres çubuğundan yapılan çağrıları etkilemez
// CORS Politikasına göre sadece aynı kökenli uygulamalar birbirlerine HTTP istekleri gönderebilir
// Aynı kökenli uygulamalardaki ayrım {{ http protokolü + host adı + port }} şeklindedir ve bunların hepsi aynı olmalıdır
// {{ http://localhost:4200 }} != {{ http://localhost:8080 }}
// @CrossOrigin yazıp bırakırsak API'yi aleni (herkese açık) yaparız
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
}