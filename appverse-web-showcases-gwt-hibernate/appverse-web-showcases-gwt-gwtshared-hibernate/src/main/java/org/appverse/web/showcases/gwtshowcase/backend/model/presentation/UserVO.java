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
package org.appverse.web.showcases.gwtshowcase.backend.model.presentation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTAbstractPresentationAuditedBean;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTItemVO;

import java.util.ArrayList;

public class UserVO extends GWTAbstractPresentationAuditedBean {

	private static final long serialVersionUID = 1L;

	private long id;

	@Size(min = 1, max = 40, groups = { Default.class })
	@NotNull
	private String name;

	@Size(min = 1, max = 40, groups = { Default.class })
	@NotNull
	private String lastName;

	@Size(min = 1, max = 40, groups = { Default.class })
	@NotNull
	private String email;

	@Size(min = 1, max = 40, groups = { Default.class })
	@NotNull
	private String password;
	private String signup;

	@NotNull
	private boolean active = true;

    @Size(min = 1, message = "{UserVO.roles.Size.message}", groups = { Default.class })
    private ArrayList<GWTItemVO> roles = new ArrayList<GWTItemVO>();


	public UserVO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getSignup() {
		return signup;
	}

    public ArrayList<GWTItemVO> getRoles() {
        return roles;
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setSignup(final String signup) {
		this.signup = signup;
	}

    public void setRoles(ArrayList<GWTItemVO> roles) {
        this.roles = roles;
    }

}
