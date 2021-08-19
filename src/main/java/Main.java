import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.service.PostService;

import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args){

        // Если мы хотим создать бины через аннотирование, то нужные классы аннотируются и конфигурация происходит следующим образом
        //при использовании аннотаций именем бина становится имя аннотированного класса с первой буквой в нижнем регистре
        //из списка пакетов, в которых нужно искать аннотированные классы получаем эти классы
        final var context = new AnnotationConfigApplicationContext("ru.netology");
        //получение бинов по имени класса
        final var service = context.getBean(PostService.class);
        //по умолчанию создается лишь один объект на BeanDefinition
        final var isSame = service ==context.getBean("postService");

    }
}
