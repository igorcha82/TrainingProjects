import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {

            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            List<Purchaselist> students = session.createQuery("From " + Purchaselist.class.getSimpleName()).getResultList();

            for(Purchaselist item: students) {

                KeyLink key = new KeyLink(item.getPurchaseKey().getStudent(), item.getPurchaseKey().getCourse()); //item.getPurchaseKey().getStudent(), item.getPurchaseKey().getCourse());
                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(key);
                linkedPurchaseList.setStudentId(item.getPurchaseKey().getStudent().getId());
                linkedPurchaseList.setStudentName(item.getPurchaseKey().getStudent().getName());
                linkedPurchaseList.setCourseId(item.getPurchaseKey().getCourse().getId());
                linkedPurchaseList.setCourseName(item.getPurchaseKey().getCourse().getName());
                linkedPurchaseList.setCoursePrice(item.getPrice());
                linkedPurchaseList.setSubscriptionDate(item.getSubscriptionDate());

                session.saveOrUpdate(linkedPurchaseList);

            }


            transaction.commit();

            sessionFactory.close();

        } catch (Exception ex) {ex.printStackTrace();}

    }
}
