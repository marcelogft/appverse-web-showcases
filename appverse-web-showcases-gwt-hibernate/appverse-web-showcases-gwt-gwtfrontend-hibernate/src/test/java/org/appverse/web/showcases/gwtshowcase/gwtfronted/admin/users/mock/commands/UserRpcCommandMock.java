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

import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.framework.frontend.gwt.callback.AppverseCallback;
import org.appverse.web.framework.frontend.gwt.rpc.ApplicationAsyncCallback;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.UserVO;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.commands.UserRpcCommand;

import java.util.List;

public class UserRpcCommandMock implements UserRpcCommand{

    List<UserVO> users = null;

    public UserRpcCommandMock(List<UserVO> users) {
        this.users = users;
    }

    @Override
    public void deleteUser(UserVO user, ApplicationAsyncCallback<Void> asyncCallback) {
        // TODO: Implement
        asyncCallback.onSuccess( null );
    }

    @Override
    public void loadUsers(ApplicationAsyncCallback<List<UserVO>> asyncCallback) {
        asyncCallback.onSuccess( users );
    }

    @Override
    public void loadUsers(GWTPresentationPaginatedDataFilter config, AppverseCallback<GWTPresentationPaginatedResult<UserVO>> callback) {
        GWTPresentationPaginatedResult<UserVO> paginatedResult = new GWTPresentationPaginatedResult<UserVO>();
        paginatedResult.setData(users.subList(config.getOffset(), config.getOffset() + config.getLimit()));
        paginatedResult.setTotalLength(config.getLimit());
        paginatedResult.setOffset(config.getOffset());
        callback.onSuccess( paginatedResult );
    }

    @Override
    public void saveUser(UserVO user, ApplicationAsyncCallback<Long> asyncCallback) {
        asyncCallback.onSuccess( null );
    }

    @Override
    public void loadUser(long userId, ApplicationAsyncCallback<UserVO> asyncCallback) {
        asyncCallback.onSuccess( null );
    }
}
