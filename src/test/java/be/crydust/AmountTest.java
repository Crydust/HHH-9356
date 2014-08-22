package be.crydust;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AmountTest {

    EntityManagerFactory emf;
    EntityManager em;
    BigDecimal ONE = BigDecimal.ONE;
    BigDecimal NINE = new BigDecimal("9");
    BigDecimal TEN = BigDecimal.TEN;
    BigDecimal ELEVEN = new BigDecimal("11");

    @Before
    public void setUp() {
        System.out.println("*** setUp ***");
        emf = Persistence.createEntityManagerFactory("manager");
        em = emf.createEntityManager();
        em.getTransaction().begin();

        Amount oneDollar = new Amount();
        oneDollar.setDollars(ONE);
        em.persist(oneDollar);

        Amount tenDollars = new Amount();
        tenDollars.setDollars(TEN);
        em.persist(tenDollars);
		
        em.flush();
    }

    @After
    public void tearDown() {
        System.out.println("*** tearDown ***");
        em.getTransaction().rollback();
        em.close();
        emf.close();
    }

//    @Test
//    public void testJpqlGe9AndLe11Is10() {
//        System.out.println("*** testJpqlGe9AndLe11Is10 ***");
//        List<Amount> resultListGeLowAndLeHigh = em.createQuery("SELECT a FROM Amount a WHERE a.dollars >= :low and a.dollars <= :high", Amount.class)
//                .setParameter("low", NINE)
//                .setParameter("high", ELEVEN)
//                .getResultList();
//        assertThat(resultListGeLowAndLeHigh.size(), is(1));
//    }
//
//    @Test
//    public void testJpqlBetween9And11Is10() {
//        System.out.println("*** testJpqlBetween9And11Is10 ***");
//        List<Amount> resultListBetweenLowAndHigh = em.createQuery("SELECT a FROM Amount a WHERE a.dollars between :low and :high", Amount.class)
//                .setParameter("low", NINE)
//                .setParameter("high", ELEVEN)
//                .getResultList();
//        assertThat(resultListBetweenLowAndHigh.size(), is(1));
//    }
//
//    @Test
//    public void testCriteriaGe9AndLe11Is10() {
//        System.out.println("*** testCriteriaGe9AndLe11Is10 ***");
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Amount> cq = cb.createQuery(Amount.class);
//        Root<Amount> root = cq.from(Amount.class);
//        List<Amount> resultListGeLowAndLeHighWithCB = em.createQuery(cq
//                .select(root)
//                .where(cb.and(
//                                cb.ge(root.get(Amount_.dollars), NINE),
//                                cb.le(root.get(Amount_.dollars), ELEVEN))))
//                .getResultList();
//        assertThat(resultListGeLowAndLeHighWithCB.size(), is(1));
//    }

    @Test
    public void testCriteriaBetween9And11Is10() {
        System.out.println("*** testCriteriaBetween9And11Is10 ***");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Amount> cq = cb.createQuery(Amount.class);
        Root<Amount> root = cq.from(Amount.class);
        List<Amount> resultListBetweenLowAndHighWithCB = em.createQuery(cq
                .select(root)
                .where(cb.between(root.get(Amount_.dollars), NINE, ELEVEN)))
                .getResultList();
        assertThat(resultListBetweenLowAndHighWithCB.size(), is(1));
    }

}
