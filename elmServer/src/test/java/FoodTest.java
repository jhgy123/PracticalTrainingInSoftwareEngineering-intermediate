import com.example.elmserver.dao.impl.BusinessDao;
import com.example.elmserver.dao.impl.FoodDao;
import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Food;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class FoodTest {
    @Test
    public void FoodSaveTest() throws SQLException {
        FoodDao dao=new FoodDao();
        BusinessDao ad=new BusinessDao();
        Food admin=  Food.builder()
                .foodName("wwww")
                .business(ad.getOne(12))
                .build();
        boolean resultset=dao.save(admin);
        System.out.println(resultset);
    }

    @Test
    public void FoodUpdateTest() throws SQLException {
        FoodDao dao=new FoodDao();
        BusinessDao ad=new BusinessDao();
        Food admin=  Food.builder()
                .foodName("wwww")
                .foodId(1009)
                .foodPrice(100)
                .business(ad.getOne(13))
                .build();
        boolean resultset=dao.update(admin);
        System.out.println(resultset);
    }

    @Test
    public void FoodDeleteTest() throws SQLException {
        FoodDao dao=new FoodDao();
        boolean resultset=dao.delete(1009);
        System.out.println(resultset);
    }

    @Test
    public void FoodGetOneTest() throws SQLException {
        FoodDao ad=new FoodDao();
        var food=ad.getOne(1010);
        System.out.println(food.getBusiness());
    }

    @Test
    public void FoodGetAllTest() throws SQLException {
        FoodDao dao=new FoodDao();
        List<Food> resultset=dao.getAll();
        System.out.println(resultset.get(1));
    }

    @Test
    public void FoodQueryFoodByBussinessIdTest() throws SQLException {
        FoodDao dao=new FoodDao();
        List<Food> resultset=dao.queryFoodByBussinessId(13);
        System.out.println(resultset.get(1));
        
    }

}
