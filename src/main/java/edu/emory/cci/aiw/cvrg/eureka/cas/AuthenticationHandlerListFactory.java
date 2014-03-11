package edu.emory.cci.aiw.cvrg.eureka.cas;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.FactoryBean;

/**
 *
 * @author Andrew Post
 */
public class AuthenticationHandlerListFactory implements FactoryBean {
  private List list = new ArrayList();

  public AuthenticationHandlerListFactory(List pre, List middle, List post) {
    list.addAll(pre);
	list.addAll(middle);
	list.addAll(post);
  }

  @Override
  public Object getObject() throws Exception {
    return list;
  }

  @Override
  public Class getObjectType() {
    return list.getClass();
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

}