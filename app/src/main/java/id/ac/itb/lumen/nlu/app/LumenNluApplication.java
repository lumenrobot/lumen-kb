package id.ac.itb.lumen.nlu.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableTransactionManagement
@Profile("daemon")
public class LumenNluApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LumenNluApplication.class);

//    @Inject
//    protected PersonRepository personRepo
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        def person = new Person()
//        person.label = 'Budhi Yulianto'
//        person.uri = LUMEN_NAMESPACE + 'Budhi_Yulianto'
//        person = personRepo.save(person)
//        final people = ImmutableList.copyOf(personRepo.findAll())
//        log.info('People: {}', people)
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(LumenNluApplication.class)
                .profiles("daemon")
                .run(args);
    }
}
