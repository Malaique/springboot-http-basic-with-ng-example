# Belajar Authentication with HTTP Basic

Http Basic is ...


membuat project dengan Spring boot dari [link berikut](https://start.spring.io) pilih dependency

- Web
- Security
- Lombok
- Actuator

## Sample request get

Buat class dalam package `model` namanya `Buku` seperti berikut:

```java

package com.maryanto.dimas.rental.buku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buku {

    private String id;
    private String nama;
    private String pengarang;
    private Integer tahunTerbit;
}
```

Setelah membuat model buku kemudian kita buat sample RESTful API dengan Spring Web, caranya buat class baru dengan nama `BukuController` dalam package `controller` seperti ini:

```java
package com.maryanto.dimas.rental.buku.controller;

// import classes should be here!

@RestController
@RequestMapping("/api/buku")
public class BukuController {

    @GetMapping("/{bukuId}")
    public Buku findById(@PathVariable("bukuId") String id) {
        return new Buku(id, "Belajar Pemograman Java", "Dimas Maryanto", 2017);
    }
}
``` 

setelah itu temen temen bisa jalankan aplikasi dengan menggunakan perintah:

```sh
mvn clean spring-boot:run
```

maka outputnya seperti berikut:

```sh
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.8.RELEASE)

2017-10-24 17:50:08.914  INFO 14773 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2017-10-24 17:50:08.931  INFO 14773 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2017-10-24 17:50:08.932  INFO 14773 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.23
2017-10-24 17:50:09.031  INFO 14773 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2017-10-24 17:50:09.032  INFO 14773 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 2963 ms
2017-10-24 17:50:09.711  INFO 14773 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/api/buku/{bukuId}],methods=[GET]}" onto public com.maryanto.dimas.rental.buku.model.Buku com.maryanto.dimas.rental.buku.controller.BukuController.findById(java.lang.String)
2017-10-24 17:50:09.715  INFO 14773 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)


Using default security password: ec25f83f-b26c-4f94-9c5f-4b0bd6712f4e

2017-10-24 17:50:10.492  INFO 14773 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-10-24 17:50:10.513  INFO 14773 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
2017-10-24 17:50:10.648  INFO 14773 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-10-24 17:50:10.659  INFO 14773 --- [           main] c.m.d.rental.buku.BukuApiApplication     : Started BukuApiApplication in 5.151 seconds (JVM running for 6.034)
```

Nah jadi dari output tersebut kita tau klo aplikasi kita jalan di port `8080` hostnya `localhost` seperti berikut:

```sh
2017-10-24 17:50:10.648  INFO 14773 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-10-24 17:50:10.659  INFO 14773 --- [           main] c.m.d.rental.buku.BukuApiApplication     : Started BukuApiApplication in 5.151 seconds (JVM running for 6.034)
```

dan kita tadi udah membuat API untuk class buku dengan url `localhost:8080/buku/{id}` seperti yang terlihat di output berikut:

```sh
2017-10-24 17:50:09.711  INFO 14773 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/api/buku/{bukuId}],methods=[GET]}" onto public com.maryanto.dimas.rental.buku.model.Buku com.maryanto.dimas.rental.buku.controller.BukuController.findById(java.lang.String)
```

Nah sekarang coba kita akess link tersebut menggunakan :

- browser
- Rested
- Rest Client (IntelliJ IDEA)

![Rest Client with auth](/docs/imgs/rest-auth.png)

Masukan username `user` dan passwordnya `ec25f83f-b26c-4f94-9c5f-4b0bd6712f4e`

nah sekarang coba perhatikan di tab Request Header ada properti `Authorization` dengan nilai `Basic generated-base64` jadi untuk setiap request dari client jika menggunakan http-basic kita harus kirim header dengan properti `Authorization` dengan valunya format `Basic <generate-base63>` setelah itu kirim menggunakan method `GET` karena kita deklarasi dengan `@GetMapping` (di sisi spring web mvc) dengan urlnya `localhost:8080/buku/10` maka hasilnya seperti berikut

![Rest client result](/docs/imgs/rest-client.png)

atau seperti berikut:

```json
{
  "id": "10",
  "nama": "Belajar Pemograman Java",
  "pengarang": "Dimas Maryanto",
  "tahunTerbit": 2017
}
```
