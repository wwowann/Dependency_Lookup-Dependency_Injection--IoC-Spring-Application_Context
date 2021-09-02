import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.config.JavaConfig;
import ru.netology.service.PostService;
import ru.netology.servlet.MainServlet;

import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args){
      /* //создание бинов через config.xml. В конфигурационном файле хранится информация о том, как создаются бины, из каких классов и т.д.
        final var factory = new DefaultListableBeanFactory(); //создается бинфактори

        final var reader = new XmlBeanDefinitionReader(factory);//создается пустой читальщик бинов
        reader.loadBeanDefinitions("beans.xml");//происходит загрузка бинов из конфига

        //теперь можно получать объекты из мапы бинов:
        //по имени бина
        final var controller = factory.getBean("postController");

        //по классу бина
        final var service = factory.getBean(PostService.class);

        // умолчанию создается только один объект на BeanDefinition
        final var isSame = service == factory.getBean("postService");
               */
              final var context = new AnnotationConfigApplicationContext(JavaConfig.class);
//        new MainServlet();
        //получение бина по имени класса-бина
//        final var controller = context.getBean("postController");
//        //получение бинов по имени класса
//        final var service = context.getBean(PostService.class);
//        //по умолчанию создается лишь один объект на BeanDefinition
//        final var isSame = service ==context.getBean("postService");

    }
}
