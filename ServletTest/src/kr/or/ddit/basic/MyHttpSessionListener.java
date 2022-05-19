package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener 
											, HttpSessionAttributeListener
{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("[HttpSessionListener] " + "sessionCreated.");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("[HttpSessionListener] " + "sessionDistroyed.");
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("[HttpSessionListener] attributeAdded." + arg0.getName() + ": "  + arg0.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("[HttpSessionListener] attributeRemoved." + arg0.getName() + ": "  + arg0.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("[HttpSessionListener] attributeReplaced." + arg0.getName() + ": "  + arg0.getValue());
	}
	
}
