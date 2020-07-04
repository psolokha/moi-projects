import com.moi.requests.SQLApplication;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        SQLApplication.getInstance().executeQuery("select name from user_info where id_user = 3");

        System.out.println("---------------------------------------");
        List<Map<String, String>> resultQuery = SQLApplication.getInstance().executeQuery("select * from user_info");
        System.out.println("result:");
        System.out.println(resultQuery.get(3).values().toArray()[2]);
    }
}
