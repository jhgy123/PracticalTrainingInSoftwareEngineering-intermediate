

import com.example.elmserver.dao.olddao.impl.OldAdminDao;
import com.example.elmserver.entities.Admin;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class AdminTest {
    @Test
    public void adminGetOneTest() throws SQLException {
        OldAdminDao ad=new OldAdminDao();
        var admin=ad.getOne(1);
        System.out.println(admin.getName());
    }
    @Test
    public void adminGetAllTest() throws SQLException {
        OldAdminDao ad=new OldAdminDao();
       List<Admin> resultset=ad.getAll();
        System.out.println(resultset.get(1).getName());
    }

    @Test
    public void adminSaveTest() throws SQLException {
        OldAdminDao ad=new OldAdminDao();
        Admin admin= Admin.builder()
                .name("TOM")
                .password("tom")
                .build();
        boolean resultset=ad.save(admin);
        System.out.println(resultset);
    }
    @Test
    public void adminDeleteTest() throws SQLException {
        OldAdminDao ad=new OldAdminDao();
        boolean resultset=ad.delete(6);
        System.out.println(resultset);
    }

    @Test
    public void adminUpdateTest() throws SQLException {
        OldAdminDao ad=new OldAdminDao();
        Admin admin= Admin.builder()
                .id(5)
                .name("root")
                .password("0000")
                .build();
        boolean resultset=ad.update(admin);
        System.out.println(resultset);
    }

}
