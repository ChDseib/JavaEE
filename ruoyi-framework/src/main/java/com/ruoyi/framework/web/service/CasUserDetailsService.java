package com.ruoyi.framework.web.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用于加载用户信息 实现UserDetailsService接口，或者实现AuthenticationUserDetailsService接口
 */

@Service
public class CasUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	private static final Logger log = LoggerFactory.getLogger(CasUserDetailsService.class);

	@Autowired
	private ISysUserService userService;

	@Autowired
	private ISysRoleService roleService;

	@Autowired
	private SysPermissionService permissionService;

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		String username = token.getName();
		SysUser user = userService.selectUserByUserName(username);
		if (StringUtils.isNull(user)) {
			log.info("用户：{} 初次登录.", username);
			user = new SysUser();
			user.setUserName(username);
			user.setNickName(username);
			user.setStatus("0");
			userService.insertUser(user);
			if (username.startsWith("SD")) {
				roleService.insertAuthUsers(100L, new Long[]{user.getUserId()});
			} else if (username.startsWith("b2") || username.startsWith("f2")) {
				roleService.insertAuthUsers(101L, new Long[]{user.getUserId()});
			}
		} else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
			log.info("登录用户：{} 已被删除.", username);
			throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
		} else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
			log.info("登录用户：{} 已被停用.", username);
			throw new ServiceException("对不起，您的账号：" + username + " 已停用");
		}

		return createLoginUser(user);
	}

	public UserDetails createLoginUser(SysUser user) {
		return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
	}
}