import com.example.elmserver.dao.olddao.impl.OldBusinessDao;
import com.example.elmserver.entities.Business;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class BusinessTest {
    @Test
        public void BusinessQueryTest() throws SQLException {
        OldBusinessDao dao=new OldBusinessDao();
        List<Business> resultset=dao.query("","沈阳","");
        System.out.println(resultset.get(1));
    }

    @Test
    public void BusinessGetAllTest() throws SQLException {
        OldBusinessDao dao=new OldBusinessDao();
        List<Business> resultset=dao.getAll();
        System.out.println(resultset.get(1));
    }

    @Test
    public void BusinessSaveTest() throws SQLException {
        OldBusinessDao ad=new OldBusinessDao();
        Business admin= Business.builder()
                .name("TOM")
                .password("tom")
                .starPrice(12.3)
                .deliveryPrice(78)
                .build();
        boolean resultset=ad.save(admin);
        System.out.println(resultset);
    }

    @Test
    public void BusinessUpdateTest() throws SQLException {
        OldBusinessDao ad=new OldBusinessDao();
        Business admin= Business.builder()
                .name("jack")
                .password("tom")
                .starPrice(12.3)
                .deliveryPrice(78)
                .id(25)
                .explain("fwjojfwoj")
                .build();
        boolean resultset=ad.update(admin);
        System.out.println(resultset);
    }

    @Test
    public void BusinessDeleteTest() throws SQLException {
        OldBusinessDao dao=new OldBusinessDao();
        boolean resultset=dao.delete(27);
        System.out.println(resultset);
    }

    @Test
    public void BusinessGetOneTest() throws SQLException {
        OldBusinessDao ad=new OldBusinessDao();
        var business=ad.getOne(12);
//        System.out.println(business.getName());
    }
}
