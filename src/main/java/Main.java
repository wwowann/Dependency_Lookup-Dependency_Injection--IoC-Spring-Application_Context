import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.config.JavaConfig;
import ru.netology.service.PostService;

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
        // Если мы хотим создать бины через аннотирование, то нужные классы аннотируются и конфигурация происходит следующим образом
        //при использовании аннотаций именем бина становится имя аннотированного класса с первой буквой в нижнем регистре
        //из списка пакетов, в которых нужно искать аннотированные классы получаем эти классы
        final var context = new AnnotationConfigApplicationContext(JavaConfig.class);
        //получение бина по имени класса-бина
        final var controller = context.getBean("postController");
        //получение бинов по имени класса
        final var service = context.getBean(PostService.class);
        //по умолчанию создается лишь один объект на BeanDefinition
        final var isSame = service ==context.getBean("postService");

    }
}
