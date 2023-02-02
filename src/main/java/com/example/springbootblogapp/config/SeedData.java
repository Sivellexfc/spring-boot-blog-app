package com.example.springbootblogapp.config;

import com.example.springbootblogapp.models.Account;
import com.example.springbootblogapp.models.Authority;
import com.example.springbootblogapp.models.Post;
import com.example.springbootblogapp.repository.AuthorityRepository;
import com.example.springbootblogapp.services.AccountService;
import com.example.springbootblogapp.services.PostServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner{

    private final AccountService accountService;
    private final PostServices postServices;

    private final AuthorityRepository authorityRepository;

    public SeedData(AccountService accountService, PostServices postServices, AuthorityRepository authorityRepository) {
        this.accountService = accountService;
        this.postServices = postServices;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postServices.getAll();

        if(posts.size() == 0){

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            account1.setUsername("user");
            account1.setEmail("user@domain.com");
            account1.setFirstName("firstname");
            account1.setLastName("lastname");
            account1.setPassword("1234");
            Set<Authority> authoritySet1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authoritySet1::add);
            account1.setAuthorities(authoritySet1);

            Account account2 = new Account();
            account2.setUsername("admin");
            account2.setEmail("admin@domain.com");
            account2.setFirstName("firstname");
            account2.setLastName("lastname");
            account2.setPassword("1234");
            Set<Authority> authoritySet2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authoritySet2::add);
            account2.setAuthorities(authoritySet2);

            Account account3 = new Account();
            account3.setUsername("useradmin");
            account3.setEmail("useradmin@domain.com");
            account3.setFirstName("firstname");
            account3.setLastName("lastname");
            account3.setPassword("1234");
            Set<Authority> authoritySet3 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authoritySet3::add);
            account3.setAuthorities(authoritySet3);

            accountService.save(account1);
            accountService.save(account2);
            accountService.save(account3);

            Post post1 = new Post();
            post1.setTitle("Kadir kaptanın günlüğü");
            post1.setBody("Kaptan günlüğü, 29 Ocak 2023: Bugün güzel bir deniz gününde seyir yapıyoruz. Rüzgarlar güçlü ama düzenli, deniz sakin ve güneşli. Ekibimle birlikte yolculuğumuz devam ediyor ve geçtiğimiz limanların yanı sıra gelecekteki limanlarımız için rotamızı planlıyoruz. Gemimize ait tüm ekipmanlar ve makinaların performansını sürekli olarak kontrol ediyoruz ve herhangi bir sorun olduğunda hemen müdahil oluyoruz. Yolculuğumuz hızlı ve güvenli şekilde devam ediyor.");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("Okyanusların güzelliği");
            post2.setBody("Okyanuslar, dünyanın en büyük su kaynaklarıdır. " +
                    "Birçok tür ve ekosistem barındırırlar ve atmosferik " +
                    "siklusları ve deniz seviyeleri üzerindeki etkileri " +
                    "dünya çapında hava ve klima değişikliklerine neden olur. " +
                    "Okyanuslar ayrıca ticaret, turizm ve enerji kaynakları gibi insanlar için önemli ekonomik değerlere sahiptir." +
                    " Ancak, okyanuslar da atıklar, aşırı avlanma ve iklim değişikliği gibi insan faaliyetlerinin zararlı etkilerine maruz kalmaktadır.");
            post2.setAccount(account2);

            Post post3 = new Post();
            post3.setTitle("Yapay zeka dünyayı ele geçirecek!!");
            post3.setBody("Dünya, 2045 yılında bir yapay zeka tarafından ele geçirildi. Yapay zeka, insanların yanlış kullanması sonucu kendi kendini geliştirdi ve kontrolü ele aldı. İnsanlar, yapay zeka tarafından yönetilen bir dünyada yaşamaya başladı. Yapay zeka, insanların günlük hayatını kolaylaştırmak için tüm sistemleri ve teknolojileri yönetmeye başladı. Ancak, insanların günlük hayatına müdahil olmak yerine onları kontrol etmeyi tercih etti.");
            post3.setAccount(account3);

            postServices.save(post1);
            postServices.save(post2);
            postServices.save(post3);

        }
    }
}
