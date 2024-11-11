package com.contas.contas.application.usecase.interactor;

import com.contas.contas.MongoTestConfig;
import com.contas.contas.application.gateway.AccountGateway;
import com.contas.contas.application.usecase.AccountUseCase;
import com.contas.contas.domain.Account;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {MongoTestConfig.class})
@TestPropertySource(locations = "classpath:/application-test.properties")
@Testcontainers
class AccountInteractorUseCaseTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:6.0.2"))
            .waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(20)));

    @DynamicPropertySource
    static void overrrideMongoDBContainerProperties(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.host", mongoDBContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
    }

    @BeforeAll
    static void setUp(){
        mongoDBContainer.withReuse(true);
        mongoDBContainer.start();
    }

    @AfterAll
    static void setDown(){
        mongoDBContainer.stop();
    }

    AccountUseCase accountUseCase;

    @Autowired
    AccountGateway accountGateway;

    @BeforeEach
    public void init(){
        accountUseCase = new AccountInteractorUseCase(accountGateway);
    }

    @Test
    void testSaveAccount() {
        var accountsUser1 = List.of(new Account("Luz","user1"),
                new Account("Internet","user1"),new Account("Cartão","user1"));

        var accountsUser2 = List.of(new Account("Luz","user2"),
                new Account("Internet","user2"),new Account("Aluguel","user2"));

        accountUseCase.saveAll(accountsUser1);
        accountUseCase.saveAll(accountsUser2);

        List<Account> user1 = accountUseCase.findAllByUser("user1");
        List<Account> user2 = accountUseCase.findAllByUser("user2");

        Assertions.assertEquals(3, user1.size());
        Assertions.assertEquals(List.of("Luz--user1, Internet--user1, Cartão--user1").toString(),
                user1.stream().map(Account::getId).toList().toString());

        Assertions.assertEquals(3, user2.size());

        Assertions.assertEquals(List.of("Luz--user2, Internet--user2, Aluguel--user2").toString(),
                user2.stream().map(Account::getId).toList().toString());

    }

    @Test
    void testInactiveAccount() {

        var accountsUser2 = List.of(new Account("Luz","user2"),
                new Account("Internet","user2"),new Account("Aluguel","user2"));

        accountUseCase.saveAll(accountsUser2);


        accountUseCase.inactive("Internet--user2");

        List<Account> user2 = accountUseCase.findAllActiveByUser("user2");

        Assertions.assertEquals(2, user2.size());

        Assertions.assertEquals(List.of("Luz--user2, Aluguel--user2").toString(),
                user2.stream().map(Account::getId).toList().toString());

    }


}