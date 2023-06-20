package service;

import support.dto.User;
import support.result.AuthorizationResult;
import support.result.CreationResult;

public interface UserService {
	CreationResult register(User user);
	
	AuthorizationResult login(User user);
}
