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
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.appverse.web.framework.backend.api.model.presentation.ItemVO;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTItemVO;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.framework.frontend.gwt.commands.AbstractRpcCommand;
import org.appverse.web.framework.frontend.gwt.rpc.ApplicationAsyncCallback;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.UserVO;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.RoleServiceFacade;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.RoleServiceFacadeAsync;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminEventBus;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.commands.RolesRpcCommand;

import java.util.ArrayList;
import java.util.List;

public class RolesRpcCommandMockGWTTestCase extends AbstractRpcCommand<AdminEventBus>
		implements RolesRpcCommand {

    public static List<RoleVO> roleVOList;

    public RolesRpcCommandMockGWTTestCase() {
        roleVOList = new ArrayList<RoleVO>();
        initializeMockRolesList();
    }

/* Real implementation
	private final RoleServiceFacadeAsync serviceRoles = (RoleServiceFacadeAsync) GWT
			.create(RoleServiceFacade.class);
*/

	@Override
	public void deleteRole(final RoleVO role,
			final ApplicationAsyncCallback<Void> callback) {
// Real implementation		serviceRoles.deleteRole(role.getId(), callback);
        callback.onSuccess(null);
	}

/* Real implementation
	protected RoleServiceFacadeAsync getServiceRoles() {
		return super.getService(serviceRoles);
	}
*/

	@Override
	public void loadRoles(final ApplicationAsyncCallback<List<RoleVO>> callback) {
// Real implementation		getServiceRoles().loadRoles(callback);
        callback.onSuccess(roleVOList);
	}

   @Override
   public void loadRoles(final GWTPresentationPaginatedDataFilter config,
			final AsyncCallback<GWTPresentationPaginatedResult<RoleVO>> callback) {
// Real implementation		getServiceRoles().loadRoles(config, callback);

        List<RoleVO> data = roleVOList.subList(config.getOffset(), config.getOffset() + config.getLimit());

        GWTPresentationPaginatedResult<RoleVO> paginatedResult = new GWTPresentationPaginatedResult<RoleVO>();
        paginatedResult.setOffset(config.getOffset());
        paginatedResult.setData(data);
        paginatedResult.setTotalLength(data.size());
        callback.onSuccess(paginatedResult);
   }

	@Override
	public void loadRolesMap(
			final ApplicationAsyncCallback<List<GWTItemVO>> callback) {
//		getServiceRoles().loadRolesMap(callback);

        List<GWTItemVO> rolesMap = new ArrayList<GWTItemVO>();
        GWTItemVO itemVO = new GWTItemVO();
        itemVO.setId(1L);
        itemVO.setName("ROLE_USER_VIEW");
        rolesMap.add(itemVO);
        itemVO = new GWTItemVO();
        itemVO.setId(2L);
        itemVO.setName("ROLE_USER_CREATE");
        rolesMap.add(itemVO);
        itemVO = new GWTItemVO();
        itemVO.setId(3L);
        itemVO.setName("ROLE_USER_EDIT");
        rolesMap.add(itemVO);
        itemVO = new GWTItemVO();
        itemVO.setId(4L);
        itemVO.setName("ROLE_USER_DISABLE");
        rolesMap.add(itemVO);

        callback.onSuccess(rolesMap);
	}

	@Override
	public void saveRole(final RoleVO role,
			final ApplicationAsyncCallback<Long> callback) {
// Real implementation		getServiceRoles().saveRole(role, callback);

        // Assign ID and assign to the list
        role.setId(roleVOList.size() + 1);
        roleVOList.add(role);

        // Return new role Id
        callback.onSuccess(role.getId());
	}

    private void initializeMockRolesList(){
        RoleVO roleVO;
        roleVO = new RoleVO();
        roleVO.setId(1);
        roleVO.setName("ROLE_USER_VIEW");
        roleVOList.add(roleVO);
        roleVO = new RoleVO();
        roleVO.setId(2);
        roleVO.setName("ROLE_USER_CREATE");
        roleVOList.add(roleVO);
        roleVO = new RoleVO();
        roleVO.setId(3);
        roleVO.setName("ROLE_USER_EDIT");
        roleVOList.add(roleVO);
        roleVO = new RoleVO();
        roleVO.setId(4);
        roleVO.setName("ROLE_USER_DISABLE");
        roleVOList.add(roleVO);
    }


}
