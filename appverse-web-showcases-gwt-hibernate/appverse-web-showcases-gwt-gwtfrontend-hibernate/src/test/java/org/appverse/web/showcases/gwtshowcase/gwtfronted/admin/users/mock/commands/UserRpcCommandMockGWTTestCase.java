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
package org.appverse.web.showcases.gwtshowcase.gwtfronted.admin.users.mock.commands;

import com.google.gwt.core.client.GWT;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.framework.frontend.gwt.callback.AppverseCallback;
import org.appverse.web.framework.frontend.gwt.commands.AbstractRpcCommand;
import org.appverse.web.framework.frontend.gwt.rpc.ApplicationAsyncCallback;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.UserVO;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.UserServiceFacade;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.UserServiceFacadeAsync;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminEventBus;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.commands.UserRpcCommand;

import java.util.ArrayList;
import java.util.List;

public class UserRpcCommandMockGWTTestCase extends AbstractRpcCommand<AdminEventBus>
		implements UserRpcCommand {

    public static List<UserVO> userVOList;

    public UserRpcCommandMockGWTTestCase() {
        userVOList = new ArrayList<UserVO>();
        initializeMockUsersList();
    }
	
/* Real implementation
	private final UserServiceFacadeAsync service = (UserServiceFacadeAsync) GWT
			.create(UserServiceFacade.class);
*/

	@Override
	public void deleteUser(final UserVO user,
			final ApplicationAsyncCallback<Void> callback) {
//	Real implementation	getService().deleteUser(user, callback);
        callback.onSuccess(null);
	}

/* Real implementation
	protected UserServiceFacadeAsync getService() {
		return super.getService(service);
	}
*/

	@Override
	public void loadUsers(final ApplicationAsyncCallback<List<UserVO>> callback) {
// Real implementatioin		getService().loadUsers(callback);

        callback.onSuccess(userVOList);
	}

	@Override
	public void loadUsers(final GWTPresentationPaginatedDataFilter config,
			final AppverseCallback<GWTPresentationPaginatedResult<UserVO>> callback) {

        List<UserVO> data = userVOList.subList(config.getOffset(), config.getOffset() + config.getLimit());

        GWTPresentationPaginatedResult<UserVO> paginatedResult = new GWTPresentationPaginatedResult<UserVO>();
        paginatedResult.setOffset(config.getOffset());
        paginatedResult.setData(data);
        paginatedResult.setTotalLength(data.size());

        callback.onSuccess(paginatedResult);

// Real implementation       getService().loadUsers(config, callback);
	}

    @Override
    public void loadUser(final long userId, final ApplicationAsyncCallback<UserVO> callback) {
// Real implementation        getService().loadUser(userId,callback);

        callback.onSuccess(userVOList.get((int)userId));
    }

	@Override
	public void saveUser(final UserVO user,
			final ApplicationAsyncCallback<Long> callback) {
// Real implementation		getService().saveUser(user, callback);

        // Assign ID and assign to the list
        user.setId(userVOList.size() + 1);
        userVOList.add(user);

        // Return new user Id
        callback.onSuccess(user.getId());
	}


    private void initializeMockUsersList(){
        UserVO userVO;
        for (int i=0; i < 500; i++){
            userVO = new UserVO();
            userVO.setId(i);
            userVO.setName("User " + i);
            userVO.setLastName("User surname " + i);
            userVO.setEmail("email@user" + i + ".com");
            // And so on...

            userVOList.add(userVO);
        }
    }
}
