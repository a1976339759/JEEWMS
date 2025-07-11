//package org.base;
//
//import org.base.modules.demo.mock.MockController;
//import org.base.modules.demo.test.entity.baseDemo;
//import org.base.modules.demo.test.mapper.baseDemoMapper;
//import org.base.modules.demo.test.service.IbaseDemoService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = baseSystemApplication.class)
//public class SampleTest {
//
//	@Resource
//	private baseDemoMapper baseDemoMapper;
//	@Resource
//	private IbaseDemoService baseDemoService;
////	@Resource
////	private ISysDataLogService sysDataLogService;
//	@Resource
//	private MockController mock;
//
//	@Test
//	public void testSelect() {
//		System.out.println(("----- selectAll method test ------"));
//		List<baseDemo> userList = baseDemoMapper.selectList(null);
//		Assert.assertEquals(5, userList.size());
//		userList.forEach(System.out::println);
//	}
//
//	@Test
//	public void testXmlSql() {
//		System.out.println(("----- selectAll method test ------"));
//		List<baseDemo> userList = baseDemoMapper.getDemoByName("Sandy12");
//		userList.forEach(System.out::println);
//	}
//
//	/**
//	 * 测试事务
//	 */
//	@Test
//	public void testTran() {
//		baseDemoService.testTran();
//	}
//
//	//author:lvdandan-----date：20190315---for:添加数据日志测试----
//	/**
//	 * 测试数据日志添加
//	 */
////	@Test
////	public void testDataLogSave() {
////		System.out.println(("----- datalog test ------"));
////		String tableName = "base_demo";
////		String dataId = "4028ef81550c1a7901550c1cd6e70001";
////		String dataContent = mock.sysDataLogJson();
////		sysDataLogService.addDataLog(tableName, dataId, dataContent);
////	}
//	//author:lvdandan-----date：20190315---for:添加数据日志测试----
//}
