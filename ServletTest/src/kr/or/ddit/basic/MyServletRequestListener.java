package kr.or.ddit.basic;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener
											   , ServletRequestAttributeListener
{

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("[MyServletRequestListener] requestDestroyed 호출됨");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("[MyServletRequestListener] requestInitialized 호출됨");
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		System.out.println("[MyServletRequestListener] attributeAdded 호출됨 => " + arg0.getName());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		System.out.println("[MyServletRequestListener] attributeRemoved 호출됨 => " + arg0.getName());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		System.out.println("[MyServletRequestListener] attributeReplaced 호출됨 => " + arg0.getName());
	}

}
