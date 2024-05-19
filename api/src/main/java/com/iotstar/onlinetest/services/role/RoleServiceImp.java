package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.RoleResponse;
import com.iotstar.onlinetest.common.paging.PagingRequest;
import com.iotstar.onlinetest.exceptions.ResourceExistException;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.repositories.RoleDAO;
import com.iotstar.onlinetest.statval.ERole;
import com.iotstar.onlinetest.utils.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImp extends RolePaging implements RoleService {

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private ModelMapper mapper;

    private Role role;

    public Role getRoleReturnRole(String roleName){
        return roleDAO.getByRoleName(roleName).orElseThrow(()->
                new ResourceNotFoundException(ERole.ROLE_NOTFOUND.getDes(roleName)));
    }


    @Override
    public boolean existByRoleName(String roleName){
        return roleDAO.existsByRoleName(roleName);
    }
    @Override
    public void createRole(RoleRequest roleRequest) {
        if (existByRoleName(roleRequest.getRoleName())){
           throw new ResourceExistException(ERole.ROLE_EXIT.getDes());
        }
        role = mapper.map(roleRequest, Role.class);
        role.setStatus(1);
        roleDAO.save(role);
    }

    @Override
    public RoleResponse updateRole(RoleRequest roleRequest) {
        role = getRoleReturnRole(roleRequest.getRoleName());
        return mapper.map(roleDAO.save(role), RoleResponse.class);
    }

    @Override
    public List<RoleResponse> getAllRole() {
        List<RoleResponse> roleResponses = new ArrayList<>();
        List<Role> roles  =roleDAO.findAll(pageable()).getContent();
        for (Role i: roles) {
            roleResponses.add(mapper.map(i, RoleResponse.class));
        }
        return roleResponses;
    }

    @Override
    public RoleResponse getRoleByRoleName(String roleName) {
        role = getRoleReturnRole(roleName);
        return mapper.map(role, RoleResponse.class);
    }

    @Override
    public void deleteRole(RoleRequest roleRequest) {
        role = getRoleReturnRole(roleRequest.getRoleName());
        role.setStatus(0);
        roleDAO.save(role);
    }
}
