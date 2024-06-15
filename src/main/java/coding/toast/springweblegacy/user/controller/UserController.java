package coding.toast.springweblegacy.user.controller;

import coding.toast.springweblegacy.user.model.request.UserInfoRequest;
import coding.toast.springweblegacy.user.model.response.UserInfoResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
	@GetMapping("/users")
	public UserInfoResponse userInfoResponse(@Valid UserInfoRequest userInfoRequest) {
		String id = userInfoRequest.id();
		UserInfoResponse infoResponse = UserInfoResponse.builder()
			.id(id)
			.age(30)
			.username("daily-code").build();
		log.info("userInfo => {}", infoResponse);
		return infoResponse;
	}
}
