package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MySessionBindingListener implements HttpSessionBindingListener {
	//바인딩되는 순간이 아닌 바인딩되는 객체에 관심이 있을 때 사용한다.
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("[MySessionBindingListener] valueBound => " + arg0.getName() + ": " + arg0.getValue());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("[MySessionBindingListener] valueUnbound => " + arg0.getName() + ": " + arg0.getValue());
		
	}

}
