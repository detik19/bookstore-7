1. Spring menload --> WebApplicationInitializer--> Initialize WebApplicationContext
Yang dipakai disini --> AnnotationConfigWebApplicationContext


Ada 3 cara memberikan controller..
1. Implement Interface org.springframework.web.servlet.mvc.Controller 
Return object -> org.springframework.web.servlet.ModelAndView 
untuk membuat controller, kita harus mengkonfigurasi instansi dari org.springframework.web.servlet.HandlerMapping untuk memetakan
"/index.htm" pada kontroller yang dimaksud. 
We would also need to make sure that there is an org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter

2. Pake annotation @Controller
Buat class, dan kasih anotasi @Controller. Tambahkan juga anotasi @RequestMapping(value = "/index.htm")--> berarti method trsebut
dipetakan untuk URL /index.htm

Ada dua jenis implementasi 
a.org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping->DispatcherServlet

b.org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping.->Spring @MVC, enable di @EnableWebMvc

Contoh ini, hanya merupakan ViewController, sekedar menampilkan halaman jsp, bisa gunakan ini
untuk lebih sederhana :
1. org.springframework.web.servlet.mvc.ParameterizableViewController 

2. atau bisa juga mengoverride  addViewControllers dari class org.springframework.web.servlet.config.annotation
.WebMvcConfigurerAdapter


Login Controller--> 
@Autowired menscan implementasi interface yang diberi @service

===============
ModelAttributes
===============

A. At Method level
Menggunakan anotasi @ModelAttribute pada level method untuk menyediakan data referensi untuk model. 
Method beranotasi @ModelAttribute dieksekusi sebelum handler method beranotasi @RequestMapping dijalankan . 
Ia secara efektif  pre-populate model implisit dengan atribut spesifik, 
Sering memuat data dari database. 

Setiap sebuah attribut siap diakses melalui method beranotasi @ModelAttribute melalui parameter handler method yang dipilih, 
Secara potensial dengan binding dan validasi yang diterapkan padanya.

In other words; a method annotated with @ModelAttribute will populate the specified “key” in the model. This happens BEFORE the @RequestMapping


B. At Method Parameter level
When you place @ModelAttribute on a method parameter,
 @ModelAttribute maps a model attribute to the specific, annotated method parameter. 
 This is how the controller gets a reference to the object holding the data entered in the form.

