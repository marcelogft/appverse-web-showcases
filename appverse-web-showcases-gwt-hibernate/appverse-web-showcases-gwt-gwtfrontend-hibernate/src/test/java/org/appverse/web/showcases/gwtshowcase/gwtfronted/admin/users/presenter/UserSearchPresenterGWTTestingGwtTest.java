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

import com.google.gwt.junit.client.GWTTestCase;
import org.appverse.web.showcases.gwtshowcase.gwtfronted.admin.users.mock.commands.UserRpcCommandMockGWTTestCase;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters.UserSearchPresenter;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.views.impl.gxt.UserSearchViewImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


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

        // Necessary to inject presenter to the view and view to the presenter
        presenter.setView(view);
        view.setPresenter(presenter);

        // We need to add the mock command after createPresenter to override the default command implementation
        presenter.createPresenter();

        UserRpcCommandMockGWTTestCase userRpcCommandMockGWTTestCase = new UserRpcCommandMockGWTTestCase();
        presenter.setUserRpcCommand(userRpcCommandMockGWTTestCase);

        view.createView();

        System.out.println("*********** UserSearchPresenterGWTTestingGwtTest: view created");
        presenter.setView(view);
        System.out.println("*********** UserSearchPresenterGWTTestingGwtTest: view injected in presenter. Before! End");
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

        presenter.searchUsers();
        System.out.println("*********** testSecurityUserCanEditAndAddUsers: after calling Before! END");
    }
}
