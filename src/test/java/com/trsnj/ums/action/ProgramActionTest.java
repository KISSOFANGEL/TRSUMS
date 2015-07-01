package com.trsnj.ums.action;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.trsnj.ums.service.impl.ProgramServiceImpl;

public class ProgramActionTest {

	  @Test
	    public void testProgramAction(){
	    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","/spring-conf/applicationContext-dao.xml","/spring-conf/applicationContext-action.xml","/spring-conf/applicationContext-service.xml"});
	    	context.start();  
	    	
	    //	UMSProgramDaoImpl pdi = (UMSProgramDaoImpl)context.getBean("programDaoImpl");
	    	ProgramAction pdi = (ProgramAction)context.getBean("programAction");
	    	System.out.println(pdi);
	    }
}
