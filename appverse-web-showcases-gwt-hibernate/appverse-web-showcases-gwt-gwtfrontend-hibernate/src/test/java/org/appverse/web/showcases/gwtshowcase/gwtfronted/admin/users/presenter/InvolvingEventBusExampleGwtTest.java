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

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.mvp4g.client.Mvp4gModule;
import com.mvp4g.client.event.BaseEventBus;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.UserVO;
import org.appverse.web.showcases.gwtshowcase.gwtfronted.admin.users.mock.commands.RolesRpcCommandMockGWTTestCase;
import org.appverse.web.showcases.gwtshowcase.gwtfronted.admin.users.mock.commands.UserRpcCommandMockGWTTestCase;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminEventBus;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters.UserEditPresenter;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters.UserSearchPresenter;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.views.impl.gxt.UserEditViewImpl;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.views.impl.gxt.UserSearchViewImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class InvolvingEventBusExampleGwtTest extends GWTTestCase {

    AdminEventBus adminEventBus = null;

    // Presenters and views involved in the test
    UserSearchPresenter userSearchPresenter;
    UserSearchViewImpl userSearchView;

    UserEditPresenter userEditPresenter;
    UserEditViewImpl userEditView;

    UserRpcCommandMockGWTTestCase userRpcCommandMockGWTTestCase;
    RolesRpcCommandMockGWTTestCase rolesRpcCommandMockGWTTestCase;

    @Override
    public String getModuleName() {
        return "org.appverse.web.showcases.gwtshowcase.gwtfronted.Test";
    }

    @Before
    public void before() {

        init();

        // Init all your presenters and views here
        initUserSearchPresenterAndView();
        initUserEditPresenterAndView();

    }

    private void init(){
        // Enabling real EventBus
        Mvp4gModule module = (Mvp4gModule) GWT.create(Mvp4gModule.class);
        module.createAndStartModule();
        adminEventBus = (AdminEventBus)module.getEventBus();

        userRpcCommandMockGWTTestCase = new UserRpcCommandMockGWTTestCase();
        rolesRpcCommandMockGWTTestCase = new RolesRpcCommandMockGWTTestCase();

        System.out.println("*********** InvolvingEventBusExampleGwtTest: initEventBus successful");
    }

    private void initUserSearchPresenterAndView(){
        userSearchPresenter = new UserSearchPresenter();
        userSearchView = new UserSearchViewImpl();

        // Necessary to inject presenter to the view and view to the presenter
        userSearchPresenter.setView(userSearchView);
        userSearchView.setPresenter(userSearchPresenter);

        // We need to add the mock command after createPresenter to override the default command implementation
        userSearchPresenter.createPresenter();

        // Add here injected commands and other artifacts
        userSearchPresenter.setUserRpcCommand(userRpcCommandMockGWTTestCase);

        userSearchPresenter.setView(userSearchView);
        userSearchView.createView();

        // Associating real event bus to recently created presenter (as is not injected automatically)
        BaseEventBus baseEventBus = (BaseEventBus) adminEventBus;
        baseEventBus.setPresenter(userSearchPresenter, userSearchView, adminEventBus);
        userSearchPresenter.setEventBus(adminEventBus);
    }

    private void initUserEditPresenterAndView(){
        userEditPresenter = new UserEditPresenter();
        userEditView = new UserEditViewImpl();

        // Necessary to inject presenter to the view and view to the presenter
        userEditPresenter.setView(userEditView);
        userEditView.setPresenter(userEditPresenter);

        // We need to add the mock command after createPresenter to override the default command implementation
        userEditPresenter.createPresenter();

        // Add here injected commands and other artifacts
        userEditPresenter.setUserRpcCommand(userRpcCommandMockGWTTestCase);
        userEditPresenter.setRolesRpcCommand(rolesRpcCommandMockGWTTestCase);

        userEditPresenter.setView(userEditView);
        userEditView.createView();

        // Associating real event bus to recently created presenter (as is not injected automatically)
        BaseEventBus baseEventBus = (BaseEventBus) adminEventBus;
        baseEventBus.setPresenter(userEditPresenter, userEditView, adminEventBus);
        userEditPresenter.setEventBus(adminEventBus);
    }

    @After
    public void tearUp() {

    }

    @Test
    public void testLoadUsers() {
        System.out.println("*********** testSecurityUserCanEditAndAddUsers: before calling Before!");
        before();

        // View shoud have loaded the users in the paginated grid


        System.out.println("*********** testSecurityUserCanEditAndAddUsers: after calling Before! END");
    }

    @Test
    public void testSearchUser() {
        System.out.println("*********** testSecurityUserCanEditAndAddUsers: before calling Before!");
        before();

        userSearchPresenter.searchUsers();
        System.out.println("*********** testSecurityUserCanEditAndAddUsers: after calling Before! END");
    }

    @Test
    public void testEditUser() {
        System.out.println("*********** testSecurityUserCanEditAndAddUsers: before calling Before!");
        before();

        UserVO newUserVO = new UserVO();
        newUserVO.setName("new user name");
        newUserVO.setLastName("new user last name");
        newUserVO.setEmail("email");

        userSearchPresenter.editUser(newUserVO);

        System.out.println("*********** testSecurityUserCanEditAndAddUsers: after calling Before! END");
    }

}
