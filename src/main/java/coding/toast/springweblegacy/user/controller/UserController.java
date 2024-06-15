package coding.toast.springweblegacy.user.controller;

import coding.toast.springweblegacy.user.model.request.UserInfoRequest;
import coding.toast.springweblegacy.user.model.response.UserInfoResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class UserController {
	@GetMapping("/users")
	public String userListInfo(Model model) {
		model.addAttribute("users",
			List.of(
				new UserInfoResponse("aaa", "dailyCode1", 23),
				new UserInfoResponse("bbb", "dailyCode2", 24),
				new UserInfoResponse("ccc", "dailyCode3", 25),
				new UserInfoResponse("ddd", "dailyCode4", 26),
				new UserInfoResponse("eee", "dailyCode5", 27)
			));
		return "user/userList";
	}
	
	@GetMapping("/users/{id}")
	@ResponseBody
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
