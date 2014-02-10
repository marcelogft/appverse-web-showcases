package org.appverse.web.showcases.gwtshowcase.gwtfronted.admin.users.presenter;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import org.appverse.web.framework.frontend.gwt.helpers.security.PrincipalInformation;
import org.appverse.web.showcases.gwtshowcase.backend.constants.AuthoritiesConstants;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters.UserSearchPresenter;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters.interfaces.UserSearchView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UserSearchPresenterPureUnitTestingGwtTest {

    UserSearchPresenter presenter = null;
    UserSearchView mockView = null;


    @Before
    public void setUp() {

        presenter = new UserSearchPresenter();

        // Default authorities for the test
        List<String> authorities = new ArrayList<String>();
        authorities.add(AuthoritiesConstants.ROLE_USER_CREATE);
        authorities.add(AuthoritiesConstants.ROLE_USER_EDIT);
        PrincipalInformation.setAuthorities(authorities);

        // Mock view with EasyMock
        mockView = createMock( UserSearchView.class );
        presenter.setView(mockView);
    }

    @After
    public void tearUp() {

    }

    @Test
    public void testSecurityUserCanEditAndAddUsers() {

        // We say here the methods we expect to have been called by the presenter
        // In this case we expect nothing as the user has the proper roles and nothing needs to be
        // disabled

        replay(mockView);

        // We call the test
        // Test with default authorities
        presenter.bindView();

        // Verify if the mock has the expected values
        verify(mockView);
    }

    @Test
    public void testSecurityUserCanNotEditAndAddUsers() {

        // Modify the default authority list so the user has no rights to edit and add users
        List<String> authorities = new ArrayList<String>();
        PrincipalInformation.setAuthorities(authorities);

        // We say here the methods we expect to have been called by the presenter.
        // In this case the presenter needs to call the view to disable user addition and edition
        mockView.disableAddFeature();
        mockView.disableEditFeature();

        replay(mockView);

        // We call the test. We expect the presenter to tell the view to disable add and edit features
        presenter.bindView();

        // Verify if the mock has the expected values
        verify(mockView);
    }


    @Test
    public void addUser(){

    }

    @Test
    public void editUser(){

    }

    @Test
    public void loadUsers(){

    }

    @Test
    public void searchUsers(){

    }

/* Eventos
    // Presenter methods to force presenter to implementation to implement the methods here.
    void onPlaceUserSearch();
    void onUsersSearch(boolean refresh);
*/


    private void enable() {
    }

}
