import com.moi.requests.SQLApplication;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
//        SQLApplication.getInstance().updateExecute("create table test_tbl (" +
//                "num1 varchar(3)," +
//                "num2 varchar(3))");
//        SQLApplication.getInstance().updateExecute("insert into test_tbl values (\"333\", \"444\")");
        SQLApplication.getInstance().executeQuery("select * from user_info");
//        SQLApplication.getInstance().executeQuery("select * from test_tbl");
//        SQLApplication.getInstance().updateExecute("drop table test_tbl");
    }
}
