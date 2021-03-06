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
package org.appverse.web.showcases.gwtshowcase.backend.services.business.impl.live;

import java.util.List;

import org.appverse.web.framework.backend.api.converters.ConversionType;
import org.appverse.web.framework.backend.api.converters.b2i.PaginatedDataFilterB2IBeanConverter;
import org.appverse.web.framework.backend.api.model.business.BusinessPaginatedDataFilter;
import org.appverse.web.framework.backend.api.model.integration.IntegrationPaginatedDataFilter;
import org.appverse.web.framework.backend.api.services.business.AbstractBusinessService;
import org.appverse.web.showcases.gwtshowcase.backend.converters.b2i.UserB2IBeanConverter;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.Role;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.User;
import org.appverse.web.showcases.gwtshowcase.backend.model.integration.UserDTO;
import org.appverse.web.showcases.gwtshowcase.backend.services.business.UserService;
import org.appverse.web.showcases.gwtshowcase.backend.services.integration.RoleRepository;
import org.appverse.web.showcases.gwtshowcase.backend.services.integration.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends AbstractBusinessService implements
		UserService {

	@Autowired
	private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


	@Autowired
	private UserB2IBeanConverter userB2IBeanConverter;

	@Autowired
	private PaginatedDataFilterB2IBeanConverter paginatedDataFilterB2IBeanConverter;

	@Override
	public int countUsers(final BusinessPaginatedDataFilter filter)
			throws Exception {
		final IntegrationPaginatedDataFilter integrationDataFilter = paginatedDataFilterB2IBeanConverter
				.convert(filter);
		return userRepository.count(integrationDataFilter);
	}

	@Override
	public User loadUser(final long pk) throws Exception {
		// Note that default conversion type (not specified) is
		// ConversionType.Complete. This will convert all collections
		// using the proper corresponding mapping
		final UserDTO userDTO = userRepository.retrieve(pk);
		final User user = userB2IBeanConverter
				.convert(userDTO);
		return user;
	}
	
	@Override
	public void deleteUser(final User user) throws Exception {
		final UserDTO userDTO = userRepository.retrieve(user.getId());
		userRepository.delete(userDTO);
	}	
	
	@Override
	public List<User> loadUsers()
			throws Exception {
		// Note that ConversitonType.WithoutDependencies will not convert
		// collections using the corresponding mapping
		List<UserDTO> userList = userRepository.retrieveList();
		return userB2IBeanConverter.convertIntegrationList(userList, ConversionType.WithoutDependencies);
	}

	@Override
	public List<User> loadUsers(
			final BusinessPaginatedDataFilter config) throws Exception {
		final IntegrationPaginatedDataFilter integrationDataFilter = paginatedDataFilterB2IBeanConverter
				.convert(config);

		final List<UserDTO> userList = userRepository
				.retrieveList(integrationDataFilter);

		return userB2IBeanConverter.convertIntegrationList(userList, ConversionType.WithoutDependencies);
	}

	@Override
	public long saveUser(
			final User user)
			throws Exception {

        // This call is just to demostrate the use of the native Hibernate API. Does not add any functionality it is here just as a example
        // showing a transaction that mixes JPA queries and native queries. It does not add any functional value to the save method.
        // We recommend to use JPA as much as possible, avoiding your JPA provider (ORM) native API. Following the JPA specification will
        // make your application much more portable in case you want to change your JPA provider.
        final List<UserDTO> UserList =  userRepository.retrieveUserListUsingNativeOrmApiExample();

		UserDTO userDTO;
		if (user.getId() != 0L) {
			// As it is an existing user we retrieve the entity manager managed
			// object
			userDTO = userRepository.retrieve(user.getId());
			userB2IBeanConverter.convert(user, userDTO,
					ConversionType.WithoutDependencies);
		} else {
			// We are creating a new DTO (not managed by the entity manager yet)
			userDTO = userB2IBeanConverter.convert(user);
		}

        // Make sure that DTO object dependencies that should previously stored
        // in database are complete.
        // We need to ensure that pre-existing objects that we are asigning to
        // user
        // are complet as we don't send full objects to the front-end and in
        // some case
        // we send just even the id's. The business objects might not be
        // completed.
        if (userDTO.getRoles() != null) {
            userDTO.getRoles().clear();
            for (Role role : user.getRoles()) {
                userDTO.getRoles().add(roleRepository.retrieve(role.getId()));
            }
        }

		return userRepository.persist(userDTO);
	}
}