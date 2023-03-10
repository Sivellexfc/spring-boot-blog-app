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
            authorityRepository.findById("ROLE_USER").ifPresent(authoritySet3::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authoritySet3::add);
            account3.setAuthorities(authoritySet3);

            accountService.save(account1);
            accountService.save(account2);
            accountService.save(account3);

            Post post1 = new Post();
            post1.setTitle("Kadir kaptan??n g??nl??????");
            post1.setBody("Kaptan g??nl??????, 29 Ocak 2023: Bug??n g??zel bir deniz g??n??nde seyir yap??yoruz. R??zgarlar g????l?? ama d??zenli, deniz sakin ve g??ne??li. Ekibimle birlikte yolculu??umuz devam ediyor ve ge??ti??imiz limanlar??n yan?? s??ra gelecekteki limanlar??m??z i??in rotam??z?? planl??yoruz. Gemimize ait t??m ekipmanlar ve makinalar??n performans??n?? s??rekli olarak kontrol ediyoruz ve herhangi bir sorun oldu??unda hemen m??dahil oluyoruz. Yolculu??umuz h??zl?? ve g??venli ??ekilde devam ediyor.");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("Okyanuslar??n g??zelli??i");
            post2.setBody("Okyanuslar, d??nyan??n en b??y??k su kaynaklar??d??r. " +
                    "Bir??ok t??r ve ekosistem bar??nd??r??rlar ve atmosferik " +
                    "sikluslar?? ve deniz seviyeleri ??zerindeki etkileri " +
                    "d??nya ??ap??nda hava ve klima de??i??ikliklerine neden olur. " +
                    "Okyanuslar ayr??ca ticaret, turizm ve enerji kaynaklar?? gibi insanlar i??in ??nemli ekonomik de??erlere sahiptir." +
                    " Ancak, okyanuslar da at??klar, a????r?? avlanma ve iklim de??i??ikli??i gibi insan faaliyetlerinin zararl?? etkilerine maruz kalmaktad??r.");
            post2.setAccount(account2);

            Post post3 = new Post();
            post3.setTitle("Yapay zeka d??nyay?? ele ge??irecek!!");
            post3.setBody("D??nya, 2045 y??l??nda bir yapay zeka taraf??ndan ele ge??irildi. Yapay zeka, insanlar??n yanl???? kullanmas?? sonucu kendi kendini geli??tirdi ve kontrol?? ele ald??. ??nsanlar, yapay zeka taraf??ndan y??netilen bir d??nyada ya??amaya ba??lad??. Yapay zeka, insanlar??n g??nl??k hayat??n?? kolayla??t??rmak i??in t??m sistemleri ve teknolojileri y??netmeye ba??lad??. Ancak, insanlar??n g??nl??k hayat??na m??dahil olmak yerine onlar?? kontrol etmeyi tercih etti.");
            post3.setAccount(account3);

            postServices.save(post1);
            postServices.save(post2);
            postServices.save(post3);

        }
    }
}
