package org.appverse.web.showcases.gwtshowcase.gwtfronted.admin.users.presenter;

import com.google.gwt.junit.client.GWTTestCase;
import org.appverse.web.framework.frontend.gwt.helpers.security.PrincipalInformation;
import org.appverse.web.showcases.gwtshowcase.backend.constants.AuthoritiesConstants;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters.UserSearchPresenter;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters.interfaces.UserSearchView;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.views.impl.gxt.UserSearchViewImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class UserSearchPresenterGWTTestingGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "org.appverse.web.showcases.gwtshowcase.gwtfronted.Test";
    }

    UserSearchPresenter presenter = null;
    UserSearchViewImpl view = null;

    @Before
    public void before() {
        System.out.println("*********** UserSearchPresenterGWTTestingGwtTest: Before! start");
        presenter = new UserSearchPresenter();
        System.out.println("*********** UserSearchPresenterGWTTestingGwtTest: presenter created");
        view = new UserSearchViewImpl();
        view.createView();
        System.out.println("*********** UserSearchPresenterGWTTestingGwtTest: view created");
        presenter.setView(view);
        System.out.println("*********** UserSearchPresenterGWTTestingGwtTest: view injected in presenter. Before! End");
    }

    @After
    public void tearUp() {

    }

    @Test
    public void testSecurityUserCanEditAndAddUsers() {
        System.out.println("*********** testSecurityUserCanEditAndAddUsers: before calling Before!");
        before();
        System.out.println("*********** testSecurityUserCanEditAndAddUsers: after calling Before! END");
    }


}
