package coding.toast.springweblegacy.user.controller;

import coding.toast.springweblegacy.user.repository.UserRepository;
import coding.toast.springweblegacy.user.data.request.UserInfoRequest;
import coding.toast.springweblegacy.user.data.table.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserRepository userRepository;
	
	@GetMapping("/users")
	public String userListInfo(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "user/userList";
	}
	
	@GetMapping("/users/{id}")
	@ResponseBody
	public User userInfoResponse(@Valid UserInfoRequest userInfoRequest) {
		return userRepository
			.findById(userInfoRequest.id())
			.orElseThrow(() -> new NoSuchElementException("No User Found"));
	}
	
}
