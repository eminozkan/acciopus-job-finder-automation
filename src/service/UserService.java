package service;

import java.io.File;

import support.dto.CVObject;
import support.dto.ChangePasswordRequest;
import support.dto.User;
import support.result.AuthorizationResult;
import support.result.CreationResult;
import support.result.UpdateResult;

public interface UserService {
	CreationResult register(User user);
	
	AuthorizationResult login(User user);
	
	UpdateResult updateProfile(User user);
	
	UpdateResult uploadCV(File file);
	
	CVObject getCV();

	UpdateResult changePassword(ChangePasswordRequest request);
	
}
