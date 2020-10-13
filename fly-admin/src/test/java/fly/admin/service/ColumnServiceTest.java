package fly.admin.service;

import fly.admin.FlyAdminApplication;
import fly.admin.entity.model.Column;
import fly.admin.repository.ColumnRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyAdminApplication.class})
class ColumnServiceTest {

    @Resource
    private ColumnService columnService;

    @Resource
    private ColumnRepository columnRepository;

    @Test
    public void getTest()
    {
        System.out.println(columnService.get(1));
    }

    @Test
    public void addTest()
    {
        System.out.println(columnService.add(Column.builder().name("测试").sort(3).build()));
    }

    @Test
    public void updateTest()
    {
        Column column = columnService.get(5);
        column.setName("测试栏");

//        System.out.println(columnService.update(column));
    }

    @Test
    public void deleteTest()
    {
        columnService.delete(Column.builder().id(4).build());
    }
}