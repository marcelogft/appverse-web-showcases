/*
 Copyright (c) 2012 GFT Appverse, S.L., Sociedad Unipersonal.

 This Source Code Form is subject to the terms of the Mozilla Public
 License, v. 2.0. If a copy of the MPL was not distributed with this
 file, You can obtain one at http://mozilla.org/MPL/2.0/.

 Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the conditions of the Mozilla Public License v2.0
 are met.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. EXCEPT IN CASE OF WILLFUL MISCONDUCT OR GROSS NEGLIGENCE, IN NO EVENT
 SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE)
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.
 */
package org.appverse.web.showcases.gwtshowcase.gwtfronted.admin.users.presenter;

import static junit.framework.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.framework.frontend.gwt.helpers.security.PrincipalInformation;
import org.appverse.web.framework.frontend.gwt.rpc.ApplicationAsyncCallback;
import org.appverse.web.showcases.gwtshowcase.backend.constants.AuthoritiesConstants;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.UserVO;
import org.appverse.web.showcases.gwtshowcase.gwtfronted.admin.users.mock.commands.UserRpcCommandMock;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminEventBus;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.commands.UserRpcCommand;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters.UserSearchPresenter;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters.interfaces.UserSearchView;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


// This could be done as well with Jmock, Mockito, Jukito
public class UserSearchPUnitTestingEasyMockGwtTest {

    List users = null;

    UserSearchPresenter presenter = null;
    UserRpcCommand mockUserRpcCommand = null;
    UserSearchView mockView = null;
    AdminEventBus mockEventBus = null;


    @Before
    public void setUp() {

        // Create mock users
        users = createUsers();

        // Create presenter implementation
        presenter = new UserSearchPresenter();

        // Create mock command implementation
        mockUserRpcCommand = new UserRpcCommandMock(users);
        presenter.setUserRpcCommand(mockUserRpcCommand);

        // Default authorities for the test
        List<String> authorities = new ArrayList<String>();
        authorities.add(AuthoritiesConstants.ROLE_USER_CREATE);
        authorities.add(AuthoritiesConstants.ROLE_USER_EDIT);
        PrincipalInformation.setAuthorities(authorities);

        // Mock view with EasyMock
        mockView = createMock( UserSearchView.class );

        presenter.setView(mockView);

        // Mock bus with EasyMock
        mockEventBus = createMock( AdminEventBus.class );
        presenter.setEventBus(mockEventBus);
    }

    @After
    public void tearUp() {

    }

    @Test
    public void testOnStart() {

        // We say here the methods we expect to have been called by the presenter in the eventBus
        Capture<Widget> capturedArgs = new Capture();

        // This will capture the last call
        mockEventBus.adminLayoutChangeBody(EasyMock.<Widget>capture(capturedArgs));
        EasyMock.expectLastCall().once();

        // We also check that asWidget() method in mockView have been invoked
        // Return value is ignored here.. we need to pass null as we cannot invoke new Widget as this implies GWT.create and this will not
        // work in unit testing
        EasyMock.expect(mockView.asWidget()).andReturn(null);

        replay(mockEventBus, mockView);

        // Init event
        presenter.onInit();

        // Verify if the mock has the expected values
        verify(mockEventBus, mockView);
    }

    @Test
    public void testSecurityUserCanEditAndAddUsers() {

        // We say here the methods we expect to have been called by the presenter
        // In this case we expect nothing as the user has the proper roles and nothing needs to be disabled

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
        Capture<UserVO> capturedArgs = new Capture();

        // This will capture the last call
        mockEventBus.userEdit(EasyMock.<UserVO>capture(capturedArgs));
        // We expect 'userEdit' have been called once with a new user after calling 'addUser'
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockEventBus);

        // Call presenter add user
        presenter.addUser();

        // Verify if the mock has the expected values
        verify(mockEventBus);

        // Check that the method have been called with a new empty user
        UserVO createdUser = capturedArgs.getValue();
        assertNotNull(createdUser);
        assertEquals(0, createdUser.getId());
        assertNull(createdUser.getName());
        assertNull(createdUser.getLastName());
    }

    @Test
    public void editUser(){
        // We say here the methods we expect to have been called by the presenter
        // In this case
        // mockEventBus.
        UserVO userVO = new UserVO();
        userVO.setName("name");
        userVO.setLastName("surname");
        mockEventBus.userEdit(userVO);

        replay(mockEventBus);

        // We call the presenter editUser method
        presenter.editUser(userVO);

        // Verify if the mock has the expected values
        verify(mockEventBus);
    }

    @Test
    public void loadUsers(){
        // Prepare a paginated data filter
        GWTPresentationPaginatedDataFilter config = new GWTPresentationPaginatedDataFilter();
        config.setLimit(4);
        config.setOffset(2);

        // Prepare the callback
        ApplicationAsyncCallback callbackListUsers = new ApplicationAsyncCallback<GWTPresentationPaginatedResult<UserVO>>() {
            @SuppressWarnings("unchecked")
            @Override
            public void onSuccess(
                    final GWTPresentationPaginatedResult<UserVO> result) {
                // Expect 4 users to be retrieved
                assertEquals(4, result.getData().size());
            }
        };

        // Call the presenter to load users pasing the pagidated data filter and the callback
        presenter.loadUsers(config, callbackListUsers);
    }

    private List<UserVO> createUsers() {

        List<UserVO> users = new ArrayList<UserVO>();

        int NUM_USERS = 10;
        String[] names = { "User1", "User2", "User3", "User4", "User5", "User6", "User7", "User8", "User9", "User10" };
        String[] lastNames = { "Surname1", "Surname2", "Surname3", "Surname4", "Surname5", "Surname6", "Surname7", "Surname8", "Surname9", "Surname10" };

        for (int i=0; i < NUM_USERS; i++){
            UserVO userVO = new UserVO();
            userVO.setId(i);
            userVO.setName(names[i]);
            userVO.setLastName(lastNames[i]);
            users.add(userVO);
        }
        return users;
    }

    private void enable() {
    }

}
