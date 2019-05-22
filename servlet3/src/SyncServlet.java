import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "SyncServlet", urlPatterns = {"/syncServlet"})
public class SyncServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long t1 = System.currentTimeMillis();
        // 执行业务代码
        doSomeThing(request, response);
        System.out.println("sync use: " + (System.currentTimeMillis() - t1));
    }

    private void doSomeThing(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 模拟耗时操作
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        response.getWriter().append("done");
    }
}
