package com.contas.contas.application.usecase.interactor;

import com.contas.contas.MongoTestConfig;
import com.contas.contas.application.gateway.AccountGateway;
import com.contas.contas.application.gateway.MonthlyGateway;
import com.contas.contas.application.usecase.AccountUseCase;
import com.contas.contas.application.usecase.MonthlyUseCase;
import com.contas.contas.domain.Account;
import com.contas.contas.domain.Monthly;
import com.contas.contas.domain.MonthlyId;
import com.contas.contas.infrastructure.controller.dto.MonthlyRequest;
import com.contas.contas.infrastructure.dto.Pagination;
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

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {MongoTestConfig.class})
@TestPropertySource(locations = "classpath:/application-test.properties")
@Testcontainers
class MonthlyInteractorUseCaseTest {

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

    MonthlyUseCase monthlyUseCase;

    @Autowired
    MonthlyGateway monthlyGateway;
    AccountUseCase accountUseCase;

    @Autowired
    AccountGateway accountGateway;
    @BeforeEach
    public void init(){
        accountUseCase = new AccountInteractorUseCase(accountGateway);
        monthlyUseCase = new MonthlyInteractorUseCase(monthlyGateway,accountUseCase);
    }

    @Test
    void testCreateAccountByMonthly() {
        var accountsUser1 = List.of(new Account("Luz","user1"),
                new Account("Internet","user1"),new Account("Cartão","user1"));
        String id = MonthlyId.getId(LocalDate.now(), "user1");
        accountUseCase.saveAll(accountsUser1);
        monthlyUseCase.createAccountByMonthly(id);
        Pagination pagination = monthlyUseCase.findAll("user1",0, 10);

        Assertions.assertEquals(id, pagination.getMonthlies().get(0).getId());

        Assertions.assertEquals(List.of("Luz--user1, Internet--user1, Cartão--user1").toString(),
                pagination.getMonthlies().get(0).getAccounts().stream().map(Account::getId).toList().toString());

        Assertions.assertEquals(1, pagination.getMonthlies().size());
    }

    @Test
    void testCreckoutMonthly() {

        var accountsUser1 = List.of(new Account("Luz","user2"),
                new Account("Internet","user2"),new Account("Cartão","user2"));
        String id = MonthlyId.getId(LocalDate.now(), "user2");
        accountUseCase.saveAll(accountsUser1);
        monthlyUseCase.createAccountByMonthly(id);

        Optional<Monthly> maybeMonthly = monthlyUseCase.findById(id);
        MonthlyRequest request = new MonthlyRequest(id, "Internet--user2", new BigDecimal("22.0"),true);

        var checkAccount = monthlyUseCase.checkAccount(request);
        var internet = checkAccount.getAccounts().stream().filter(account -> account.getId().equals("Internet--user2")).findFirst().orElse(null);

        Assertions.assertEquals(true, internet.getCheck());
        Assertions.assertEquals( new BigDecimal("22.0"), internet.getValor());
        Assertions.assertEquals(id, maybeMonthly.get().getId());

        Assertions.assertEquals(List.of("Luz--user2, Internet--user2, Cartão--user2").toString(),
                maybeMonthly.get().getAccounts().stream().map(Account::getId).toList().toString());

        Assertions.assertEquals(3, maybeMonthly.get().getAccounts().size());
    }
}