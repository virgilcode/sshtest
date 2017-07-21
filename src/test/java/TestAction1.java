import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.virgil.hibernate.Department;
import org.virgil.hibernate.Employee;
import org.virgil.hibernate.TreeNode;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by virgil on 2017/3/2.
 */
public class TestAction1 {

    private static SessionFactory sessionFactory = null;
    ObjectMapper mapper = new ObjectMapper();

    static {
        //读取classpath中applicationContext.xml配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取session中配置的sessionFactory对象
        sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
    }

    private TreeNode initData() {
        TreeNode rootNode = new TreeNode("micmiu.com");

        // 一级
        TreeNode node0 = new TreeNode("Michael");
        node0.setParent(rootNode);
        rootNode.getChildren().add(node0);

        // 二级
        TreeNode node0_0 = new TreeNode("J2EE");
        node0_0.setParent(node0);
        node0.getChildren().add(node0_0);
        // 二级
        TreeNode node0_1 = new TreeNode("SOA");
        node0_1.setParent(node0);
        node0.getChildren().add(node0_1);
        // 二级
        TreeNode node0_2 = new TreeNode("NoSQL");
        node0_2.setParent(node0);
        node0.getChildren().add(node0_2);

        // 二级
        TreeNode node0_3 = new TreeNode("编程语言");
        node0_3.setParent(node0);
        node0.getChildren().add(node0_3);

        // 三级
        TreeNode node0_3_0 = new TreeNode("Java");
        node0_3_0.setParent(node0_3);
        node0_3.getChildren().add(node0_3_0);

        TreeNode node0_3_1 = new TreeNode("Groovy");
        node0_3_1.setParent(node0_3);
        node0_3.getChildren().add(node0_3_1);

        TreeNode node0_3_2 = new TreeNode("javascript");
        node0_3_2.setParent(node0_3);
        node0_3.getChildren().add(node0_3_2);

        // 一级
        TreeNode node1 = new TreeNode("Hazel");
        node1.setParent(rootNode);
        rootNode.getChildren().add(node1);
        // 二级
        TreeNode node1_0 = new TreeNode("life");
        node1_0.setParent(node1);
        node1.getChildren().add(node1_0);
        // 二级
        TreeNode node1_1 = new TreeNode("美食");
        node1_1.setParent(node1);
        node1.getChildren().add(node1_1);
        // 二级
        TreeNode node1_2 = new TreeNode("旅游");
        node1_2.setParent(node1);
        node1.getChildren().add(node1_2);
        return rootNode;
    }

    private void printNode(TreeNode node, int level) {
        String preStr = "";
        for (int i = 0; i < level; i++) {
            preStr += "|----";
        }
        System.out.println(preStr + node.getName());
        for (TreeNode children : node.getChildren()) {
            printNode(children, level + 1);
        }
    }

    @Test
    public void testSave1() {
        Session session = sessionFactory.openSession();
        TreeNode rootNode = initData();
        System.out.println(rootNode.getName());
        //开始事务
        Transaction t = session.beginTransaction();
        try {
            session.save(rootNode);
            t.commit();
        } catch (RuntimeException e) {
            //有异常则回滚事务
            t.rollback();
            e.printStackTrace();
        } finally {
            //关闭session
            session.close();
        }
        System.out.println("========>测试添加 end <========");
        // 读取添加的数据
        testRead();
    }

    public void testRead() {
        System.out.println("========>读取 start <========");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("-----> get root node:");
        TreeNode rootNode = (TreeNode) session.get(TreeNode.class, 1);

        System.out.println("-----> 输出树形结构如下:");
        printNode(rootNode, 0);

        session.getTransaction().commit();
        session.close();
        System.out.println("========>读取 end <========");
    }

    @Test
    public void testSave() {
        //创建一个部门对象
        Department d1 = new Department();
        d1.setDname("sxx我还要");

        //创建两个员工对象
        Employee e1 = new Employee();
        e1.setName("vv1");
        Employee e2 = new Employee();
        e2.setName("vv2");

        //设置对象关联
        d1.getEmployeeList().add(e1);
        d1.getEmployeeList().add(e2);
        e1.setDepartment(d1);
        e2.setDepartment(d1);

        //获取Session
        Session session = sessionFactory.openSession();
        //开始事务
        Transaction t = session.beginTransaction();
        try {
            //添加数据
            session.save(d1);
            session.save(e1);
            session.save(e2);

            //提交事务
            t.commit();
        } catch (RuntimeException e) {
            //有异常则回滚事务
            t.rollback();
            e.printStackTrace();
        } finally {
            //关闭session
            session.close();
        }
    }

    @Test
    public void testList() {
        //获取Session
        Session session = sessionFactory.openSession();
        //开始事务
        Transaction t = session.beginTransaction();
        String hql = "from TreeNode where parent = 1";

        Query query = session.createQuery(hql);
        List<TreeNode> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
            System.out.println(list.get(i).getParent().getName());
            Set<TreeNode> nodes = list.get(i).getChildren();
            System.out.println("**********");

            for (TreeNode node : nodes) {
                System.out.println(node.getName());
            }
        }

//        String hql = "from Department";
//        Query query = session.createQuery(hql);
//        List<Department> list = query.list();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getId());
//            List<Employee> employeeList = list.get(i).getEmployeeList();
//            for (Employee ee : employeeList) {
//                System.out.println(ee.getName());
//            }
//        }
        t.commit();

    }

}
